package tokyo.ramune.farmmc.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.database.SQL;
import tokyo.ramune.farmmc.event.player.FarmPlayerChangeExpEvent;
import tokyo.ramune.farmmc.event.player.FarmPlayerLevelUpEvent;

import javax.annotation.Nonnull;

public class PlayerHandler {
    private static BukkitTask updateLevelTimerTask;


    public static void initialize() {
        createTable();
        startUpdateLevelTimer();
    }

    private static void createTable() {
        if (SQL.tableExists("player_status")) return;
        SQL.createTable("player_status",
                "uuid TEXT NOT NULL, " +
                        "name TEXT NOT NULL, " +
                        "language TEXT NOT NULL, " +
                        "level BIGINT DEFAULT 1, " +
                        "exp BIGINT DEFAULT 0, " +
                        "coin BIGINT DEFAULT 0");
    }

    public static void initializeDatabasePlayer(@Nonnull Player player) {
        if (SQL.exists("uuid", player.getUniqueId().toString(), "player_status"))
            return;

        SQL.insertData("uuid, name, language", "'" + player.getUniqueId() + "', '" + player.getName() + "', 'default'", "player_status");
    }

    private static void startUpdateLevelTimer() {
        if (updateLevelTimerTask != null)
            updateLevelTimerTask.cancel();

        updateLevelTimerTask = Bukkit.getScheduler().runTaskTimer(FarmMC.getPlugin(), ()
                -> Bukkit.getOnlinePlayers().forEach(PlayerHandler::updateLevel), 60, 60);
    }

    // --- Get ---
    public static PlayerStatus getStatus(@Nonnull Player player) {
        return new PlayerStatus(player);
    }

    public static double getAttackDistance(@Nonnull Player player) {
        return 1.5;
    }

    public static long getRequireLevelUpExp(@Nonnull Player player) {
        return getRequireLevelUpExp(getLevel(player));
    }

    public static long getRequireLevelUpExp(long level) {
        return level * 100;
    }

    public static String getLanguageCode(@Nonnull Player player) {
        String code = (String) SQL.get("language", "uuid", "=", player.getUniqueId().toString(), "player_status");
        return code == null ? "default" : code;
    }

    public static long getLevel(@Nonnull Player player) {
        Object value = SQL.get("level", "uuid", "=", player.getUniqueId().toString(), "player_status");
        return value != null ? (Long) value : 0;
    }

    public static long getExp(@Nonnull Player player) {
        Object value = SQL.get("exp", "uuid", "=", player.getUniqueId().toString(), "player_status");
        return value != null ? (Long) value : 0;
    }

    public static long getCoin(@Nonnull Player player) {
        Object value = SQL.get("coin", "uuid", "=", player.getUniqueId().toString(), "player_status");
        return value != null ? (Long) value : 0;
    }

    // --- Set ---
    public static void setLanguageCode(@Nonnull Player player, String language) {
        if (getLanguageCode(player).equals(language))
            return;

        SQL.set("language", language, "uuid", "=", player.getUniqueId().toString(), "player_status");
    }

    public static void setLevel(@Nonnull Player player, long level) {
        if (getLevel(player) == level)
            return;

        SQL.set("level", level, "uuid", "=", player.getUniqueId().toString(), "player_status");
    }

    public static void setExp(@Nonnull Player player, long exp) {
        long currentExp = getExp(player);
        if (currentExp == exp)
            return;

        FarmPlayerChangeExpEvent event = new FarmPlayerChangeExpEvent(player, currentExp, exp);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled())
            return;

        SQL.set("exp", exp, "uuid", "=", player.getUniqueId().toString(), "player_status");
        updateLevel(player, getLevel(player), exp);
    }

    public static void setCoin(@Nonnull Player player, long coin) {
        if (getCoin(player) == coin)
            return;

        SQL.set("coin", coin, "uuid", "=", player.getUniqueId().toString(), "player_status");
    }

    // --- Update ---
    public static void updateName(@Nonnull Player player) {
        Object name = SQL.get("name", "uuid", "=", player.getUniqueId().toString(), "player_status");

        if (name == null)
            return;

        if (name.equals(player.getName()))
            return;

        SQL.set("name", player.getName(), "uuid", "=", player.getUniqueId().toString(), "player_status");
    }

    public static void updateLevel(@Nonnull Player player) {
        updateLevel(player, getLevel(player), getExp(player));
    }

    private static void updateLevel(@Nonnull Player player, long currentLevel, long currentExp) {
        if (getRequireLevelUpExp(currentLevel) > currentExp)
            return;

        player.sendMessage("checked");

        long toLevel = currentLevel;

        while (getRequireLevelUpExp(toLevel) <= currentExp) {
            currentExp -= getRequireLevelUpExp(toLevel);
            toLevel++;
        }

        FarmPlayerLevelUpEvent event = new FarmPlayerLevelUpEvent(player
                , currentLevel, toLevel);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled())
            return;

        setLevel(player, toLevel);
        setExp(player, currentExp);
    }
}

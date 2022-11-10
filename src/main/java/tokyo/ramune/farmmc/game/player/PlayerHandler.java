package tokyo.ramune.farmmc.game.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.core.database.SQL;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.game.event.player.FarmPlayerChangeExpEvent;
import tokyo.ramune.farmmc.game.event.player.FarmPlayerLevelUpEvent;

import javax.annotation.Nonnull;

public class PlayerHandler {
    private static BukkitTask updateLevelTimerTask;


    public static void initialize() {
        startUpdateLevelTimer();
    }

    public static void createTable() {
        if (SQL.tableExists("player_status")) return;
        SQL.createTable("player_status",
                "uuid TEXT NOT NULL, " +
                        "name TEXT NOT NULL, " +
                        "language TEXT NOT NULL, " +
                        "stamina BIGINT DEFAULT " + getDefaultStamina() + ", " +
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

    public static long getRequireLevelUpExp(@Nonnull Player player) {
        return getRequireLevelUpExp(getLevel(player));
    }

    public static long getRequireLevelUpExp(long level) {
        return level * 100;
    }

    public static long getStamina(@Nonnull Player player) {
        Object value = SQL.get("stamina", "uuid", "=", player.getUniqueId().toString(), "player_status");
        return value != null ? (long) value : 0;
    }

    public static long getDefaultStamina() {
        return 100L;
    }

    public static long getMaxStamina(@Nonnull Player player) {
        return getMaxStamina(getLevel(player));
    }

    public static long getMaxStamina(long level) {
        return getDefaultStamina() + (level * 10);
    }

    public static long getLevel(@Nonnull Player player) {
        Object value = SQL.get("level", "uuid", "=", player.getUniqueId().toString(), "player_status");
        return value != null ? (long) value : 0;
    }

    public static long getExp(@Nonnull Player player) {
        Object value = SQL.get("exp", "uuid", "=", player.getUniqueId().toString(), "player_status");
        return value != null ? (long) value : 0;
    }

    public static long getCoin(@Nonnull Player player) {
        Object value = SQL.get("coin", "uuid", "=", player.getUniqueId().toString(), "player_status");
        return value != null ? (long) value : 0;
    }

    // --- Set ---
    public static void setStamina(@Nonnull Player player, long stamina) {
        if (getStamina(player) == stamina)
            return;

        SQL.set("stamina", stamina, "uuid", "=", player.getUniqueId().toString(), "player_status");
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

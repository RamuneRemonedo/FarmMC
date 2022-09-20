package tokyo.ramune.farmmc.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.database.SQL;
import tokyo.ramune.farmmc.event.FarmPlayerChangeExpEvent;
import tokyo.ramune.farmmc.event.FarmPlayerLevelUpEvent;

import javax.annotation.Nonnull;

public class PlayerHandler {
    public static void createTable() {
        if (SQL.tableExists("player_status")) return;
        SQL.createTable("player_status",
                "uuid TEXT NOT NULL, " +
                        "name TEXT NOT NULL, " +
                        "level BIGINT DEFAULT 1, " +
                        "exp BIGINT DEFAULT 0, " +
                        "coin BIGINT DEFAULT 0");
    }

    public static void initializeDatabasePlayer(@Nonnull Player player) {
        if (SQL.exists("uuid", player.getUniqueId().toString(), "player_status")) return;
        SQL.insertData("uuid, name", "'" + player.getUniqueId() + "', '" + player.getName() + "'", "player_status");
    }

    public static PlayerStatus getStatus(@Nonnull Player player) {
        return new PlayerStatus(player);
    }

    public static long getRequireLevelUpExp(@Nonnull Player player) {
        return getRequireLevelUpExp(getLevel(player));
    }

    public static long getRequireLevelUpExp(long level) {
        return level * 10;
    }

    public static void updateName(@Nonnull Player player) {
        Object name = SQL.get("name", "uuid", "=", player.getUniqueId().toString(), "player_status");

        if (name == null)
            return;

        if (name.equals(player.getName()))
            return;

        SQL.set("name", player.getName(), "uuid", "=", player.getUniqueId().toString(), "player_status");
    }

    private static void updateLevel(@Nonnull Player player, long currentLevel, long currentExp) {
        if (getRequireLevelUpExp(currentLevel) < currentExp)
            return;

        long toLevel = currentLevel;

        while (getRequireLevelUpExp(toLevel) <= currentExp) {
            currentExp -= getRequireLevelUpExp(toLevel);
            toLevel ++;
        }

        FarmPlayerLevelUpEvent event = new FarmPlayerLevelUpEvent(player, currentLevel, toLevel);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled())
            return;

        setExp(player, currentExp);
        setLevel(player, currentLevel);
    }

    private static void updateExpBar(@Nonnull Player player, long currentExp, long currentLevel) {
        player.setLevel((int) currentLevel);
        player.setExp((float) (currentExp / getRequireLevelUpExp(currentLevel)));
    }

    // --- Get ---
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
}

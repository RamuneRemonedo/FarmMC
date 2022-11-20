package tokyo.ramune.farmmc.game.statistic;

import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.core.database.SQL;
import tokyo.ramune.farmmc.game.crop.FarmCropType;

import javax.annotation.Nonnull;

public class StatisticHandler {
    public static void createTable() {
        if (!SQL.tableExists("statistic_harvest"))
            SQL.createTable("statistic_harvest", "uuid TEXT NOT NULL" + toBiglongColumCropTypes());

        if (!SQL.tableExists("statistic_plant"))
            SQL.createTable("statistic_plant", "uuid TEXT NOT NULL" + toBiglongColumCropTypes());
    }

    public static void createColum(Player player) {
        if (!existsPlant(player))
            SQL.insertData("uuid", "'" + player.getUniqueId() + "'", "statistic_harvest");

        if (!existsPlant(player))
            SQL.insertData("uuid", "'" + player.getUniqueId() + "'", "statistic_plant");
    }

    public static boolean existsHarvest(@Nonnull Player player) {
        return SQL.exists("uuid", player.getUniqueId().toString(), "statistic_harvest");
    }

    public static boolean existsPlant(@Nonnull Player player) {
        return SQL.exists("uuid", player.getUniqueId().toString(), "statistic_plant");
    }

    private static String toBiglongColumCropTypes() {
        String colum = "";
        for (FarmCropType cropType : FarmCropType.values()) {
            colum += "," + cropType.name() + " BIGINT DEFAULT 0 ";
        }

        return colum;
    }

    public static void setHarvestCount(@Nonnull Player player, @Nonnull FarmCropType type, long count) {
        if (!existsHarvest(player))
            createColum(player);

        SQL.set(type.name(), count, "uuid", "=", player.getUniqueId().toString(), "statistic_harvest");
    }

    public static void setPlantCount(@Nonnull Player player, @Nonnull FarmCropType type, long count) {
        if (!existsPlant(player))
            createColum(player);

        SQL.set(type.name(), count, "uuid", "=", player.getUniqueId().toString(), "statistic_plant");
    }

    public static long getHarvestCount(@Nonnull Player player, @Nonnull FarmCropType type) {
        if (!existsHarvest(player))
            return 0;

        Object value = SQL.get(type.name(), "uuid", "=", player.getUniqueId().toString(), "statistic_harvest");
        return value == null ? 0 : (long) value;
    }

    public static long getPlantCount(@Nonnull Player player, @Nonnull FarmCropType type) {
        if (!existsPlant(player))
            return 0;

        Object value = SQL.get(type.name(), "uuid", "=", player.getUniqueId().toString(), "statistic_plant");
        return value == null ? 0 : (long) value;
    }

    public static void addHarvestCount(@Nonnull Player player, @Nonnull FarmCropType type, long count) {
        setHarvestCount(player, type, getHarvestCount(player, type) + count);
    }

    public static void addPlantCount(@Nonnull Player player, @Nonnull FarmCropType type, long count) {
        setPlantCount(player, type, getPlantCount(player, type) + count);
    }
}

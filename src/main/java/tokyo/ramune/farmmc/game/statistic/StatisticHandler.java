package tokyo.ramune.farmmc.game.statistic;

import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.core.database.SQL;
import tokyo.ramune.farmmc.game.crop.FarmCropType;

public class StatisticHandler {
    public static void createTable() {
        if (!SQL.tableExists("statistic_harvest"))
            SQL.createTable("statistic_harvest", "uuid TEXT NOT NULL" + toBigIntColumCropTypes());

        if (!SQL.tableExists("statistic_plant"))
            SQL.createTable("statistic_plant", "uuid TEXT NOT NULL" + toBigIntColumCropTypes());
    }

    public static void createColum(Player player) {
        if (!existsPlant(player))
            SQL.insertData("uuid", "'" + player.getUniqueId() + "'", "statistic_harvest");

        if (!existsPlant(player))
            SQL.insertData("uuid", "'" + player.getUniqueId() + "'", "statistic_plant");
    }

    public static boolean existsHarvest(Player player) {
        return SQL.exists("uuid", player.getUniqueId().toString(), "statistic_harvest");
    }

    public static boolean existsPlant(Player player) {
        return SQL.exists("uuid", player.getUniqueId().toString(), "statistic_plant");
    }

    private static String toBigIntColumCropTypes() {
        String colum = "";
        for (FarmCropType cropType : FarmCropType.values()) {
            colum += "," + cropType.name() + " BIGINT DEFAULT 0 ";
        }

        return colum;
    }

    public static void setHarvestCount(Player player, FarmCropType type, int count) {
        if (!existsHarvest(player))
            createColum(player);

        SQL.set(type.name(), count, "uuid", "=", player.getUniqueId().toString(), "statistic_harvest");
    }

    public static void setPlantCount(Player player, FarmCropType type, int count) {
        if (!existsPlant(player))
            createColum(player);

        SQL.set(type.name(), count, "uuid", "=", player.getUniqueId().toString(), "statistic_plant");
    }

    public static int getHarvestCount(Player player, FarmCropType type) {
        if (!existsHarvest(player))
            return 0;

        Object value = SQL.get(type.name(), "uuid", "=", player.getUniqueId().toString(), "statistic_harvest");
        return value == null ? 0 : (int) value;
    }

    public static int getPlantCount(Player player, FarmCropType type) {
        if (!existsPlant(player))
            return 0;

        Object value = SQL.get(type.name(), "uuid", "=", player.getUniqueId().toString(), "statistic_plant");
        return value == null ? 0 : (int) value;
    }

    public static void addHarvestCount(Player player, FarmCropType type, int count) {
        setHarvestCount(player, type, getHarvestCount(player, type) + count);
    }

    public static void addPlantCount(Player player, FarmCropType type, int count) {
        setPlantCount(player, type, getPlantCount(player, type) + count);
    }
}

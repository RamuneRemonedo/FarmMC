package tokyo.ramune.farmmc.game.crop;

import org.bukkit.Location;
import tokyo.ramune.farmmc.core.database.SQL;

import java.util.HashMap;

public class CropArtificialHandler {
    public static void createTable() {
        if (SQL.tableExists("crop"))
            return;

        SQL.createTable("crop", "x INT, y INT, z INT");
    }

    public static void add(Location blockLocation) {
        if (isArtificialPlaced(blockLocation))
            return;

        SQL.insertData(
                "x, y, z",
                "'" + blockLocation.getBlockX() + "', '" + blockLocation.getBlockY() + "', '" + blockLocation.getBlockZ() + "'",
                "crop");
    }

    public static void remove(Location blockLocation) {
        if (!isArtificialPlaced(blockLocation))
            return;

        SQL.deleteData(
                new HashMap<>() {{
                    put("x", String.valueOf(blockLocation.getBlockX()));
                    put("y", String.valueOf(blockLocation.getBlockY()));
                    put("z", String.valueOf(blockLocation.getBlockZ()));
                }},
                "crop");
    }

    public static boolean isArtificialPlaced(Location blockLocation) {
        return SQL.exists(
                new HashMap<>() {{
                    put("x", String.valueOf(blockLocation.getBlockX()));
                    put("y", String.valueOf(blockLocation.getBlockY()));
                    put("z", String.valueOf(blockLocation.getBlockZ()));
                }},
                "crop");
    }
}

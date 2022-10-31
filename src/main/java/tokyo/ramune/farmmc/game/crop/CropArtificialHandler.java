package tokyo.ramune.farmmc.game.crop;

import org.bukkit.Location;
import tokyo.ramune.farmmc.core.database.SQL;

import java.util.HashMap;

public class CropArtificialHandler {
    public static void createTable() {
        if (SQL.tableExists("crop"))
            return;

        SQL.createTable("crop", "uuid TEXT NOT NULL, x INT NOT NULL, y INT NOT NULL, z INT NOT NULL");
    }

    public static void add(Location blockLocation) {
        if (isArtificialPlaced(blockLocation))
            return;

        SQL.insertData(
                "uuid, x, y, z",
                "'" + blockLocation.getWorld().getUID() + "', '" + blockLocation.getBlockX() + "', '" + blockLocation.getBlockY() + "', '" + blockLocation.getBlockZ() + "'",
                "crop");
    }

    public static void remove(Location blockLocation) {
        if (!isArtificialPlaced(blockLocation))
            return;

        SQL.deleteData(
                new HashMap<>() {{
                    put("uuid", "'" + blockLocation.getWorld().getUID() + "'");
                    put("x", String.valueOf(blockLocation.getBlockX()));
                    put("y", String.valueOf(blockLocation.getBlockY()));
                    put("z", String.valueOf(blockLocation.getBlockZ()));
                }},
                "crop");
    }

    public static boolean isArtificialPlaced(Location blockLocation) {
        return SQL.exists(
                new HashMap<>() {{
                    put("uuid", "'" + blockLocation.getWorld().getUID() + "'");
                    put("x", String.valueOf(blockLocation.getBlockX()));
                    put("y", String.valueOf(blockLocation.getBlockY()));
                    put("z", String.valueOf(blockLocation.getBlockZ()));
                }},
                "crop");
    }
}

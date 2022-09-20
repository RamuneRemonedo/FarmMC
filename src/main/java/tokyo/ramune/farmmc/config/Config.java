package tokyo.ramune.farmmc.config;

import org.bukkit.Location;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.world.WorldHandler;

public class Config extends ConfigFile {
    public final String MYSQL_HOST, MYSQL_USER, MYSQL_PASSWORD, MYSQL_PORT, MYSQL_DATABASE,
            WORLD_TEMPLATE_NAME, WORLD_GAME_NAME;
    public final Location WORLD_SPAWN_LOCATION;

    public Config() {
        super(FarmMC.getPlugin(), "config.yml");
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();

        // Load Config Values
        MYSQL_HOST = getConfig().getString("config.MYSQL_HOST", "127.0.0.1");
        MYSQL_PORT = getConfig().getString("config.MYSQL_PORT", "3306");
        MYSQL_USER = getConfig().getString("config.MYSQL_USER", "root");
        MYSQL_PASSWORD = getConfig().getString("config.MYSQL_PASSWORD", "password");
        MYSQL_DATABASE = getConfig().getString("config.MYSQL_DATABASE", "FarmMC");

        WORLD_SPAWN_LOCATION = new Location(
                WorldHandler.getGameWorld(),
                getConfig().getDouble("config.WORLD_SPAWN_LOCATION.x", 0.0),
                getConfig().getDouble("config.WORLD_SPAWN_LOCATION.y", 0.0),
                getConfig().getDouble("config.WORLD_SPAWN_LOCATION.z", 0.0),
                getConfig().getInt("config.WORLD_SPAWN_LOCATION.yaw", 0),
                getConfig().getInt("config.WORLD_SPAWN_LOCATION.pitch", 0));
        WORLD_TEMPLATE_NAME = getConfig().getString("config.WORLD_TEMPLATE_NAME", "farmmc-template");
        WORLD_GAME_NAME = getConfig().getString("config.WORLD_GAME_NAME", "farmmc-game");
    }
}

package tokyo.ramune.farmmc.core.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import tokyo.ramune.farmmc.FarmMC;

import javax.annotation.Nonnull;

public class CoreConfig extends ConfigFile {
    public String MYSQL_TYPE, MYSQL_HOST, MYSQL_USER, MYSQL_PASSWORD, MYSQL_PORT, MYSQL_DATABASE,
            LANGUAGE_DEFAULT;
    public Location SPAWN_LOCATION;
    public boolean FORCE_SPAWN_TELEPORT, PLUGIN_GAME_MODE, PLUGIN_MAINTENANCE_MODE;

    public CoreConfig() {
        super(FarmMC.getPlugin(), "core.yml");
    }

    public void load() {
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();

        loadValues();
    }

    private void loadValues() {
        MYSQL_TYPE = getConfig().getString("config.MYSQL_TYPE", "mysql");
        MYSQL_HOST = getConfig().getString("config.MYSQL_HOST", "127.0.0.1");
        MYSQL_PORT = getConfig().getString("config.MYSQL_PORT", "3306");
        MYSQL_USER = getConfig().getString("config.MYSQL_USER", "root");
        MYSQL_PASSWORD = getConfig().getString("config.MYSQL_PASSWORD", "password");
        MYSQL_DATABASE = getConfig().getString("config.MYSQL_DATABASE", "FarmMC");

        LANGUAGE_DEFAULT = getConfig().getString("config.LANGUAGE_DEFAULT", "ja");

        FORCE_SPAWN_TELEPORT = getConfig().getBoolean("config.FORCE_SPAWN_TELEPORT", false);
        SPAWN_LOCATION = getAsLocation("config.SPAWN_LOCATION");

        PLUGIN_GAME_MODE = getConfig().getBoolean("config.PLUGIN_GAME_MODE", false);
        PLUGIN_MAINTENANCE_MODE = getConfig().getBoolean("config.PLUGIN_MAINTENANCE_MODE", false);
    }

    public void reload() {
        loadValues();
    }

    public void setLocation(String path, @Nonnull Location location) {
        getConfig().set(path + ".world", location.getWorld().getName());

        getConfig().set(path + ".x", location.getX());
        getConfig().set(path + ".y", location.getY());
        getConfig().set(path + ".z", location.getZ());

        getConfig().set(path + ".yaw", location.getYaw());
        getConfig().set(path + ".pitch", location.getPitch());
    }

    private Location getAsLocation(String path) {
        Location location = new Location(Bukkit.getWorlds().get(0), 0, 0, 0, 0, 0);

        location.setWorld(Bukkit.getWorld(getConfig().getString(path + ".world", "world")));
        location.set(
                getConfig().getDouble(path + ".x", 0.0),
                getConfig().getDouble(path + ".y", 0.0),
                getConfig().getDouble(path + ".z", 0.0)
        );
        location.setYaw(getConfig().getInt(path + ".yaw", 0));
        location.setPitch(getConfig().getInt(path + ".pitch", 0));

        return location;
    }
}

package tokyo.ramune.farmmc.config;

import tokyo.ramune.farmmc.FarmMC;

public class Config extends ConfigFile {
    public String MYSQL_HOST, MYSQL_USER, MYSQL_PASSWORD, MYSQL_PORT, MYSQL_DATABASE,
            LANGUAGE_DEFAULT;

    public Config() {
        super(FarmMC.getPlugin(), "config.yml");
    }

    public void load() {
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();

        // Load Config Values
        MYSQL_HOST = getConfig().getString("config.MYSQL_HOST", "127.0.0.1");
        MYSQL_PORT = getConfig().getString("config.MYSQL_PORT", "3306");
        MYSQL_USER = getConfig().getString("config.MYSQL_USER", "root");
        MYSQL_PASSWORD = getConfig().getString("config.MYSQL_PASSWORD", "password");
        MYSQL_DATABASE = getConfig().getString("config.MYSQL_DATABASE", "FarmMC");

        LANGUAGE_DEFAULT = getConfig().getString("config.LANGUAGE_DEFAULT", "ja");
    }
}

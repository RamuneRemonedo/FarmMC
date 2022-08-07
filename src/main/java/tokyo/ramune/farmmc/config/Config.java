package tokyo.ramune.farmmc.config;

import tokyo.ramune.farmmc.FarmMC;

public class Config {

    private final ConfigFile configFile;

    public Config() {
        configFile = new ConfigFile(FarmMC.getPlugin(), "config.yml");
        configFile.saveDefaultConfig();
        configFile.getConfig().options().copyDefaults(true);
        configFile.saveConfig();
    }

    public int getInt(ConfigPhase configPhase) {
        return configFile.getConfig().getInt("config." + configPhase.name());
    }

    public String getString(ConfigPhase configPhase) {
        return configFile.getConfig().getString("config." + configPhase.name());
    }

    public boolean getBoolean(ConfigPhase configPhase) {
        return configFile.getConfig().getBoolean("config." + configPhase.name());
    }
}

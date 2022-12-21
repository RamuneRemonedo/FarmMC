package tokyo.ramune.farmmc.core.language;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.core.CoreHandler;
import tokyo.ramune.farmmc.core.config.ConfigFile;
import tokyo.ramune.farmmc.core.database.SQL;
import tokyo.ramune.farmmc.core.setting.CoreSettingHandler;
import tokyo.ramune.farmmc.core.util.Chat;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class LanguageHandler {
    private static final Map<Language, ConfigFile> languageConfigs = new HashMap<>();

    public static void load() {
        languageConfigs.clear();

        for (Language langCode : Language.values()) {
            ConfigFile languageConfig = new ConfigFile(FarmMC.getPlugin(), langCode.name().toLowerCase() + "_lang.yml");

            languageConfig.saveDefaultConfig();
            languageConfig.getConfig().options().copyDefaults(true);
            languageConfig.saveConfig();

            languageConfigs.put(langCode, languageConfig);
            FarmMC.getPlugin().getLogger().info(langCode.name().toLowerCase() + "_lang.yml loaded!");
        }
    }

    public static boolean isSupportedLangCode(String languageCode) {
        for (Language supportedLangCode : Language.values()) {
            if (supportedLangCode.name().equalsIgnoreCase(languageCode))
                return true;
        }

        return false;
    }

    public static Language getLanguage(@Nonnull Player player) {
        return Language.valueOf(CoreSettingHandler.LANGUAGE.getData(player.getUniqueId()).getAsString().toUpperCase());
    }

    public static String getPhase(@Nonnull Player player, Phase phase) {
        Language language = getLanguage(player);

        return getPhase(language, phase);
    }

    public static String getPhase(@Nonnull CommandSender sender, Phase phase) {
        Language language;

        if (sender instanceof Player) {
            language = Language.valueOf(CoreSettingHandler.LANGUAGE.getData(((Player) sender).getUniqueId()).getAsString().toUpperCase());
        } else {
            language = Language.EN;
        }

        return getPhase(language, phase);
    }

    public static String getPhase(Language language, Phase phase) {
        return Chat.replaceColor(
                getRawPhase(language, phase),
                getRawPhase(language, Phase.LANG_COLOR_PREFIX).charAt(0)
        );
    }

    public static String getRawPhase(Language language, Phase phase) {
        if (!languageConfigs.containsKey(language))
            return "Language File Missing.";

        ConfigFile languageConfig = languageConfigs.get(language);

        return languageConfig.getConfig().getString("lang." + phase.name(), "\"Cannot find " + phase.name() + " phase\"");
    }
}

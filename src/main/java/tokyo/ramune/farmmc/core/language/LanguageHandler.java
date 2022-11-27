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

    public static void createTable() {
        if (SQL.tableExists("language"))
            return;

        SQL.createTable("language", "uuid TEXT NOT NULL, lang_code TEXT NOT NULL");
    }

    public static void load() {
        languageConfigs.clear();

        for (Language langCode : Language.values()) {
            if (langCode.equals(Language.DEFAULT))
                continue;

            ConfigFile languageConfig = new ConfigFile(FarmMC.getPlugin(), langCode + "_lang.yml");

            languageConfig.saveDefaultConfig();
            languageConfig.getConfig().options().copyDefaults(true);
            languageConfig.saveConfig();

            languageConfigs.put(langCode, languageConfig);
            FarmMC.getPlugin().getLogger().info(langCode + "_lang.yml loaded!");
        }

        if (!existsDefaultLanguage()) {
            throw new IllegalStateException("There is no supported language: \"" + CoreHandler.getInstance().getCoreConfig().LANGUAGE_DEFAULT + "\"");
        }
    }

    public static boolean isSupportedLangCode(String languageCode) {
        for (Language supportedLangCode : Language.values()) {
            if (supportedLangCode.name().equalsIgnoreCase(languageCode))
                return true;
        }

        return false;
    }

    private static boolean existsDefaultLanguage() {
        String defaultLanguage = CoreHandler.getInstance().getCoreConfig().LANGUAGE_DEFAULT;

        for (Language supportedLangCode : Language.values()) {
            if (supportedLangCode.name().equalsIgnoreCase(defaultLanguage))
                return true;
        }

        return false;
    }

    public static Language getLanguage(@Nonnull Player player) {
        return Language.valueOf(CoreSettingHandler.LANGUAGE.getData(player.getUniqueId()).getAsString().toUpperCase());
    }

    public static String getPhase(@Nonnull Player player, Phase phase) {
        Language language = Language.valueOf(CoreSettingHandler.LANGUAGE.getData(player.getUniqueId()).getAsString().toUpperCase());

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
        if (language.equals(Language.DEFAULT))
            language = Language.valueOf(CoreHandler.getInstance().getCoreConfig().LANGUAGE_DEFAULT.toUpperCase());

        return Chat.replaceColor(
                getRawPhase(language, phase),
                getRawPhase(language, Phase.LANG_COLOR_PREFIX).charAt(0)
        );
    }

    public static String getRawPhase(Language language, Phase phase) {
        if (!languageConfigs.containsKey(language))
            return "Config File Missing.";

        ConfigFile languageConfig = languageConfigs.get(language);

        return languageConfig.getConfig().getString("lang." + phase.name(), "\"Cannot find " + phase.name() + " phase\"");
    }
}

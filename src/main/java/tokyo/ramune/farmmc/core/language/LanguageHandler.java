package tokyo.ramune.farmmc.core.language;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.core.FarmCoreHandler;
import tokyo.ramune.farmmc.core.config.ConfigFile;
import tokyo.ramune.farmmc.core.database.SQL;
import tokyo.ramune.farmmc.core.utility.Chat;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LanguageHandler {
    private static final String[] supportedLangCodes = {"en", "ja"};
    private static final Map<String, ConfigFile> languageConfigs = new HashMap<>();

    public static void createTable() {
        if (SQL.tableExists("language"))
            return;

        SQL.createTable("language", "uuid TEXT NOT NULL, lang_code TEXT NOT NULL");
    }

    public static void load() {
        languageConfigs.clear();

        for (String langCode : supportedLangCodes) {
            ConfigFile languageConfig = new ConfigFile(FarmMC.getPlugin(), langCode + "_lang.yml");

            languageConfig.saveDefaultConfig();
            languageConfig.getConfig().options().copyDefaults(true);
            languageConfig.saveConfig();

            languageConfigs.put(langCode, languageConfig);
            FarmMC.getPlugin().getLogger().info(langCode + "_lang.yml loaded!");
        }

        if (!existsDefaultLanguage()) {
            throw new IllegalStateException("There is no supported language: \"" + FarmCoreHandler.getInstance().getCoreConfig().LANGUAGE_DEFAULT + "\"");
        }
    }

    public static void createColum(Player player) {
        createColum(player.getUniqueId());
    }

    public static void createColum(UUID uuid) {
        if (existsColum(uuid))
            return;

        SQL.insertData("uuid, lang_code", "'" + uuid + "', 'default'", "language");
    }

    public static String getLanguageCode(Player player) {
        return getLanguageCode(player.getUniqueId());
    }

    public static String getLanguageCode(UUID uuid) {
        String langCode = (String) SQL.get("lang_code", "uuid", "=", uuid.toString(), "language");

        return langCode == null ? "default" : langCode;
    }

    public static void setLanguageCode(UUID uuid, String langCode) {
        if (!isSupportedLangCode(langCode))
            langCode = "default";

        if (!existsColum(uuid))
            createColum(uuid);

        if (getLanguageCode(uuid).equals(langCode))
            return;

        SQL.set("lang_code", langCode, "uuid", "=", uuid.toString(), "language");
    }

    public static boolean existsColum(UUID uuid) {
        return SQL.exists("uuid", uuid.toString(), "language");
    }

    public static String[] getSupportedLangCodes() {
        return supportedLangCodes;
    }

    public static boolean isSupportedLangCode(String languageCode) {
        for (String supportedLangCode : supportedLangCodes) {
            if (supportedLangCode.equals(languageCode))
                return true;
        }

        return false;
    }

    private static boolean existsDefaultLanguage() {
        String defaultLanguage = FarmCoreHandler.getInstance().getCoreConfig().LANGUAGE_DEFAULT;

        for (String supportedLangCode : supportedLangCodes) {
            if (supportedLangCode.equals(defaultLanguage))
                return true;
        }

        return false;
    }

    public static String getPhase(@Nonnull Player player, Phase phase) {
        String langCode = getLanguageCode(player.getUniqueId());

        return getPhase(langCode, phase);
    }

    public static String getPhase(@Nonnull CommandSender sender, Phase phase) {
        String langCode;

        if (sender instanceof Player) {
            langCode = getLanguageCode(((Player) sender).getUniqueId());
        } else {
            langCode = "en";
        }

        return getPhase(langCode, phase);
    }

    public static String getPhase(String langCode, Phase phase) {
        if (langCode.equals("default"))
            langCode = FarmCoreHandler.getInstance().getCoreConfig().LANGUAGE_DEFAULT;

        return Chat.replaceColor(
                getRawPhase(langCode, phase),
                getRawPhase(langCode, Phase.LANG_COLOR_PREFIX).toCharArray()[0]
        );
    }

    public static String getRawPhase(String langCode, Phase phase) {
        if (!languageConfigs.containsKey(langCode))
            return "Config File Missing.";

        ConfigFile languageConfig = languageConfigs.get(langCode);

        return languageConfig.getConfig().getString("lang." + phase.name(), "\"Cannot find " + phase.name() + " phase\"");
    }
}

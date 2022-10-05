package tokyo.ramune.farmmc.language;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.config.ConfigFile;
import tokyo.ramune.farmmc.player.PlayerStatus;
import tokyo.ramune.farmmc.utility.Chat;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class FarmLanguageHandler {
    private static final String[] supportedLangCodes = {"en", "ja"};
    private static final Map<String, ConfigFile> languageConfigs = new HashMap<>();

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
    }

    public static String getPhase(@Nonnull Player player, Phase phase) {
        String langCode = new PlayerStatus(player).getLanguage();

        return Chat.replaceColor(
                getRawPhase(langCode, phase),
                getRawPhase(langCode, Phase.LANG_COLOR_PREFIX).toCharArray()[0]
        );
    }

    public static String getPhase(@Nonnull CommandSender sender, Phase phase) {
        String langCode;

        if (sender instanceof Player) {
            langCode = new PlayerStatus((Player) sender).getLanguage();
        } else {
            langCode = "en";
        }

        return Chat.replaceColor(
                getRawPhase(langCode, phase),
                getRawPhase(langCode, Phase.LANG_COLOR_PREFIX).toCharArray()[0]
        );
    }

    public static String getPhase(String langCode, Phase phase) {
        return Chat.replaceColor(
                getRawPhase(langCode, phase),
                getRawPhase(langCode, Phase.LANG_COLOR_PREFIX).toCharArray()[0]
        );
    }

    public static String getRawPhase(String langCode, Phase phase) {
        if (languageConfigs.containsKey(langCode))
            return "-Config File Missing.-";

        ConfigFile languageConfig = languageConfigs.get(langCode);

        return languageConfig.getConfig().getString("lang." + phase.name(), "null");
    }
}

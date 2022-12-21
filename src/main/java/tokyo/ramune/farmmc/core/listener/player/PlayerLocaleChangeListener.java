package tokyo.ramune.farmmc.core.listener.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLocaleChangeEvent;
import tokyo.ramune.farmmc.core.CoreHandler;
import tokyo.ramune.farmmc.core.language.Language;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.util.Chat;

import java.util.Locale;

public class PlayerLocaleChangeListener implements Listener {
    @EventHandler
    public void onPlayerLocaleChange(PlayerLocaleChangeEvent event) {
        Player player = event.getPlayer();
        Locale locale = event.locale();

        if (LanguageHandler.getLanguage(player).equals(Language.JA) && !locale.equals(Locale.JAPAN)) {
            Bukkit.getScheduler().runTaskLater(CoreHandler.getInstance().getPlugin(), () -> {
                Location location = player.getLocation();

                player.playSound(location, Sound.ENTITY_WOLF_AMBIENT, 1, 0);
                Chat.sendMessage(player, LanguageHandler.getPhase(player, Phase.LANG_AUTO_DETECT), true);
                player.sendMessage(
                        Component.text(LanguageHandler.getPhase(Language.EN, Phase.LANG_AUTO_DETECT_CLICK_HERE))
                                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/farmmc language en"))
                );
            }, 100);
        }

        if (LanguageHandler.getLanguage(player).equals(Language.EN) && locale.equals(Locale.JAPAN)) {
            Bukkit.getScheduler().runTaskLater(CoreHandler.getInstance().getPlugin(), () -> {
                Location location = player.getLocation();

                player.playSound(location, Sound.ENTITY_WOLF_AMBIENT, 1, 0);
                Chat.sendMessage(player, LanguageHandler.getPhase(player, Phase.LANG_AUTO_DETECT), true);
                player.sendMessage(
                        Component.text(LanguageHandler.getPhase(Language.JA, Phase.LANG_AUTO_DETECT_CLICK_HERE))
                                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/farmmc language ja"))
                );
            }, 100);
        }
    }
}

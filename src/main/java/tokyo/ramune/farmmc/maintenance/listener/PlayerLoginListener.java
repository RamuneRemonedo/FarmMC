package tokyo.ramune.farmmc.maintenance.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.utility.Permission;

public class PlayerLoginListener implements Listener {
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        event.setResult(player.hasPermission(
                Permission.JOIN_MAINTENANCE.toPermission()) ?
                PlayerLoginEvent.Result.ALLOWED : PlayerLoginEvent.Result.KICK_OTHER
        );

        event.kickMessage(
                Component.text(
                        LanguageHandler.getPhase(player, Phase.LOGIN_MAINTENANCE_MODE)
                                .replace("{0}", Permission.JOIN_MAINTENANCE.toPermission().getName())
                )
        );
    }
}

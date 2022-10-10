package tokyo.ramune.farmmc.listener.player;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.language.Phase;
import tokyo.ramune.farmmc.utility.FarmPermission;
import tokyo.ramune.farmmc.utility.PluginStatus;

public class PlayerLoginListener implements Listener {
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        // Maintenance Mode
        if (FarmMC.getStatus().equals(PluginStatus.MAINTENANCE)) {
            event.setResult(player.hasPermission(
                    FarmPermission.JOIN_MAINTENANCE.toPermission()) ?
                    PlayerLoginEvent.Result.ALLOWED : PlayerLoginEvent.Result.KICK_OTHER
            );

            event.kickMessage(
                    Component.text(
                            FarmLanguageHandler.getPhase(player, Phase.LOGIN_MAINTENANCE_MODE)
                                    .replace("{0}", FarmPermission.JOIN_MAINTENANCE.toPermission().getName())
                    )
            );
        }
    }
}

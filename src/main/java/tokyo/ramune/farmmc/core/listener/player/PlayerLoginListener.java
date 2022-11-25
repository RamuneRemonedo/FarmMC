package tokyo.ramune.farmmc.core.listener.player;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import tokyo.ramune.farmmc.core.auth.AuthHandler;
import tokyo.ramune.farmmc.core.auth.PlayerProfile;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;

public class PlayerLoginListener implements Listener {
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        // Auth
        PlayerProfile playerProfile = AuthHandler.get(player.getUniqueId());
        if (playerProfile.isBanned()) {
            event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            event.kickMessage(Component.text(LanguageHandler.getPhase(player, Phase.AUTH_BANNED)
                    .replace("{0}", playerProfile.getBannedReason() == null ? "No reason" : playerProfile.getBannedReason())));
        }
    }
}

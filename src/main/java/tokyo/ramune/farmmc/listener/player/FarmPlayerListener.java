package tokyo.ramune.farmmc.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.player.FarmPlayer;

import java.util.Objects;

public class FarmPlayerListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        FarmPlayer farmPlayer =  FarmMC.getFarmPlayerManager().getFarmPlayer(player);
        farmPlayer.getCursor().spawn(Objects.requireNonNull(player.getTargetBlock(50)).getLocation());
        FarmMC.getBossBarManager().createExpBossBar(farmPlayer);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        FarmMC.getFarmPlayerManager().removePlayer(FarmMC.getFarmPlayerManager().getFarmPlayer(player));
    }
}

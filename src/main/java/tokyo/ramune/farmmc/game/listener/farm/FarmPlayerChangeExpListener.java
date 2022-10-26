package tokyo.ramune.farmmc.game.listener.farm;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.game.event.player.FarmPlayerChangeExpEvent;

public class FarmPlayerChangeExpListener implements Listener {
    @EventHandler
    public void onFarmPlayerChangeExp(FarmPlayerChangeExpEvent event) {
        Player player = event.getPlayer();

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1.1F);
    }
}

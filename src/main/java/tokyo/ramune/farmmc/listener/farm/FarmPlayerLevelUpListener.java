package tokyo.ramune.farmmc.listener.farm;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.event.player.FarmPlayerLevelUpEvent;
import tokyo.ramune.farmmc.utility.Notice;

public class FarmPlayerLevelUpListener implements Listener {
    @EventHandler
    public void onFarmPlayerLevelUp(FarmPlayerLevelUpEvent event) {
        Player player = event.getPlayer();

        Notice.noticeLevelUp(player, event.getFrom(), event.getTo());
    }
}

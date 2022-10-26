package tokyo.ramune.farmmc.game.listener.farm;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.core.utility.Notice;
import tokyo.ramune.farmmc.game.event.player.FarmPlayerLevelUpEvent;

public class FarmPlayerLevelUpListener implements Listener {
    @EventHandler
    public void onFarmPlayerLevelUp(FarmPlayerLevelUpEvent event) {
        Player player = event.getPlayer();

        Notice.noticeLevelUp(player, event.getFrom(), event.getTo());
    }
}

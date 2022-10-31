package tokyo.ramune.farmmc.game.listener.player;

import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import tokyo.ramune.farmmc.game.crop.FarmServantHandler;

public class PlayerArmorStandManipulateListener implements Listener {
    @EventHandler
    public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent event) {
        ArmorStand armorStand = event.getRightClicked();

        if (FarmServantHandler.isServant(armorStand))
            event.setCancelled(true);
    }
}

package tokyo.ramune.farmmc.listener.farm;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.crop.CropArtificialHandler;
import tokyo.ramune.farmmc.crop.FarmCropType;
import tokyo.ramune.farmmc.event.player.FarmCropHarvestEvent;
import tokyo.ramune.farmmc.player.PlayerStatus;

public class FarmCropHarvestListener implements Listener {
    @EventHandler
    public void onFarmCropHarvest(FarmCropHarvestEvent event) {
        Player player = event.getPlayer();
        FarmCropType type = event.getType();
        Location blockLocation = event.getBlockLocation();

        PlayerStatus playerStatus = new PlayerStatus(player);

        if (CropArtificialHandler.isArtificialPlaced(blockLocation)) {
            CropArtificialHandler.remove(blockLocation);
        } else {
            playerStatus.setExp(playerStatus.getExp() + type.getExp());
        }
    }
}

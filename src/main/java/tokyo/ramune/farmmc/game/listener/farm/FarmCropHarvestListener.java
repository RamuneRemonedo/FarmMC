package tokyo.ramune.farmmc.game.listener.farm;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.game.crop.CropArtificialHandler;
import tokyo.ramune.farmmc.game.crop.FarmCropType;
import tokyo.ramune.farmmc.game.event.crop.FarmCropHarvestEvent;
import tokyo.ramune.farmmc.game.player.PlayerStatus;
import tokyo.ramune.farmmc.game.utility.FarmUtil;

import java.util.List;

public class FarmCropHarvestListener implements Listener {
    @EventHandler
    public void onFarmCropHarvest(FarmCropHarvestEvent event) {
        Player player = event.getPlayer();
        FarmCropType type = event.getType();
        Location blockLocation = event.getBlockLocation();

        PlayerStatus playerStatus = new PlayerStatus(player);
        List<Location> cropLocations = FarmUtil.getHarvestedCropLocations(blockLocation);

        cropLocations.forEach(cropLocation -> {
            if (CropArtificialHandler.isArtificialPlaced(cropLocation)) {
                cropLocations.remove(cropLocation);
                CropArtificialHandler.remove(blockLocation);
            }
        });

        playerStatus.setExp(playerStatus.getExp() + (type.getExp() * cropLocations.size()));
    }
}

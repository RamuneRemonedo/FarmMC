package tokyo.ramune.farmmc.game.listener.farm;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.game.crop.CropArtificialHandler;
import tokyo.ramune.farmmc.game.crop.CropType;
import tokyo.ramune.farmmc.game.event.crop.FarmCropHarvestEvent;
import tokyo.ramune.farmmc.game.player.PlayerStatus;
import tokyo.ramune.farmmc.game.statistic.StatisticHandler;
import tokyo.ramune.farmmc.game.util.FarmUtil;

import java.util.List;

public class FarmCropHarvestListener implements Listener {
    @EventHandler
    public void onFarmCropHarvest(FarmCropHarvestEvent event) {
        Player player = event.getPlayer();
        Location blockLocation = event.getBlockLocation();
        List<Location> cropLocations = FarmUtil.getHarvestedCropLocations(blockLocation);
        CropArtificialHandler.remove(blockLocation);

        PlayerStatus playerStatus = new PlayerStatus(player);

        for (Location cropLocation : cropLocations) {
            CropType type = CropType.getCropType(cropLocation.getBlock().getType());
            if (type == null)
                continue;

            playerStatus.setExp(playerStatus.getExp() + (type.getExp()));
            StatisticHandler.addHarvestCount(player, type, 1);
            CropArtificialHandler.remove(cropLocation);
        }
    }
}

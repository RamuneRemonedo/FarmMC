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
        List<Location> cropLocations = FarmUtil.getHarvestedCropLocations(event.getBlockLocation());

        PlayerStatus playerStatus = new PlayerStatus(player);

        System.out.println(cropLocations.size());
        for (Location cropLocation : cropLocations) {
            CropType type = CropType.getCropType(cropLocation.getBlock().getType());

            if (CropArtificialHandler.isArtificialPlaced(cropLocation))
                CropArtificialHandler.remove(cropLocation);

            playerStatus.setExp(playerStatus.getExp() + (type.getExp()));

            StatisticHandler.addHarvestCount(player, type, 1);
        }

        // TODO: 2022/12/10
        event.setCancelled(true);
    }
}

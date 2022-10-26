package tokyo.ramune.farmmc.game.listener.world;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import tokyo.ramune.farmmc.game.crop.FarmCropType;
import tokyo.ramune.farmmc.game.event.crop.FarmCropPlantEvent;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();
        FarmCropType type = FarmCropType.getCropType(block.getType());

        if (type != null) {
            FarmCropPlantEvent farmEvent = new FarmCropPlantEvent(player, block.getLocation(), type);

            Bukkit.getPluginManager().callEvent(farmEvent);

            if (farmEvent.isCancelled())
                event.setCancelled(true);
        }
    }
}

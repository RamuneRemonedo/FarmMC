package tokyo.ramune.farmmc.game.listener.world;

import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;
import tokyo.ramune.farmmc.game.crop.FarmCropType;

public class StructureGrowListener implements Listener {
    @EventHandler
    public void onStructureGrow(StructureGrowEvent event) {
        boolean isGrowable = true;

        for (BlockState block : event.getBlocks()) {
            FarmCropType type = FarmCropType.getCropType(block.getType());
            Location location = block.getLocation();

            if (type == null)
                continue;

            if (!type.isGrowableAt(location))
                isGrowable = false;
        }

        event.setCancelled(!isGrowable);
    }
}

package tokyo.ramune.farmmc.game.listener.world;

import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockSpreadEvent;
import tokyo.ramune.farmmc.game.crop.FarmCropType;

public class BlockSpreadListener implements Listener {
    @EventHandler
    public void onBlockSpread(BlockSpreadEvent event) {
        boolean isGrowable = true;
        BlockState block = event.getNewState();

        FarmCropType type = FarmCropType.getCropType(block.getType());
        Location location = block.getLocation();

        if (type == null)
            return;

        if (!type.isGrowableAt(location))
            isGrowable = false;

        event.setCancelled(!isGrowable);
    }
}

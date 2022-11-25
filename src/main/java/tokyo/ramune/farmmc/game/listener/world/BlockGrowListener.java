package tokyo.ramune.farmmc.game.listener.world;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import tokyo.ramune.farmmc.game.crop.CropType;

public class BlockGrowListener implements Listener {
    @EventHandler
    public void onBlockGrow(BlockGrowEvent event) {
        CropType type = CropType.getCropType(event.getNewState().getType());
        Location location = event.getNewState().getLocation();

        if (type == null)
            return;

        if (!type.isGrowableAt(location))
            event.setCancelled(true);
    }
}

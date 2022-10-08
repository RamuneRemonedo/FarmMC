package tokyo.ramune.farmmc.listener.world;

import io.papermc.paper.event.block.BlockPreDispenseEvent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BlockPreDispenseListener implements Listener {
    @EventHandler
    public void onBlockPreDispense(BlockPreDispenseEvent event) {
        if (event.getItemStack().getType().equals(Material.BONE_MEAL))
            event.setCancelled(true);
    }
}

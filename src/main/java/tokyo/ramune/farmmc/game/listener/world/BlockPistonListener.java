package tokyo.ramune.farmmc.game.listener.world;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import tokyo.ramune.farmmc.game.crop.CropType;

import java.util.List;

public class BlockPistonListener implements Listener {
    @EventHandler
    public void onBlockPistonExtend(BlockPistonExtendEvent event) {
        List<Block> blocks = event.getBlocks();

        for (Block block : blocks) {
            if (CropType.getCropType(block.getType()) != null)
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPistonRetract(BlockPistonRetractEvent event) {
        List<Block> blocks = event.getBlocks();

        for (Block block : blocks) {
            if (CropType.getCropType(block.getType()) != null)
                event.setCancelled(true);
        }
    }
}

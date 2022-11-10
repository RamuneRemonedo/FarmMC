package tokyo.ramune.farmmc.game.listener.world;

import io.papermc.paper.event.block.BlockBreakBlockEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BlockBreakBlockListener implements Listener {
    @EventHandler
    public void onBlockBreakBlock(BlockBreakBlockEvent event) {
        System.out.println(event.getBlock().getType());
    }
}

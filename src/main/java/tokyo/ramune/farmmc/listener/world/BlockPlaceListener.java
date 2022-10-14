package tokyo.ramune.farmmc.listener.world;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import tokyo.ramune.farmmc.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.language.Phase;
import tokyo.ramune.farmmc.utility.Chat;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();

        if (block.getType().equals(Material.PUMPKIN)
                || block.getType().equals(Material.MELON)) {
            Chat.sendMessage(player, FarmLanguageHandler.getPhase(player, Phase.WORLD_CANNOT_PLACE), true);
            event.setCancelled(true);
        }
    }
}

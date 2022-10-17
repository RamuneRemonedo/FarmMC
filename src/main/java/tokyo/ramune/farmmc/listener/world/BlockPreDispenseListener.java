package tokyo.ramune.farmmc.listener.world;

import io.papermc.paper.event.block.BlockPreDispenseEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.language.Phase;
import tokyo.ramune.farmmc.utility.Chat;
import tokyo.ramune.farmmc.utility.PlayerUtil;

public class BlockPreDispenseListener implements Listener {
    @EventHandler
    public void onBlockPreDispense(BlockPreDispenseEvent event) {
        if (event.getItemStack().getType().equals(Material.BONE_MEAL)) {
            Player nearestPlayer = PlayerUtil.getNearestPlayer(event.getBlock().getLocation(), 5);

            if (nearestPlayer != null)
                Chat.sendMessage(nearestPlayer, FarmLanguageHandler.getPhase(nearestPlayer, Phase.WORLD_BONE_MEAL_DISABLED), true, true);

            event.setCancelled(true);
        }
    }
}

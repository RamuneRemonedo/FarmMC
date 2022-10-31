package tokyo.ramune.farmmc.game.listener.world;

import io.papermc.paper.event.block.BlockPreDispenseEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.utility.Chat;
import tokyo.ramune.farmmc.game.utility.PlayerUtil;

public class BlockPreDispenseListener implements Listener {
    @EventHandler
    public void onBlockPreDispense(BlockPreDispenseEvent event) {
        if (event.getItemStack().getType().equals(Material.BONE_MEAL)) {
            Player nearestPlayer = PlayerUtil.getNearestPlayer(event.getBlock().getLocation(), 5);

            if (nearestPlayer != null)
                Chat.sendMessage(nearestPlayer, LanguageHandler.getPhase(nearestPlayer, Phase.WORLD_BONE_MEAL_DISABLED), true, true);

            event.setCancelled(true);
        }
    }
}

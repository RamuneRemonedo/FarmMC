package tokyo.ramune.farmmc.listener.world;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFertilizeEvent;
import tokyo.ramune.farmmc.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.language.Phase;
import tokyo.ramune.farmmc.utility.Chat;

public class BlockFertilizeListener implements Listener {
    @EventHandler
    public void onBlockFertilize(BlockFertilizeEvent event) {
        Player player = event.getPlayer();

        if (player != null)
            Chat.sendMessage(player, FarmLanguageHandler.getPhase(player, Phase.WORLD_BONE_MEAL_DISABLED), true);

        event.setCancelled(true);
    }
}

package tokyo.ramune.farmmc.game.listener.world;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFertilizeEvent;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.util.Chat;

public class BlockFertilizeListener implements Listener {
    @EventHandler
    public void onBlockFertilize(BlockFertilizeEvent event) {
        Player player = event.getPlayer();

        if (player != null)
            Chat.sendMessage(player, LanguageHandler.getPhase(player, Phase.WORLD_BONE_MEAL_DISABLED), true, true);

        event.setCancelled(true);
    }
}

package tokyo.ramune.farmmc.game.listener.world;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;

import java.util.Objects;

public class AnvilTakeResultListener implements Listener {
    @EventHandler
    public void onAnvilTakeResult(InventoryClickEvent event) {
        if (!(event.getClickedInventory() instanceof AnvilInventory))
            return;

        Player player = (Player) event.getWhoClicked();

        if (Objects.requireNonNull(event.getCurrentItem()).getType().equals(Material.BARRIER)) {
            player.playSound(player.getLocation(), Sound.ENTITY_DONKEY_ANGRY, 1, 2);
            player.sendMessage(LanguageHandler.getPhase(player, Phase.ANVIL_DENY_RENAMING));
            event.setCancelled(true);
            player.closeInventory();
        }
    }
}

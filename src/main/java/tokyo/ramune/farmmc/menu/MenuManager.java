package tokyo.ramune.farmmc.menu;

import org.bukkit.ChatColor;
import org.bukkit.inventory.InventoryView;
import tokyo.ramune.farmmc.player.FarmPlayer;

public class MenuManager {

    public MenuManager() {
    }

    public boolean isFarmMCMenu(FarmPlayer farmPlayer) {
        return farmPlayer.getPlayer().getOpenInventory().getTitle()
                .startsWith(ChatColor.GOLD + ChatColor.YELLOW.toString() + ChatColor.RESET);
    }

    public boolean isFarmMCMenu(InventoryView inventoryView) {
        return inventoryView.getTitle()
                .startsWith(ChatColor.GOLD + ChatColor.YELLOW.toString() + ChatColor.RESET);
    }
}

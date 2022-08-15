package tokyo.ramune.farmmc.menu.menuItem;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.menu.MenuItem;
import tokyo.ramune.farmmc.player.FarmPlayer;

public class CloseMenuItem implements MenuItem {
    @Override
    public ItemStack getItem(FarmPlayer farmPlayer) {
        return new ItemStack(Material.BARRIER);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.getWhoClicked().closeInventory(InventoryCloseEvent.Reason.PLUGIN);
    }
}

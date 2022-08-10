package tokyo.ramune.farmmc.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.player.FarmPlayer;

public interface MenuItem {

    ItemStack getItem(FarmPlayer farmPlayer);

    int getSlot();

    void onClick(InventoryClickEvent event);
}

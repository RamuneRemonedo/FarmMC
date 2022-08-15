package tokyo.ramune.farmmc.menu.menuItem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.menu.MenuItem;
import tokyo.ramune.farmmc.player.FarmPlayer;
import tokyo.ramune.farmmc.utility.ItemStackBuilder;

import java.util.ArrayList;

public class BackgroundMenuItem implements MenuItem {
    @Override
    public ItemStack getItem(FarmPlayer farmPlayer) {
        return new ItemStackBuilder()
                .setMaterial(Material.GRAY_STAINED_GLASS_PANE)
                .setLore(new ArrayList<>())
                .setTitle(ChatColor.GRAY.toString())
                .setAmount(1)
                .build();
    }

    @Override
    public void onClick(InventoryClickEvent event) {
    }
}

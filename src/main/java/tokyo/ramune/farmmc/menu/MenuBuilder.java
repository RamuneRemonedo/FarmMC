package tokyo.ramune.farmmc.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.player.FarmPlayer;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MenuBuilder {

    private String title = "MenuTitle";
    private int inventorySize = 27;
    private final ArrayList<MenuItem> menuItemList = new ArrayList<>();
    private Consumer<? super InventoryOpenEvent> onOpen = (event -> {});
    private Consumer<? super InventoryCloseEvent> onClose = (event -> {});

    public MenuBuilder() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    public ArrayList<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void addMenuItem(MenuItem menuItem) {
        if (menuItemList.contains(menuItem)) return;
        menuItemList.removeIf(item -> item.getSlot() == menuItem.getSlot());
        menuItemList.add(menuItem);
    }

    public void removeMenuItem(int menuSlot) {
        menuItemList.removeIf(menuItem -> menuItem.getSlot() == menuSlot);
    }

    public void removeMenuItem(MenuItem menuItem) {
        menuItemList.remove(menuItem);
    }

    public void setOnOpen(Consumer<? super InventoryOpenEvent> onOpen) {
        this.onOpen = onOpen;
    }

    public void setOnClose(Consumer<? super InventoryCloseEvent> onClose) {
        this.onClose = onClose;
    }

    public Menu build() {
        return new Menu() {
            @Override
            public Inventory getInventory(FarmPlayer farmPlayer) {
                Inventory inventory = Bukkit.createInventory(null, inventorySize, ChatColor.GOLD + ChatColor.YELLOW.toString() + ChatColor.RESET + title);
                getMenuItemList().forEach(menuItem -> inventory.setItem(menuItem.getSlot(), menuItem.getItem(farmPlayer)));
                return inventory;
            }

            @Override
            public String getTitle() {
                return ChatColor.GOLD + ChatColor.YELLOW.toString() + ChatColor.RESET + title;
            }

            @Override
            public int getInventorySize() {
                return inventorySize;
            }

            @NotNull
            @Override
            public ArrayList<MenuItem> getMenuItemList() {
                return menuItemList;
            }

            @Override
            public void onOpen(InventoryOpenEvent event) {
                onOpen.accept(event);
            }

            @Override
            public void onClose(InventoryCloseEvent event) {
                onClose.accept(event);
            }
        };
    }
}

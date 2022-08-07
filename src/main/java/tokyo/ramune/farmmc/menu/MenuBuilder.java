package tokyo.ramune.farmmc.menu;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MenuBuilder {

    private String title = "MenuTitle";
    private int inventorySize = 27;
    private ArrayList<MenuItem> menuItemList = new ArrayList<>();
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

    public void setMenuItemList(ArrayList<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    public void setOnOpen(Consumer<? super InventoryOpenEvent> onOpen) {
        this.onOpen = onOpen;
    }

    public void setOnClose(Consumer<? super InventoryCloseEvent> onClose) {
        this.onClose = onClose;
    }

    public Menu build() {
        Menu menu = new Menu() {
            Inventory inventory = Bukkit.createInventory(null, inventorySize, title);

            @Override
            public Inventory getInventory() {

                return inventory;
            }

            @Override
            public String getTitle() {
                return title;
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
        // TODO: 2022/08/07
        return menu;
    }
}

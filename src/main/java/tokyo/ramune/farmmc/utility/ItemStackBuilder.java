package tokyo.ramune.farmmc.utility;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemStackBuilder {

    String title = "";
    ArrayList<String> lore = new ArrayList<>();
    Material material = Material.GRASS_BLOCK;
    int amount = 1;


    public ItemStackBuilder() {

    }

    public ItemStackBuilder(String title, ArrayList<String> lore, Material material, int amount) {
        this.title = title;
        this.lore = lore;
        this.material = material;
        this.amount = amount;
    }

    public ItemStackBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ItemStackBuilder addLore(String loreLine) {
        lore.add(loreLine);
        return this;
    }

    public ItemStackBuilder setLore(ArrayList<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemStackBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public ItemStackBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(title);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
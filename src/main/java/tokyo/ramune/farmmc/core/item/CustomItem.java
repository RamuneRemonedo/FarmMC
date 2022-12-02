package tokyo.ramune.farmmc.core.item;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CustomItem {
    private final String id;
    private final Material material;
    private final String title;
    private final List<String> lore;
    private final HashMap<String, Object> customData;
    private final Consumer<PlayerInteractEvent> onClick;

    public CustomItem(@Nonnull String id,
                      @Nonnull Material material,
                      @Nonnull String title,
                      @Nonnull List<String> lore,
                      @Nonnull HashMap<String, Object> customData,
                      @Nonnull Consumer<PlayerInteractEvent> onClick) {
        this.id = id;
        this.material = material;
        this.title = title;
        this.lore = lore;
        this.customData = customData;
        this.onClick = onClick;
    }

    public String getId() {
        return id;
    }

    public Material getMaterial() {
        return material;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLore() {
        return lore;
    }

    public Consumer<PlayerInteractEvent> getOnClick() {
        return onClick;
    }

    public ItemStack getAsItemStack() {
        // Create ItemStack
        ItemStack customItem = new ItemStack(material);
        ItemMeta meta = customItem.getItemMeta();

        meta.setDisplayName(title);
        meta.setLore(lore);

        customItem.setItemMeta(meta);

        // Apply NBT
        customItem = NBTEditor.set(customItem, "FarmMC.item.id", id);

        for (Map.Entry<String, Object> valueEntry : customData.entrySet()) {
            customItem = NBTEditor.set(customItem, "FarmMC.item.custom." + valueEntry.getKey(), valueEntry.getValue());
        }

        return customItem;
    }
}

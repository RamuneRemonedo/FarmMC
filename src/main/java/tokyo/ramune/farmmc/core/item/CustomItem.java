package tokyo.ramune.farmmc.core.item;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tokyo.ramune.farmmc.core.menu.Glow;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CustomItem {
    private final String id;
    private final Material material;
    private final String title;
    private List<String> lore;
    private Consumer<PlayerInteractEvent> onClick = (event) -> {
    };
    private boolean allowEnchantGlow = false;

    public CustomItem(@Nonnull String id,
                      @Nonnull Material material,
                      @Nonnull String title) {
        this.id = id;
        this.material = material;
        this.title = title;

        CustomItemHandler.registerCustomItem(this);
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

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public void setLore(String lore) {
        this.lore = Arrays.asList(StringUtils.split(lore, "\n"));
    }

    public Consumer<PlayerInteractEvent> getOnClick() {
        return onClick;
    }

    public void setOnClick(Consumer<PlayerInteractEvent> onClick) {
        this.onClick = onClick;
    }

    public boolean isAllowEnchantGlow() {
        return allowEnchantGlow;
    }

    public void setAllowEnchantGlow(boolean allowEnchantGlow) {
        this.allowEnchantGlow = allowEnchantGlow;
    }

    public ItemStack getAsItemStack() {
        // Create ItemStack
        ItemStack customItem = new ItemStack(material);
        ItemMeta meta = customItem.getItemMeta();

        meta.setDisplayName(title);
        meta.setLore(lore);

        customItem.setItemMeta(meta);

        if (isAllowEnchantGlow())
            customItem.addUnsafeEnchantment(new Glow(), 1);

        return customItem;
    }
}

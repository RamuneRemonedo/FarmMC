package tokyo.ramune.farmmc.core.util;

import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tokyo.ramune.farmmc.core.menu.Glow;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemStackBuilder {
    private final Material material;
    private final String title;
    private final List<Component> lore;
    private final boolean addEnchantAnimation;

    public ItemStackBuilder(@Nonnull Material material, @Nullable String title, @Nullable String lore, boolean addEnchantAnimation) {
        this.material = material;
        this.title = title;
        this.lore = new ArrayList<>();
        if (lore != null) {
            for (String s : StringUtils.split(lore, "\n")) {
                this.lore.add(Component.text(s));
            }
        }
        this.addEnchantAnimation = addEnchantAnimation;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();

        meta.displayName(Component.text(title == null ? "" : title));
        meta.lore(lore);
        itemStack.setItemMeta(meta);

        if (addEnchantAnimation)
            itemStack.addUnsafeEnchantment(new Glow(), 1);

        return itemStack;
    }
}
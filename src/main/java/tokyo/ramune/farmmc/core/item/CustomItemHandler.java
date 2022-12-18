package tokyo.ramune.farmmc.core.item;

import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class CustomItemHandler {
    private static Map<String, CustomItem> registeredCustomItem = new HashMap<>();

    public static void initialize() {
        registeredCustomItem = new HashMap<>();
    }

    public static void registerCustomItem(@Nonnull CustomItem customItem) {
        registeredCustomItem.put(customItem.getId(), customItem);
    }

    @Nullable
    public static CustomItem getCustomItem(@Nonnull String id) {
        if (!isRegistered(id))
            return null;

        return registeredCustomItem.get(id);
    }

    @Nullable
    public static CustomItem toCustomItem(@Nonnull ItemStack itemStack) {
        for (Map.Entry<String, CustomItem> customItemEntry : registeredCustomItem.entrySet()) {
            if (customItemEntry.getValue().getAsItemStack().equals(itemStack))
                return customItemEntry.getValue();
        }
        return null;
    }

    public static boolean isRegistered(String id) {
        return registeredCustomItem.containsKey(id);
    }

    public static boolean isRegistered(CustomItem customItem) {
        return registeredCustomItem.containsValue(customItem);
    }
}

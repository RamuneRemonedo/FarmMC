package tokyo.ramune.farmmc.core.item;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class CustomItemHandler {
    private Map<String, CustomItem> registeredCustomItem = new HashMap<>();

    public void initialize() {
        registeredCustomItem = new HashMap<>();
    }

    public void registerCustomItem(@Nonnull CustomItem customItem) {
        registeredCustomItem.put(customItem.getId(), customItem);
    }

    @Nullable
    public CustomItem getCustomItem(@Nonnull String id) {
        if (!isRegistered(id))
            return null;

        return registeredCustomItem.get(id);
    }

    public boolean isRegistered(String id) {
        return registeredCustomItem.containsKey(id);
    }

    public boolean isRegistered(CustomItem customItem) {
        return registeredCustomItem.containsValue(customItem);
    }
}

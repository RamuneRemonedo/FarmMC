package tokyo.ramune.farmmc.core.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.core.item.CustomItem;
import tokyo.ramune.farmmc.core.item.CustomItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class PlayerUtil {

    @Nullable
    public static Player getNearestPlayer(@Nonnull Location location, double lastDistance, Player... ignorePlayers) {
        List<Player> ignorePlayersList = Arrays.asList(ignorePlayers);

        Player resultPlayer = null;

        for (Player player : location.getWorld().getPlayers()) {
            if (ignorePlayersList.contains(player))
                continue;

            double distance = player.getLocation().distance(location);
            if (distance < lastDistance) {
                lastDistance = distance;
                resultPlayer = player;
            }
        }

        return resultPlayer;
    }

    public static boolean hasCustomItem(@Nonnull Player player, @Nonnull CustomItem customItem) {
        for (ItemStack content : player.getInventory().getContents()) {
            if (content == null)
                continue;

            CustomItem invCustomItem = CustomItemHandler.toCustomItem(content);
            if (invCustomItem != null && invCustomItem.getId().equals(customItem.getId()))
                return true;
        }

        return false;
    }
}

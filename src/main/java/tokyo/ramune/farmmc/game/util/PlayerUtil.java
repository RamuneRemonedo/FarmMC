package tokyo.ramune.farmmc.game.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;

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
}

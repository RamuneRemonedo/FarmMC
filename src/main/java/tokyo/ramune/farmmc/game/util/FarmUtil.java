package tokyo.ramune.farmmc.game.util;

import org.bukkit.Location;
import org.bukkit.Material;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FarmUtil {
    public static List<Location> getHarvestedCropLocations(@Nonnull Location blockLocation) {
        List<Material> supportedMaterials =
                Arrays.asList(Material.BAMBOO,
                        Material.BAMBOO_SAPLING,
                        Material.KELP,
                        Material.KELP_PLANT,
                        Material.SUGAR_CANE,
                        Material.CACTUS
                );
        List<Location> locations = new ArrayList<>();
        locations.add(blockLocation);

        if (!supportedMaterials.contains(blockLocation.getBlock().getType()))
            return locations;

        Location currentBlockLocation = blockLocation;

        while (supportedMaterials.contains(currentBlockLocation.getBlock().getType())) {
            currentBlockLocation = currentBlockLocation.add(0, 1, 0);
            locations.add(currentBlockLocation);
        }
        return locations;
    }
}

package tokyo.ramune.farmmc.game.util;

import org.bukkit.Location;
import tokyo.ramune.farmmc.game.crop.CropArtificialHandler;
import tokyo.ramune.farmmc.game.crop.CropType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class FarmUtil {
    public static List<Location> getHarvestedCropLocations(@Nonnull Location blockLocation) {
        blockLocation = blockLocation.clone();

        CropType type = CropType.getCropType(blockLocation.getBlock().getType());

        if (type == null)
            return new ArrayList<>();

        List<Location> locations = new ArrayList<>();

        int h = 0;
        while (type.getCropTypes().contains(blockLocation.getBlock().getType())) {
            if (!type.getCropTypes().contains(blockLocation.getBlock().getType()))
                return locations;

            if (CropArtificialHandler.isArtificialPlaced(blockLocation)) {
                return locations;
            }

            if (h > 0 && !type.canPlacedOn()) {
                return locations;
            }

            locations.add(blockLocation.clone());
            blockLocation.add(0, 1, 0);
            h++;
        }
        return locations;
    }
}

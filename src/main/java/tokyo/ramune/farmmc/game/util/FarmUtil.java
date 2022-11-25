package tokyo.ramune.farmmc.game.util;

import org.bukkit.Location;
import tokyo.ramune.farmmc.game.crop.CropArtificialHandler;
import tokyo.ramune.farmmc.game.crop.CropType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class FarmUtil {
    public static List<Location> getHarvestedCropLocations(@Nonnull Location blockLocation) {
        CropType type = CropType.getCropType(blockLocation.getBlock().getType());

        if (type == null || !type.canPlacedOn())
            return new ArrayList<>();

        List<Location> locations = new ArrayList<>();
        Location currentBlockLocation = blockLocation;

        while (type.getCropTypes().contains(currentBlockLocation.getBlock().getType())) {
            if (!type.getCropTypes().contains(currentBlockLocation.getBlock().getType()))
                return locations;

            if (!CropArtificialHandler.isArtificialPlaced(currentBlockLocation))
                continue;

            locations.add(currentBlockLocation);
            currentBlockLocation = currentBlockLocation.add(0, 1, 0);
        }
        return locations;
    }
}

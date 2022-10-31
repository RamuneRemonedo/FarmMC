package tokyo.ramune.farmmc.game.crop;

import org.bukkit.entity.ArmorStand;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class FarmServantHandler {
    private static List<FarmServantEntity> servants = new ArrayList<>();

    public static void initialize() {
        servants = new ArrayList<>();
    }

    public static void removeAll() {
        servants.forEach(FarmServantEntity::remove);
        servants = new ArrayList<>();
    }

    public static void register(@Nonnull FarmServantEntity servant) {
        if (servants.contains(servant))
            return;

        servants.add(servant);
    }

    public static boolean isServant(ArmorStand armorStand) {
        for (FarmServantEntity servant : servants) {
            if (servant.getServantEntity().equals(armorStand))
                return true;
        }
        return false;
    }

    public static List<FarmServantEntity> getServants() {
        return servants;
    }
}
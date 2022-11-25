package tokyo.ramune.farmmc.game.servant;

import org.bukkit.entity.ArmorStand;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ServantHandler {
    private static List<ServantEntity> servants = new ArrayList<>();

    public static void initialize() {
        servants = new ArrayList<>();
    }

    public static void removeAll() {
        servants.forEach(ServantEntity::remove);
        servants = new ArrayList<>();
    }

    public static void register(@Nonnull ServantEntity servant) {
        if (servants.contains(servant))
            return;

        servants.add(servant);
    }

    public static boolean isServant(ArmorStand armorStand) {
        for (ServantEntity servant : servants) {
            if (servant.getServantEntity().equals(armorStand))
                return true;
        }
        return false;
    }

    public static List<ServantEntity> getServants() {
        return servants;
    }
}
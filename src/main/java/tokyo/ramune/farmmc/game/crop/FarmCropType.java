package tokyo.ramune.farmmc.game.crop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public enum FarmCropType {
    GRASS(World.Environment.NORMAL, true, List.of(Material.GRASS, Material.TALL_GRASS)),
    WHEAT(World.Environment.NORMAL, false, List.of(Material.WHEAT, Material.WHEAT_SEEDS)),
    BEETROOT(World.Environment.NORMAL, false, List.of(Material.BEETROOTS, Material.BEETROOT, Material.BEETROOT_SEEDS)),
    CARROT(World.Environment.NORMAL, false, List.of(Material.CARROTS, Material.CARROT)),
    POTATO(World.Environment.NORMAL, false, List.of(Material.POTATOES, Material.POTATO)),
    MELON(World.Environment.NORMAL, true, List.of(Material.MELON)),
    PUMPKIN(World.Environment.NORMAL, true, List.of(Material.PUMPKIN)),
    BAMBOO(World.Environment.NORMAL, true, List.of(Material.BAMBOO, Material.BAMBOO_SAPLING)),
    COCOA_BEAN(World.Environment.NORMAL, false, List.of(Material.COCOA, Material.COCOA_BEANS)),
    SUGAR_CANE(World.Environment.NORMAL, true, List.of(Material.SUGAR_CANE)),
    SWEET_BERRY(World.Environment.NORMAL, false, List.of(Material.SWEET_BERRIES, Material.SWEET_BERRY_BUSH)),
    CACTUS(World.Environment.NORMAL, true, List.of(Material.CACTUS)),
    MUSHROOM(World.Environment.NORMAL, true, List.of(Material.BROWN_MUSHROOM, Material.RED_MUSHROOM)),
    KELP(World.Environment.NORMAL, true, List.of(Material.KELP, Material.KELP_PLANT)),
    NETHER_WART(World.Environment.NETHER, true, List.of(Material.NETHER_WART)),
    CHORUS_FRUIT(World.Environment.THE_END, true, List.of(Material.CHORUS_FLOWER, Material.CHORUS_PLANT, Material.CHORUS_FRUIT));

    private final List<Material> cropTypes;
    private final World.Environment growEnvironment;
    private final boolean placeable;

    FarmCropType(World.Environment growEnvironment, boolean placeable, List<Material> cropTypes) {
        this.growEnvironment = growEnvironment;
        this.placeable = placeable;
        this.cropTypes = cropTypes;
    }

    @Nullable
    public static FarmCropType getCropType(Material material) {
        for (FarmCropType value : FarmCropType.values()) {
            if (value.getCropTypes().contains(material))
                return value;
        }

        return null;
    }

    public List<Material> getCropTypes() {
        return cropTypes;
    }

    public World.Environment getGrowEnvironment() {
        return growEnvironment;
    }

    public boolean isPlaceableAsPlayer() {
        return placeable;
    }

    public boolean isGrowableAt(@Nonnull Location blockLocation) {
        FarmCropType type = FarmCropType.valueOf(super.name());
        World.Environment environment = blockLocation.getWorld().getEnvironment();

        return type.getGrowEnvironment().equals(environment);
    }

    public int getExp() {
        return 1;
    }
}

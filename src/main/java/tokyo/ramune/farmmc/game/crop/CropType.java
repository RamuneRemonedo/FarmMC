package tokyo.ramune.farmmc.game.crop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public enum CropType {
    GRASS(World.Environment.NORMAL, true, true, Set.of(Material.GRASS, Material.TALL_GRASS)),
    WHEAT(World.Environment.NORMAL, false, false, Set.of(Material.WHEAT, Material.WHEAT_SEEDS)),
    BEETROOT(World.Environment.NORMAL, false, false, Set.of(Material.BEETROOTS, Material.BEETROOT, Material.BEETROOT_SEEDS)),
    CARROT(World.Environment.NORMAL, false, false, Set.of(Material.CARROTS, Material.CARROT)),
    POTATO(World.Environment.NORMAL, false, false, Set.of(Material.POTATOES, Material.POTATO)),
    MELON(World.Environment.NORMAL, true, false, Set.of(Material.MELON)),
    PUMPKIN(World.Environment.NORMAL, true, false, Set.of(Material.PUMPKIN)),
    BAMBOO(World.Environment.NORMAL, true, true, Set.of(Material.BAMBOO, Material.BAMBOO_SAPLING)),
    COCOA_BEAN(World.Environment.NORMAL, false, false, Set.of(Material.COCOA, Material.COCOA_BEANS)),
    SUGAR_CANE(World.Environment.NORMAL, true, true, Set.of(Material.SUGAR_CANE)),
    SWEET_BERRY(World.Environment.NORMAL, false, false, Set.of(Material.SWEET_BERRIES, Material.SWEET_BERRY_BUSH)),
    CACTUS(World.Environment.NORMAL, true, true, Set.of(Material.CACTUS)),
    MUSHROOM(World.Environment.NORMAL, true, false, Set.of(Material.BROWN_MUSHROOM, Material.RED_MUSHROOM)),
    KELP(World.Environment.NORMAL, true, true, Set.of(Material.KELP, Material.KELP_PLANT)),
    NETHER_WART(World.Environment.NETHER, true, false, Set.of(Material.NETHER_WART)),
    CHORUS_FRUIT(World.Environment.THE_END, true, true, Set.of(Material.CHORUS_FLOWER, Material.CHORUS_PLANT, Material.CHORUS_FRUIT));

    private final Set<Material> cropTypes;
    private final World.Environment growEnvironment;
    private final boolean replaceable, canPlacedOn;

    CropType(World.Environment growEnvironment, boolean replaceable, boolean canPlacedOn, Set<Material> cropTypes) {
        this.growEnvironment = growEnvironment;
        this.replaceable = replaceable;
        this.canPlacedOn = canPlacedOn;
        this.cropTypes = cropTypes;
    }

    @Nullable
    public static CropType getCropType(Material material) {
        for (CropType value : CropType.values()) {
            if (value.getCropTypes().contains(material))
                return value;
        }

        return null;
    }

    public Set<Material> getCropTypes() {
        return cropTypes;
    }

    public World.Environment getGrowEnvironment() {
        return growEnvironment;
    }

    public boolean isReplaceableAsPlayer() {
        return replaceable;
    }

    public boolean canPlacedOn() {
        return canPlacedOn;
    }

    public boolean isGrowableAt(@Nonnull Location blockLocation) {
        CropType type = CropType.valueOf(super.name());
        World.Environment environment = blockLocation.getWorld().getEnvironment();

        return type.getGrowEnvironment().equals(environment);
    }

    public int getExp() {
        return 1;
    }
}

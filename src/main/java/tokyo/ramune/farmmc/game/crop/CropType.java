package tokyo.ramune.farmmc.game.crop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public enum CropType {
    GRASS(World.Environment.NORMAL, true, true, List.of(Material.GRASS, Material.TALL_GRASS)),
    WHEAT(World.Environment.NORMAL, false, false, List.of(Material.WHEAT, Material.WHEAT_SEEDS)),
    BEETROOT(World.Environment.NORMAL, false, false, List.of(Material.BEETROOTS, Material.BEETROOT, Material.BEETROOT_SEEDS)),
    CARROT(World.Environment.NORMAL, false, false, List.of(Material.CARROTS, Material.CARROT)),
    POTATO(World.Environment.NORMAL, false, false, List.of(Material.POTATOES, Material.POTATO)),
    MELON(World.Environment.NORMAL, true, false, List.of(Material.MELON)),
    PUMPKIN(World.Environment.NORMAL, true, false, List.of(Material.PUMPKIN)),
    BAMBOO(World.Environment.NORMAL, true, true, List.of(Material.BAMBOO, Material.BAMBOO_SAPLING)),
    COCOA_BEAN(World.Environment.NORMAL, false, false, List.of(Material.COCOA, Material.COCOA_BEANS)),
    SUGAR_CANE(World.Environment.NORMAL, true, true, List.of(Material.SUGAR_CANE)),
    SWEET_BERRY(World.Environment.NORMAL, false, false, List.of(Material.SWEET_BERRIES, Material.SWEET_BERRY_BUSH)),
    CACTUS(World.Environment.NORMAL, true, true, List.of(Material.CACTUS)),
    MUSHROOM(World.Environment.NORMAL, true, false, List.of(Material.BROWN_MUSHROOM, Material.RED_MUSHROOM)),
    KELP(World.Environment.NORMAL, true, true, List.of(Material.KELP, Material.KELP_PLANT)),
    NETHER_WART(World.Environment.NETHER, true, false, List.of(Material.NETHER_WART)),
    CHORUS_FRUIT(World.Environment.THE_END, true, true, List.of(Material.CHORUS_FLOWER, Material.CHORUS_PLANT, Material.CHORUS_FRUIT));

    private final List<Material> cropTypes;
    private final World.Environment growEnvironment;
    private final boolean replaceable, canPlacedOn;

    CropType(World.Environment growEnvironment, boolean replaceable, boolean canPlacedOn, List<Material> cropTypes) {
        this.growEnvironment = growEnvironment;
        this.replaceable = replaceable;
        this.canPlacedOn = canPlacedOn;
        this.cropTypes = cropTypes;
    }

    public List<Material> getCropTypes() {
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

    @Nullable
    public static CropType getCropType(Material material) {
        for (CropType value : CropType.values()) {
            if (value.getCropTypes().contains(material))
                return value;
        }

        return null;
    }
}

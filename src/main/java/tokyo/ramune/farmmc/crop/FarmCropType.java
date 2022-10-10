package tokyo.ramune.farmmc.crop;

import com.google.common.collect.Range;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.List;

public enum FarmCropType {
    GRASS(
            List.of(Material.GRASS, Material.TALL_GRASS),
            List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.2)
    ),
    WHEAT(
            List.of(Material.WHEAT_SEEDS, Material.WHEAT_SEEDS),
            List.of(World.Environment.NORMAL),
            Range.closed(0.5, 0.8)
    ),
    BEETROOT(
            List.of(Material.BEETROOT, Material.BEETROOTS, Material.BEETROOT_SEEDS),
            List.of(World.Environment.NORMAL),
            Range.closed(-0.5, 0.2)
    ),
    CARROT(
            List.of(Material.CARROT, Material.CARROTS),
            List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.0)
    ),
    POTATO(
            List.of(Material.POTATO, Material.POTATOES, Material.POISONOUS_POTATO),
            List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.0)
    ),
    MELON(
            List.of(Material.MELON, Material.MELON_SEEDS, Material.MELON_STEM),
            List.of(World.Environment.NORMAL),
            Range.closed(0.9, 1.2)
    ),
    PUMPKIN(
            List.of(Material.PUMPKIN, Material.PUMPKIN_SEEDS, Material.PUMPKIN_STEM),
            List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.0)
    ),
    BAMBOO(
            List.of(Material.BAMBOO, Material.BAMBOO_SAPLING),
            List.of(World.Environment.NORMAL),
            Range.closed(0.8, 0.9)
    ),
    COCOA_BEAN(
            List.of(Material.COCOA, Material.COCOA_BEANS),
            List.of(World.Environment.NORMAL),
            Range.closed(0.9, 0.9)
    ),
    SUGAR_CANE(
            List.of(Material.SUGAR_CANE),
            List.of(World.Environment.NORMAL),
            Range.closed(0.8, 1.2)
    ),
    SWEET_BERRY(
            List.of(Material.SWEET_BERRIES, Material.SWEET_BERRY_BUSH),
            List.of(World.Environment.NORMAL),
            Range.closed(0.2, 0.3)
    ),
    CACTUS(
            List.of(Material.CACTUS),
            List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.2)
    ),
    MUSHROOM(
            List.of(Material.BROWN_MUSHROOM, Material.RED_MUSHROOM),
            List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.2)
    ),
    KELP(
            List.of(Material.KELP, Material.KELP_PLANT),
            List.of(World.Environment.NORMAL),
            Range.closed(0.5, 0.5)
    ),
    SEA_PICKLE(
            List.of(Material.SEA_PICKLE),
            List.of(World.Environment.NORMAL),
            Range.closed(0.5, 0.5)
    ),
    NETHER_WART(
            List.of(Material.NETHER_WART),
            List.of(World.Environment.NETHER),
            Range.closed(2.0, 2.0)
    ),
    CHORUS_FRUIT(
            List.of(Material.CHORUS_FRUIT, Material.CHORUS_FLOWER, Material.CHORUS_PLANT),
            List.of(World.Environment.THE_END),
            Range.closed(0.5, 0.5)
    );

    private final List<Material> cropTypes;
    private final List<World.Environment> growEnvironment;
    private final Range<Double> temperatureRange;

    FarmCropType(List<Material> cropTypes, List<World.Environment> growEnvironment, Range<Double> temperatureRange) {
        this.cropTypes = cropTypes;
        this.growEnvironment = growEnvironment;
        this.temperatureRange = temperatureRange;
    }

    public List<Material> getCropTypes() {
        return cropTypes;
    }

    public List<World.Environment> getGrowEnvironment() {
        return growEnvironment;
    }

    public Range<Double> getGrowTemperatureRange() {
        return temperatureRange;
    }
}

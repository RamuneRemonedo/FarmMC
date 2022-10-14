package tokyo.ramune.farmmc.crop;

import com.google.common.collect.Range;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.List;

public enum FarmCropType {
    GRASS(
            List.of(Material.GRASS, Material.TALL_GRASS),
            World.Environment.NORMAL,
            Range.closed(0.2, 1.2)
    ),
    WHEAT(
            List.of(Material.WHEAT_SEEDS),
            World.Environment.NORMAL,
            Range.closed(0.5, 0.8)
    ),
    BEETROOT(
            List.of(Material.BEETROOT, Material.BEETROOTS),
            World.Environment.NORMAL,
            Range.closed(-0.5, 0.2)
    ),
    CARROT(
            List.of(Material.CARROT, Material.CARROTS),
            World.Environment.NORMAL,
            Range.closed(0.2, 1.0)
    ),
    POTATO(
            List.of(Material.POTATO, Material.POTATOES, Material.POISONOUS_POTATO),
            World.Environment.NORMAL,
            Range.closed(0.2, 1.0)
    ),
    MELON(
            List.of(Material.MELON, Material.MELON_SEEDS, Material.MELON_STEM),
            World.Environment.NORMAL,
            Range.closed(0.9, 1.2)
    ),
    PUMPKIN(
            List.of(Material.PUMPKIN, Material.PUMPKIN_SEEDS, Material.PUMPKIN_STEM),
            World.Environment.NORMAL,
            Range.closed(0.2, 1.0)
    ),
    BAMBOO(
            List.of(Material.BAMBOO, Material.BAMBOO_SAPLING),
            World.Environment.NORMAL,
            Range.closed(0.8, 0.9)
    ),
    COCOA_BEAN(
            List.of(Material.COCOA, Material.COCOA_BEANS),
            World.Environment.NORMAL,
            Range.closed(0.9, 0.9)
    ),
    SUGAR_CANE(
            List.of(Material.SUGAR_CANE),
            World.Environment.NORMAL,
            Range.closed(0.8, 1.2)
    ),
    SWEET_BERRY(
            List.of(Material.SWEET_BERRIES, Material.SWEET_BERRY_BUSH),
            World.Environment.NORMAL,
            Range.closed(0.2, 0.3)
    ),
    CACTUS(
            List.of(Material.CACTUS),
            World.Environment.NORMAL,
            Range.closed(0.2, 1.2)
    ),
    MUSHROOM(
            List.of(Material.BROWN_MUSHROOM, Material.RED_MUSHROOM),
            World.Environment.NORMAL,
            Range.closed(0.2, 1.2)
    ),
    KELP(
            List.of(Material.KELP, Material.KELP_PLANT),
            World.Environment.NORMAL,
            Range.closed(0.5, 0.5)
    ),
    SEA_PICKLE(
            List.of(Material.SEA_PICKLE),
            World.Environment.NORMAL,
            Range.closed(0.5, 0.5)
    ),
    NETHER_WART(
            List.of(Material.NETHER_WART),
            World.Environment.NETHER,
            Range.closed(2.0, 2.0)
    ),
    CHORUS_FRUIT(
            List.of(Material.CHORUS_FRUIT, Material.CHORUS_FLOWER, Material.CHORUS_PLANT),
            World.Environment.THE_END,
            Range.closed(0.5, 0.5)
    );

    private final List<Material> cropTypes;
    private final World.Environment growEnvironment;
    private final Range<Double> temperatureRange;

    FarmCropType(List<Material> cropTypes, World.Environment growEnvironment, Range<Double> temperatureRange) {
        this.cropTypes = cropTypes;
        this.growEnvironment = growEnvironment;
        this.temperatureRange = temperatureRange;
    }

    public List<Material> getCropTypes() {
        return cropTypes;
    }

    public World.Environment getGrowEnvironment() {
        return growEnvironment;
    }

    public Range<Double> getGrowTemperatureRange() {
        return temperatureRange;
    }

    public int getExp() {
        FarmCropType type = FarmCropType.valueOf(super.name());
        Range<Double> temperatureRange = type.temperatureRange;

        int exp;

        exp = (int) Math.floor(temperatureRange.upperEndpoint() - temperatureRange.lowerEndpoint());

        System.out.println("Executed " + exp);

        switch (type.getGrowEnvironment()) {
            case NORMAL:
                exp += 1;
                break;

            case NETHER:
                exp += 2;
                break;

            case THE_END:
                exp += 3;
                break;
        }

        return exp;
    }
}

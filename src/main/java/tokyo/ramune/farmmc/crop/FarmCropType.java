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
            List.of(),
            List.of(World.Environment.NORMAL),
            Range.closed(0.5, 0.8)
    ),
    BEETROOT(
            List.of(),
            List.of(World.Environment.NORMAL),
            Range.closed(-0.5, 0.2)
    ),
    CARROT(
            List.of(),
            List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.0)
    ),
    POTATO(
            List.of(),
            List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.0)
    ),
    MELON(
            List.of(),
            List.of(World.Environment.NORMAL),
            Range.closed(0.9, 1.2)
    ),
    PUMPKIN(
            List.of(),
            List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.0)
    ),
    BAMBOO(
            List.of(),
            List.of(World.Environment.NORMAL),
            Range.closed(0.8, 0.9)
    ),
    CACAO_BEAN(
            List.of(),
            List.of(World.Environment.NORMAL),
            Range.closed(0.9, 0.9)
    ),
    SUGAR_CANE(
            List.of(),
            List.of(World.Environment.NORMAL),
            Range.closed(0.8, 1.2)
    ),
    SWEET_BERRY(
            List.of(),
            List.of(World.Environment.NORMAL),
            Range.closed(0.2, 0.3)
    ),
    CACTUS(
            List.of(),
            List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.2)
    ),
    MUSHROOM(
            List.of(),
            List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.2)
    ),
    KELP(
            List.of(),
            List.of(World.Environment.NORMAL),
            Range.closed(0.5, 0.5)
    ),
    SEA_PICKLE(
            List.of(),
            List.of(World.Environment.NORMAL),
            Range.closed(0.5, 0.5)
    ),
    NETHER_WART(
            List.of(),
            List.of(World.Environment.NETHER),
            Range.closed(2.0, 2.0)
    ),
    CHORUS_FRUIT(
            List.of(),
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


    public List<World.Environment> getGrowEnvironment() {
        return growEnvironment;
    }

    public Range<Double> getGrowTemperatureRange() {
        return temperatureRange;
    }
}

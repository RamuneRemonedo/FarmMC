package tokyo.ramune.farmmc.crop;

import com.google.common.collect.Range;
import org.bukkit.World;

import java.util.List;

public enum FarmCropType {
    GRASS(List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.2)),
    WHEAT(List.of(World.Environment.NORMAL),
            Range.closed(0.5, 0.8)),
    BEETROOT(List.of(World.Environment.NORMAL),
            Range.closed(-0.5, 0.2)),
    CARROT(List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.0)),
    POTATO(List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.0)),
    MELON(List.of(World.Environment.NORMAL),
            Range.closed(0.9, 1.2)),
    PUMPKIN(List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.0)),
    BAMBOO(List.of(World.Environment.NORMAL),
            Range.closed(0.8, 0.9)),
    CACAO_BEAN(List.of(World.Environment.NORMAL),
            Range.closed(0.9, 0.9)),
    SUGAR_CANE(List.of(World.Environment.NORMAL),
            Range.closed(0.8, 1.2)),
    SWEET_BERRY(List.of(World.Environment.NORMAL),
            Range.closed(0.2, 0.3)),
    CACTUS(List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.2)),
    MUSHROOM(List.of(World.Environment.NORMAL),
            Range.closed(0.2, 1.2)),
    KELP(List.of(World.Environment.NORMAL),
            Range.closed(0.5, 0.5)),
    SEA_PICKLE(List.of(World.Environment.NORMAL),
            Range.closed(0.5, 0.5)),
    NETHER_WART(List.of(World.Environment.NETHER),
            Range.closed(2.0, 2.0)),
    CHORUS_FRUIT(List.of(World.Environment.THE_END),
            Range.closed(0.5, 0.5));

    private final List<World.Environment> growEnvironment;
    private final Range<Double> temperatureRange;

    FarmCropType(List<World.Environment> growEnvironment, Range<Double> temperatureRange) {
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

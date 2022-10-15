package tokyo.ramune.farmmc.crop;

import com.google.common.collect.Range;
import org.bukkit.Material;
import org.bukkit.World;

import javax.annotation.Nullable;
import java.util.List;

public enum FarmCropType {
    GRASS(World.Environment.NORMAL, Range.closed(0.2, 1.2), List.of(Material.GRASS, Material.TALL_GRASS)),
    WHEAT(World.Environment.NORMAL, Range.closed(0.5, 0.8), List.of(Material.WHEAT)),
    BEETROOT(World.Environment.NORMAL, Range.closed(-0.5, 0.2), List.of(Material.BEETROOTS)),
    CARROT(World.Environment.NORMAL, Range.closed(0.2, 1.0), List.of(Material.CARROTS)),
    POTATO(World.Environment.NORMAL, Range.closed(0.2, 1.0), List.of(Material.POTATOES)),
    MELON(World.Environment.NORMAL, Range.closed(0.9, 1.2), List.of(Material.MELON)),
    PUMPKIN(World.Environment.NORMAL, Range.closed(0.2, 1.0), List.of(Material.PUMPKIN)),
    BAMBOO(World.Environment.NORMAL, Range.closed(0.8, 0.9), List.of(Material.BAMBOO, Material.BAMBOO_SAPLING)),
    COCOA_BEAN(World.Environment.NORMAL, Range.closed(0.9, 0.9), List.of(Material.COCOA, Material.COCOA_BEANS)),
    SUGAR_CANE(World.Environment.NORMAL, Range.closed(0.8, 1.2), List.of(Material.SUGAR_CANE)),
    SWEET_BERRY(World.Environment.NORMAL, Range.closed(0.2, 0.3), List.of(Material.SWEET_BERRIES, Material.SWEET_BERRY_BUSH)),
    CACTUS(World.Environment.NORMAL, Range.closed(0.2, 1.2), List.of(Material.CACTUS)),
    MUSHROOM(World.Environment.NORMAL, Range.closed(0.2, 1.2), List.of(Material.BROWN_MUSHROOM, Material.RED_MUSHROOM)),
    KELP(World.Environment.NORMAL, Range.closed(0.5, 0.5), List.of(Material.KELP)),
    SEA_PICKLE(World.Environment.NORMAL, Range.closed(0.5, 0.5), List.of(Material.SEA_PICKLE)),
    NETHER_WART(World.Environment.NETHER, Range.closed(2.0, 2.0), List.of(Material.NETHER_WART)),
    CHORUS_FRUIT(World.Environment.THE_END, Range.closed(0.5, 0.5), List.of(Material.CHORUS_FLOWER, Material.CHORUS_PLANT));

    private final List<Material> cropTypes;
    private final World.Environment growEnvironment;
    private final Range<Double> temperatureRange;

    FarmCropType(World.Environment growEnvironment, Range<Double> temperatureRange, List<Material> cropTypes) {
        this.growEnvironment = growEnvironment;
        this.temperatureRange = temperatureRange;
        this.cropTypes = cropTypes;
    }

    public List<Material> getCropTypes() {
        return cropTypes;
    }

    @Nullable
    public FarmCropType getCropType(Material material) {
        for (FarmCropType value : FarmCropType.values()) {
            if (value.getCropTypes().contains(material))
                return value;
        }

        return null;
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

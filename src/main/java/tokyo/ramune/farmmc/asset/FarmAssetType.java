package tokyo.ramune.farmmc.asset;

import org.bukkit.Material;

import java.util.HashMap;

public enum FarmAssetType {
    LOG,
    STONE,


    WHEAT,
    CARROT,
    BEETROOT,
    POTATO,
    SWEET_BERRIES,
    COCOA_BEANS,
    GLOW_BERRIES,
    MELON,
    PUMPKIN;

    private final HashMap<FarmAssetType, Integer> growSpeedHashMap = new HashMap<>() {{
        put(WHEAT, 10);
        put(CARROT, 10);
        put(BEETROOT, 10);
        put(POTATO, 10);
        put(SWEET_BERRIES, 10);
        put(COCOA_BEANS, 10);
        put(GLOW_BERRIES, 10);
        put(MELON, 10);
        put(PUMPKIN, 10);
    }};
    private final HashMap<FarmAssetType, Material> itemMaterialHashMap = new HashMap<>() {{
        put(WHEAT, Material.WHEAT);
        put(CARROT, Material.CARROT);
        put(BEETROOT, Material.BEETROOT);
        put(POTATO, Material.POTATO);
        put(SWEET_BERRIES, Material.SWEET_BERRIES);
        put(COCOA_BEANS, Material.COCOA_BEANS);
        put(GLOW_BERRIES, Material.GLOW_BERRIES);
        put(MELON, Material.MELON);
        put(PUMPKIN, Material.PUMPKIN);
    }};
    private final HashMap<FarmAssetType, Material> grownMaterialHashMap = new HashMap<>() {{
        put(WHEAT, Material.WHEAT_SEEDS);
        put(CARROT, Material.CARROTS);
        put(BEETROOT, Material.BEETROOTS);
        put(POTATO, Material.POTATOES);
        put(SWEET_BERRIES, Material.SWEET_BERRY_BUSH);
        put(COCOA_BEANS, Material.COCOA_BEANS);
        put(GLOW_BERRIES, Material.GLOW_BERRIES);
        put(MELON, Material.MELON);
        put(PUMPKIN, Material.PUMPKIN);
    }};
    private final HashMap<FarmAssetType, Material> childMaterialHashMap = new HashMap<>() {{
        put(WHEAT, Material.WHEAT_SEEDS);
        put(CARROT, Material.CARROTS);
        put(BEETROOT, Material.BEETROOTS);
        put(POTATO, Material.POTATOES);
        put(SWEET_BERRIES, Material.SWEET_BERRY_BUSH);
        put(COCOA_BEANS, Material.COCOA_BEANS);
        put(GLOW_BERRIES, Material.GLOW_BERRIES);
        put(MELON, Material.MELON_SEEDS);
        put(PUMPKIN, Material.PUMPKIN_SEEDS);
    }};

    public int getGrownSpeed() {
        return growSpeedHashMap.get(FarmAssetType.valueOf(super.name()));
    }

    public Material getMaterial() {
        return itemMaterialHashMap.get(FarmAssetType.valueOf(super.name()));
    }

    public Material getGrown() {
        return grownMaterialHashMap.get(FarmAssetType.valueOf(super.name()));
    }

    public Material getChild() {
        return childMaterialHashMap.get(FarmAssetType.valueOf(super.name()));
    }
}

package tokyo.ramune.farmmc.game.crop;

import org.bukkit.Material;
import tokyo.ramune.farmmc.core.language.Phase;

public enum CropTool {
    WOODEN_HOE(1, Phase.TOOL_WOODEN_HOE, Material.WOODEN_HOE),
    STONE_HOE(2, Phase.TOOL_STONE_HOE, Material.STONE_HOE);

    private final int strength;
    private final Phase title;
    private final Material material;

    CropTool(int strength, Phase title, Material material) {
        this.strength = strength;
        this.title = title;
        this.material = material;
    }

    public int getStrength() {
        return strength;
    }

    public Material getMaterial() {
        return material;
    }
}
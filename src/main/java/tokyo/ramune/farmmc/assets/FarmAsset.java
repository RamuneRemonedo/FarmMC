package tokyo.ramune.farmmc.assets;

import org.bukkit.generator.structure.Structure;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

public interface FarmAsset {
    AssetType getType();



    BlockVector getSize();
}

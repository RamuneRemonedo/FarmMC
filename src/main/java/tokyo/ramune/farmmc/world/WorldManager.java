package tokyo.ramune.farmmc.world;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.config.ConfigPhase;

public class WorldManager {

    public WorldManager() {
    }

    public World getAssetsWorld() {
        return Bukkit.getWorld(FarmMC.getConfigFile().getString(ConfigPhase.WORLD_ASSETS_WORLD_NAME));
    }

    public void loadAssetsWorld() {
        String assetsWorldName = FarmMC.getConfigFile().getString(ConfigPhase.WORLD_ASSETS_WORLD_NAME);
        if (getAssetsWorld() != null) return;
        FarmMC.getPlugin().getLogger().info("Loading assets world...");
        new WorldCreator(assetsWorldName)
                .type(WorldType.FLAT)
                .generateStructures(false)
                .environment(World.Environment.NORMAL)
                .createWorld();
        FarmMC.getPlugin().getLogger().info("Successfully loading assets world!");
    }
}

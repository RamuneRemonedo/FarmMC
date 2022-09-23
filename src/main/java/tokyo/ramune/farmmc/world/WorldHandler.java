package tokyo.ramune.farmmc.world;

import org.bukkit.*;
import tokyo.ramune.farmmc.FarmMC;

public class WorldHandler {
    public static World getTemplateWorld() {
        return Bukkit.getWorld(FarmMC.getConfigValue().WORLD_TEMPLATE_NAME);
    }

    public static World getGameWorld() {
        return Bukkit.getWorld(FarmMC.getConfigValue().WORLD_GAME_NAME);
    }

    public static void loadGameWorld() {
        String gameWorldName = FarmMC.getConfigValue().WORLD_GAME_NAME;
        if (Bukkit.getWorld(gameWorldName) != null) return;
        new WorldCreator(gameWorldName)
                .generateStructures(false)
                .type(WorldType.FLAT)
                .environment(World.Environment.NORMAL)
                .createWorld();
        FarmMC.getPlugin().getLogger().info(ChatColor.AQUA + "Loaded GameWorld!");
    }

    private static void loadCopiedGameWorld() {
        String gameWorldName = FarmMC.getConfigValue().WORLD_GAME_NAME;
        if (Bukkit.getWorld(gameWorldName) != null) return;
        new WorldCreator(gameWorldName)
                .generateStructures(false)
                .type(WorldType.FLAT)
                .environment(World.Environment.NORMAL)
                .createWorld();
        FarmMC.getPlugin().getLogger().info(ChatColor.AQUA + "Copied!");
    }

    public static void unloadGameWorld() {
        FarmMC.getPlugin().getLogger().info(ChatColor.RED + "Unloading game world...");
        String gameWorldName = FarmMC.getConfigValue().WORLD_GAME_NAME;
        Bukkit.unloadWorld(gameWorldName, false);
        FarmMC.getPlugin().getLogger().info(ChatColor.AQUA + "Unloaded GameWorld!");
    }

    public static void loadTemplateWorld() {
        String templateWorldName = FarmMC.getConfigValue().WORLD_TEMPLATE_NAME;
        if (Bukkit.getWorld(templateWorldName) != null) return;
        new WorldCreator(templateWorldName)
                .generateStructures(false)
                .type(WorldType.FLAT)
                .environment(World.Environment.NORMAL)
                .createWorld();
        FarmMC.getPlugin().getLogger().info(ChatColor.AQUA + "Loaded TemplateWorld!");
    }

    public static void unloadTemplateWorld() {
        FarmMC.getPlugin().getLogger().info(ChatColor.RED + "Unloading template world...");
        String templateWorldName = FarmMC.getConfigValue().WORLD_TEMPLATE_NAME;
        Bukkit.unloadWorld(templateWorldName, true);
        FarmMC.getPlugin().getLogger().info(ChatColor.AQUA + "Unloaded TemplateWorld!");
    }

    public static void resetGameWorld() {
        FarmMC.getPlugin().getLogger().info(ChatColor.RED + "--- Resetting game world... ---");
        unloadGameWorld();
        loadTemplateWorld();
        loadCopiedGameWorld();
        unloadTemplateWorld();
        FarmMC.getPlugin().getLogger().info(ChatColor.AQUA + "--- Game world has been reset. ---");
    }
}

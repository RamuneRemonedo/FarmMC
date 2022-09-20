package tokyo.ramune.farmmc.world;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import tokyo.ramune.farmmc.FarmMC;

public class WorldHandler {
    public static World getTemplateWorld() {
        loadTemplateWorld();
        return Bukkit.getWorld(FarmMC.getConfigValue().WORLD_TEMPLATE_NAME);
    }

    public static World getGameWorld() {
        loadGameWorld();
        return Bukkit.getWorld(FarmMC.getConfigValue().WORLD_GAME_NAME);
    }

    public static void loadGameWorld() {
        FarmMC.getPlugin().getLogger().info(ChatColor.RED + "Loading game world...");
        String gameWorldName = FarmMC.getConfigValue().WORLD_GAME_NAME;
        if (Bukkit.getWorld(gameWorldName) != null) return;
        new WorldCreator(gameWorldName)
                .generateStructures(false)
                .createWorld();
        FarmMC.getPlugin().getLogger().info(ChatColor.AQUA + "Loaded!");
    }

    private static void loadCopiedGameWorld() {
        FarmMC.getPlugin().getLogger().info(ChatColor.RED + "Copying game world...");
        String gameWorldName = FarmMC.getConfigValue().WORLD_GAME_NAME;
        if (Bukkit.getWorld(gameWorldName) != null) return;
        new WorldCreator(gameWorldName)
                .copy(getTemplateWorld())
                .generateStructures(false)
                .createWorld();
        FarmMC.getPlugin().getLogger().info(ChatColor.AQUA + "Copied!");
    }

    public static void unloadGameWorld() {
        FarmMC.getPlugin().getLogger().info(ChatColor.RED + "Unloading game world...");
        String gameWorldName = FarmMC.getConfigValue().WORLD_GAME_NAME;
        Bukkit.unloadWorld(gameWorldName, false);
        FarmMC.getPlugin().getLogger().info(ChatColor.AQUA + "Unloaded!");
    }

    public static void loadTemplateWorld() {
        FarmMC.getPlugin().getLogger().info(ChatColor.RED + "Loading template world...");
        String templateWorldName = FarmMC.getConfigValue().WORLD_TEMPLATE_NAME;
        if (Bukkit.getWorld(templateWorldName) != null) return;
        new WorldCreator(templateWorldName)
                .generateStructures(false)
                .createWorld();
        FarmMC.getPlugin().getLogger().info(ChatColor.AQUA + "Loaded!");
    }

    public static void unloadTemplateWorld() {
        FarmMC.getPlugin().getLogger().info(ChatColor.RED + "Unloading template world...");
        String templateWorldName = FarmMC.getConfigValue().WORLD_TEMPLATE_NAME;
        Bukkit.unloadWorld(templateWorldName, true);
        FarmMC.getPlugin().getLogger().info(ChatColor.AQUA + "Unloaded!");
    }

    public static void resetGameWorld() {
        FarmMC.getPlugin().getLogger().info(ChatColor.RED + "--- Resetting game world... ---");
        unloadGameWorld();
        loadTemplateWorld();
        loadCopiedGameWorld();
        FarmMC.getPlugin().getLogger().info(ChatColor.AQUA + "--- Game world has been reset. ---");
    }
}

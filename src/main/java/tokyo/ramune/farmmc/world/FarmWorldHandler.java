package tokyo.ramune.farmmc.world;

import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import tokyo.ramune.farmmc.FarmMC;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FarmWorldHandler {
    public static void copyTemplate() {
        if (!new File(FarmMC.getConfigValue().WORLD_TEMPLATE_NAME).exists()) {
            FarmMC.getPlugin().getLogger().info("Copy template skipped. Template World dose not exist.");
            return;
        }

        if (Bukkit.getWorlds().size() > 1) {
            throw new IllegalStateException("Couldn't copy template world. Worlds have already loaded!");
        }

        File templateDir = new File(FarmMC.getConfigValue().WORLD_TEMPLATE_NAME);
        File worldDir = new File("world");

        try {
            FileUtils.copyDirectory(templateDir, worldDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FarmMC.getPlugin().getLogger().info("Template world copied.");
    }

    public static void loadTemplate() {
        if (isLoadedTemplate())
            return;

        new WorldCreator(FarmMC.getConfigValue().WORLD_TEMPLATE_NAME)
                .environment(World.Environment.NORMAL)
                .type(WorldType.FLAT)
                .generateStructures(false)
                .createWorld();

        World templateWorld = Objects.requireNonNull(Bukkit.getWorld(FarmMC.getConfigValue().WORLD_TEMPLATE_NAME));

        templateWorld.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        templateWorld.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        templateWorld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
    }

    public static void unloadTemplate() {
        if (!isLoadedTemplate())
            return;

        Bukkit.unloadWorld(FarmMC.getConfigValue().WORLD_TEMPLATE_NAME, true);
    }

    public static boolean isLoadedTemplate() {
        return Bukkit.getWorld(FarmMC.getConfigValue().WORLD_TEMPLATE_NAME) != null;
    }
}

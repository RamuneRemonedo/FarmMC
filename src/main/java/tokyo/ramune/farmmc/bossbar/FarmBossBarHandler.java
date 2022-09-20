package tokyo.ramune.farmmc.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import tokyo.ramune.farmmc.FarmMC;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class FarmBossBarHandler {
    private static List<FarmBossBar> farmBossBars;
    private static BukkitTask updateTimer;

    public static void initialize() {
        if (farmBossBars != null)
            removeAll();
        farmBossBars = new ArrayList<>();
    }

    public static void create(@Nonnull FarmBossBar farmBossBar) {
        if (farmBossBars.contains(farmBossBar))
            return;

        Bukkit.createBossBar(
                farmBossBar.getNamespacedKey(),
                farmBossBar.getTitle(),
                farmBossBar.getBarColor(),
                farmBossBar.getBarStyle());

        farmBossBars.add(farmBossBar);
    }

    public static void remove(@Nonnull FarmBossBar farmBossBar) {
        if (!farmBossBars.contains(farmBossBar))
            return;

        farmBossBar.remove();
        Bukkit.removeBossBar(farmBossBar.getNamespacedKey());
        farmBossBars.remove(farmBossBar);
    }

    public static void removeAll() {
        farmBossBars.forEach(FarmBossBar::remove);
    }

    private static void startUpdateTimer() {
        if (updateTimer != null)
            updateTimer.cancel();

        updateTimer = Bukkit.getScheduler().runTaskTimer(FarmMC.getPlugin(), FarmBossBarHandler::update, 100, 100);
    }

    private static void update() {
        farmBossBars.forEach(FarmBossBar::update);
    }
}

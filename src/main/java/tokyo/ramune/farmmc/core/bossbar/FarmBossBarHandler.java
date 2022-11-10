package tokyo.ramune.farmmc.core.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.core.setting.CoreSettingHandler;

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
        startUpdateTimer();

        List<KeyedBossBar> bossBars = new ArrayList<>();
        Bukkit.getBossBars().forEachRemaining(bossBars::add);

        for (KeyedBossBar bossBar : bossBars)
            Bukkit.removeBossBar(bossBar.getKey());
    }

    public static void create(@Nonnull FarmBossBar farmBossBar) {
        if (farmBossBars.contains(farmBossBar))
            return;

        if (Bukkit.getServer().getBossBar(farmBossBar.getNamespacedKey()) == null) {
            Bukkit.createBossBar(
                    farmBossBar.getNamespacedKey(),
                    farmBossBar.getTitle(),
                    farmBossBar.getBarColor(),
                    farmBossBar.getBarStyle());
        }

        farmBossBar.initialize();

        farmBossBars.add(farmBossBar);
    }

    public static boolean isCreated(@Nonnull FarmBossBar farmBossBar) {
        return farmBossBars.contains(farmBossBar);
    }

    public static void remove(@Nonnull FarmBossBar farmBossBar) {
        if (!farmBossBars.contains(farmBossBar))
            return;

        farmBossBars.remove(farmBossBar);
    }

    public static void remove(@Nonnull FarmBossBar... farmBossBar) {
        for (FarmBossBar bossBar : farmBossBar) {
            remove(bossBar);
        }
    }

    public static void removeAll() {
        farmBossBars.forEach(farmBossBar -> {
            Bukkit.removeBossBar(farmBossBar.getNamespacedKey());
            farmBossBar.remove();
            farmBossBars.remove(farmBossBar);
        });
    }

    public static FarmBossBar[] getBossBar(@Nonnull Player player) {
        List<FarmBossBar> bossBars = new ArrayList<>();

        farmBossBars.forEach(bossBar -> {
            if (bossBar.getPlayer().equals(player))
                bossBars.add(bossBar);
        });

        return bossBars.toArray(new FarmBossBar[0]);
    }

    private static void startUpdateTimer() {
        if (updateTimer != null)
            updateTimer.cancel();

        updateTimer = Bukkit.getScheduler().runTaskTimer(FarmMC.getPlugin(), FarmBossBarHandler::update, 20, 20);
    }

    public static void updateVisible(@Nonnull Player player) {
        for (FarmBossBar bossBar : getBossBar(player)) {
            bossBar.setVisible(CoreSettingHandler.BOSSBAR_ENABLE.getData(player.getUniqueId()).getAsBoolean());
            bossBar.getAutoHide().update();
        }
    }

    private static void update() {
        farmBossBars.forEach(farmBossBar -> {
            if (!isCreated(farmBossBar))
                return;

            farmBossBar.update();
        });
    }
}

package tokyo.ramune.farmmc.maintenance.bossbar;

import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.core.bossbar.FarmBossBar;
import tokyo.ramune.farmmc.core.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;

import javax.annotation.Nonnull;

public class MaintenanceFarmBossBar implements FarmBossBar {
    private final Player player;

    public MaintenanceFarmBossBar(@Nonnull Player player) {
        this.player = player;
    }

    @NotNull
    @Override
    public BarColor getBarColor() {
        if (!isCreated())
            return BarColor.RED;

        return getBossBar().getColor().equals(BarColor.RED)
                ? BarColor.YELLOW : BarColor.RED;
    }

    @NotNull
    @Override
    public BarStyle getBarStyle() {
        return BarStyle.SOLID;
    }

    @NotNull
    @Override
    public String getTitle() {
        return FarmLanguageHandler.getPhase(player, Phase.BOSSBAR_MAINTENANCE_TITLE);
    }

    @Override
    public double getProgress() {
        return 1.0F;
    }

    @NotNull
    @Override
    public Player getPlayer() {
        return player;
    }

    @NotNull
    @Override
    public NamespacedKey getNamespacedKey() {
        return new NamespacedKey(FarmMC.getPlugin(), "maintenance." + player.getUniqueId());
    }

    @Override
    public void initialize() {
        FarmBossBar.super.initialize();
    }

    @Override
    public void update() {
        FarmBossBar.super.update();
    }

    @Override
    public void remove() {
    }
}

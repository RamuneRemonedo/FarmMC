package tokyo.ramune.farmmc.bossbar;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.FarmMC;

import javax.annotation.Nonnull;

public class MaintenanceFarmBossBar implements FarmBossBar {
    private final Player player;

    public MaintenanceFarmBossBar(@Nonnull Player player) {
        this.player = player;
    }

    @NotNull
    @Override
    public FarmBossBarType getType() {
        return FarmBossBarType.MAINTENANCE;
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
        return ChatColor.RED + "現在、メンテナンスモードでプラグインが実行されています!";
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
        String currentTitle = getBossBar().getTitle();
        String toTitle = getTitle();

        FarmBossBar.super.initialize();
    }

    @Override
    public void remove() {
    }
}
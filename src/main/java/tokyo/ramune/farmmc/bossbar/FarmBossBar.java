package tokyo.ramune.farmmc.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public interface FarmBossBar {

    default BossBar getBossBar() {
        if (Bukkit.getBossBar(getNamespacedKey()) == null)
            Bukkit.createBossBar(
                    getNamespacedKey(),
                    getTitle(),
                    getBarColor(),
                    getBarStyle());

        return Bukkit.getBossBar(getNamespacedKey());
    }

    @Nonnull
    FarmBossBarType getType();

    @Nonnull
    BarColor getBarColor();

    @Nonnull
    BarStyle getBarStyle();

    @Nonnull
    String getTitle();

    double getProgress();

    @Nonnull
    Player getPlayer();

    @Nonnull
    NamespacedKey getNamespacedKey();

    default void initialize() {
        getBossBar().addPlayer(getPlayer());
        getBossBar().setTitle(getTitle());
        getBossBar().setProgress(getProgress());
        getBossBar().setStyle(getBarStyle());
        getBossBar().setColor(getBarColor());
    }

    default void update() {
        getBossBar().setTitle(getTitle());
        getBossBar().setProgress(getProgress());
        getBossBar().setStyle(getBarStyle());
        getBossBar().setColor(getBarColor());
    }

    void remove();

    default boolean isCreated() {
        return FarmBossBarHandler.isCreated(this);
    }
}

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
            create();

        return Bukkit.getBossBar(getNamespacedKey());
    }
    @Nonnull FarmBossBarType getType();
    @Nonnull BarColor getBarColor();
    @Nonnull BarStyle getBarStyle();
    @Nonnull String getTitle();
    @Nonnull Player getPlayer();
    @Nonnull NamespacedKey getNamespacedKey();
    boolean isAutoHide();
    void setAutoHide(boolean autoHide);
    void initialize();
    void update();

    default void create() {
        FarmBossBarHandler.create(this);
    }

    default void remove() {
        FarmBossBarHandler.remove(this);
    }
}

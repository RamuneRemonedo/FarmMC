package tokyo.ramune.farmmc.bossbar;

import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import tokyo.ramune.farmmc.player.FarmPlayer;

public interface FarmBossBar {

    FarmPlayer getPlayer();

    void update();

    BossBar getBossBar();

    void setVisible(boolean visible);

    boolean isVisible();

    boolean isAutoHide();

    void setAutoHide(boolean isAutoHide);

    NamespacedKey getNamespacedKey();

    void remove();
}

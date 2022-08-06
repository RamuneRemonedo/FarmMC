package tokyo.ramune.farmmc.bossbar;

import org.bukkit.boss.BossBar;
import tokyo.ramune.farmmc.player.FarmPlayer;

public interface FarmBossBar {

    FarmPlayer getPlayer();

    BossBar getBossBar();

    void setVisible(boolean visible);

    boolean isVisible();
}

package tokyo.ramune.farmmc.bossbar;


import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public interface FarmBossBar {
    BossBar getBossBar();
    Player getPlayer();
    NamespacedKey getNamespacedKey();
    boolean isAutoHide();
    void setAutoHide(boolean autoHide);
    void initialize();
    void update();

    default void create() {
        BossBarHandler.create(this);
    }

    default void remove() {
        BossBarHandler.remove(this);
    }
}

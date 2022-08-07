package tokyo.ramune.farmmc.player;

import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.bossbar.FarmBossBar;
import tokyo.ramune.farmmc.cursor.Cursor;
import tokyo.ramune.farmmc.event.FarmEvent;

import java.util.ArrayDeque;

public interface FarmPlayer {
    Player getPlayer();

    ArrayDeque<FarmEvent> getFarmEventDeque();

    long getLevel();

    void setLevel(long level);

    long getExp();

    void setExp(long exp);

    long getRequireExp();

    long getCrystal();

    void setCrystal(long crystal);

    long getCoin();

    void setCoin(long coin);

    Cursor getCursor();

    FarmBossBar getExpBossBar();

    boolean isAllowFixedHeight();

    void setAllowFixedHeight(boolean isAllowFixedHeight);
}

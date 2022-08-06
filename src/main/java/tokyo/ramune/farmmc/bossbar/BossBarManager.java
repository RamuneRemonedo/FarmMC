package tokyo.ramune.farmmc.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.player.FarmPlayer;

import java.util.HashMap;
import java.util.Objects;

public class BossBarManager {

    private final HashMap<FarmPlayer, FarmBossBar> bossBarHashMap = new HashMap<>();

    public BossBarManager() {
    }

    public FarmBossBar getExpBossBar(FarmPlayer player) {
        if (!bossBarHashMap.containsKey(player)) {
            bossBarHashMap.put(player, createExpBossBar(player));
        }
        return bossBarHashMap.get(player);
    }

    private FarmBossBar createExpBossBar(FarmPlayer player) {
        if (Bukkit.getBossBar(new NamespacedKey(FarmMC.getPlugin(),
                "FarMC.bossbar.exp." + player.getPlayer().getUniqueId())) == null) {
            Bukkit.createBossBar(
                    new NamespacedKey(FarmMC.getPlugin(), "FarMC.bossbar.exp." + player.getPlayer().getUniqueId())
                    , "ExpBossBar"
                    , BarColor.BLUE
                    , BarStyle.SOLID);
        }
        return new FarmBossBar() {
            private final BossBar bossBar = Bukkit.getBossBar(new NamespacedKey(FarmMC.getPlugin(), "FarMC.bossbar.exp." + player.getPlayer().getUniqueId()));

            @Override
            public FarmPlayer getPlayer() {
                return player;
            }

            @Override
            public BossBar getBossBar() {
                return bossBar;
            }

            public void setVisible(boolean visible) {
                if (visible) {
                    if (isVisible()) return;
                    Objects.requireNonNull(bossBar).addPlayer(player.getPlayer());
                } else {
                    if (!isVisible()) return;
                    Objects.requireNonNull(bossBar).removePlayer(player.getPlayer());
                }
            }

            @Override
            public boolean isVisible() {
                return Objects.requireNonNull(bossBar).getPlayers().contains(player.getPlayer());
            }
        };
    }
}
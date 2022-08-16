package tokyo.ramune.farmmc.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.player.FarmPlayer;

import java.util.HashMap;
import java.util.Objects;

public class BossBarManager {

    private final HashMap<FarmPlayer, FarmBossBar> bossBarHashMap = new HashMap<>();

    public BossBarManager() {
        startUpdateTimer();
    }

    private void startUpdateTimer() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(FarmMC.getPlugin(), () -> bossBarHashMap.forEach((player, farmBossBar) -> farmBossBar.update()), 100, 100);
    }

    public FarmBossBar getExpBossBar(FarmPlayer player) {
        createExpBossBar(player);
        return bossBarHashMap.get(player);
    }

    public FarmBossBar getExpBossBar(Player player) {
        FarmPlayer farmPlayer = FarmMC.getFarmPlayerManager().getFarmPlayer(player);
        createExpBossBar(farmPlayer);
        return bossBarHashMap.get(farmPlayer);
    }

    public void createExpBossBar(FarmPlayer player) {
        if (bossBarHashMap.containsKey(player)) return;
        if (Bukkit.getBossBar(new NamespacedKey(FarmMC.getPlugin(),
                "bossBar.exp." + player.getPlayer().getUniqueId())) == null) {
            Bukkit.createBossBar(
                    new NamespacedKey(FarmMC.getPlugin(), "bossBar.exp." + player.getPlayer().getUniqueId())
                    , "FarmMC:bossBar.exp"
                    , BarColor.BLUE
                    , BarStyle.SOLID);
        }
        FarmBossBar bossBar = new FarmBossBar() {
            private final BossBar bossBar = Bukkit.getBossBar(new NamespacedKey(FarmMC.getPlugin(), "bossBar.exp." + player.getPlayer().getUniqueId()));
            private boolean isAutoHide = true;
            private BukkitTask autoHideTask = Bukkit.getScheduler().runTaskLaterAsynchronously(FarmMC.getPlugin(), () -> {
                if (isAutoHide) setVisible(false);
            }, 60);

            @Override
            public FarmPlayer getPlayer() {
                return player;
            }

            @Override
            public void update() {
                long value = player.getExp(), max = player.getRequireExp();
                double progress = (double) value / (double) max;
                if (progress > 1) progress = 1;
                if (Double.isNaN(progress)) return;
                if (Math.abs(progress - Objects.requireNonNull(bossBar).getProgress()) < 0.0000001) return;
                if (isAutoHide) {
                    setVisible(true);
                    if (!autoHideTask.isCancelled()) {
                        autoHideTask.cancel();
                        autoHideTask = Bukkit.getScheduler().runTaskLaterAsynchronously(FarmMC.getPlugin(), () -> {
                            if (isAutoHide) setVisible(false);
                        }, 60);
                    }
                }
                if (0.0 < progress && progress < 0.3) {
                    Objects.requireNonNull(bossBar).setColor(BarColor.BLUE);
                    Objects.requireNonNull(bossBar).setTitle(
                            ChatColor.GREEN + ChatColor.BOLD.toString() + "レベル" + ChatColor.WHITE + ": " + player.getLevel() + ChatColor.AQUA + ChatColor.BOLD + " 経験値" + ChatColor.WHITE + ": " + ChatColor.GRAY + player.getExp() + ChatColor.WHITE + ChatColor.BOLD + "/" + ChatColor.WHITE + max);
                } else if (0.3 < progress && progress < 0.6) {
                    Objects.requireNonNull(bossBar).setColor(BarColor.YELLOW);
                    Objects.requireNonNull(bossBar).setTitle(
                            ChatColor.GREEN + ChatColor.BOLD.toString() + "レベル" + ChatColor.WHITE + ": " + player.getLevel() + ChatColor.AQUA + ChatColor.BOLD + " 経験値" + ChatColor.WHITE + ": " + ChatColor.YELLOW + player.getExp() + ChatColor.WHITE + ChatColor.BOLD + "/" + ChatColor.WHITE + max);
                } else {
                    Objects.requireNonNull(bossBar).setColor(BarColor.GREEN);
                    Objects.requireNonNull(bossBar).setTitle(
                            ChatColor.GREEN + ChatColor.BOLD.toString() + "レベル" + ChatColor.WHITE + ": " + player.getLevel() + ChatColor.AQUA + ChatColor.BOLD + " 経験値" + ChatColor.WHITE + ": " + ChatColor.GREEN + player.getExp() + ChatColor.WHITE + ChatColor.BOLD + "/" + ChatColor.WHITE + max);
                }
                if (value < max && progress > 0) {
                    Objects.requireNonNull(bossBar).setProgress(progress);
                } else if (progress <= 0) {
                    Objects.requireNonNull(bossBar).setProgress(0.0);
                } else {
                    Objects.requireNonNull(bossBar).setProgress(1.0);
                }
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

            @Override
            public boolean isAutoHide() {
                return isAutoHide;
            }

            @Override
            public void setAutoHide(boolean isAutoHide) {
                this.isAutoHide = isAutoHide;
            }

            @Override
            public NamespacedKey getNamespacedKey() {
                return new NamespacedKey(FarmMC.getPlugin(),
                        "bossBar.exp." + player.getPlayer().getUniqueId());
            }

            @Override
            public void remove() {
                Bukkit.getServer().removeBossBar(getNamespacedKey());
            }
        };
        bossBar.update();
        bossBar.setVisible(true);
        if (!bossBarHashMap.containsKey(player)) {
            bossBarHashMap.put(player, bossBar);
        }
    }
}
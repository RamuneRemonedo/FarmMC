package tokyo.ramune.farmmc;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import tokyo.ramune.farmmc.core.CoreHandler;
import tokyo.ramune.farmmc.core.util.Notice;

public class ShutdownHandler {
    private int shutdownTimeMax = 60;
    private int shutdownTimeLeft = -1;
    private BukkitTask shutdownTimerTask;

    public ShutdownHandler() {
    }

    public int getShutdownTimeLeft() {
        return shutdownTimeLeft;
    }

    public int getShutdownTimeMax() {
        return shutdownTimeMax;
    }

    public void startShutdownTimer(int seconds) {
        shutdownTimeLeft = seconds;
        shutdownTimeMax = seconds;
        try {
            shutdownTimerTask.cancel();
        } catch (Exception ignored) {
        }

        Notice.noticeAutoRestart(seconds);

        shutdownTimerTask = Bukkit.getScheduler().runTaskTimer(CoreHandler.getInstance().getPlugin(), () -> {
            if (shutdownTimeLeft < 0) {
                cancelShutdownTimer();
                Bukkit.getServer().shutdown();
                return;
            }
            if (shutdownTimeLeft % 5 == 0)
                CoreHandler.getInstance().getPlugin().getLogger().info("This server will be shutdown in " + shutdownTimeLeft + "seconds left.");

            shutdownTimeLeft--;
        }, 20, 20);
    }

    public void cancelShutdownTimer() {
        try {
            shutdownTimerTask.cancel();
        } catch (Exception ignored) {
        }
        shutdownTimerTask = null;
        shutdownTimeLeft = -1;
    }
}

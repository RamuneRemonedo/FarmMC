package tokyo.ramune.farmmc.core.bossbar;

import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.Shutdown;
import tokyo.ramune.farmmc.core.CoreHandler;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;

public class ShutdownBossBar extends FarmBossBar {
    public ShutdownBossBar(Player player) {
        super(player, new NamespacedKey(CoreHandler.getInstance().getPlugin(), "shutdown." + player.getUniqueId()));
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
        return LanguageHandler.getPhase(getPlayer(), Phase.BOSSBAR_SHUTDOWN_TITLE)
                        .replace("{0}", String.valueOf(CoreHandler.getInstance().getShutdownHandler().getShutdownTimeLeft()));
    }

    @Override
    public double getProgress() {
        Shutdown shutdownHandler = CoreHandler.getInstance().getShutdownHandler();
        double progress = (double) shutdownHandler.getShutdownTimeLeft() / (double) shutdownHandler.getShutdownTimeMax();

        return progress < 0 ? 0 : progress;
    }

    @Override
    public void initialize() {
        getAutoHide().setAutoHide(false);

        super.initialize();
    }

    @Override
    public void update() {
        super.update();

        int leftTime = CoreHandler.getInstance().getShutdownHandler().getShutdownTimeLeft();
        if (leftTime < 10 && leftTime > 0) {
            setVisible(!isVisible());
        } else {
            setVisible(leftTime > 0);
        }
    }

    @Override
    public void remove() {
    }
}

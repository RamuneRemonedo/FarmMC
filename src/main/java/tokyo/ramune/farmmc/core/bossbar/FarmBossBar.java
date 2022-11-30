package tokyo.ramune.farmmc.core.bossbar;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class FarmBossBar {
    private final Player player;
    private final NamespacedKey namespacedKey;
    private final AutoHide autoHide;
    private boolean visible;
    private BarColor barColor;
    private BarStyle barStyle;
    private String title;
    private double progress;

    public FarmBossBar(Player player, NamespacedKey namespacedKey) {
        visible = true;
        barColor = BarColor.RED;
        barStyle = BarStyle.SOLID;
        title = "title";
        progress = 0.0;
        this.player = player;
        this.namespacedKey = namespacedKey;
        autoHide = new AutoHide(this);
    }

    public BossBar getBossBar() {
        if (Bukkit.getBossBar(getNamespacedKey()) == null)
            Bukkit.createBossBar(
                    getNamespacedKey(),
                    getTitle(),
                    getBarColor(),
                    getBarStyle());

        return Bukkit.getBossBar(getNamespacedKey());
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        getBossBar().setVisible(visible);
    }

    @Nonnull
    public BarColor getBarColor() {
        return barColor;
    }

    public void setBarColor(BarColor barColor) {
        this.barColor = barColor;
    }

    @Nonnull
    public BarStyle getBarStyle() {
        return barStyle;
    }

    public void setBarStyle(BarStyle barStyle) {
        this.barStyle = barStyle;
    }

    @Nonnull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    @Nonnull
    public Player getPlayer() {
        return player;
    }

    @Nonnull
    public NamespacedKey getNamespacedKey() {
        return namespacedKey;
    }

    public AutoHide getAutoHide() {
        return autoHide;
    }

    public void initialize() {
        getBossBar().addPlayer(getPlayer());
        getBossBar().setTitle(getTitle());
        getBossBar().setProgress(getProgress());
        getBossBar().setStyle(getBarStyle());
        getBossBar().setColor(getBarColor());

        getAutoHide().update();
    }

    public void update() {
        double originProgress = Math.floor(getBossBar().getProgress() * 10) / 10;
        String originTitle = getBossBar().getTitle();

        getBossBar().setTitle(getTitle());
        getBossBar().setProgress(getProgress());
        getBossBar().setStyle(getBarStyle());
        getBossBar().setColor(getBarColor());


        String title = getTitle();
        double progress = Math.floor(getProgress() * 10) / 10;

        if (!originTitle.equals(title)
                || originProgress != progress) {
            getAutoHide().update();
        }
    }

    public void remove() {

    }

    public boolean isCreated() {
        return FarmBossBarHandler.isCreated(this);
    }
}

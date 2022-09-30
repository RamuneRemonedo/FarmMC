package tokyo.ramune.farmmc.bossbar;

import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.player.PlayerStatus;
import tokyo.ramune.farmmc.utility.Chat;

import javax.annotation.Nonnull;

public class ExpFarmBossBar implements FarmBossBar {
    private final Player player;
    private final AutoHide autoHide = new AutoHide(this);
    private final PlayerStatus playerStatus;
    private long exp;

    public ExpFarmBossBar(@Nonnull Player player) {
        this.player = player;
        playerStatus = new PlayerStatus(player);
    }

    @NotNull
    @Override
    public FarmBossBarType getType() {
        return FarmBossBarType.EXP;
    }

    @NotNull
    @Override
    public BarColor getBarColor() {
        return BarColor.BLUE;
    }

    @NotNull
    @Override
    public BarStyle getBarStyle() {
        return BarStyle.SOLID;
    }

    @NotNull
    @Override
    public String getTitle() {
        String defaultTitle =
                Chat.replaceColor(
                        "&b&lExp &f:&f  " + playerStatus.getExp() + "&f / &7" + playerStatus.getRequireLevelUpExp(),
                        '&'
                );
        String title = defaultTitle;

        if (!isCreated())
            return defaultTitle;

        if (!getBossBar().getTitle().equals(defaultTitle)) {
            autoHide.update();
            long currentExp = playerStatus.getExp();

            if (exp < currentExp) {
                title = defaultTitle + Chat.replaceColor("&a +" + (currentExp - exp), '&');
            } else if (exp > currentExp) {
                Chat.replaceColor("&c -" + (exp - currentExp), '&');
            }
            exp = currentExp;
        }
        return title;
    }

    @Override
    public double getProgress() {
        double progress = (double) playerStatus.getExp() / (double) playerStatus.getRequireLevelUpExp();

        if (progress < 0.0) {
            return 0.0;
        } else if (progress > 1.0) {
            return 1.0;
        } else if (Double.isNaN(progress)) {
            return 0.0;
        }
        return progress;
    }

    @NotNull
    @Override
    public Player getPlayer() {
        return player;
    }

    @NotNull
    @Override
    public NamespacedKey getNamespacedKey() {
        return new NamespacedKey(FarmMC.getPlugin(), "exp." + player.getUniqueId());
    }

    @Override
    public void initialize() {
        autoHide.setAutoHide(true);
        autoHide.update();
        exp = playerStatus.getExp();
        FarmBossBar.super.initialize();
    }

    @Override
    public void remove() {

    }
}
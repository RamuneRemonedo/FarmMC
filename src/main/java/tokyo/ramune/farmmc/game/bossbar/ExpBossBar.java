package tokyo.ramune.farmmc.game.bossbar;

import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.core.bossbar.FarmBossBar;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.util.Chat;
import tokyo.ramune.farmmc.game.player.PlayerStatus;

public class ExpBossBar extends FarmBossBar {
    private long exp;

    public ExpBossBar(Player player) {
        super(player, new NamespacedKey(FarmMC.getPlugin(), "exp." + player.getUniqueId()));
        getAutoHide().setAutoHide(true);
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
        PlayerStatus playerStatus = new PlayerStatus(getPlayer());

        String defaultTitle =
                LanguageHandler.getPhase(getPlayer(), Phase.BOSSBAR_EXP_TITLE)
                        .replace("{0}", String.valueOf(playerStatus.getExp()))
                        .replace("{1}", String.valueOf(playerStatus.getRequireLevelUpExp()));
        String title = defaultTitle;

        if (!isCreated())
            return defaultTitle;

        if (!getBossBar().getTitle().equals(defaultTitle)) {
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
        PlayerStatus playerStatus = new PlayerStatus(getPlayer());

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

    @Override
    public void initialize() {
        PlayerStatus playerStatus = new PlayerStatus(getPlayer());

        getAutoHide().setAutoHide(true);
        exp = playerStatus.getExp();

        super.initialize();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void remove() {

    }
}

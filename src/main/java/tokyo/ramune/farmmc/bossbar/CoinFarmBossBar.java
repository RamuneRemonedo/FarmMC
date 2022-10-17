package tokyo.ramune.farmmc.bossbar;

import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.language.Phase;
import tokyo.ramune.farmmc.player.PlayerStatus;
import tokyo.ramune.farmmc.utility.Chat;

import javax.annotation.Nonnull;

public class CoinFarmBossBar implements FarmBossBar {
    private final Player player;
    private final AutoHide autoHide = new AutoHide(this);
    private long coin;

    public CoinFarmBossBar(@Nonnull Player player) {
        this.player = player;
    }

    @NotNull
    @Override
    public FarmBossBarType getType() {
        return FarmBossBarType.COIN;
    }

    @NotNull
    @Override
    public BarColor getBarColor() {
        return BarColor.YELLOW;
    }

    @NotNull
    @Override
    public BarStyle getBarStyle() {
        return BarStyle.SOLID;
    }

    @NotNull
    @Override
    public String getTitle() {
        PlayerStatus playerStatus = new PlayerStatus(player);

        String defaultTitle = FarmLanguageHandler.getPhase(player, Phase.BOSSBAR_COIN_TITLE)
                .replace("{0}", String.valueOf(playerStatus.getCoin()));
        String title = defaultTitle;

        if (!isCreated())
            return defaultTitle;

        if (!getBossBar().getTitle().equals(defaultTitle)) {
            autoHide.update();
            long currentCoin = playerStatus.getCoin();

            if (coin < currentCoin) {
                title = defaultTitle + Chat.replaceColor("&a +" + (currentCoin - coin), '&');
            } else if (coin > currentCoin) {
                title = defaultTitle + Chat.replaceColor("&c -" + (coin - currentCoin), '&');
            }
            coin = currentCoin;
        }
        return title;
    }

    @Override
    public double getProgress() {
        return 1.0F;
    }

    @NotNull
    @Override
    public Player getPlayer() {
        return player;
    }

    @NotNull
    @Override
    public NamespacedKey getNamespacedKey() {
        return new NamespacedKey(FarmMC.getPlugin(), "coin." + player.getUniqueId());
    }

    @Override
    public void initialize() {
        autoHide.setAutoHide(true);
        autoHide.update();
        coin = new PlayerStatus(getPlayer()).getCoin();
        FarmBossBar.super.initialize();
    }

    @Override
    public void remove() {

    }
}

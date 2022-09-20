package tokyo.ramune.farmmc.bossbar;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.player.PlayerStatus;

import javax.annotation.Nonnull;

public class CoinFarmBossBar implements FarmBossBar {
    private final Player player;
    private final AutoHide autoHide = new AutoHide(this);

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
        return ChatColor.YELLOW + "所持コイン " + ChatColor.WHITE + ":" + ChatColor.BOLD + "  " + new PlayerStatus(getPlayer()).getCoin();
    }

    @Override
    public float getProgress() {
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
        FarmBossBar.super.initialize();
    }

    @Override
    public void update() {
        String currentTitle = getBossBar().getTitle();
        String toTitle = getTitle();

        if (!currentTitle.equals(toTitle))
            autoHide.update();

        FarmBossBar.super.initialize();
    }
}

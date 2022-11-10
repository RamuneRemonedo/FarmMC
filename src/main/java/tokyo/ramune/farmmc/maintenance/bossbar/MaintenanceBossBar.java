package tokyo.ramune.farmmc.maintenance.bossbar;

import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.core.bossbar.FarmBossBar;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;

public class MaintenanceBossBar extends FarmBossBar {
    public MaintenanceBossBar(Player player) {
        super(player, new NamespacedKey(FarmMC.getPlugin(), "maintenance." + player.getUniqueId()));
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
        return LanguageHandler.getPhase(getPlayer(), Phase.BOSSBAR_MAINTENANCE_TITLE);
    }

    @Override
    public double getProgress() {
        return 1.0F;
    }

    @NotNull
    @Override
    public NamespacedKey getNamespacedKey() {
        return new NamespacedKey(FarmMC.getPlugin(), "maintenance." + getPlayer().getUniqueId());
    }

    @Override
    public void initialize() {
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

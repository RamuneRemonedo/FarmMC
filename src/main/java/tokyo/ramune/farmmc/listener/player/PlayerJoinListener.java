package tokyo.ramune.farmmc.listener.player;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.bossbar.CoinFarmBossBar;
import tokyo.ramune.farmmc.bossbar.ExpFarmBossBar;
import tokyo.ramune.farmmc.bossbar.FarmBossBarHandler;
import tokyo.ramune.farmmc.bossbar.MaintenanceFarmBossBar;
import tokyo.ramune.farmmc.player.PlayerStatus;
import tokyo.ramune.farmmc.utility.Notice;
import tokyo.ramune.farmmc.utility.PluginStatus;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Initialize player database & Update name
        PlayerStatus status = new PlayerStatus(player);
        status.initializeDatabasePlayer();
        status.updateName();

        // First join message
        if (!player.hasPlayedBefore())
            Notice.noticeFirstJoinPlayer(player);

        // Join message
        Notice.noticeJoinMessage(player);
        event.joinMessage(Component.text("\n"));


        // Add Coin & Exp BossBar
        if (FarmMC.getStatus().equals(PluginStatus.NORMAL)) {
            FarmBossBarHandler.create(new CoinFarmBossBar(player));
            FarmBossBarHandler.create(new ExpFarmBossBar(player));
        }

        // Add Maintenance BossBar
        if (FarmMC.getStatus().equals(PluginStatus.MAINTENANCE))
            FarmBossBarHandler.create(new MaintenanceFarmBossBar(player));
    }
}

package tokyo.ramune.farmmc.listener.player;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
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
import tokyo.ramune.farmmc.utility.Chat;
import tokyo.ramune.farmmc.utility.PluginStatus;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Send packet


        // Join message
        event.joinMessage(
                Component.text(
                        Chat.replaceColor(
                                "&7[&a&l+&7] &7" + player.getName() + " joined.",
                                '&'
                        )
                )
        );

        // Initialize player database & Update name
        PlayerStatus status = new PlayerStatus(player);
        status.initializeDatabasePlayer();
        status.updateName();

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

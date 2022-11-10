package tokyo.ramune.farmmc.game.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tokyo.ramune.farmmc.core.bossbar.FarmBossBarHandler;
import tokyo.ramune.farmmc.core.setting.CoreSettingHandler;
import tokyo.ramune.farmmc.core.sidebar.SideBarHandler;
import tokyo.ramune.farmmc.game.bossbar.ExpBossBar;
import tokyo.ramune.farmmc.game.player.PlayerStatus;
import tokyo.ramune.farmmc.game.sidebar.GameSideBar;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Initialize player database & Update name
        PlayerStatus status = new PlayerStatus(player);
        status.initializeDatabasePlayer();
        status.updateName();

        // Add exp BossBar
        FarmBossBarHandler.create(new ExpBossBar(player));

        // Set sidebar
        GameSideBar sideBar = new GameSideBar(player);
        SideBarHandler.setSideBar(sideBar);

        if (CoreSettingHandler.SIDEBAR_ENABLE.getData(player.getUniqueId()).getAsBoolean()) {
            sideBar.show();
        } else {
            sideBar.hide();
        }
    }
}

package tokyo.ramune.farmmc.core.listener.player;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tokyo.ramune.farmmc.core.FarmCoreHandler;
import tokyo.ramune.farmmc.core.config.CoreConfig;
import tokyo.ramune.farmmc.core.sidebar.CoreSideBar;
import tokyo.ramune.farmmc.core.sidebar.FarmSideBarHandler;
import tokyo.ramune.farmmc.core.utility.Notice;

import java.util.Calendar;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // First join message
        if (!player.hasPlayedBefore())
            Notice.noticeFirstJoinPlayer(player);

        // Join message
        Notice.noticeJoinMessage(player);
        event.joinMessage(Component.empty());

        // Force spawn teleport
        CoreConfig coreConfig = FarmCoreHandler.getInstance().getCoreConfig();
        if (coreConfig.FORCE_SPAWN_TELEPORT)
            player.teleport(coreConfig.SPAWN_LOCATION);

        // Log
        Calendar cal = Calendar.getInstance();
        FarmCoreHandler.getInstance().getPlugin()
                .getLogger().info("[" + (cal.get(Calendar.MONTH) + 1) + "mon " + cal.get(Calendar.DAY_OF_MONTH) + "d " + cal.get(Calendar.HOUR_OF_DAY) + "h]"
                        + player.getName());

        // Test farmSideBar
        CoreSideBar sideBar = new CoreSideBar(player);
        sideBar.show();
        FarmSideBarHandler.registerSideBar(sideBar);
    }
}

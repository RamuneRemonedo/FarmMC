package tokyo.ramune.farmmc.core.listener.player;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tokyo.ramune.farmmc.core.CoreHandler;
import tokyo.ramune.farmmc.core.auth.AuthHandler;
import tokyo.ramune.farmmc.core.bossbar.FarmBossBarHandler;
import tokyo.ramune.farmmc.core.bossbar.ShutdownBossBar;
import tokyo.ramune.farmmc.core.config.CoreConfig;
import tokyo.ramune.farmmc.core.sidebar.CoreSideBar;
import tokyo.ramune.farmmc.core.sidebar.SideBar;
import tokyo.ramune.farmmc.core.sidebar.SideBarHandler;
import tokyo.ramune.farmmc.core.util.Notice;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Objects;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Auth
        AuthHandler.get(player.getUniqueId())
                .setLastLoginDate(LocalDateTime.now())
                .setLastLoginAddress(Objects.requireNonNull(player.getAddress()).getHostName())
                .apply();

        // First join message
        if (!player.hasPlayedBefore())
            Notice.noticeFirstJoinPlayer(player);

        // Join message
        Notice.noticeJoinMessage(player);
        event.joinMessage(Component.empty());

        // Force spawn teleport
        CoreConfig coreConfig = CoreHandler.getInstance().getCoreConfig();
        if (coreConfig.FORCE_SPAWN_TELEPORT)
            player.teleport(coreConfig.SPAWN_LOCATION);

        // Log
        Calendar cal = Calendar.getInstance();
        CoreHandler.getInstance().getPlugin()
                .getLogger().info("[" + (cal.get(Calendar.MONTH) + 1) + "mon " + cal.get(Calendar.DAY_OF_MONTH) + "d " + cal.get(Calendar.HOUR_OF_DAY) + "h]"
                        + player.getName());

        // SideBar
        SideBar sideBar = new CoreSideBar(player);
        SideBarHandler.setSideBar(sideBar);
        sideBar.show();

        // Shutdown bossBar
        FarmBossBarHandler.create(new ShutdownBossBar(player));
    }
}

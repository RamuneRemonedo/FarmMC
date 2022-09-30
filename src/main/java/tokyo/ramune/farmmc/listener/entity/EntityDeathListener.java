package tokyo.ramune.farmmc.listener.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import tokyo.ramune.farmmc.player.PlayerStatus;

public class EntityDeathListener implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity dead = event.getEntity();

        if (dead.getKiller() == null)
            return;

        Player killer = dead.getKiller();

        PlayerStatus playerStatus = new PlayerStatus(killer);
        playerStatus.setExp(playerStatus.getExp() + 1);
    }
}

package tokyo.ramune.farmmc.game.listener.entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

    // Damaged by Player
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
    }

    // Damaged by Entity
    @EventHandler
    public void onEntityDamageByPlayer(EntityDamageByEntityEvent event) {
    }
}

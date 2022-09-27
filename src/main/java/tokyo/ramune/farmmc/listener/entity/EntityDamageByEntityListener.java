package tokyo.ramune.farmmc.listener.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import tokyo.ramune.farmmc.player.PlayerStatus;

public class EntityDamageByEntityListener implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!event.getDamager().getType().equals(EntityType.PLAYER))
            return;

        Player player = (Player) event.getDamager();
        Entity entity = event.getEntity();

        if (event.getEntityType().equals(EntityType.VILLAGER))
            return;

        if (player.getLocation().distance(entity.getLocation())
                > new PlayerStatus(player).getAttackDistance())
            event.setCancelled(true);


    }
}

package tokyo.ramune.farmmc.listener.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import tokyo.ramune.farmmc.entity.FarmEntityType;
import tokyo.ramune.farmmc.player.PlayerStatus;

public class EntityDamageByEntityListener implements Listener {

    // Damaged by Player
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!event.getDamager().getType().equals(EntityType.PLAYER))
            return;

        Player sourcePlayer = (Player) event.getDamager();
        Entity targetEntity = event.getEntity();

        FarmEntityType farmEntityType = FarmEntityType.cast(targetEntity.getType());

        if (farmEntityType == null)
            return;

        if (sourcePlayer.getLocation().distance(targetEntity.getLocation())
                > new PlayerStatus(sourcePlayer).getAttackDistance())
            event.setCancelled(true);

        double damage = event.getDamage() - farmEntityType.getDefencePower();

        sourcePlayer.sendMessage(event.getDamage() - farmEntityType.getDefencePower() + " damaged to entity");

        if (damage <= 0) {
            event.setCancelled(true);
            return;
        }

        event.setDamage(damage);
    }

    // Damaged by Entity
    @EventHandler
    public void onEntityDamageByPlayer(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType().equals(EntityType.PLAYER)
                || !event.getEntityType().equals(EntityType.PLAYER))
            return;

        Player targetPlayer = (Player) event.getEntity();
        Entity sourceEntity = event.getDamager();

        FarmEntityType farmEntityType = FarmEntityType.cast(sourceEntity.getType());

        if (farmEntityType == null)
            return;

        targetPlayer.sendMessage(event.getDamage() + "  damage from entity");

        event.setDamage(farmEntityType.getAttackPower());
    }
}

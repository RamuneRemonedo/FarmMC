package tokyo.ramune.farmmc.listener.player;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (!event.getEntityType().equals(EntityType.PLAYER)) return;
        event.setFoodLevel(20);
    }

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (!event.getEntityType().equals(EntityType.PLAYER)
                || !event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) return;
        event.setCancelled(true);
    }
}
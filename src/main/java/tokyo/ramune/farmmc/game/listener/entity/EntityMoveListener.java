package tokyo.ramune.farmmc.game.listener.entity;

import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EntityMoveListener implements Listener {
    @EventHandler
    public void onEntityMove(EntityMoveEvent event) {
        LivingEntity entity = event.getEntity();

        if (!(entity instanceof Monster)) {
        }


    }
}

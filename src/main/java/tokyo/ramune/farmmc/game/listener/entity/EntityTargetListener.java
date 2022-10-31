package tokyo.ramune.farmmc.game.listener.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class EntityTargetListener implements Listener {
    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {
        if (event.getTarget() == null
                || !event.getTarget().getType().equals(EntityType.PLAYER))
            return;


    }
}

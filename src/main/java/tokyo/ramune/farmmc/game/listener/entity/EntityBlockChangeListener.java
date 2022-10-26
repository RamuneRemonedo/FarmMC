package tokyo.ramune.farmmc.game.listener.entity;

import com.destroystokyo.paper.MaterialSetTag;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import tokyo.ramune.farmmc.FarmMC;

public class EntityBlockChangeListener implements Listener {
    @EventHandler
    public void onEntityBlockChange(EntityChangeBlockEvent event) {
        if (!event.getEntityType().equals(EntityType.VILLAGER))
            return;

        if (!MaterialSetTag.CROPS.getValues().contains(event.getTo()))
            return;

        Bukkit.getScheduler().runTaskLater(FarmMC.getPlugin(), () -> event.getBlock().setType(Material.AIR), 5);
    }
}

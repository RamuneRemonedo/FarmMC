package tokyo.ramune.farmmc.listener.cursor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.cursor.Cursor;
import tokyo.ramune.farmmc.event.cursor.CursorClickEvent;
import tokyo.ramune.farmmc.player.FarmPlayer;

import java.util.Objects;

public class CursorListener implements Listener {

    @EventHandler
    public void onEntityRemove(EntityDeathEvent event) {
        if (!event.getEntityType().equals(EntityType.ARMOR_STAND)) return;
        if (FarmMC.getCursorManager().castCursor(event.getEntity()) == null) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Block targetBlock = event.getPlayer().getTargetBlock(5);

        if (targetBlock == null) return;
        Cursor cursor = Objects.requireNonNull(FarmMC.getCursorManager().getCursor(player));
        ArmorStand cursorEntity = cursor.getCursorEntity();

        if (targetBlock.getType().equals(Material.AIR) && cursor.isVisible()) {
            cursor.setVisible(false);
        } else if (!targetBlock.getType().equals(Material.AIR) && !cursor.isVisible()) {
            cursor.setVisible(true);
        }
        try {
            Objects.requireNonNull(cursorEntity).setGlowing(!targetBlock.getLocation().clone().add(0, 1, 0).getBlock().getType().equals(Material.AIR));
        } catch (Exception ignored) {
        }
        Location loc = targetBlock.getLocation().clone();
        loc.setYaw(player.getLocation().getYaw() + 90);

        cursor.setLocation(loc);
        cursor.teleport();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        FarmPlayer player = FarmMC.getFarmPlayerManager().getFarmPlayer(event.getPlayer());
        Action action = event.getAction();

        if (!player.getCursor().isVisible()) return;
        if (Objects.equals(event.getHand(), EquipmentSlot.OFF_HAND)) return;
        CursorClickEvent cursorClickEvent = new CursorClickEvent(player.getCursor(), action);
        Bukkit.getPluginManager().callEvent(cursorClickEvent);
        event.setCancelled(cursorClickEvent.isCancelled());
    }
}

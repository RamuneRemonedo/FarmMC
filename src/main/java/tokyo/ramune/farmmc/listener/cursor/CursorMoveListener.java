package tokyo.ramune.farmmc.listener.cursor;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;
import tokyo.ramune.farmmc.cursor.Cursor;
import tokyo.ramune.farmmc.event.cursor.CursorMoveEvent;

import java.util.Objects;

public class CursorMoveListener implements Listener {

    @EventHandler
    public void onCursorMove(CursorMoveEvent event) {
        Location playerLocation = event.getPlayer().getPlayer().getLocation();
        Location to = event.getTo();
        Cursor cursor = event.getPlayer().getCursor();
        ArmorStand cursorEntity = Objects.requireNonNull(cursor.getCursorEntity());

        if (playerLocation.distance(to) > 10
                && cursorEntity.isSmall()) {
            cursorEntity.setSmall(false);
            cursor.setPositionCorrection(cursor.getDefaultPositionCorrection().add(new Vector(0, -1, 0)));
        }
        if (playerLocation.distance(to) < 10
                && !cursorEntity.isSmall()) {
            cursorEntity.setSmall(true);
            cursor.resetPositionCorrection();
        }
    }
}

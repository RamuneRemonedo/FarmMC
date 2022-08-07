package tokyo.ramune.farmmc.listener.cursor;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;
import tokyo.ramune.farmmc.cursor.Cursor;
import tokyo.ramune.farmmc.cursor.event.CursorMoveEvent;

public class CursorMoveListener implements Listener {

    @EventHandler
    public void onCursorMove(CursorMoveEvent event) {
        Location playerLocation = event.getPlayer().getPlayer().getLocation();
        Location to = event.getTo();
        Cursor cursor = event.getPlayer().getCursor();

        if (playerLocation.distance(to) > 10
                && cursor.getCursorEntity().isSmall()) {
            cursor.getCursorEntity().setSmall(false);
            cursor.setPositionCorrection(cursor.getDefaultPositionCorrection().add(new Vector(0, -1, 0)));
        }
        if (playerLocation.distance(to) < 10
                && !cursor.getCursorEntity().isSmall()) {
            cursor.getCursorEntity().setSmall(true);
            cursor.resetPositionCorrection();
        }
    }
}

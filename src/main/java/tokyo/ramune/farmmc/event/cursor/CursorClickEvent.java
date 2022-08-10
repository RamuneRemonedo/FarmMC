package tokyo.ramune.farmmc.event.cursor;

import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.cursor.Cursor;
import tokyo.ramune.farmmc.player.FarmPlayer;

import javax.annotation.Nonnull;

public class CursorClickEvent  extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;

    private final Cursor cursor;
    private final Action action;

    public CursorClickEvent(@Nonnull Cursor cursor, @Nonnull Action action) {
        this.cursor = cursor;
        this.action = action;
    }

    public FarmPlayer getPlayer() {
        return FarmMC.getFarmPlayerManager().getFarmPlayer(getCursor().getPlayer());
    }

    public Cursor getCursor() {
        return cursor;
    }

    public Location getLocation() {
        return cursor.getLocation();
    }

    public Action getAction() {
        return action;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

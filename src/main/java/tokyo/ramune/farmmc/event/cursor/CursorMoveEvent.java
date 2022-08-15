package tokyo.ramune.farmmc.event.cursor;

import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.cursor.Cursor;
import tokyo.ramune.farmmc.player.FarmPlayer;

import javax.annotation.Nonnull;

public class CursorMoveEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private final Cursor cursor;
    private final Location from, to;

    private boolean cancelled;

    public CursorMoveEvent(@Nonnull Cursor cursor, @Nonnull Location from, @Nonnull Location to) {
        this.cursor = cursor;
        this.from = from;
        this.to = to;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public FarmPlayer getPlayer() {
        return FarmMC.getFarmPlayerManager().getFarmPlayer(cursor.getPlayer());
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}

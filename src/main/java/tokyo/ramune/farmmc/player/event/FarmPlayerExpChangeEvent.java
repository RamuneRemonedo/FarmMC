package tokyo.ramune.farmmc.player.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tokyo.ramune.farmmc.player.FarmPlayer;

public class FarmPlayerExpChangeEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;

    private final FarmPlayer player;
    private final long from, to;

    public FarmPlayerExpChangeEvent(FarmPlayer player, long from, long to) {
        this.player = player;
        this.from = from;
        this.to = to;
    }

    public FarmPlayer getPlayer() {
        return player;
    }

    public long getFrom() {
        return from;
    }

    public long getTo() {
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

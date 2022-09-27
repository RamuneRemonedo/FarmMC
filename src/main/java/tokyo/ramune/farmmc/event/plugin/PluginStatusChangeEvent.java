package tokyo.ramune.farmmc.event.plugin;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import tokyo.ramune.farmmc.utility.PluginStatus;

import javax.annotation.Nonnull;

public class PluginStatusChangeEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;

    private final PluginStatus from, to;

    public PluginStatusChangeEvent(@Nonnull PluginStatus from, @Nonnull PluginStatus to) {
        this.from = from;
        this.to = to;
    }

    public PluginStatus getFrom() {
        return from;
    }

    public PluginStatus getTo() {
        return to;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public @Nonnull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

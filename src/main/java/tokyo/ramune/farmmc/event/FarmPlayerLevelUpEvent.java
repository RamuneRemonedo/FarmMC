package tokyo.ramune.farmmc.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import tokyo.ramune.farmmc.player.PlayerStatus;

import javax.annotation.Nonnull;

public class FarmPlayerLevelUpEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final long from, to;

    private boolean cancelled;

    public FarmPlayerLevelUpEvent(@Nonnull Player player, long from, long to) {
        this.player = player;
        this.from = from;
        this.to = to;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerStatus getStatus() {
        return new PlayerStatus(player);
    }

    public long getFrom() {
        return from;
    }

    public long getTo() {
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

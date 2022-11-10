package tokyo.ramune.farmmc.core.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import tokyo.ramune.farmmc.game.player.PlayerStatus;

import javax.annotation.Nonnull;

public class FarmMenuCloseEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Inventory inventory;

    private boolean cancelled;

    public FarmMenuCloseEvent(@Nonnull Player player, Inventory inventory) {
        this.player = player;
        this.inventory = inventory;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerStatus getStatus() {
        return new PlayerStatus(player);
    }

    public Inventory getInventory() {
        return inventory;
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
}

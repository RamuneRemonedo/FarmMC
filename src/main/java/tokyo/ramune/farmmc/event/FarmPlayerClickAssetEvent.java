package tokyo.ramune.farmmc.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import tokyo.ramune.farmmc.asset.FarmAsset;
import tokyo.ramune.farmmc.player.PlayerStatus;

import javax.annotation.Nonnull;

public class FarmPlayerClickAssetEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final FarmAsset farmAsset;

    private boolean cancelled;

    public FarmPlayerClickAssetEvent(@Nonnull Player player, FarmAsset farmAsset) {
        this.player = player;
        this.farmAsset = farmAsset;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerStatus getStatus() {
        return new PlayerStatus(player);
    }

    public FarmAsset getFarmAsset() {
        return farmAsset;
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

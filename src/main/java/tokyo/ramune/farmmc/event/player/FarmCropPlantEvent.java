package tokyo.ramune.farmmc.event.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import tokyo.ramune.farmmc.crop.FarmCropType;
import tokyo.ramune.farmmc.player.PlayerStatus;

import javax.annotation.Nonnull;

public class FarmCropPlantEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Location blockLocation;
    private final FarmCropType type;

    private boolean cancelled;

    public FarmCropPlantEvent(@Nonnull Player player, @Nonnull Location blockLocation, @Nonnull FarmCropType type) {
        this.player = player;
        this.blockLocation = blockLocation;
        this.type = type;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerStatus getStatus() {
        return new PlayerStatus(player);
    }

    public FarmCropType getType() {
        return type;
    }

    public Location getBlockLocation() {
        return blockLocation;
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
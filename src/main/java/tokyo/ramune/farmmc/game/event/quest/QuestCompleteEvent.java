package tokyo.ramune.farmmc.game.event.quest;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import tokyo.ramune.farmmc.game.player.PlayerStatus;
import tokyo.ramune.farmmc.game.quest.Quest;

import javax.annotation.Nonnull;

public class QuestCompleteEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Quest quest;

    private boolean cancelled;

    public QuestCompleteEvent(@Nonnull Player player, @Nonnull Quest quest) {
        this.player = player;
        this.quest = quest;
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

    public Quest getQuest() {
        return quest;
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

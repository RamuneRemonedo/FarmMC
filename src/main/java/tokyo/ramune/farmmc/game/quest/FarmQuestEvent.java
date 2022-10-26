package tokyo.ramune.farmmc.game.quest;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public enum FarmQuestEvent {
    PLAYER_JOIN_EVENT(PlayerJoinEvent.class),
    PLAYER_CHAT_EVENT(PlayerChatEvent.class);

    private final Class<? extends Event> eventClass;

    FarmQuestEvent(Class<? extends Event> eventClass) {
        this.eventClass = eventClass;
    }

    public Class<? extends Event> getEventClass() {
        return eventClass;
    }
}

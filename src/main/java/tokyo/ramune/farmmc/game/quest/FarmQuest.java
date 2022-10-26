package tokyo.ramune.farmmc.game.quest;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import tokyo.ramune.farmmc.core.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;

import java.util.function.Function;

public enum FarmQuest {
    FIRST_JOIN(
            Phase.QUEST_FIRST_JOIN_TITLE,
            Phase.QUEST_HELLO_DESCRIPTION,
            FarmQuestEvent.PLAYER_JOIN_EVENT,
            event -> {
                PlayerJoinEvent joinEvent = (PlayerJoinEvent) event;
                return joinEvent.getPlayer();
            },
            new FarmQuestReward(100, 100, null)
    ),
    HELLO(
            Phase.QUEST_HELLO_TITLE,
            Phase.QUEST_HELLO_DESCRIPTION,
            FarmQuestEvent.PLAYER_CHAT_EVENT,
            event -> {
                PlayerChatEvent castedEvent = (PlayerChatEvent) event;
                Player player = castedEvent.getPlayer();

                if (castedEvent.getMessage()
                        .contains(FarmLanguageHandler.getPhase(player, Phase.QUEST_HELLO_MESSAGE)))
                    return player;

                return null;
            },
            new FarmQuestReward(100, 100, null)
    );

    private final Phase title;
    private final Phase description;
    private final FarmQuestEvent triggerEvent;
    private final Function<Event, Player> questCondition;
    private final FarmQuestReward reward;

    FarmQuest(Phase title, Phase description,
              FarmQuestEvent triggerEvent,
              Function<Event, Player> questCondition,
              FarmQuestReward reward) {
        this.title = title;
        this.description = description;
        this.triggerEvent = triggerEvent;
        this.questCondition = questCondition;
        this.reward = reward;
    }

    public Phase getTitlePhase() {
        return title;
    }

    public Phase getDescriptionPhase() {
        return description;
    }

    public FarmQuestReward getReward() {
        return reward;
    }

    public Function<Event, Player> getQuestCondition() {
        return questCondition;
    }

    public FarmQuestEvent getTriggerEvent() {
        return triggerEvent;
    }
}

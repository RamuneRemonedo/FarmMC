package tokyo.ramune.farmmc.game.quest;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;

import javax.annotation.Nullable;
import java.util.function.Function;

public enum Quest {
    FIRST_JOIN(
            Phase.QUEST_FIRST_JOIN_TITLE,
            Phase.QUEST_HELLO_DESCRIPTION,
            QuestDifficulty.EASY,
            null,
            PlayerJoinEvent.class,
            event -> {
                PlayerJoinEvent joinEvent = (PlayerJoinEvent) event;
                return joinEvent.getPlayer();
            },
            new QuestReward(100, 100, null)
    ),
    HELLO(
            Phase.QUEST_HELLO_TITLE,
            Phase.QUEST_HELLO_DESCRIPTION,
            QuestDifficulty.EASY,
            null,
            PlayerChatEvent.class,
            event -> {
                PlayerChatEvent castedEvent = (PlayerChatEvent) event;
                Player player = castedEvent.getPlayer();

                System.out.println("aaa");

                if (castedEvent.getMessage()
                        .contains(LanguageHandler.getPhase(player, Phase.QUEST_HELLO_MESSAGE)))
                    return player;

                return null;
            },
            new QuestReward(100, 100, null)
    ),
    CRAFT_WORKBENCH(
            Phase.QUEST_CRAFT_WORKBENCH_TITLE,
            Phase.QUEST_CRAFT_WORKBENCH_DESCRIPTION,
            QuestDifficulty.EASY,
            HELLO,
            CraftItemEvent.class,
            event -> {
                CraftItemEvent castedEvent = (CraftItemEvent) event;
                Player player = (Player) castedEvent.getWhoClicked();
                Material resultMaterial = castedEvent.getRecipe().getResult().getType();

                return resultMaterial.equals(Material.CRAFTING_TABLE) ? player : null;
            },
            new QuestReward(100, 200, null)
    );

    private final Phase title;
    private final Phase description;
    private final QuestDifficulty difficulty;
    private final @Nullable Quest requireQuest;
    private final Class<? extends Event> triggerEventClass;
    private final Function<Event, Player> questCondition;
    private final QuestReward reward;

    Quest(
            Phase title,
            Phase description,
            QuestDifficulty difficulty,
            @Nullable Quest requireQuest,
            Class<? extends Event> triggerEventClass,
            Function<Event, Player> questCondition,
            QuestReward reward) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.requireQuest = requireQuest;
        this.triggerEventClass = triggerEventClass;
        this.questCondition = questCondition;
        this.reward = reward;
    }

    public Phase getTitlePhase() {
        return title;
    }

    public Phase getDescriptionPhase() {
        return description;
    }

    public QuestDifficulty getDifficulty() {
        return difficulty;
    }

    @Nullable
    public Quest getRequireQuest() {
        return requireQuest;
    }

    public QuestReward getReward() {
        return reward;
    }

    public Function<Event, Player> getQuestCondition() {
        return questCondition;
    }

    public Class<? extends Event> getTriggerEventClass() {
        return triggerEventClass;
    }
}

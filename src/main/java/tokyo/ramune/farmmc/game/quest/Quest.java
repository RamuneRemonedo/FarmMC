package tokyo.ramune.farmmc.game.quest;

import io.papermc.paper.event.block.BlockBreakBlockEvent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import tokyo.ramune.farmmc.core.language.Language;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;

import javax.annotation.Nullable;
import java.util.function.Function;

public enum Quest {
    FIRST_JOIN(
            language -> LanguageHandler.getPhase(language, Phase.QUEST_FIRST_JOIN_TITLE),
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
            language -> LanguageHandler.getPhase(language, Phase.QUEST_HELLO_TITLE),
            QuestDifficulty.EASY,
            null,
            PlayerChatEvent.class,
            event -> {
                PlayerChatEvent castedEvent = (PlayerChatEvent) event;
                Player player = castedEvent.getPlayer();

                if (castedEvent.getMessage()
                        .contains(LanguageHandler.getPhase(player, Phase.QUEST_HELLO_MESSAGE)))
                    return player;

                return null;
            },
            new QuestReward(100, 100, null)
    ),
    COLLECT_LOG(
            language -> LanguageHandler.getPhase(language, Phase.QUEST_COLLECT_TITLE)
                    .replace("{0}", LanguageHandler.getPhase(language, Phase.QUEST_LOG)),
            QuestDifficulty.EASY,
            HELLO,
            BlockBreakEvent.class,
            event -> {
                BlockBreakEvent castedEvent = (BlockBreakEvent) event;
                Player player = castedEvent.getPlayer();

                return castedEvent.getBlock().getType().name().endsWith("LOG") ? player : null;
            },
            new QuestReward(100, 200, null)
    ),
    CRAFT_WORKBENCH(
            language -> LanguageHandler.getPhase(language, Phase.QUEST_CRAFT_TITLE)
                    .replace("{0}", LanguageHandler.getPhase(language, Phase.QUEST_WORKBENCH)),
            QuestDifficulty.EASY,
            COLLECT_LOG,
            CraftItemEvent.class,
            event -> {
                CraftItemEvent castedEvent = (CraftItemEvent) event;
                Player player = (Player) castedEvent.getWhoClicked();
                Material resultMaterial = castedEvent.getRecipe().getResult().getType();

                return resultMaterial.equals(Material.CRAFTING_TABLE) ? player : null;
            },
            new QuestReward(100, 200, null)
    ),
    CRAFT_WOODEN_AXE(
            language -> LanguageHandler.getPhase(language, Phase.QUEST_CRAFT_TITLE)
                    .replace("{0}", LanguageHandler.getPhase(language, Phase.QUEST_WOODEN_AXE)),
            QuestDifficulty.NORMAL,
            CRAFT_WORKBENCH,
            CraftItemEvent.class,
            event -> {
                CraftItemEvent castedEvent = (CraftItemEvent) event;
                Player player = (Player) castedEvent.getWhoClicked();
                Material resultMaterial = castedEvent.getRecipe().getResult().getType();

                return resultMaterial.equals(Material.WOODEN_AXE) ? player : null;
            },
            new QuestReward(100, 200, null)
    ),
    ;

    private final Function<Language, String> title;
    private final QuestDifficulty difficulty;
    private final @Nullable Quest requireQuest;
    private final Class<? extends Event> triggerEventClass;
    private final Function<Event, Player> questCondition;
    private final QuestReward reward;

    Quest(
            Function<Language, String> title,
            QuestDifficulty difficulty,
            @Nullable Quest requireQuest,
            Class<? extends Event> triggerEventClass,
            Function<Event, Player> questCondition,
            QuestReward reward) {
        this.title = title;
        this.difficulty = difficulty;
        this.requireQuest = requireQuest;
        this.triggerEventClass = triggerEventClass;
        this.questCondition = questCondition;
        this.reward = reward;
    }

    public Function<Language, String> getTitle() {
        return title;
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

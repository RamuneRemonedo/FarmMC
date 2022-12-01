package tokyo.ramune.farmmc.game.quest;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import tokyo.ramune.farmmc.core.language.Language;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

public enum Quest {
    FIRST_JOIN(
            language -> LanguageHandler.getPhase(language, Phase.QUEST_FIRST_JOIN_TITLE),
            QuestDifficulty.EASY,
            Material.ACACIA_DOOR,
            null,
            null,
            PlayerJoinEvent.class,
            event -> {
                PlayerJoinEvent joinEvent = (PlayerJoinEvent) event;
                return joinEvent.getPlayer();
            },
            QuestReward.Rarity.LOW.getQuestReward()
    ),
    HELLO(
            language -> LanguageHandler.getPhase(language, Phase.QUEST_HELLO_TITLE),
            QuestDifficulty.EASY,
            Material.TORCH,
            null,
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
            QuestReward.Rarity.LOW.getQuestReward()
    ),
    COLLECT_LOG(
            language -> LanguageHandler.getPhase(language, Phase.QUEST_COLLECT_TITLE)
                    .replace("{0}", LanguageHandler.getPhase(language, Phase.QUEST_LOG)),
            QuestDifficulty.EASY,
            Material.OAK_LOG,
            World.Environment.NORMAL,
            HELLO,
            BlockBreakEvent.class,
            event -> {
                BlockBreakEvent castedEvent = (BlockBreakEvent) event;
                Player player = castedEvent.getPlayer();

                return castedEvent.getBlock().getType().name().endsWith("LOG") ? player : null;
            },
            QuestReward.Rarity.LOW.getQuestReward()    ),
    CRAFT_WORKBENCH(
            language -> LanguageHandler.getPhase(language, Phase.QUEST_CRAFT_TITLE)
                    .replace("{0}", LanguageHandler.getPhase(language, Phase.QUEST_WORKBENCH)),
            QuestDifficulty.EASY,
            Material.CRAFTING_TABLE,
            World.Environment.NORMAL,
            COLLECT_LOG,
            CraftItemEvent.class,
            event -> {
                CraftItemEvent castedEvent = (CraftItemEvent) event;
                Player player = (Player) castedEvent.getWhoClicked();
                Material resultMaterial = castedEvent.getRecipe().getResult().getType();

                return resultMaterial.equals(Material.CRAFTING_TABLE) ? player : null;
            },
            QuestReward.Rarity.LOW.getQuestReward()
    ),
    CRAFT_WOODEN_AXE(
            language -> LanguageHandler.getPhase(language, Phase.QUEST_CRAFT_TITLE)
                    .replace("{0}", LanguageHandler.getPhase(language, Phase.QUEST_WOODEN_AXE)),
            QuestDifficulty.NORMAL,
            Material.WOODEN_AXE,
            World.Environment.NORMAL,
            CRAFT_WORKBENCH,
            CraftItemEvent.class,
            event -> {
                CraftItemEvent castedEvent = (CraftItemEvent) event;
                Player player = (Player) castedEvent.getWhoClicked();
                Material resultMaterial = castedEvent.getRecipe().getResult().getType();

                return resultMaterial.equals(Material.WOODEN_AXE) ? player : null;
            },
            QuestReward.Rarity.LOW.getQuestReward()
    ),
    CRAFT_WOODEN_PICKAXE(
            language -> LanguageHandler.getPhase(language, Phase.QUEST_CRAFT_TITLE)
                    .replace("{0}", LanguageHandler.getPhase(language, Phase.QUEST_WOODEN_PICKAXE)),
            QuestDifficulty.NORMAL,
            Material.WOODEN_PICKAXE,
            World.Environment.NORMAL,
            CRAFT_WOODEN_AXE,
            CraftItemEvent.class,
            event -> {
                CraftItemEvent castedEvent = (CraftItemEvent) event;
                Player player = (Player) castedEvent.getWhoClicked();
                Material resultMaterial = castedEvent.getRecipe().getResult().getType();

                return resultMaterial.equals(Material.WOODEN_PICKAXE) ? player : null;
            },
            QuestReward.Rarity.LOW.getQuestReward()
    ),
    COLLECT_COBBLESTONE(
            language -> LanguageHandler.getPhase(language, Phase.QUEST_COLLECT_TITLE)
                    .replace("{0}", LanguageHandler.getPhase(language, Phase.QUEST_COBBLESTONE)),
            QuestDifficulty.NORMAL,
            Material.COBBLESTONE,
            World.Environment.NORMAL,
            CRAFT_WOODEN_PICKAXE,
            BlockBreakEvent.class,
            event -> {
                BlockBreakEvent castedEvent = (BlockBreakEvent) event;
                Player player = castedEvent.getPlayer();
                Material brokeMaterial = castedEvent.getBlock().getType();

                return brokeMaterial.equals(Material.STONE)
                        || brokeMaterial.equals(Material.COBBLESTONE) ? player : null;
            },
            QuestReward.Rarity.LOW.getQuestReward()
    );

    private final Function<Language, String> title;
    private final QuestDifficulty difficulty;
    private final Material icon;
    private final @Nullable World.Environment environment;
    private final @Nullable Quest requireQuest;
    private final Class<? extends Event> triggerEventClass;
    private final Function<Event, Player> questCondition;
    private final QuestReward reward;

    Quest(
            Function<Language, String> title,
            QuestDifficulty difficulty,
            Material icon,
            @Nullable World.Environment environment,
            @Nullable Quest requireQuest,
            Class<? extends Event> triggerEventClass,
            Function<Event, Player> questCondition,
            QuestReward reward) {
        this.title = title;
        this.difficulty = difficulty;
        this.icon = icon;
        this.environment = environment;
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

    public Material getIcon() {
        return icon;
    }

    @Nullable
    public World.Environment getEnvironment() {
        return environment;
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

    public boolean isGranted(@Nonnull Player player) {
        return QuestHandler.isGranted(player, this);
    }
}

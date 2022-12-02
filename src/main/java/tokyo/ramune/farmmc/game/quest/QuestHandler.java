package tokyo.ramune.farmmc.game.quest;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import tokyo.ramune.farmmc.core.database.SQL;
import tokyo.ramune.farmmc.game.event.quest.QuestCompleteEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class QuestHandler {
    public static void createTable() {
        if (SQL.tableExists("quest"))
            return;

        SQL.createTable("quest", "uuid TEXT NOT NULL" + toColumQuests());
    }

    private static String toColumQuests() {
        String colum = "";
        for (Quest quest : Quest.values()) {
            colum += "," + quest.name() + " BOOLEAN NOT NULL DEFAULT false";
        }

        return colum;
    }

    @Nullable
    public static Quest getCurrentQuest(@Nonnull Player player) {
        for (Quest quest : Quest.values()) {
            if (quest.getRequireQuest() != null && !quest.getRequireQuest().isGranted(player)) continue;
            if (quest.isGranted(player)) continue;

            return quest;
        }

        return null;
    }

    public static void check(@Nonnull Event event) {
        for (Quest quest : Quest.values()) {
            if (!quest.getTriggerEventClass().getName().equals(event.getClass().getName()))
                continue;

            Player targetPlayer = quest.getQuestCondition().apply(event);

            if (targetPlayer == null)
                continue;

            if (isCompleted(targetPlayer, quest))
                continue;

            if (quest.getRequireQuest() != null
                    && !QuestHandler.isCompleted(targetPlayer, quest.getRequireQuest()))
                continue;

            set(targetPlayer, quest, true);
        }
    }

    public static void set(@Nonnull Player player, @Nonnull Quest quest, boolean grant) {
        if (!exists(player))
            insert(player);


        if (grant) {
            // Call event
            QuestCompleteEvent event = new QuestCompleteEvent(player, quest);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) return;
        }

        SQL.set(quest.name(), grant ? 1 : 0, "uuid", "=", player.getUniqueId().toString(), "quest");
    }

    public static boolean isCompleted(@Nonnull Player player, @Nonnull Quest quest) {
        if (!exists(player))
            return false;

        Object result = SQL.get(quest.name(), "uuid", "=", player.getUniqueId().toString(), "quest");

        if (result == null)
            return false;

        return SQL.toBoolean(result);
    }

    private static void insert(@Nonnull Player player) {
        if (exists(player))
            return;

        SQL.insertData("uuid", "'" + player.getUniqueId() + "'", "quest");
    }

    private static boolean exists(@Nonnull Player player) {
        return SQL.exists("uuid", player.getUniqueId().toString(), "quest");
    }
}
package tokyo.ramune.farmmc.game.quest;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import tokyo.ramune.farmmc.core.database.SQL;
import tokyo.ramune.farmmc.core.language.FarmLanguageHandler;

public class FarmQuestHandler {
    public static void createTable() {
        if (SQL.tableExists("quest"))
            return;

        SQL.createTable("quest", "uuid TEXT NOT NULL" + toColumQuests());
    }

    private static String toColumQuests() {
        String colum = "";
        for (FarmQuest quest : FarmQuest.values()) {
            colum += "," + quest.name() + " BOOLEAN NOT NULL DEFAULT false";
        }

        return colum;
    }

    public static void check(Event event) {
        for (FarmQuest quest : FarmQuest.values()) {
            if (quest.getTriggerEvent().getEventClass().getName().equals(event.getClass().getName())) {
                Player targetPlayer = quest.getQuestCondition().apply(event);

                if (targetPlayer != null)
                    set(targetPlayer, quest, true);
            }
        }
    }

    public static void set(Player player, FarmQuest quest, boolean grant) {
        if (grant == alreadyGranted(player, quest))
            return;

        if (!exists(player))
            insert(player);

        SQL.set(quest.name(), grant ? 1 : 0, "uuid", "=", player.getUniqueId().toString(), "quest");
        System.out.println(FarmLanguageHandler.getPhase("en", quest.getTitlePhase()) + " granted!");
    }

    public static boolean alreadyGranted(Player player, FarmQuest quest) {
        if (!exists(player))
            return false;

        Object result = SQL.get(quest.name(), "uuid", "=", player.getUniqueId().toString(), "quest");

        if (result == null)
            return false;

        return (int) result != 0;
    }

    private static void insert(Player player) {
        if (exists(player))
            return;

        SQL.insertData("uuid", "'" + player.getUniqueId() + "'", "quest");
    }

    private static boolean exists(Player player) {
        return SQL.exists("uuid", player.getUniqueId().toString(), "quest");
    }
}
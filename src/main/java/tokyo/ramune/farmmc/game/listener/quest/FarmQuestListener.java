package tokyo.ramune.farmmc.game.listener.quest;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import tokyo.ramune.farmmc.game.quest.FarmQuestHandler;

public class FarmQuestListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FarmQuestHandler.check(event);
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        FarmQuestHandler.check(event);
    }
}

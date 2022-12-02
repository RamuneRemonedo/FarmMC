package tokyo.ramune.farmmc.game.listener.quest;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.core.util.Notice;
import tokyo.ramune.farmmc.game.event.quest.QuestCompleteEvent;
import tokyo.ramune.farmmc.game.player.PlayerStatus;
import tokyo.ramune.farmmc.game.quest.Quest;

public class QuestCompleteListener implements Listener {
    @EventHandler
    public void onQuestComplete(QuestCompleteEvent event) {
        Player player = event.getPlayer();
        Quest quest = event.getQuest();

        // Notice
        Notice.noticeQuestComplete(player, quest);

        // Give rewards
        PlayerStatus status = new PlayerStatus(player);

        status.addExp(quest.getReward().getExp());
        status.addCoin(quest.getReward().getCoin());
        for (ItemStack itemStack : quest.getReward().getItemStacks()) {
            player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
        }
        if (quest.getReward().getOnComplete() != null)
            quest.getReward().getOnComplete().accept(player);
    }
}

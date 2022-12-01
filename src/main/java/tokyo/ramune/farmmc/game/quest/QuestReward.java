package tokyo.ramune.farmmc.game.quest;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.game.player.PlayerStatus;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class QuestReward {
    private int exp, coin;
    private List<ItemStack> itemStacks;
    private Consumer<Player> onComplete;

    QuestReward(int exp, int coin, Consumer<Player> onComplete, ItemStack... itemStacks) {
        this.exp = exp;
        this.coin = coin;
        this.onComplete = onComplete;
        this.itemStacks = Arrays.asList(itemStacks);
    }

    public int getExp() {
        return exp;
    }

    public QuestReward setExp(int exp) {
        this.exp = exp;
        return this;
    }

    public int getCoin() {
        return coin;
    }

    public QuestReward setCoin(int coin) {
        this.coin = coin;
        return this;
    }

    public List<ItemStack> getItemStacks() {
        return itemStacks;
    }

    public QuestReward setItemStacks(ItemStack... itemStacks) {
        this.itemStacks = Arrays.asList(itemStacks);
        return this;
    }

    public QuestReward addItemStack(ItemStack itemStack) {
        itemStacks.add(itemStack);
        return this;
    }

    public Consumer<Player> getOnComplete() {
        return onComplete;
    }

    public QuestReward setOnComplete(Consumer<Player> onComplete) {
        this.onComplete = onComplete;
        return this;
    }

    public void give(Player player) {
        PlayerStatus playerStatus = new PlayerStatus(player);

        if (exp != 0)
            playerStatus.setExp(playerStatus.getExp() + exp);

        if (coin != 0)
            playerStatus.setCoin(playerStatus.getCoin() + coin);

        if (itemStacks != null)
            player.getInventory().addItem(itemStacks.toArray(new ItemStack[0]));

        if (onComplete != null)
            onComplete.accept(player);
    }

    public enum Rarity {
        LOW(new QuestReward(50, 1000, null)),
        NORMAL(new QuestReward(100, 3000, null)),
        HIGH(new QuestReward(200, 5000, null)),
        EXPERT(new QuestReward(500, 10000, null));

        private final QuestReward questReward;

        Rarity(QuestReward questReward) {
            this.questReward = questReward;
        }

        public QuestReward getQuestReward() {
            return questReward;
        }
    }
}
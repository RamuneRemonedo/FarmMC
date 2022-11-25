package tokyo.ramune.farmmc.game.quest;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.game.player.PlayerStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class QuestReward {
    private int exp = 0, coin = 0;
    private List<ItemStack> itemStacks = new ArrayList<>();
    private Consumer<Player> onComplete;

    public QuestReward() {
    }

    public QuestReward(int exp, int coin, Consumer<Player> onComplete, ItemStack... itemStacks) {
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

    public QuestReward setItemStacks(List<ItemStack> itemStacks) {
        this.itemStacks = itemStacks;
        return this;
    }

    public QuestReward setOnComplete(Consumer<Player> onComplete) {
        this.onComplete = onComplete;
        return this;
    }

    public QuestReward addItemStack(ItemStack itemStack) {
        itemStacks.add(itemStack);
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
}

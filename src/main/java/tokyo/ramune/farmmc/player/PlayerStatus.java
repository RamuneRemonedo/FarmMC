package tokyo.ramune.farmmc.player;

import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class PlayerStatus {
    private final Player player;

    public PlayerStatus(@Nonnull Player player) {
        this.player = player;
    }

    public void initializeDatabasePlayer() {
        PlayerHandler.initializeDatabasePlayer(player);
    }

    public long requireLevelUpExp() {
        return requireLevelUpExp(getLevel());
    }

    public long requireLevelUpExp(long level) {
        return level * 10;
    }

    public void updateName() {
        PlayerHandler.updateName(player);
    }

    // --- Get ---
    public long getLevel() {
        return PlayerHandler.getLevel(player);
    }

    public long getExp() {
        return PlayerHandler.getExp(player);
    }

    public long getCoin() {
        return PlayerHandler.getCoin(player);
    }

    // --- Set ---
    public void setLevel(long level) {
        PlayerHandler.setLevel(player, level);
    }

    public void setExp(long exp) {
        PlayerHandler.setExp(player, exp);
    }

    public void setCoin(long coin) {
        PlayerHandler.setCoin(player, coin);
    }
}

package tokyo.ramune.farmmc.game.player;

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

    // --- Get ---
    public long getRequireLevelUpExp() {
        return getRequireLevelUpExp(getLevel());
    }

    public long getRequireLevelUpExp(long level) {
        return PlayerHandler.getRequireLevelUpExp(level);
    }

    // --- Set ---
    public long getStamina() {
        return PlayerHandler.getStamina(player);
    }

    public void setStamina(long stamina) {
        PlayerHandler.setStamina(player, stamina);
    }

    public long getLevel() {
        return PlayerHandler.getLevel(player);
    }

    public void setLevel(long level) {
        PlayerHandler.setLevel(player, level);
    }

    public long getExp() {
        return PlayerHandler.getExp(player);
    }

    public void setExp(long exp) {
        PlayerHandler.setExp(player, exp);
    }

    public long getCoin() {
        return PlayerHandler.getCoin(player);
    }

    public void setCoin(long coin) {
        PlayerHandler.setCoin(player, coin);
    }

    public double getTemperature() {
        return Math.round(player.getLocation().getBlock().getTemperature() * 10.0) / 10.0;
    }

    // --- Update ---

    public void updateName() {
        PlayerHandler.updateName(player);
    }

    public void updateLevel() {
        PlayerHandler.updateLevel(player);
    }
}

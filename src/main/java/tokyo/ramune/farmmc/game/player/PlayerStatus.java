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


    public long getRequireLevelUpExp() {
        return getRequireLevelUpExp(getLevel());
    }

    public long getRequireLevelUpExp(long level) {
        return PlayerHandler.getRequireLevelUpExp(level);
    }

    public long getStamina() {
        return PlayerHandler.getStamina(player);
    }

    public void setStamina(long stamina) {
        PlayerHandler.setStamina(player, stamina);
    }

    public void addStamina(long stamina) {
        setStamina(getStamina() + stamina);
    }

    public long getLevel() {
        return PlayerHandler.getLevel(player);
    }

    public void setLevel(long level) {
        PlayerHandler.setLevel(player, level);
    }

    public void addLevel(long level) {
        setLevel(getLevel() + level);
    }

    public long getExp() {
        return PlayerHandler.getExp(player);
    }

    public void setExp(long exp) {
        PlayerHandler.setExp(player, exp);
    }

    public void addExp(long exp) {
        setExp(getExp() + exp);
    }

    public long getCoin() {
        return PlayerHandler.getCoin(player);
    }

    public void setCoin(long coin) {
        PlayerHandler.setCoin(player, coin);
    }

    public void addCoin(long coin) {
        setCoin(getCoin() + coin);
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

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

    // --- Get ---
    public long getRequireLevelUpExp() {
        return getRequireLevelUpExp(getLevel());
    }

    public long getRequireLevelUpExp(long level) {
        return PlayerHandler.getRequireLevelUpExp(level);
    }

    public double getAttackDistance() {
        return PlayerHandler.getAttackDistance(player);
    }

    public String getLanguage() {
        return PlayerHandler.getLanguage(player);
    }

    public long getLevel() {
        return PlayerHandler.getLevel(player);
    }

    public long getExp() {
        return PlayerHandler.getExp(player);
    }

    public long getCoin() {
        return PlayerHandler.getCoin(player);
    }

    public double getTemperature() {
        return Math.round(player.getLocation().getBlock().getTemperature() * 10.0) / 10.0;
    }

    // --- Set ---
    public void setLanguage(String language) {
        PlayerHandler.setLanguage(player, language);
    }

    public void setLevel(long level) {
        PlayerHandler.setLevel(player, level);
    }

    public void setExp(long exp) {
        PlayerHandler.setExp(player, exp);
    }

    public void setCoin(long coin) {
        PlayerHandler.setCoin(player, coin);
    }

    // --- Update ---

    public void updateName() {
        PlayerHandler.updateName(player);
    }
    public void updateLevel() {
        PlayerHandler.updateLevel(player);
    }
}

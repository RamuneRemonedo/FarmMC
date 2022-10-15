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
    public int getRequireLevelUpExp() {
        return getRequireLevelUpExp(getLevel());
    }

    public int getRequireLevelUpExp(int level) {
        return PlayerHandler.getRequireLevelUpExp(level);
    }

    public String getLanguageCode() {
        return PlayerHandler.getLanguageCode(player);
    }

    public int getStamina() {
        return PlayerHandler.getStamina(player);
    }

    public int getLevel() {
        return PlayerHandler.getLevel(player);
    }

    public int getExp() {
        return PlayerHandler.getExp(player);
    }

    public int getCoin() {
        return PlayerHandler.getCoin(player);
    }

    public double getTemperature() {
        return Math.round(player.getLocation().getBlock().getTemperature() * 10.0) / 10.0;
    }

    // --- Set ---
    public void setLanguageCode(String language) {
        PlayerHandler.setLanguageCode(player, language);
    }

    public void setStamina(int stamina) {
        PlayerHandler.setStamina(player, stamina);
    }

    public void setLevel(int level) {
        PlayerHandler.setLevel(player, level);
    }

    public void setExp(int exp) {
        PlayerHandler.setExp(player, exp);
    }

    public void setCoin(int coin) {
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

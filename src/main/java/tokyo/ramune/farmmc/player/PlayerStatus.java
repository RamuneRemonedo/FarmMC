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

    // --- Set ---
    public void setLanguageCode(String language) {
        PlayerHandler.setLanguageCode(player, language);
    }

    public int getStamina() {
        return PlayerHandler.getStamina(player);
    }

    public void setStamina(int stamina) {
        PlayerHandler.setStamina(player, stamina);
    }

    public int getLevel() {
        return PlayerHandler.getLevel(player);
    }

    public void setLevel(int level) {
        PlayerHandler.setLevel(player, level);
    }

    public int getExp() {
        return PlayerHandler.getExp(player);
    }

    public void setExp(int exp) {
        PlayerHandler.setExp(player, exp);
    }

    public int getCoin() {
        return PlayerHandler.getCoin(player);
    }

    public void setCoin(int coin) {
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

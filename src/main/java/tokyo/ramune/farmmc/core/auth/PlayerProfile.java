package tokyo.ramune.farmmc.core.auth;

import tokyo.ramune.farmmc.core.database.SQLDate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class PlayerProfile {
    @Nonnull
    private final UUID playerUniqueId;
    @Nullable
    private SQLDate lastLoginDate;
    @Nullable
    private String lastLoginAddress;
    @Nullable
    private SQLDate bannedDate;
    @Nullable
    private String bannedReason;

    public PlayerProfile(@Nonnull UUID playerUniqueId,
                         @Nullable SQLDate lastLoginDate,
                         @Nullable String lastLoginAddress,
                         @Nullable SQLDate bannedDate,
                         @Nullable String bannedReason) {
        this.playerUniqueId = playerUniqueId;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginAddress = lastLoginAddress;
        this.bannedDate = bannedDate;
        this.bannedReason = bannedReason;
    }

    @Nonnull
    public UUID getPlayerUniqueId() {
        return playerUniqueId;
    }

    @Nullable
    public SQLDate getLastLoginDate() {
        return lastLoginDate;
    }

    @Nullable
    public String getLastLoginAddress() {
        return lastLoginAddress;
    }

    @Nullable
    public SQLDate getBannedDate() {
        return bannedDate;
    }

    @Nullable
    public String getBannedReason() {
        return bannedReason;
    }

    public PlayerProfile setLastLoginDate(@Nullable SQLDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public PlayerProfile setLastLoginAddress(@Nullable String lastLoginAddress) {
        this.lastLoginAddress = lastLoginAddress;
        return this;
    }

    public PlayerProfile setBannedDate(@Nullable SQLDate bannedDate) {
        this.bannedDate = bannedDate;
        return this;
    }

    public PlayerProfile setBannedReason(@Nullable String bannedReason) {
        this.bannedReason = bannedReason;
        return this;
    }

    public PlayerProfile ban(@Nonnull String reason) {
        setBannedDate(new SQLDate());
        setBannedReason(reason);
        return this;
    }

    public PlayerProfile unban() {
        setBannedDate(null);
        setBannedReason(null);
        return this;
    }

    public void apply() {
        AuthHandler.set(this);
    }

    public boolean isBanned() {
        return bannedDate != null;
    }
}

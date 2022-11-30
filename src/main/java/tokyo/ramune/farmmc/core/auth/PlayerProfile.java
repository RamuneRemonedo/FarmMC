package tokyo.ramune.farmmc.core.auth;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.UUID;

public class PlayerProfile {
    @Nonnull
    private final UUID playerUniqueId;
    @Nullable
    private LocalDateTime lastLoginDatetime;
    @Nullable
    private String lastLoginAddress;
    @Nullable
    private LocalDateTime bannedDatetime;
    @Nullable
    private String bannedReason;

    public PlayerProfile(@Nonnull UUID playerUniqueId,
                         @Nullable LocalDateTime lastLoginDatetime,
                         @Nullable String lastLoginAddress,
                         @Nullable LocalDateTime bannedDatetime,
                         @Nullable String bannedReason) {
        this.playerUniqueId = playerUniqueId;
        this.lastLoginDatetime = lastLoginDatetime;
        this.lastLoginAddress = lastLoginAddress;
        this.bannedDatetime = bannedDatetime;
        this.bannedReason = bannedReason;
    }

    @Nonnull
    public UUID getPlayerUniqueId() {
        return playerUniqueId;
    }

    @Nullable
    public LocalDateTime getLastLoginDate() {
        return lastLoginDatetime;
    }

    public PlayerProfile setLastLoginDate(@Nullable LocalDateTime lastLoginDatetime) {
        this.lastLoginDatetime = lastLoginDatetime;
        return this;
    }

    @Nullable
    public String getLastLoginAddress() {
        return lastLoginAddress;
    }

    public PlayerProfile setLastLoginAddress(@Nullable String lastLoginAddress) {
        this.lastLoginAddress = lastLoginAddress;
        return this;
    }

    @Nullable
    public LocalDateTime getBannedDate() {
        return bannedDatetime;
    }

    public PlayerProfile setBannedDate(@Nullable LocalDateTime bannedDatetime) {
        this.bannedDatetime = bannedDatetime;
        return this;
    }

    @Nullable
    public String getBannedReason() {
        return bannedReason;
    }

    public PlayerProfile setBannedReason(@Nullable String bannedReason) {
        this.bannedReason = bannedReason;
        return this;
    }

    public PlayerProfile ban(@Nonnull String reason) {
        setBannedDate(LocalDateTime.now());
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
        return bannedDatetime != null;
    }
}

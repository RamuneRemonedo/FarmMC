package tokyo.ramune.farmmc.core.auth;

import tokyo.ramune.farmmc.core.database.SQL;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.UUID;

public class AuthHandler {
    public static void createTable() {
        if (SQL.tableExists("auth"))
            return;

        SQL.createTable("auth",
                "player_uuid TEXT NOT NULL," +
                        "last_login_datetime DATETIME," +
                        "last_login_address TEXT," +
                        "banned_datetime DATETIME," +
                        "banned_reason TEXT");
    }

    public static void set(@Nonnull PlayerProfile playerProfile) {
        if (!SQL.exists("player_uuid", playerProfile.getPlayerUniqueId().toString(), "auth")) {
            SQL.insertData("player_uuid, last_login_datetime, last_login_address, banned_datetime, banned_reason",
                    "'" + playerProfile.getPlayerUniqueId() + "','" + playerProfile.getLastLoginDate() + "','" + playerProfile.getLastLoginAddress() + "'," + playerProfile.getBannedDate() + "," + playerProfile.getBannedReason(),
                    "auth");
        } else {
            SQL.set("last_login_datetime", playerProfile.getLastLoginDate() == null ? null : playerProfile.getLastLoginDate().toString(), "player_uuid", "=", playerProfile.getPlayerUniqueId().toString(), "auth");
            SQL.set("last_login_address", playerProfile.getLastLoginAddress(), "player_uuid", "=", playerProfile.getPlayerUniqueId().toString(), "auth");
            SQL.set("banned_datetime", playerProfile.getLastLoginDate() == null ? null : playerProfile.getLastLoginDate().toString(), "player_uuid", "=", playerProfile.getPlayerUniqueId().toString(), "auth");
            SQL.set("banned_reason", playerProfile.getBannedReason(), "player_uuid", "=", playerProfile.getPlayerUniqueId().toString(), "auth");
        }
    }

    public static PlayerProfile get(@Nonnull UUID playerUuid) {
        if (!SQL.exists("player_uuid", playerUuid.toString(), "auth"))
            return new PlayerProfile(playerUuid, null, null, null, null);

        Object lastLoginDatetime = SQL.get("last_login_datetime", "player_uuid", "=", playerUuid.toString(), "auth"),
                lastLoginAddress = SQL.get("last_login_address", "player_uuid", "=", playerUuid.toString(), "auth"),
                bannedDatetime = SQL.get("banned_datetime", "player_uuid", "=", playerUuid.toString(), "auth"),
                bannedReason = SQL.get("banned_reason", "player_uuid", "=", playerUuid.toString(), "auth");

        return new PlayerProfile(playerUuid,
                SQL.isNull(lastLoginDatetime) ? null : (LocalDateTime) lastLoginDatetime,
                SQL.isNull(lastLoginAddress) ? null : (String) lastLoginAddress,
                SQL.isNull(bannedDatetime) ? null : (LocalDateTime) bannedDatetime,
                SQL.isNull(bannedReason) ? null : (String) bannedReason);
    }

    public static boolean isExists(@Nonnull UUID playerUuid) {
        return SQL.exists("player_uuid", playerUuid.toString(), "auth");
    }
}

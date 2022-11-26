package tokyo.ramune.farmmc.core.auth;

import tokyo.ramune.farmmc.core.database.SQL;
import tokyo.ramune.farmmc.core.database.SQLDate;

import javax.annotation.Nonnull;
import java.util.UUID;

public class AuthHandler {
    public static void createTable() {
        if (SQL.tableExists("auth"))
            return;

        SQL.createTable("auth",
                "player_uuid TEXT NOT NULL," +
                        "last_login_date DATE," +
                        "last_login_address TEXT," +
                        "banned_date DATE," +
                        "banned_reason TEXT");
    }

    public static void set(@Nonnull PlayerProfile playerProfile) {
        if (!SQL.exists("player_uuid", playerProfile.getPlayerUniqueId().toString(), "auth")) {
            SQL.insertData("player_uuid, last_login_date, last_login_address, banned_date, banned_reason",
                    "'" + playerProfile.getPlayerUniqueId() + "','" + playerProfile.getLastLoginDate() + "','" + playerProfile.getLastLoginAddress() + "'," + playerProfile.getBannedDate() + "," + playerProfile.getBannedReason(),
                    "auth");
        } else {
            SQL.set("last_login_date", playerProfile.getLastLoginDate() == null ? null : playerProfile.getLastLoginDate().toString(), "player_uuid", "=", playerProfile.getPlayerUniqueId().toString(), "auth");
            SQL.set("last_login_address", playerProfile.getLastLoginAddress(), "player_uuid", "=", playerProfile.getPlayerUniqueId().toString(), "auth");
            SQL.set("banned_date", playerProfile.getLastLoginDate() == null ? null : playerProfile.getLastLoginDate().toString(), "player_uuid", "=", playerProfile.getPlayerUniqueId().toString(), "auth");
            SQL.set("banned_reason", playerProfile.getBannedReason(), "player_uuid", "=", playerProfile.getPlayerUniqueId().toString(), "auth");
        }
    }

    public static PlayerProfile get(@Nonnull UUID playerUuid) {
        if (!SQL.exists("player_uuid", playerUuid.toString(), "auth"))
            return new PlayerProfile(playerUuid, null, null, null, null);

        Object lastLoginDate = SQL.get("last_login_date", "player_uuid", "=", playerUuid.toString(), "auth"),
                lastLoginAddress = SQL.get("last_login_address", "player_uuid", "=", playerUuid.toString(), "auth"),
                bannedDate = SQL.get("banned_date", "player_uuid", "=", playerUuid.toString(), "auth"),
                bannedReason = SQL.get("banned_reason", "player_uuid", "=", playerUuid.toString(), "auth");

        return new PlayerProfile(playerUuid,
                SQL.isNull(lastLoginDate) ? null : new SQLDate((String) lastLoginDate),
                SQL.isNull(lastLoginAddress) ? null : (String) lastLoginAddress,
                SQL.isNull(bannedDate) ? null : new SQLDate((String) bannedDate),
                SQL.isNull(bannedReason) ? null : (String) bannedReason);
    }

    public static boolean isExists(@Nonnull UUID playerUuid) {
        return SQL.exists("player_uuid", playerUuid.toString(), "auth");
    }
}

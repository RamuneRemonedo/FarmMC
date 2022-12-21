package tokyo.ramune.farmmc.core.util;

import org.bukkit.Bukkit;
import org.bukkit.permissions.PermissionDefault;

import javax.annotation.Nonnull;

public enum Permission {
    COMMAND_HELP(PermissionDefault.TRUE),
    COMMAND_MAINTENANCE(PermissionDefault.TRUE),
    COMMAND_LANGUAGE(PermissionDefault.TRUE),
    COMMAND_SETTING(PermissionDefault.TRUE),
    COMMAND_RELOAD(PermissionDefault.OP),
    COMMAND_TP$TEMPLATE(PermissionDefault.OP),
    COMMAND_SPAWN(PermissionDefault.OP),
    COMMAND_FORCE$SPAWN(PermissionDefault.OP),
    COMMAND_SQL$QUERY(PermissionDefault.OP),
    COMMAND_BAN(PermissionDefault.OP),
    COMMAND_UNBAN(PermissionDefault.OP),
    COMMAND_SHUTDOWN(PermissionDefault.OP),
    JOIN_TEMPLATE(PermissionDefault.OP),
    JOIN_MAINTENANCE(PermissionDefault.OP);

    private final PermissionDefault permissionDefault;

    Permission(@Nonnull PermissionDefault permissionDefault) {
        this.permissionDefault = permissionDefault;
    }

    public static void registerAll() {
        for (Permission permission : values()) {
            Bukkit.getPluginManager().addPermission(new org.bukkit.permissions.Permission("FarmMC." + permission.name().toLowerCase(), permission.getPermissionDefault()));
        }
    }

    public PermissionDefault getPermissionDefault() {
        return permissionDefault;
    }

    public org.bukkit.permissions.Permission toPermission() {
        return new org.bukkit.permissions.Permission("FarmMC." + super.name().toLowerCase());
    }
}

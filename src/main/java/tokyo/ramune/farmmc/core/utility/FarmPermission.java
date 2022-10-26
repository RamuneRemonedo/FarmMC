package tokyo.ramune.farmmc.core.utility;

import org.bukkit.permissions.Permission;

public enum FarmPermission {
    COMMAND_HELP,
    COMMAND_MAINTENANCE,
    COMMAND_LANGUAGE,
    COMMAND_RELOAD,
    COMMAND_TP$TEMPLATE,
    COMMAND_SPAWN,
    COMMAND_FORCE$SPAWN,

    JOIN_TEMPLATE,
    JOIN_MAINTENANCE;

    public Permission toPermission() {
        return new Permission("FarmMC." + super.name()
                .toLowerCase()
                .replace("_", ".")
                .replace("$", "-")
        );
    }
}

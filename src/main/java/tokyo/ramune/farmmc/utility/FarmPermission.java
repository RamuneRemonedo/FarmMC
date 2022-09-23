package tokyo.ramune.farmmc.utility;

import org.bukkit.permissions.Permission;

public enum FarmPermission {
    COMMAND_HELP,
    JOIN_TEMPLATE,
    JOIN_MAINTENANCE;

    public Permission toPermission() {
        return new Permission("FarmMC." + super.name()
                .toLowerCase()
                .replace("_", "."));
    }
}

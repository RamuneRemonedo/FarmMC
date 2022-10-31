package tokyo.ramune.farmmc.core.utility;

public enum Permission {
    COMMAND_HELP,
    COMMAND_MAINTENANCE,
    COMMAND_LANGUAGE,
    COMMAND_RELOAD,
    COMMAND_TP$TEMPLATE,
    COMMAND_SPAWN,
    COMMAND_FORCE$SPAWN,

    JOIN_TEMPLATE,
    JOIN_MAINTENANCE;

    public org.bukkit.permissions.Permission toPermission() {
        return new org.bukkit.permissions.Permission("FarmMC." + super.name()
                .toLowerCase()
                .replace("_", ".")
                .replace("$", "-")
        );
    }
}

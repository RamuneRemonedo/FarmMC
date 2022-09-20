package tokyo.ramune.farmmc.utility;

import org.bukkit.permissions.Permission;

public enum FarmPermission {
    COMMAND_HELP;

    public Permission toPermission() {
        switch (FarmPermission.valueOf(super.name())) {
            case COMMAND_HELP: return new Permission("FarmMC.command.help");
            default: return new Permission("FarmMC");
        }
    }
}

package tokyo.ramune.farmmc.core.language;

public enum Phase {
    LANG_COLOR_PREFIX,
    LANG_NAME,

    LANGUAGE_MENU_TITLE,

    PLUGIN_RESTART,

    NOTICE_FIRST_JOIN_PLAYER,
    NOTICE_JOIN_MESSAGE,
    NOTICE_QUITE_MESSAGE,
    NOTICE_LEVEL_UP_MESSAGE,
    NOTICE_LEVEL_UP_TITLE,
    NOTICE_AUTO_RESTART_MESSAGE,
    NOTICE_AUTO_RESTART_TITLE,
    NOTICE_AUTO_RESTART_SUB_TITLE,
    NOTICE_QUEST_COMPLETE,

    CHAT_PREFIX,
    CHAT_REQUIRE_PERMISSION,

    LOGIN_MAINTENANCE_MODE,

    COMMAND_AN_ERROR_OCCURRED,
    COMMAND_REQUIRE_AS_PLAYER,
    COMMAND_RATE_LIMIT,
    COMMAND_NOT_FOUND,
    COMMAND_HELP_DESCRIPTION,
    COMMAND_HELP_HELP,
    COMMAND_HELP_LIST,
    COMMAND_HELP_NOT_FOUND,
    COMMAND_LANGUAGE_DESCRIPTION,
    COMMAND_LANGUAGE_HELP,
    COMMAND_LANGUAGE_IS_NOT_SUPPORTED,
    COMMAND_LANGUAGE_SUCCESS,
    COMMAND_SETTING_DESCRIPTION,
    COMMAND_SETTING_HELP,
    COMMAND_MAINTENANCE_DESCRIPTION,
    COMMAND_MAINTENANCE_HELP,
    COMMAND_MAINTENANCE_ALREADY,
    COMMAND_RELOAD_DESCRIPTION,
    COMMAND_RELOAD_HELP,
    COMMAND_SPAWN_DESCRIPTION,
    COMMAND_SPAWN_HELP,
    COMMAND_SPAWN_SUCCESS,
    COMMAND_FORCE_SPAWN_DESCRIPTION,
    COMMAND_FORCE_SPAWN_HELP,
    COMMAND_FORCE_SPAWN_ALREADY,
    COMMAND_FORCE_SPAWN_SUCCESS,
    COMMAND_SQL_QUERY_DESCRIPTION,
    COMMAND_SQL_QUERY_HELP,
    COMMAND_BAN_DESCRIPTION,
    COMMAND_BAN_HELP,
    COMMAND_BAN_PLAYER_NOT_FOUND,
    COMMAND_BAN_SUCCESS,
    COMMAND_BAN_ALREADY_BANNED,
    COMMAND_UNBAN_DESCRIPTION,
    COMMAND_UNBAN_HELP,
    COMMAND_UNBAN_PLAYER_NOT_FOUND,
    COMMAND_UNBAN_ALREADY_UNBANNED,
    COMMAND_UNBAN_SUCCESS,
    COMMAND_SHUTDOWN_DESCRIPTION,
    COMMAND_SHUTDOWN_HELP,

    SIDEBAR_VERSION,
    SIDEBAR_COIN,
    SIDEBAR_BIOME,

    WORLD_BONE_MEAL_DISABLED,
    WORLD_CROP_TEMPERATURE_MISMATCH,
    WORLD_CANNOT_PLACE,

    BOSSBAR_SHUTDOWN_TITLE,
    BOSSBAR_MAINTENANCE_TITLE,
    BOSSBAR_EXP_TITLE,
    BOSSBAR_COIN_TITLE,

    QUEST_MENU_TITLE,
    QUEST_CRAFT_TITLE,
    QUEST_COLLECT_TITLE,
    QUEST_FIRST_JOIN_TITLE,
    QUEST_HELLO_TITLE,
    QUEST_HELLO_MESSAGE,
    QUEST_WORKBENCH,
    QUEST_WOODEN_AXE,
    QUEST_WOODEN_PICKAXE,
    QUEST_LOG,

    TOOL_WOODEN_HOE,
    TOOL_STONE_HOE,
    TOOL_IRON_HOE,
    TOOL_GOLDEN_HOE,
    TOOL_DIAMOND_HOE,
    TOOL_NETHERITE_HOE,

    MENU_SETTINGS_TITLE,

    SETTING_LANGUAGE_TITLE,
    SETTING_LANGUAGE_DESCRIPTION,
    SETTING_SIDEBAR_ENABLE_TITLE,
    SETTING_SIDEBAR_ENABLE_DESCRIPTION,
    SETTING_BOSSBAR_ENABLE_TITLE,
    SETTING_BOSSBAR_ENABLE_DESCRIPTION,

    ANVIL_DENY_RENAMING,

    COOK_MENU_TITLE,

    AUTH_BANNED
}

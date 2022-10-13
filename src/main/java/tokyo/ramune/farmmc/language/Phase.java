package tokyo.ramune.farmmc.language;

public enum Phase {
    LANG_COLOR_PREFIX,
    LANG_NAME,

    PLUGIN_RESTART,

    NOTICE_FIRST_JOIN_PLAYER,
    NOTICE_JOIN_MESSAGE,
    NOTICE_QUITE_MESSAGE,
    NOTICE_LEVEL_UP_MESSAGE,
    NOTICE_LEVEL_UP_TITLE,
    NOTICE_AUTO_RESTART_MESSAGE,
    NOTICE_AUTO_RESTART_TITLE,
    NOTICE_AUTO_RESTART_SUB_TITLE,

    CHAT_PREFIX,
    CHAT_REQUIRE_PERMISSION,

    LOGIN_MAINTENANCE_MODE,

    COMMAND_RATE_LIMIT,
    COMMAND_NOT_FOUND,
    COMMAND_HELP_DESCRIPTION,
    COMMAND_HELP_HELP,
    COMMAND_HELP_LIST,
    COMMAND_LANGUAGE_DESCRIPTION,
    COMMAND_LANGUAGE_HELP,
    COMMAND_LANGUAGE_IS_NOT_SUPPORTED,
    COMMAND_LANGUAGE_SUCCESS,
    COMMAND_MAINTENANCE_DESCRIPTION,
    COMMAND_MAINTENANCE_HELP,
    COMMAND_MAINTENANCE_ALREADY,

    WORLD_BONE_MEAL_DISABLED,
    WORLD_CROP_TEMPERATURE_MISMATCH,

    BOSSBAR_MAINTENANCE_TITLE,
    BOSSBAR_EXP_TITLE,
    BOSSBAR_COIN_TITLE
}

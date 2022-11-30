package tokyo.ramune.farmmc.game.setting;

import tokyo.ramune.farmmc.core.database.SQL;
import tokyo.ramune.farmmc.core.setting.Setting;

import javax.annotation.Nonnull;
import java.util.UUID;

public enum GameSettingHandler {
    ;

    private static final String tableName = "game_settings";
    private final Object defaultValue;

    GameSettingHandler(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public static void createTable() {
        if (SQL.tableExists(tableName))
            return;

        String columns = "";
        for (GameSettingHandler value : values()) {
            columns += value.name() + " TEXT,";
        }
        columns = columns.substring(0, 1);

        SQL.createTable(tableName, columns);
    }

    private static void initializeData(@Nonnull UUID uuid) {
        String columns = "", values = "";
        for (GameSettingHandler value : values()) {
            columns += value.name() + ",";
            values += value.defaultValue + ",";
        }
        columns = columns.substring(0, 1);
        values = values.substring(0, 1);

        if (isExists(uuid)) {
            SQL.set(columns, values, "uuid", "=", uuid.toString(), tableName);
        } else {
            SQL.insertData("uuid," + columns, "'" + uuid + "'" + values, tableName);
        }
    }

    private static boolean isExists(@Nonnull UUID uuid) {
        return SQL.exists("uuid", uuid.toString(), tableName);
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public Setting getData(@Nonnull UUID uuid) {
        if (!isExists(uuid))
            initializeData(uuid);

        return new Setting(
                SQL.get(super.name(), "uuid", "=", uuid.toString(), tableName),
                valueOf(super.name()).getDefaultValue()
        );
    }

    public void set(@Nonnull UUID uuid, Object value) {
        if (!isExists(uuid))
            initializeData(uuid);

        SQL.set(super.name(), value, "uuid", "=", uuid.toString(), tableName);
    }
}
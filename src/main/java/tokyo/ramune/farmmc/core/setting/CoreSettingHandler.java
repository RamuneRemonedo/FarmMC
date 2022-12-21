package tokyo.ramune.farmmc.core.setting;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.core.bossbar.FarmBossBarHandler;
import tokyo.ramune.farmmc.core.database.SQL;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.sidebar.SideBarHandler;

import javax.annotation.Nonnull;
import java.util.UUID;
import java.util.function.Consumer;

public enum CoreSettingHandler {
    LANGUAGE("ja", null, Phase.SETTING_LANGUAGE_TITLE, Phase.SETTING_LANGUAGE_DESCRIPTION, Material.PLAYER_HEAD),
    SIDEBAR_ENABLE(true, SideBarHandler::updateVisible, Phase.SETTING_SIDEBAR_ENABLE_TITLE, Phase.SETTING_SIDEBAR_ENABLE_DESCRIPTION, Material.CRIMSON_FUNGUS),
    BOSSBAR_ENABLE(true, FarmBossBarHandler::updateVisible, Phase.SETTING_BOSSBAR_ENABLE_TITLE, Phase.SETTING_BOSSBAR_ENABLE_DESCRIPTION, Material.WARPED_FUNGUS),
    EXP_BOSSBAR_TOGGLE_SHOW(false, FarmBossBarHandler::updateVisible, Phase.SETTING_BOSSBAR_TOGGLE_SHOW_TITLE, Phase.SETTING_BOSSBAR_TOGGLE_SHOW_DESCRIPTION, Material.REDSTONE_TORCH);

    private static final String tableName = "core_settings";
    private final Object defaultValue;
    private final Consumer<Player> onUpdate;
    private final Phase title, description;
    private final Material icon;

    CoreSettingHandler(Object defaultValue, Consumer<Player> onUpdate, Phase title, Phase description, Material icon) {
        this.defaultValue = defaultValue;
        this.onUpdate = onUpdate;
        this.title = title;
        this.description = description;
        this.icon = icon;
    }

    public static void createTable() {
        if (SQL.tableExists(tableName))
            return;

        String columns = "uuid TEXT,";
        for (CoreSettingHandler value : values()) {
            columns += value.name() + " TEXT,";
        }
        columns = columns.substring(0, columns.length() - 1);

        SQL.createTable(tableName, columns);
    }

    private static void initializeData(@Nonnull UUID uuid) {
        System.out.println("aaa");
        String columns = "uuid,", values = "";
        for (CoreSettingHandler value : values()) {
            columns += value.name() + ",";
            values += wrapSingleQuotation(value.defaultValue.toString()) + ",";
        }
        columns = columns.substring(0, columns.length() - 1);
        values = values.substring(0, values.length() - 1);

        if (isExists(uuid)) {
            SQL.set(columns, values, "uuid", "=", uuid.toString(), tableName);
        } else {
            SQL.insertData(columns, wrapSingleQuotation(uuid.toString()) + "," + values, tableName);
        }
    }

    public static void update(CoreSettingHandler setting, Player player) {
        setting.onUpdate.accept(player);
    }

    private static String wrapSingleQuotation(String text) {
        return "'" + text + "'";
    }

    private static boolean isExists(@Nonnull UUID uuid) {
        return SQL.exists("uuid", uuid.toString(), tableName);
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public Consumer<Player> getOnUpdate() {
        return onUpdate;
    }

    public Phase getTitle() {
        return title;
    }

    public Phase getDescription() {
        return description;
    }

    public Material getIcon() {
        return icon;
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
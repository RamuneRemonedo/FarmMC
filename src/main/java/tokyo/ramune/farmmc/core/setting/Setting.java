package tokyo.ramune.farmmc.core.setting;

import tokyo.ramune.farmmc.core.language.Language;

public class Setting {
    private final Object defaultValue;
    private Object value;

    public Setting(Object value, Object defaultValue) {
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getRawValue() {
        return value != null ? value : defaultValue;
    }

    public String getAsString() {
        return String.valueOf(value != null ? value : defaultValue);
    }

    public int getAsInt() {
        return Integer.parseInt(String.valueOf(value != null ? value : defaultValue));
    }

    public double getAsDouble() {
        return Double.parseDouble(String.valueOf(value != null ? value : defaultValue));
    }

    public boolean getAsBoolean() {
        return Boolean.parseBoolean(String.valueOf(value != null ? value : defaultValue));
    }

    public Language getAsLanguage() {
        return Language.valueOf(String.valueOf(value != null ? value : defaultValue));
    }
}
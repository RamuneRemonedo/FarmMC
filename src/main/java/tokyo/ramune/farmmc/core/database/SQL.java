package tokyo.ramune.farmmc.core.database;


import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class SQL {
    public static String query(String query, CommandSender sender) {
        ResultSet rs = MySQL.query(query, sender);
        try {
            if (rs.next()) {
                return toString(rs);
            }
        } catch (Exception ignored) {
        }

        return null;
    }

    private static String toString(ResultSet rs) {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int nColumns = metaData.getColumnCount();
            for (int i = 1; i <= nColumns; ++i) {
                buf.append("\n");
                buf.append(metaData.getColumnName(i));
                buf.append(" = ");
                buf.append(rs.getString(i));
                if (i < nColumns)
                    buf.append(" , ");
            }
        } catch (SQLException e) {
            buf.append(e.getMessage());
            e.printStackTrace();
        }
        buf.append("]");

        return buf.toString();
    }

    public static String toRawConditional(Map<String, String> conditional) {
        ArrayList<String> conditionalList = new ArrayList<>();

        conditional.forEach((key, value) -> {
            if (conditionalList.size() % 2 != 0 && conditional.size() != 0)
                conditionalList.add(" AND ");

            conditionalList.add(key + "=" + value);
        });

        String rawConditional = "";

        for (String s : conditionalList) {
            rawConditional += s;
        }

        return rawConditional;
    }

    public static boolean tableExists(String table) {
        try {
            Connection connection = MySQL.getConnection();
            if (connection == null) {
                return false;
            }

            DatabaseMetaData metadata = connection.getMetaData();
            if (metadata == null) {
                return false;
            }

            ResultSet rs = metadata.getTables(null, null, table, null);
            if (rs.next()) {
                return true;
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean insertData(String columns, String values, String table) {
        return MySQL.update("INSERT INTO " + table + " (" + columns + ") VALUES (" + values + ");");
    }

    public static boolean deleteData(String column, String logic_gate, String data, String table) {
        if (data != null) {
            data = "'" + data + "'";
        }

        return MySQL.update("DELETE FROM " + table + " WHERE " + column + logic_gate + data + ";");
    }

    public static boolean deleteData(Map<String, String> conditional, String table) {
        return MySQL.update("DELETE FROM " + table + " WHERE " + toRawConditional(conditional) + ";");
    }

    public static boolean exists(String column, String data, String table) {
        if (data != null) {
            data = "'" + data + "'";
        }

        try {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + column + "=" + data + ";");
            if (rs.next()) {
                return true;
            }
        } catch (Exception ignored) {
        }

        return false;
    }

    public static boolean exists(Map<String, String> conditional, String table) {
        try {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + toRawConditional(conditional) + ";");
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean deleteTable(String table) {
        return MySQL.update("DROP TABLE " + table + ";");
    }

    public static boolean truncateTable(String table) {
        return MySQL.update("TRUNCATE TABLE " + table + ";");
    }

    public static boolean createTable(String table, String columns) {
        return MySQL.update("CREATE TABLE IF NOT EXISTS " + table + " (" + columns + ");");
    }

    public static boolean upsert(String selected, Object object, String column, String data, String table) {
        if (object != null) {
            object = "'" + object + "'";
        }

        if (data != null) {
            data = "'" + data + "'";
        }

        try {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + column + "=" + data + ";");
            if (rs.next()) {
                MySQL.update("UPDATE " + table + " SET " + selected + "=" + object + " WHERE " + column + "=" + data + ";");
            } else {
                insertData(column + ", " + selected, data + ", " + object, table);
            }
        } catch (Exception ignored) {
        }

        return false;
    }

    public static boolean set(String selected, Object object, String column, String logic_gate, String data, String table) {
        if (object != null) {
            object = "'" + object + "'";
        }

        if (data != null) {
            data = "'" + data + "'";
        }

        return MySQL.update("UPDATE " + table + " SET " + selected + "=" + object + " WHERE " + column + logic_gate + data + ";");
    }

    public static boolean set(String selected, Object object, String[] where_arguments, String table) {
        String arguments = "";
        String[] var5 = where_arguments;
        int var6 = where_arguments.length;

        for (int var7 = 0; var7 < var6; ++var7) {
            String argument = var5[var7];
            arguments = arguments + argument + " AND ";
        }

        if (arguments.length() <= 5) {
            return false;
        } else {
            arguments = arguments.substring(0, arguments.length() - 5);
            if (object != null) {
                object = "'" + object + "'";
            }

            return MySQL.update("UPDATE " + table + " SET " + selected + "=" + object + " WHERE " + arguments + ";");
        }
    }

    public static Object get(String selected, String[] where_arguments, String table) {
        String arguments = "";
        String[] var4 = where_arguments;
        int var5 = where_arguments.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            String argument = var4[var6];
            arguments = arguments + argument + " AND ";
        }

        if (arguments.length() <= 5) {
            return false;
        } else {
            arguments = arguments.substring(0, arguments.length() - 5);

            try {
                ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + arguments + ";");
                if (rs.next()) {
                    return rs.getObject(selected);
                }
            } catch (Exception ignored) {
            }

            return null;
        }
    }

    public static ArrayList<Object> listGet(String selected, String[] where_arguments, String table) {
        ArrayList<Object> array = new ArrayList();
        String arguments = "";
        String[] var5 = where_arguments;
        int var6 = where_arguments.length;

        for (int var7 = 0; var7 < var6; ++var7) {
            String argument = var5[var7];
            arguments = arguments + argument + " AND ";
        }

        if (arguments.length() <= 5) {
            return array;
        } else {
            arguments = arguments.substring(0, arguments.length() - 5);

            try {
                ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + arguments + ";");

                while (rs.next()) {
                    array.add(rs.getObject(selected));
                }
            } catch (Exception ignored) {
            }

            return array;
        }
    }

    public static Object get(String selected, String column, String logic_gate, String data, String table) {
        if (data != null) {
            data = "'" + data + "'";
        }

        try {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + column + logic_gate + data + ";");
            if (rs.next()) {
                return rs.getObject(selected);
            }
        } catch (Exception ignored) {
        }

        return null;
    }

    public static ArrayList<Object> listGet(String selected, String column, String logic_gate, String data, String table) {
        ArrayList<Object> array = new ArrayList();
        if (data != null) {
            data = "'" + data + "'";
        }

        try {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + column + logic_gate + data + ";");

            while (rs.next()) {
                array.add(rs.getObject(selected));
            }
        } catch (Exception ignored) {
        }

        return array;
    }

    public static int countRows(String table) {
        int i = 0;
        if (table == null) {
            return i;
        } else {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + ";");

            try {
                while (rs.next()) {
                    ++i;
                }
            } catch (Exception ignored) {
            }

            return i;
        }
    }

    public static String removeEscapeCharacter(@Nonnull String text) {
        return text
                .replaceAll("'", "")
                .replaceAll(";", "");
    }
}


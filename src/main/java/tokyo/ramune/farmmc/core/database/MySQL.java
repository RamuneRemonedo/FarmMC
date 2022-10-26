package tokyo.ramune.farmmc.core.database;

import org.bukkit.Bukkit;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.core.FarmCoreHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQL {
    private static Connection con;

    public MySQL() {
    }

    public static Connection getConnection() {
        return con;
    }

    public static void setConnection(String host, String user, String password, String database, String port) {
        if (host != null && user != null && password != null && database != null) {
            disconnect(false);

            switch (FarmCoreHandler.getInstance().getCoreConfig().MYSQL_TYPE) {
                case "mysql":
                    try {
                        String driver = "mysql";
                        con = DriverManager.getConnection("jdbc:" + driver + "://" + host + ":" + port + "/" + database, user, password);
                    } catch (Exception var7) {
                        Bukkit.getConsoleSender().sendMessage("&c" + "SQL Connect Error: " + var7.getMessage());
                        var7.printStackTrace();
                    }
                    break;

                case "sqlite":
                    try {
                        String driver = "sqlite";
                        con = DriverManager.getConnection("jdbc:" + driver + ":FarmMC.db");
                    } catch (Exception var7) {
                        Bukkit.getConsoleSender().sendMessage("&c" + "SQL Connect Error: " + var7.getMessage());
                        var7.printStackTrace();
                    }
                    break;

                default:
                    FarmMC.getPlugin().getLogger().warning("Unsupported database type detected! Please choose mysql or sqlite on config.yml.");
                    break;
            }
        }
    }

    public static void connect() {
        connect(true);
    }

    private static void connect(boolean message) {
        String host = FarmCoreHandler.getInstance().getCoreConfig().MYSQL_HOST;
        String user = FarmCoreHandler.getInstance().getCoreConfig().MYSQL_USER;
        String password = FarmCoreHandler.getInstance().getCoreConfig().MYSQL_PASSWORD;
        String database = FarmCoreHandler.getInstance().getCoreConfig().MYSQL_DATABASE;
        String port = FarmCoreHandler.getInstance().getCoreConfig().MYSQL_PORT;

        if (isConnected()) {
            if (message) {
                Bukkit.getConsoleSender().sendMessage("&c" + "SQL Connect Error: Already connected");
            }
        } else if (host.length() == 0) {
            Bukkit.getConsoleSender().sendMessage("&c" + "Config Error: Host is blank");
        } else if (user.length() == 0) {
            Bukkit.getConsoleSender().sendMessage("&c" + "Config Error: User is blank");
        } else if (password.length() == 0) {
            Bukkit.getConsoleSender().sendMessage("&c" + "Config Error: Password is blank");
        } else if (database.length() == 0) {
            Bukkit.getConsoleSender().sendMessage("&c" + "Config Error: Database is blank");
        } else if (port.length() == 0) {
            Bukkit.getConsoleSender().sendMessage("&c" + "Config Error: Port is blank");
        } else {
            setConnection(host, user, password, database, port);
        }
    }

    public static void disconnect() {
        disconnect(true);
    }

    private static void disconnect(boolean message) {
        try {
            if (isConnected()) {
                con.close();
                if (message) {
                    Bukkit.getConsoleSender().sendMessage("&a" + "SQL disconnected.");
                }
            } else if (message) {
                Bukkit.getConsoleSender().sendMessage("&c" + "SQL Disconnect Error: No existing connection");
            }
        } catch (Exception var2) {
            if (message) {
                Bukkit.getConsoleSender().sendMessage("&c" + "SQL Disconnect Error: " + var2.getMessage());
            }
        }

        con = null;
    }

    public static void reconnect() {
        disconnect();
        connect();
    }

    public static boolean isConnected() {
        if (con != null) {
            try {
                return !con.isClosed();
            } catch (Exception var1) {
                Bukkit.getConsoleSender().sendMessage("&c" + "SQL Connection:");
                Bukkit.getConsoleSender().sendMessage("&c" + "Error: " + var1.getMessage());
            }
        }

        return false;
    }

    public static boolean update(String command) {
        if (command == null) {
            return false;
        } else {
            boolean result = false;
            connect(false);

            try {
                if (con != null) {
                    Statement st = con.createStatement();
                    st.executeUpdate(command);
                    st.close();
                    result = true;
                }
            } catch (Exception var4) {
                String message = var4.getMessage();
                if (message != null) {
                    Bukkit.getConsoleSender().sendMessage("&c" + "SQL Update:");
                    Bukkit.getConsoleSender().sendMessage("&c" + "Command: " + command);
                    Bukkit.getConsoleSender().sendMessage("&c" + "Error: " + message);
                }
            }

            disconnect(false);
            return result;
        }
    }

    public static ResultSet query(String command) {
        if (command == null) {
            return null;
        } else {
            connect(false);
            ResultSet rs = null;

            try {
                if (con != null) {
                    Statement st = con.createStatement();
                    rs = st.executeQuery(command);
                }
            } catch (Exception var4) {
                String message = var4.getMessage();
                if (message != null) {
                    Bukkit.getConsoleSender().sendMessage("&c" + "SQL Query:");
                    Bukkit.getConsoleSender().sendMessage("&c" + "Command: " + command);
                    Bukkit.getConsoleSender().sendMessage("&c" + "Error: " + message);
                }
            }

            return rs;
        }
    }
}

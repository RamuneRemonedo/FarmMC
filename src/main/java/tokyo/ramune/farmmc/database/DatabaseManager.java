package tokyo.ramune.farmmc.database;

public class DatabaseManager {

    public DatabaseManager() {
        MySQL.connect();
    }

    public void createTables() {
        if (!SQL.tableExists("players")) {
            SQL.createTable("players", "uuid TEXT NOT NULL," +
                    "name TEXT NOT NULL," +
                    "level BIGINT DEFAULT 0," +
                    "exp BIGINT DEFAULT 0," +
                    "crystal BIGINT DEFAULT 0," +
                    "coin BIGINT DEFAULT 0");
        }
    }
}

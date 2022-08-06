package tokyo.ramune.farmmc.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.bossbar.FarmBossBar;
import tokyo.ramune.farmmc.cursor.Cursor;
import tokyo.ramune.farmmc.database.SQL;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class FarmPlayerManager {

    private final ArrayList<FarmPlayer> players = new ArrayList<>();

    public FarmPlayerManager() {
    }

    public FarmPlayer getFarmPlayer(Player player) {
        if (!isExistsPlayer(player)) return createPlayer(player);
        for (FarmPlayer farmPlayer : players) {
            if (farmPlayer.getPlayer().equals(player)) return farmPlayer;
        }
        return null;
    }

    private void updateDBPlayerName(Player player) {
        if (!SQL.exists("uuid", player.getUniqueId().toString(), "players")) return;
        if (String.valueOf(SQL.get("name", "uuid", "=", player.getUniqueId().toString(), "players"))
                .equals(player.getName())) return;
        SQL.set("name", player.getName(), "uuid", "=", player.getUniqueId().toString(), "players");
    }

    private void initializeDBPlayer(Player player) {
        if (SQL.exists("uuid", player.getUniqueId().toString(), "players")) return;
        SQL.insertData("uuid, name, level, exp, crystal, coin",
                surroundQuotation(player.getUniqueId().toString()) + "," +
                        surroundQuotation(player.getName()) + "," +
                        surroundQuotation("0") + "," +
                        surroundQuotation("0") + "," +
                        surroundQuotation("0") + "," +
                        surroundQuotation("0"),
                "players");
    }

    private String surroundQuotation(String string) {
        return "'" + string + "'";
    }

    private FarmPlayer createPlayer(@Nonnull Player player) {
        if (isExistsPlayer(player)) {
            return getFarmPlayer(player);
        }
        initializeDBPlayer(player);
        updateDBPlayerName(player);
        FarmPlayer farmPlayer = new FarmPlayer() {
            private boolean isAllowFixedHeight = false;

            @Override
            public Player getPlayer() {
                return player;
            }

            @Override
            public long getLevel() {
                return (long) SQL.get("level", "uuid", "=", player.getUniqueId().toString(), "players");
            }

            @Override
            public void setLevel(long level) {
                SQL.set("level", level, "uuid", "=", player.getUniqueId().toString(), "players");
            }

            @Override
            public long getExp() {
                return (long) SQL.get("exp", "uuid", "=", player.getUniqueId().toString(), "players");
            }

            @Override
            public void setExp(long exp) {
                SQL.set("exp", exp, "uuid", "=", player.getUniqueId().toString(), "players");
            }

            @Override
            public long getCrystal() {
                return (long) SQL.get("crystal", "uuid", "=", player.getUniqueId().toString(), "players");
            }

            @Override
            public void setCrystal(long crystal) {
                SQL.set("crystal", crystal, "uuid", "=", player.getUniqueId().toString(), "players");
            }

            @Override
            public long getCoin() {
                return (long) SQL.get("coin", "uuid", "=", player.getUniqueId().toString(), "players");
            }

            @Override
            public void setCoin(long coin) {
                SQL.set("coin", coin, "uuid", "=", player.getUniqueId().toString(), "players");
            }

            @Override
            public Cursor getCursor() {
                return FarmMC.getCursorManager().getCursor(player);
            }

            @Override
            public FarmBossBar getExpBossBar() {
                return FarmMC.getBossBarManager().getExpBossBar(this);
            }

            @Override
            public boolean isAllowFixedHeight() {
                return isAllowFixedHeight;
            }

            @Override
            public void setAllowFixedHeight(boolean isAllowFixedHeight) {
                this.isAllowFixedHeight = isAllowFixedHeight;
            }
        };
        players.add(farmPlayer);
        return farmPlayer;
    }

    protected void removePlayer(FarmPlayer farmPlayer) {
        farmPlayer.getCursor().remove();
        players.remove(farmPlayer);
    }

    private boolean isExistsPlayer(Player player) {
        for (FarmPlayer farmPlayer : players) {
            if (farmPlayer.getPlayer().equals(player)) return true;
        }
        return false;
    }
}
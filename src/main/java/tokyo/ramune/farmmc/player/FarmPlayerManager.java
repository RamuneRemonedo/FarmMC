package tokyo.ramune.farmmc.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.bossbar.FarmBossBar;
import tokyo.ramune.farmmc.cursor.Cursor;
import tokyo.ramune.farmmc.database.SQL;
import tokyo.ramune.farmmc.player.event.FarmPlayerExpChangeEvent;
import tokyo.ramune.farmmc.player.event.FarmPlayerLevelUpEvent;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class FarmPlayerManager {

    private final ArrayList<FarmPlayer> players = new ArrayList<>();

    public FarmPlayerManager() {
        Bukkit.getOnlinePlayers().forEach(this::createPlayer);
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
                        surroundQuotation("1") + "," +
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
                if (level == getLevel()) return;
                SQL.set("level", level, "uuid", "=", player.getUniqueId().toString(), "players");
                getExpBossBar().update();
            }

            @Override
            public long getExp() {
                return (long) SQL.get("exp", "uuid", "=", player.getUniqueId().toString(), "players");
            }

            @Override
            public void setExp(long exp) {
                if (exp == getExp()) return;
                long currentExp = getExp();
                FarmPlayerExpChangeEvent farmPlayerExpChangeEvent = new FarmPlayerExpChangeEvent(this, currentExp, exp);
                Bukkit.getPluginManager().callEvent(farmPlayerExpChangeEvent);
                if (farmPlayerExpChangeEvent.isCancelled()) return;

                long requireExp = getRequireExp();

                if (exp >= requireExp) {
                    long level = getLevel();
                    FarmPlayerLevelUpEvent farmPlayerLevelUpEvent = new FarmPlayerLevelUpEvent(this, level, level + 1);

                    Bukkit.getPluginManager().callEvent(farmPlayerLevelUpEvent);
                    if (farmPlayerLevelUpEvent.isCancelled()) {
                        SQL.set("exp", exp, "uuid", "=", player.getUniqueId().toString(), "players");
                        getExpBossBar().update();
                        return;
                    }
                    exp = exp - requireExp;
                    setLevel(level + 1);
                }
                SQL.set("exp", exp, "uuid", "=", player.getUniqueId().toString(), "players");
                getExpBossBar().update();
            }

            @Override
            public long getRequireExp() {
                return getLevel() * 15;
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

    public void removePlayer(FarmPlayer farmPlayer) {
        farmPlayer.getCursor().remove();
        farmPlayer.getCursor().remove();
        farmPlayer.getExpBossBar().remove();
        players.remove(farmPlayer);
    }

    private boolean isExistsPlayer(Player player) {
        for (FarmPlayer farmPlayer : players) {
            if (farmPlayer.getPlayer().equals(player)) return true;
        }
        return false;
    }
}
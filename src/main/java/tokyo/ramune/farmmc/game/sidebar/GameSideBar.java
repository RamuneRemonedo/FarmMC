package tokyo.ramune.farmmc.game.sidebar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.sidebar.SideBar;
import tokyo.ramune.farmmc.core.util.Chat;
import tokyo.ramune.farmmc.game.player.PlayerStatus;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class GameSideBar extends SideBar {
    private final List<String> animatedTitle =
            Arrays.asList(
                    ChatColor.GOLD.toString() + ChatColor.BOLD + "<< " + ChatColor.GREEN + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC" + ChatColor.GOLD + ChatColor.BOLD + " >>",
                    ChatColor.GOLD.toString() + ChatColor.BOLD + "<< " + ChatColor.GREEN + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC" + ChatColor.GOLD + ChatColor.BOLD + " >>",
                    ChatColor.GOLD.toString() + ChatColor.BOLD + "<< " + ChatColor.GREEN + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC" + ChatColor.GOLD + ChatColor.BOLD + " >",
                    ChatColor.GOLD.toString() + ChatColor.BOLD + "< " + ChatColor.GREEN + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "arm " + ChatColor.AQUA + ChatColor.BOLD + "M",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "rm ",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "m",
                    ChatColor.RESET.toString(),
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "rm ",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "arm " + ChatColor.AQUA + ChatColor.BOLD + "M",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC",
                    ChatColor.GOLD.toString() + ChatColor.BOLD + "< " + ChatColor.GREEN + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC" + ChatColor.GOLD + ChatColor.BOLD + " >",
                    ChatColor.GOLD.toString() + ChatColor.BOLD + "<< " + ChatColor.GREEN + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC" + ChatColor.GOLD + ChatColor.BOLD + " >>",
                    ChatColor.GOLD.toString() + ChatColor.BOLD + "<< " + ChatColor.WHITE + ChatColor.BOLD + "Farm " + ChatColor.WHITE + ChatColor.BOLD + "MC" + ChatColor.GOLD + ChatColor.BOLD + " >>",
                    ChatColor.GOLD.toString() + ChatColor.BOLD + "<< " + ChatColor.GREEN + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC" + ChatColor.GOLD + ChatColor.BOLD + " >>",
                    ChatColor.GOLD.toString() + ChatColor.BOLD + "<< " + ChatColor.WHITE + ChatColor.BOLD + "Farm " + ChatColor.WHITE + ChatColor.BOLD + "MC" + ChatColor.GOLD + ChatColor.BOLD + " >>",
                    ChatColor.GOLD.toString() + ChatColor.BOLD + "<< " + ChatColor.GREEN + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC" + ChatColor.GOLD + ChatColor.BOLD + " >>",
                    ChatColor.GOLD.toString() + ChatColor.BOLD + "<< " + ChatColor.WHITE + ChatColor.BOLD + "Farm " + ChatColor.WHITE + ChatColor.BOLD + "MC" + ChatColor.GOLD + ChatColor.BOLD + " >>",
                    ChatColor.GOLD.toString() + ChatColor.BOLD + "<< " + ChatColor.GREEN + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC" + ChatColor.GOLD + ChatColor.BOLD + " >>"
            );
    private final int animatePlayerNameLength = 12;
    private int animatedTitleIndex = 0;
    private String animatedPlayerName = getPlayer().getName();
    private int animatedPlayerNameIndex = -1;

    public GameSideBar(Player player) {
        super(player, "FarmMC");

        String playerName = getPlayer().getName();
        if (playerName.length() >= animatePlayerNameLength)
            animatedPlayerName = animatedPlayerName.substring(0, playerName.length() - (playerName.length() - animatePlayerNameLength));

        PlayerStatus playerStatus = new PlayerStatus(player);
        Calendar cal = Calendar.getInstance();

        addLine(() -> ChatColor.GRAY.toString() + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "  " + ChatColor.GRAY + ChatColor.ITALIC + ChatColor.BOLD + animatedPlayerName);
        addBlankLine();
        addLine(() -> ChatColor.GRAY.toString() + ChatColor.BOLD + "ping: " + player.getPing() + "ms");
        addBlankLine();
        addLine(() -> Chat.replaceColor("&6&l" + LanguageHandler.getPhase(player, Phase.SIDEBAR_COIN) + "&f: " + String.format("%,d", playerStatus.getCoin()), '&'));
        addBlankLine();
        addLine(() -> Chat.replaceColor("&l" + LanguageHandler.getPhase(player, Phase.SIDEBAR_BIOME) + "&f: &b&o" +
                player.getWorld().getBiome(player.getLocation()).name()
                        .toLowerCase()
                        .replaceAll("_", " "), '&'));
        addBlankLine();
        addLine(() -> ChatColor.GRAY + FarmMC.getPlugin().getDescription().getVersion());
        addLine(() -> ChatColor.YELLOW + "www.farmmc.tokyo");
    }

    @Override
    public void update() {
        super.update();

        // Title
        setTitle(animatedTitle.get(animatedTitleIndex));
        animatedTitleIndex++;
        if (animatedTitle.size() <= animatedTitleIndex)
            animatedTitleIndex = 0;

        // Name
        if (getPlayer().getName().length() >= animatePlayerNameLength) {
            if (animatedPlayerNameIndex == -1) {
                animatedPlayerName = animatedPlayerName.substring(1) + " ";
            } else {
                animatedPlayerName = animatedPlayerName.substring(1) + getPlayer().getName().charAt(animatedPlayerNameIndex);
            }
            animatedPlayerNameIndex++;

            if (animatedPlayerNameIndex >= getPlayer().getName().length())
                animatedPlayerNameIndex = -1;
        }
    }
}

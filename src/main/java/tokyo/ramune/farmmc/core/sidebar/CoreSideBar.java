package tokyo.ramune.farmmc.core.sidebar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.FarmMC;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CoreSideBar extends SideBar {
    private final List<String> animatedTitle =
            Arrays.asList(
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "arm " + ChatColor.AQUA + ChatColor.BOLD + "M",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "rm ",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "m",
                    ChatColor.RESET.toString(),
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "rm ",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "arm " + ChatColor.AQUA + ChatColor.BOLD + "M",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC",
                    ChatColor.WHITE.toString() + ChatColor.BOLD + "Farm " + ChatColor.WHITE + ChatColor.BOLD + "MC",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC",
                    ChatColor.WHITE.toString() + ChatColor.BOLD + "Farm " + ChatColor.WHITE + ChatColor.BOLD + "MC",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC",
                    ChatColor.WHITE.toString() + ChatColor.BOLD + "Farm " + ChatColor.WHITE + ChatColor.BOLD + "MC",
                    ChatColor.GREEN.toString() + ChatColor.BOLD + "Farm " + ChatColor.AQUA + ChatColor.BOLD + "MC"
            );
    private final int animatePlayerNameLength = 12;
    private int animatedTitleIndex = 0;
    private String animatedPlayerName = getPlayer().getName();
    private int animatedPlayerNameIndex = -1;

    public CoreSideBar(Player player) {
        super(player, "title");

        String playerName = getPlayer().getName();
        if (playerName.length() >= animatePlayerNameLength)
            animatedPlayerName = animatedPlayerName.substring(0, playerName.length() - (playerName.length() - animatePlayerNameLength));

        Calendar cal = Calendar.getInstance();
        addLine(() -> ChatColor.GRAY.toString() + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "  " + ChatColor.GRAY + ChatColor.ITALIC + animatedPlayerName);
        addBlankLine();
        addLine(() -> ChatColor.GRAY.toString() + player.getPing() + "ms");
        addBlankLine();
        addLine(() -> ChatColor.WHITE + "version: ");
        addLine(() -> ChatColor.WHITE + FarmMC.getPlugin().getDescription().getVersion());
        addBlankLine();
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
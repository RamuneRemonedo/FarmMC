package tokyo.ramune.farmmc.utility;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class Notice {
    public static void noticeLevelUp(@Nonnull Player player, long from, long to) {
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1, 1);
        Chat.sendMessage(player, Chat.replaceColor("\n&a&lおめでとう! レベルアップしたよ!" +
                                "\n&b" + from + " &f→ &b&l" + to,
                        '&'),
                true
        );
        player.sendTitle(Chat.replaceColor("&r", '&')
                , Chat.replaceColor("&a&lレベルアップ!!", '&'));
    }

    public static void noticeAutoRestart(long time) {

    }
}

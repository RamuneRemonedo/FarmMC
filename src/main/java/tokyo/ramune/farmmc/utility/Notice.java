package tokyo.ramune.farmmc.utility;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class Notice {
    public static void noticeLevelUp(@Nonnull Player player, long from, long to) {
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1, 1);
        Chat.sendMessage(player, Chat.replaceColor("&a&lおめでとう! レベルアップしたよ!" +
                                "\n&b" + from + " &f→ &b&l" + to,
                        '&'),
                true
        );
        player.sendTitle(Chat.replaceColor("&r", '&')
                , Chat.replaceColor("&a&lレベルアップ!!", '&'));
    }

    public static void noticeAutoRestart(long time) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            Chat.sendMessage(player,
                    Chat.replaceColor(
                            "&l[&c&lお知らせ 重要&l] &cこのサーバーは" + time + "秒後に自動で再起動されます。" +
                                    "\n再起動時はすべてのプレイヤーがキックされるので注意してください。" +
                                    "\n再起動後、しばらく経つと再びサーバーに参加できるようになります。",
                            '&'),
                    true);
            player.sendTitle(
                    Chat.replaceColor("&e&lサーバー 再起動!", '&'),
                    Chat.replaceColor("&a定期再起動が行われます &7(あと &e" + time + "秒&7)", '&'));
        });
    }
}

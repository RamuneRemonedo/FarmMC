package tokyo.ramune.farmmc.core.utility;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;

import javax.annotation.Nonnull;

public class Notice {
    public static void noticeFirstJoinPlayer(@Nonnull Player player) {
        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> onlinePlayer.sendMessage(
                LanguageHandler.getPhase(onlinePlayer, Phase.NOTICE_FIRST_JOIN_PLAYER)
                        .replace("{0}", player.getName())
        ));
    }

    public static void noticeJoinMessage(@Nonnull Player player) {
        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> onlinePlayer.sendMessage(
                LanguageHandler.getPhase(onlinePlayer, Phase.NOTICE_JOIN_MESSAGE)
                        .replace("{0}", player.getName())
        ));
    }

    public static void noticeQuitMessage(@Nonnull Player player) {
        Bukkit.getOnlinePlayers().forEach(onlinePlayer -> onlinePlayer.sendMessage(
                LanguageHandler.getPhase(onlinePlayer, Phase.NOTICE_QUITE_MESSAGE)
                        .replace("{0}", player.getName())
        ));
    }

    public static void noticeLevelUp(@Nonnull Player player, long from, long to) {
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1, 1);
        Chat.sendMessage(
                player,
                LanguageHandler.getPhase(player, Phase.NOTICE_LEVEL_UP_MESSAGE)
                        .replace("{0}", String.valueOf(from))
                        .replace("{1}", String.valueOf(to)),
                true
        );
        player.sendTitle(Chat.replaceColor("&r", '&')
                , LanguageHandler.getPhase(player, Phase.NOTICE_LEVEL_UP_TITLE));
    }

    public static void noticeAutoRestart(long time) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            Chat.sendMessage(player,
                    LanguageHandler.getPhase(player, Phase.NOTICE_AUTO_RESTART_MESSAGE)
                            .replace("{0}", String.valueOf(time)),
                    true);
            player.sendTitle(
                    LanguageHandler.getPhase(player, Phase.NOTICE_AUTO_RESTART_TITLE),
                    LanguageHandler.getPhase(player, Phase.NOTICE_AUTO_RESTART_SUB_TITLE)
                            .replace("{0}", String.valueOf(time)));
        });
    }
}
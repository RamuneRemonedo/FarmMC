package tokyo.ramune.farmmc.core.util;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import tokyo.ramune.farmmc.core.CoreHandler;
import tokyo.ramune.farmmc.core.language.LanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.game.quest.Quest;

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
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
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

    public static void noticeQuestComplete(@Nonnull Player player, @Nonnull Quest quest) {
        Bukkit.getScheduler().runTaskAsynchronously(CoreHandler.getInstance().getPlugin(), () -> {
            try {
                player.spawnParticle(Particle.SCULK_SOUL, player.getLocation().add(0, 1.5, 0), 100, 0.0, 0.1, 0.1, 0.1);
                for (int i = 0; i < 20; i++) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 0.00F + i * 0.02F, 1.78F);
                    Thread.sleep(30);
                }
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 0.59F);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1.19F);
                Thread.sleep(120);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 0.59F);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1.19F);
                Thread.sleep(120);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 0.89F);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1.78F);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
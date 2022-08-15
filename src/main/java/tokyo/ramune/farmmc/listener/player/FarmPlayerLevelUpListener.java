package tokyo.ramune.farmmc.listener.player;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.FireworkMeta;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.event.player.FarmPlayerLevelUpEvent;
import tokyo.ramune.farmmc.player.FarmPlayer;

public class FarmPlayerLevelUpListener implements Listener {

    @EventHandler
    public void onFarmPlayerLevelUp(FarmPlayerLevelUpEvent event) {
        FarmPlayer player = event.getPlayer();

        player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
        player.getPlayer().sendTitle(ChatColor.AQUA + ChatColor.ITALIC.toString() + "レベルアップ!!!"
                , ChatColor.BLUE + String.valueOf(event.getFrom()) + ChatColor.WHITE + ChatColor.BOLD + "→" + ChatColor.BLUE + event.getTo());
        Bukkit.getScheduler().runTaskAsynchronously(FarmMC.getPlugin(), () -> {
            for (int i = 0; i < 5; i++) {
                Bukkit.getScheduler().runTask(FarmMC.getPlugin(),() -> {
                    Firework firework = (Firework) player.getPlayer().getWorld().spawnEntity(player.getPlayer().getLocation().clone().add(0, 5, 0), EntityType.FIREWORK);
                    FireworkMeta fireworkMeta = firework.getFireworkMeta();
                    fireworkMeta.setPower(0);
                    fireworkMeta.addEffect(FireworkEffect.builder()
                            .flicker(true)
                            .trail(true)
                            .with(FireworkEffect.Type.BALL)
                            .withColor(Color.AQUA).build());
                    fireworkMeta.addEffect(FireworkEffect.builder()
                            .flicker(true)
                            .trail(true)
                            .with(FireworkEffect.Type.BALL)
                            .withColor(Color.BLUE).build());
                    fireworkMeta.addEffect(FireworkEffect.builder()
                            .flicker(true)
                            .trail(true)
                            .with(FireworkEffect.Type.STAR)
                            .withColor(Color.LIME).build());
                    firework.setFireworkMeta(fireworkMeta);
                    firework.detonate();
                });
                try {
                    Thread.sleep(500);
                } catch (Exception ignored) {
                }
            }
        });
    }
}

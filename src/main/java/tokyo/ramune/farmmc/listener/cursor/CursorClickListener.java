package tokyo.ramune.farmmc.listener.cursor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.cursor.Cursor;
import tokyo.ramune.farmmc.cursor.event.CursorClickEvent;
import tokyo.ramune.farmmc.player.FarmPlayer;

public class CursorClickListener implements Listener {

    @EventHandler
    public void onCursorClickEvent(CursorClickEvent event) {
        FarmPlayer player = event.getPlayer();
        Cursor cursor = event.getCursor();
        player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.BLOCK_STONE_PRESSURE_PLATE_CLICK_ON, 1, 1);
        cursor.setCursorItemStack(new ItemStack(Material.SMOOTH_SANDSTONE_STAIRS));
        cursor.getCursorEntity().setGlowing(true);
        Bukkit.getScheduler().runTaskLater(FarmMC.getPlugin(), () -> {
            cursor.setCursorItemStack(new ItemStack(Material.QUARTZ_STAIRS));
            cursor.getCursorEntity().setGlowing(false);
        }, 3);
        if (event.getAction().isRightClick()) {
            player.getExpBossBar().setAutoHide(false);
            player.getExpBossBar().setVisible(true);
            player.setExp(player.getExp() + 1);
        } else {
            player.getExpBossBar().setAutoHide(true);
            player.getExpBossBar().setVisible(true);
            player.setExp(player.getExp() - 1);
        }
    }
}

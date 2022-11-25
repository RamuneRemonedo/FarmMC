package tokyo.ramune.farmmc.game.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import tokyo.ramune.farmmc.game.crop.CropType;
import tokyo.ramune.farmmc.game.event.crop.FarmCropHarvestEvent;

public class PlayerHarvestBlockListener implements Listener {
    @EventHandler
    public void onPlayerHarvestBlock(PlayerHarvestBlockEvent event) {
        Material material = event.getHarvestedBlock().getType();
        CropType cropType = CropType.getCropType(material);

        if (cropType == null)
            return;

        Player player = event.getPlayer();
        Location blockLocation = event.getHarvestedBlock().getLocation();

        FarmCropHarvestEvent farmEvent = new FarmCropHarvestEvent(player, blockLocation, cropType);

        Bukkit.getPluginManager().callEvent(farmEvent);
        if (farmEvent.isCancelled())
            event.setCancelled(true);
    }
}

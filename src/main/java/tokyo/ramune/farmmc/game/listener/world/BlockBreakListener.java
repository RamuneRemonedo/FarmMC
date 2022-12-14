package tokyo.ramune.farmmc.game.listener.world;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import tokyo.ramune.farmmc.game.crop.CropType;
import tokyo.ramune.farmmc.game.event.crop.FarmCropHarvestEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Material material = event.getBlock().getType();
        BlockData blockData = event.getBlock().getBlockData();
        CropType cropType = CropType.getCropType(material);

        if (cropType == null)
            return;

        Player player = event.getPlayer();
        Location blockLocation = event.getBlock().getLocation();
        int age = 0, maximumAge = 0;

        if (blockData instanceof Ageable) {
            Ageable blockAge = (Ageable) blockData;

            age = blockAge.getAge();
            maximumAge = blockAge.getMaximumAge();
        }

        if (!cropType.isReplaceableAsPlayer() && age != maximumAge)
            return;

        FarmCropHarvestEvent farmEvent = new FarmCropHarvestEvent(player, blockLocation, cropType);

        Bukkit.getPluginManager().callEvent(farmEvent);
        if (farmEvent.isCancelled())
            event.setCancelled(true);
    }
}

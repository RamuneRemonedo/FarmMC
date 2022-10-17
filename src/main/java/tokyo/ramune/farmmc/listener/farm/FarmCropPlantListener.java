package tokyo.ramune.farmmc.listener.farm;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.crop.CropArtificialHandler;
import tokyo.ramune.farmmc.crop.FarmCropType;
import tokyo.ramune.farmmc.event.player.FarmCropPlantEvent;
import tokyo.ramune.farmmc.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.language.Phase;
import tokyo.ramune.farmmc.utility.Chat;

public class FarmCropPlantListener implements Listener {
    @EventHandler
    public void onFarmCropPlant(FarmCropPlantEvent event) {
        Player player = event.getPlayer();
        FarmCropType type = event.getType();
        Location location = event.getBlockLocation();

        if (!type.isGrowableAt(location))
            Chat.sendMessage(player, FarmLanguageHandler.getPhase(player, Phase.WORLD_CROP_TEMPERATURE_MISMATCH), true, true);

        if (type.isPlaceableAsPlayer())
            CropArtificialHandler.add(location);
    }
}

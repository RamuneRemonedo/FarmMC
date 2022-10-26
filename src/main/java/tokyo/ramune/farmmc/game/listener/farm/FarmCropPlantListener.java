package tokyo.ramune.farmmc.game.listener.farm;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tokyo.ramune.farmmc.core.language.FarmLanguageHandler;
import tokyo.ramune.farmmc.core.language.Phase;
import tokyo.ramune.farmmc.core.utility.Chat;
import tokyo.ramune.farmmc.game.crop.CropArtificialHandler;
import tokyo.ramune.farmmc.game.crop.FarmCropType;
import tokyo.ramune.farmmc.game.event.crop.FarmCropPlantEvent;

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

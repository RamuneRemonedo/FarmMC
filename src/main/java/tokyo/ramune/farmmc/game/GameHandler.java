package tokyo.ramune.farmmc.game;

import tokyo.ramune.farmmc.ModeHandler;
import tokyo.ramune.farmmc.core.command.CommandHandler;
import tokyo.ramune.farmmc.core.listener.ListenerHandler;
import tokyo.ramune.farmmc.core.setting.CoreSettingHandler;
import tokyo.ramune.farmmc.game.crop.CropArtificialHandler;
import tokyo.ramune.farmmc.game.crop.FarmServantHandler;
import tokyo.ramune.farmmc.game.listener.entity.*;
import tokyo.ramune.farmmc.game.listener.farm.*;
import tokyo.ramune.farmmc.game.listener.player.*;
import tokyo.ramune.farmmc.game.listener.quest.FarmQuestListener;
import tokyo.ramune.farmmc.game.listener.world.*;
import tokyo.ramune.farmmc.game.player.PlayerHandler;
import tokyo.ramune.farmmc.game.quest.FarmQuestHandler;
import tokyo.ramune.farmmc.game.statistic.StatisticHandler;
import tokyo.ramune.farmmc.game.subcommand.CookSubCommand;

public class GameHandler implements ModeHandler {
    private static GameHandler instance;

    public static GameHandler getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;

        PlayerHandler.createTable();
        StatisticHandler.createTable();
        FarmQuestHandler.createTable();
        FarmServantHandler.initialize();
        CropArtificialHandler.createTable();
        CoreSettingHandler.createTable();
        ListenerHandler.registerListeners(
                new EntityBlockChangeListener(),
                new EntityBreedListener(),
                new EntityDamageByEntityListener(),
                new EntityDeathListener(),
                new EntityTargetListener(),

                new FarmCropHarvestListener(),
                new FarmCropPlantListener(),
                new FarmMenuClickListener(),
                new FarmPlayerChangeExpListener(),
                new FarmPlayerLevelUpListener(),

                new PlayerArmorStandManipulateListener(),
                new PlayerHarvestBlockListener(),
                new PlayerInteractListener(),
                new PlayerJoinListener(),
                new PlayerMoveListener(),
                new PlayerQuitListener(),

                new FarmQuestListener(),

                new AnvilTakeResultListener(),
                new BlockBreakBlockListener(),
                new BlockBreakListener(),
                new BlockFertilizeListener(),
                new BlockGrowListener(),
                new BlockPlaceListener(),
                new BlockPreDispenseListener(),
                new BlockSpreadListener(),
                new PrepareAnvilListener(),
                new StructureGrowListener());
        CommandHandler.registerSubCommands(new CookSubCommand());
    }

    @Override
    public void onUnload() {
    }
}

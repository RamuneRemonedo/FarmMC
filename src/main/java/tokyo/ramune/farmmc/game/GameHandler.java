package tokyo.ramune.farmmc.game;

import org.bukkit.event.Listener;
import org.jetbrains.annotations.Nullable;
import tokyo.ramune.farmmc.ModeHandler;
import tokyo.ramune.farmmc.core.command.SubCommand;
import tokyo.ramune.farmmc.core.setting.CoreSettingHandler;
import tokyo.ramune.farmmc.game.crop.CropArtificialHandler;
import tokyo.ramune.farmmc.game.listener.entity.*;
import tokyo.ramune.farmmc.game.listener.farm.FarmCropHarvestListener;
import tokyo.ramune.farmmc.game.listener.farm.FarmCropPlantListener;
import tokyo.ramune.farmmc.game.listener.farm.FarmPlayerChangeExpListener;
import tokyo.ramune.farmmc.game.listener.farm.FarmPlayerLevelUpListener;
import tokyo.ramune.farmmc.game.listener.player.*;
import tokyo.ramune.farmmc.game.listener.quest.QuestCompleteListener;
import tokyo.ramune.farmmc.game.listener.quest.QuestListener;
import tokyo.ramune.farmmc.game.listener.world.*;
import tokyo.ramune.farmmc.game.player.PlayerHandler;
import tokyo.ramune.farmmc.game.quest.QuestHandler;
import tokyo.ramune.farmmc.game.servant.ServantHandler;
import tokyo.ramune.farmmc.game.statistic.StatisticHandler;
import tokyo.ramune.farmmc.game.subcommand.CookSubCommand;

import java.util.Set;

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
        QuestHandler.createTable();
        ServantHandler.initialize();
        CropArtificialHandler.createTable();
        CoreSettingHandler.createTable();

        QuestHandler.registerServerMenuItem();
    }

    @Override
    public void onUnload() {
    }

    @Nullable
    @Override
    public Set<Listener> getListeners() {
        return Set.of(
                new EntityBlockChangeListener(),
                new EntityBreedListener(),
                new EntityDamageByEntityListener(),
                new EntityDeathListener(),
                new EntityTargetListener(),

                new FarmCropHarvestListener(),
                new FarmCropPlantListener(),
                new FarmPlayerChangeExpListener(),
                new FarmPlayerLevelUpListener(),

                new PlayerArmorStandManipulateListener(),
                new PlayerHarvestBlockListener(),
                new PlayerJoinListener(),
                new PlayerMoveListener(),
                new PlayerQuitListener(),

                new QuestCompleteListener(),
                new QuestListener(),

                new AnvilTakeResultListener(),
                new BlockBreakBlockListener(),
                new BlockBreakListener(),
                new BlockFertilizeListener(),
                new BlockGrowListener(),
                new BlockPistonListener(),
                new BlockPlaceListener(),
                new BlockPreDispenseListener(),
                new BlockSpreadListener(),
                new PrepareAnvilListener(),
                new StructureGrowListener());
    }

    @Nullable
    @Override
    public Set<SubCommand> getSubCommands() {
        return Set.of(new CookSubCommand());
    }
}

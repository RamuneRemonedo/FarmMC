package tokyo.ramune.farmmc.game;

import tokyo.ramune.farmmc.ModeHandler;
import tokyo.ramune.farmmc.core.listener.ListenerHandler;
import tokyo.ramune.farmmc.game.listener.entity.*;
import tokyo.ramune.farmmc.game.listener.farm.*;
import tokyo.ramune.farmmc.game.listener.player.PlayerHarvestBlockListener;
import tokyo.ramune.farmmc.game.listener.player.PlayerJoinListener;
import tokyo.ramune.farmmc.game.listener.player.PlayerMoveListener;
import tokyo.ramune.farmmc.game.listener.player.PlayerQuitListener;
import tokyo.ramune.farmmc.game.listener.quest.FarmQuestListener;
import tokyo.ramune.farmmc.game.listener.world.*;
import tokyo.ramune.farmmc.game.player.PlayerHandler;
import tokyo.ramune.farmmc.game.quest.FarmQuestHandler;
import tokyo.ramune.farmmc.game.statistic.StatisticHandler;

public class FarmGameHandler implements ModeHandler {
    private static FarmGameHandler instance;

    public static FarmGameHandler getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;

        PlayerHandler.createTable();
        StatisticHandler.createTable();
        FarmQuestHandler.createTable();
        ListenerHandler.registerListeners(
                new EntityBlockChangeListener(),
                new EntityBreedListener(),
                new EntityDamageByEntityListener(),
                new EntityDeathListener(),
                new EntityMoveListener(),

                new FarmCropHarvestListener(),
                new FarmCropPlantListener(),
                new FarmMenuClickListener(),
                new FarmPlayerChangeExpListener(),
                new FarmPlayerLevelUpListener(),

                new PlayerHarvestBlockListener(),
                new PlayerJoinListener(),
                new PlayerMoveListener(),
                new PlayerQuitListener(),

                new FarmQuestListener(),

                new BlockBreakListener(),
                new BlockFertilizeListener(),
                new BlockGrowListener(),
                new BlockPlaceListener(),
                new BlockPreDispenseListener(),
                new BlockSpreadListener(),
                new StructureGrowListener());
    }

    @Override
    public void onUnload() {

    }
}

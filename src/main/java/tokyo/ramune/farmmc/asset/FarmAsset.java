package tokyo.ramune.farmmc.asset;

import org.bukkit.Bukkit;
import tokyo.ramune.farmmc.FarmMC;

import javax.annotation.Nonnull;

public class FarmAsset {
    private final int x, y;
    private FarmAssetType type;
    private boolean isGrown;

    public FarmAsset(int x, int y, @Nonnull FarmAssetType type, boolean isGrown) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.isGrown = isGrown;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public FarmAssetType getType() {
        return type;
    }

    public void setType(FarmAssetType type) {
        this.type = type;
    }

    public boolean isGrown() {
        return isGrown;
    }

    public void runGrowTask() {
        Bukkit.getScheduler().runTaskLaterAsynchronously(FarmMC.getPlugin(), () -> {

        }, 200);
    }

    public void setGrown(boolean grown) {
        isGrown = grown;
    }
}

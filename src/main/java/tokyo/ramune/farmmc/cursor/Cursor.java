package tokyo.ramune.farmmc.cursor;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface Cursor {

    void spawn(@Nonnull Location location);

    Block getTargetBlock();

    @Nullable
    ArmorStand getCursorEntity();

    Player getPlayer();

    void setHeadPose(@Nonnull EulerAngle headPose);

    @Nullable
    EulerAngle getHeadPose();

    void setPositionCorrection(@Nonnull Vector positionCorrection);

    Vector getPositionCorrection();

    Vector getDefaultPositionCorrection();

    void resetPositionCorrection();

    void setVisible(boolean visible);

    boolean isVisible();

    void setCursorItemStack(@Nonnull ItemStack itemStack);

    @Nullable
    ItemStack getCursorItemStack();

    void setLocation(@Nonnull Location location);

    Location getLocation();

    void teleport();

    void remove();
}

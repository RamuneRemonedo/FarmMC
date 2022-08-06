package tokyo.ramune.farmmc.cursor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import tokyo.ramune.farmmc.FarmMC;
import tokyo.ramune.farmmc.player.FarmPlayer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Objects;

public class CursorManager {

    private final ArrayList<Cursor> cursors = new ArrayList<>();

    public CursorManager() {
    }

    public ArrayList<Cursor> getCursors() {
        return cursors;
    }

    public void removeAllCursor() {
        cursors.forEach(cursor -> {
            cursor.remove();
            cursors.remove(cursor);
        });
    }

    public boolean isExistsCursor(@Nonnull Player player) {
        for (Cursor cursor : cursors) {
            if (cursor.getPlayer().getPlayer().equals(player)) return true;
        }
        return false;
    }

    @Nullable
    public Cursor getCursor(@Nonnull Player player) {
        if (!isExistsCursor(player)) return createCursor(player);
        for (Cursor cursor : cursors) {
            if (cursor.getPlayer().getPlayer().equals(player)) return cursor;
        }
        return null;
    }

    @Nullable
    public Cursor getCursor(@Nonnull Entity entity) {
        for (Cursor cursor : cursors) {
            if (cursor.getCursorEntity().equals(entity)) return cursor;
        }
        return null;
    }

    protected Cursor createCursor(@Nonnull Player player) {
        Cursor cursor = new Cursor() {
            private final Player linkedPlayer = player;

            private ArmorStand cursor;
            private ItemStack headItem = new ItemStack(Material.QUARTZ_STAIRS);
            private EulerAngle headPose = new EulerAngle(5.5, 0, 0);
            private Location location;
            private Vector positionCorrection = new Vector(0.45, 1, 0.6);

            @Override
            public void spawn() {
                cursor = (ArmorStand) Objects.requireNonNull(linkedPlayer.getPlayer()).getWorld().spawnEntity(linkedPlayer.getPlayer().getLocation(), EntityType.ARMOR_STAND);
                this.location = cursor.getLocation().getBlock().getLocation();
                cursor.getEquipment().setHelmet(headItem);
                cursor.setHeadPose(headPose);
                cursor.setGravity(false);
                cursor.setMarker(true);
                cursor.setSmall(true);
                cursor.setVisible(false);
            }

            @Override
            public Block getTargetBlock() {
                return cursor.getLocation().clone().add(0, -1, 0).getBlock();
            }

            @Override
            public ArmorStand getCursorEntity() {
                return cursor;
            }

            @Override
            public FarmPlayer getPlayer() {
                return FarmMC.getFarmPlayerManager().getFarmPlayer(player);
            }

            @Override
            public void setHeadPose(@Nonnull EulerAngle headPose) {
                this.headPose = headPose;
                teleport();
            }

            @Nullable
            @Override
            public EulerAngle getHeadPose() {
                return headPose;
            }

            @Override
            public void setPositionCorrection(@Nonnull Vector positionCorrection) {
                this.positionCorrection = positionCorrection;
            }

            @Override
            public Vector getPositionCorrection() {
                return positionCorrection;
            }

            @Override
            public Vector getDefaultPositionCorrection() {
                return new Vector(0.45, 1, 0.6);
            }

            @Override
            public void resetPositionCorrection() {
                positionCorrection = new Vector(0.45, 1, 0.6);
            }

            @Override
            public void setVisible(boolean visible) {
                if (visible == (cursor != null)) return;
                if (visible) {
                    spawn();
                } else {
                    cursor.remove();
                    cursor = null;
                }
            }

            @Override
            public boolean isVisible() {
                return cursor != null;
            }

            @Override
            public void setCursorItemStack(@Nonnull ItemStack itemStack) {
                headItem = itemStack;
                if (cursor == null) return;
                cursor.getEquipment().setHelmet(itemStack);
            }

            @Nullable
            @Override
            public ItemStack getCursorItemStack() {
                return headItem;
            }

            @Override
            public void setLocation(@Nonnull Location location) {
                this.location = location;
            }

            @Override
            public Location getLocation() {
                return location;
            }

            @Override
            public void teleport() {
                if (cursor == null) return;
                if (cursor.getLocation().equals(location) && cursor.getHeadPose().equals(headPose)) return;
                Location from = cursor.getLocation();
                Location to = location.clone().add(positionCorrection);
                CursorMoveEvent cursorMoveEvent = new CursorMoveEvent(this, from, to);
                Bukkit.getPluginManager().callEvent(cursorMoveEvent);
                if (cursorMoveEvent.isCancelled()) return;
                cursor.setHeadPose(headPose);
                cursor.teleport(to);
            }

            @Override
            public void remove() {
                if (cursor == null) return;
                cursor.remove();
                cursors.remove(this);
            }
        };
        cursors.add(cursor);
        return cursor;
    }
}
package tokyo.ramune.farmmc.game.crop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import tokyo.ramune.farmmc.game.GameHandler;

import java.util.Arrays;

public class FarmServantEntity {
    private final NameTagHandler nameTagHandler;
    private final Location location;
    private ArmorStand servant;

    public FarmServantEntity(Location location) {
        this.location = location;

        spawn();

        this.nameTagHandler = new NameTagHandler(location);

        nameTagHandler.setNameTags(
                Arrays.asList(
                        "message",
                        "subTitle",
                        "title")
        );
        nameTagHandler.setVisible(0, false);
    }

    public void setTitle(String text) {
        nameTagHandler.setNameTag(2, text);
    }

    public void setSubTitle(String text) {
        nameTagHandler.setNameTag(1, text);
    }

    public ArmorStand getServantEntity() {
        return servant;
    }

    public Location getLocation() {
        return location;
    }

    private void spawn() {
        remove();

        location.set(location.getBlockX() + 0.5, location.getBlockY(), location.getBlockZ() + 0.5);
        location.setPitch(0);
        servant = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        servant.setVisible(false);

        servant.setSmall(true);
        servant.setArms(true);
        servant.setBasePlate(false);
        servant.setGravity(false);
        servant.setSilent(true);
        servant.setInvulnerable(true);

        EntityEquipment equipment = servant.getEquipment();
        equipment.setHelmet(new ItemStack(Material.PLAYER_HEAD));
        equipment.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        equipment.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        equipment.setBoots(new ItemStack(Material.LEATHER_BOOTS));
        equipment.setItemInMainHand(new ItemStack(Material.GOLDEN_HOE));

        if (nameTagHandler != null)
            nameTagHandler.setVisible(true);

        servant.setVisible(true);
    }

    public void addYaw(float yaw) {
        Bukkit.getScheduler().runTaskAsynchronously(GameHandler.getInstance().getPlugin(), () -> {
            try {
                Location location = servant.getLocation();
                for (int i = 0; i < 5; i++) {
                    location.setYaw(servant.getLocation().getYaw() + yaw / 5);
                    servant.teleportAsync(location);
                    Thread.sleep(50);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Location location = servant.getLocation();
                location.setYaw(location.getYaw() + yaw);
            }
        });
    }

    public void setYaw(float yaw) {
        Location location = servant.getLocation();
        addYaw(yaw - location.getYaw());
    }

    public void runArmAnimation() {
        Bukkit.getScheduler().runTaskAsynchronously(GameHandler.getInstance().getPlugin(), () -> {
            try {
                servant.setRightArmPose(new EulerAngle(0, 0, 0));
                for (int i = 0; i < 3; i++) {
                    for (int n = 0; n < 3; n++) {
                        servant.setRightArmPose(servant.getRightArmPose().add(-0.6, 0, 0));
                        Thread.sleep(60);
                    }
                    for (int n = 0; n < 3; n++) {
                        servant.setRightArmPose(servant.getRightArmPose().add(0.6, 0, 0));
                        Thread.sleep(40);
                    }
                }
            } catch (Exception ignored) {
            }
        });
    }

    public void runSpeakAnimation(String message, Location lookAt) {
        Bukkit.getScheduler().runTaskAsynchronously(GameHandler.getInstance().getPlugin(), () -> {
            try {
                setYaw((float) Math.toDegrees(Math.atan2(
                        lookAt.getZ() - servant.getLocation().getZ(), lookAt.getX() - servant.getLocation().getX())) - 90);
                Thread.sleep(250);
                nameTagHandler.setVisible(0, true);
                nameTagHandler.setNameTag(0, message);
                servant.getWorld().playSound(servant.getLocation(), Sound.ENTITY_FOX_AMBIENT, 1, 1);
                Thread.sleep(2000);
                nameTagHandler.setVisible(0, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void remove() {
        if (servant != null)
            servant.remove();
        servant = null;

        if (nameTagHandler != null)
            nameTagHandler.removeNameTags();
    }

    public void respawn() {
        remove();
        spawn();
    }
}

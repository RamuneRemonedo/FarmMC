package tokyo.ramune.farmmc.game.entity;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import javax.annotation.Nonnull;

public class NameTag {
    private final int index;
    private final Location location;
    private ArmorStand armorStand;
    private String text;

    public NameTag(@Nonnull String text, int index, @Nonnull Location location) {
        this.text = text;
        this.location = location;
        this.index = index;

        spawn();
    }

    public boolean getVisible() {
        return armorStand != null;
    }

    public void setVisible(boolean visible) {
        armorStand.setCustomNameVisible(visible);
    }

    public void setText(@Nonnull String text) {
        this.text = text;

        if (armorStand != null)
            armorStand.customName(Component.text(text));
    }

    public int getIndex() {
        return index;
    }

    public void respawn() {
        spawn();
    }

    private void spawn() {
        remove();

        armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        armorStand.customName(Component.text(text));
        armorStand.setCustomNameVisible(true);
        armorStand.setGravity(false);
        armorStand.setInvisible(true);
        armorStand.setVisible(true);
        armorStand.setMarker(true);

        armorStand.teleport(location.clone().add(0, 1 + (index * 0.2), 0));
    }

    public void remove() {
        if (armorStand == null)
            return;

        armorStand.remove();
        armorStand = null;
    }
}

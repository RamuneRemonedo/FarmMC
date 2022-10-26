package tokyo.ramune.farmmc.game.entity;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;

import javax.annotation.Nonnull;
import java.util.Objects;

public class FarmEntityHandler {
    public static void initializeEntity(@Nonnull LivingEntity entity) {
        EntityEquipment equipmentSlot = Objects.requireNonNull(entity.getEquipment());
        equipmentSlot.setHelmet(FarmEntityItem.HELMET.getItemStack());
    }

    public static void updateNameTag(@Nonnull LivingEntity entity) {
        entity.setCustomNameVisible(true);
        entity.customName(Component.text(entity.getType().name().toLowerCase() + " " + entity.getHealth() + "/" + entity.getMaxHealth()));
    }
}

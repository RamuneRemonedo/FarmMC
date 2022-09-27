package tokyo.ramune.farmmc.entity;

import org.bukkit.entity.EntityType;

import javax.annotation.Nonnull;

public interface FarmEntity {
    @Nonnull
    EntityType getType();

    @Nonnull
    String getName();

    int getWalkSpeed();
}

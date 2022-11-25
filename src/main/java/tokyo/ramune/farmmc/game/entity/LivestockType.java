package tokyo.ramune.farmmc.game.entity;

import org.bukkit.entity.EntityType;

public enum LivestockType {
    SHEEP(EntityType.SHEEP),
    COW(EntityType.COW),
    PIG(EntityType.PIG),
    CHICKEN(EntityType.CHICKEN),
    RABBIT(EntityType.RABBIT),
    WOLF(EntityType.WOLF),
    FOX(EntityType.FOX),
    CAT(EntityType.CAT),
    HORSE(EntityType.HORSE),
    DONKEY(EntityType.DONKEY),
    LLAMA(EntityType.LLAMA),
    OCELOT(EntityType.OCELOT),
    PANDA(EntityType.PANDA);

    private final EntityType type;

    LivestockType(EntityType type) {
        this.type = type;
    }
}

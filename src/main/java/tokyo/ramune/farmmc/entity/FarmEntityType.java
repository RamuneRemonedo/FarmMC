package tokyo.ramune.farmmc.entity;

import org.bukkit.entity.EntityType;

import javax.annotation.Nullable;

public enum FarmEntityType {
    GRASS(EntityType.SILVERFISH, 1, 0.5),
    WHEAT(EntityType.ZOMBIE, 2, 1),
    BEETROOT(EntityType.SKELETON, 1, 1),
    CARROT(EntityType.ZOMBIE_VILLAGER, 1, 1),
    POTATO(EntityType.CREEPER, 1, 1),
    MELON(EntityType.SPIDER, 1, 1),
    PUMPKIN(EntityType.CAVE_SPIDER, 1, 1),
    BAMBOO(EntityType.STRAY, 1, 1),
    CACAO_BEAN(EntityType.VINDICATOR, 1, 1),
    SUGAR_CANE(EntityType.WITCH, 1, 1),
    SWEET_BERRY(EntityType.SLIME, 1, 1),
    CACTUS(EntityType.HUSK, 1, 1),
    MUSHROOM(EntityType.VINDICATOR, 1, 1),
    KELP(EntityType.GUARDIAN, 1, 1),
    SEA_PICKLE(EntityType.ELDER_GUARDIAN, 1, 1),
    NETHER_WART(EntityType.MAGMA_CUBE, 1, 1),
    CHORUS_FRUIT(EntityType.ENDERMAN, 1, 1);

    private final EntityType entityType;
    private final double attackPower, defencePower;
    FarmEntityType(EntityType entityType, double attackPower, double defencePower) {
        this.entityType = entityType;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
    }

    @Nullable
    public static FarmEntityType cast(EntityType entityType) {
        for (FarmEntityType value : values()) {
            if (value.getEntityType().equals(entityType))
                return value;
        }
        return null;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public double getAttackPower() {
        return attackPower;
    }

    public double getDefencePower() {
        return defencePower;
    }
}

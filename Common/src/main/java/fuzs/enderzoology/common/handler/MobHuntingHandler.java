package fuzs.enderzoology.common.handler;

import fuzs.enderzoology.common.init.ModRegistry;
import fuzs.enderzoology.common.world.entity.monster.DireWolf;
import fuzs.enderzoology.common.world.entity.monster.FallenMount;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypeIds;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import org.jspecify.annotations.Nullable;

public class MobHuntingHandler {

    public static void onEntityLoad(Entity entity, ServerLevel serverLevel, boolean isLoadedFromDisk, @Nullable EntitySpawnReason entitySpawnReason) {
        if (entity instanceof PathfinderMob mob) {
            if (mob.is(EntityTypeIds.WOLF)) {
                mob.goalSelector.addGoal(3, new AvoidEntityGoal<>(mob, DireWolf.class, 16.0F, 1.0, 1.2));
            }

            if (mob.is(ModRegistry.FALLEN_MOUNT_TARGETS_ENTITY_TYPE_TAG)) {
                mob.goalSelector.addGoal(3, new AvoidEntityGoal<>(mob, FallenMount.class, 16.0F, 1.5, 1.8));
            }
        }
    }
}

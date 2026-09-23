package fuzs.enderzoology.common.world.level;

import fuzs.enderzoology.common.init.ModTags;
import fuzs.enderzoology.common.world.entity.item.PrimedCharge;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class EnderExplosionHelper {

    public static void explode(ServerLevel serverLevel, Entity exploder, @Nullable DamageSource damageSource, double x, double y, double z, float radius, Level.ExplosionInteraction explosionInteraction, EnderExplosionType type, boolean spawnLingeringCloud) {
        Objects.requireNonNull(exploder, "exploder is null");
        // we use the damage calculator for holding custom explosion data to avoid having to implement our own explosion
        EnderExplosionDamageCalculator damageCalculator = new EnderExplosionDamageCalculator(type,
                exploder,
                spawnLingeringCloud);
        serverLevel.explode(exploder, damageSource, damageCalculator, x, y, z, radius, false, explosionInteraction);
    }

    public static void onExplosionDetonate(ServerLevel serverLevel, ServerExplosion explosion, List<BlockPos> affectedBlocks, List<Entity> affectedEntities) {
        if (explosion.damageCalculator instanceof EnderExplosionDamageCalculator damageCalculator) {
            for (Entity entity : affectedEntities) {
                if (entity instanceof LivingEntity livingEntity && entity.isAlive()
                        && !entity.is(ModTags.Entities.CONCUSSION_IMMUNE_ENTITY_TYPE_TAG)) {
                    Vec3 originalPosition = livingEntity.position();
                    if (damageCalculator.type.isTeleport()) {
                        EnderTeleportHelper.teleportEntity(serverLevel, livingEntity, 48, true);
                    }
                    if (damageCalculator.type.isConfusion()) {
                        applyConfusionPotion(explosion.center(), originalPosition, livingEntity, explosion.radius());
                    }
                }
            }
            affectedEntities.removeIf(entity -> !(entity instanceof PrimedTnt));
            // don't destroy blocks, but activate other explosives
            affectedBlocks.removeIf((BlockPos blockPos) -> serverLevel.getBlockState(blockPos)
                    .getBlock()
                    .dropFromExplosion(explosion));
            if (damageCalculator.lingeringCloud) {
                spawnLingeringCloud(serverLevel,
                        explosion.center(),
                        damageCalculator.type.createEffects((int) explosion.radius()));
            }
        }
    }

    private static void applyConfusionPotion(Vec3 sourcePosition, Vec3 entityPosition, LivingEntity entity, float explosionRadius) {
        if (entity.isAffectedByPotions()) {
            double distance = sourcePosition.distanceToSqr(entityPosition);
            if (distance < explosionRadius * explosionRadius) {
                double multiplier = 1.0 - Math.sqrt(distance) / explosionRadius;
                int duration = 100 + (int) (multiplier * 200.0);
                entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, duration, 0));
            }
        }
    }

    private static void spawnLingeringCloud(Level level, Vec3 position, List<MobEffectInstance> effects) {
        AreaEffectCloud areaEffectCloud = new AreaEffectCloud(level, position.x, position.y, position.z);
        areaEffectCloud.setRadius(2.5F);
        areaEffectCloud.setRadiusOnUse(-0.5F);
        areaEffectCloud.setWaitTime(10);
        areaEffectCloud.setDuration(areaEffectCloud.getDuration() / 2);
        areaEffectCloud.setRadiusPerTick(-areaEffectCloud.getRadius() / (float) areaEffectCloud.getDuration());
        effects.forEach(areaEffectCloud::addEffect);
        level.addFreshEntity(areaEffectCloud);
    }

    /**
     * @see net.minecraft.world.level.block.TntBlock#prime(Level, BlockPos, LivingEntity, ItemStack)
     */
    public static boolean primeCharge(Level level, BlockPos pos, @Nullable LivingEntity source, ItemStack itemStack, EnderExplosionType type) {
        if (!(level instanceof ServerLevel serverLevel && serverLevel.getGameRules().get(GameRules.TNT_EXPLODES))) {
            return false;
        } else if (source instanceof Player player && player.gameMode() == GameType.ADVENTURE
                && !itemStack.canBreakBlockInAdventureMode(new BlockInWorld(level, pos, false))) {
            return false;
        } else {
            PrimedTnt primedTnt = new PrimedCharge(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, source, type);
            level.addFreshEntity(primedTnt);
            level.playSound(null,
                    primedTnt.getX(),
                    primedTnt.getY(),
                    primedTnt.getZ(),
                    SoundEvents.TNT_PRIMED,
                    SoundSource.BLOCKS,
                    1.0F,
                    1.0F);
            level.gameEvent(source, GameEvent.PRIME_FUSE, pos);
            return true;
        }
    }

    /**
     * @see net.minecraft.world.level.block.TntBlock#wasExploded(ServerLevel, BlockPos, Explosion)
     */
    public static void chargeWasExploded(ServerLevel level, BlockPos pos, Explosion explosion, EnderExplosionType type) {
        PrimedTnt primedTnt = new PrimedCharge(level,
                pos.getX() + 0.5,
                pos.getY(),
                pos.getZ() + 0.5,
                explosion.getIndirectSourceEntity(),
                type);
        int fuse = primedTnt.getFuse();
        primedTnt.setFuse(level.getRandom().nextInt(fuse / 4) + fuse / 8);
        level.addFreshEntity(primedTnt);
    }

    public static class EnderExplosionDamageCalculator extends EntityBasedExplosionDamageCalculator {
        public final EnderExplosionType type;
        public final boolean lingeringCloud;

        public EnderExplosionDamageCalculator(EnderExplosionType type, Entity source, boolean lingeringCloud) {
            super(source);
            this.type = type;
            this.lingeringCloud = lingeringCloud;
        }
    }
}

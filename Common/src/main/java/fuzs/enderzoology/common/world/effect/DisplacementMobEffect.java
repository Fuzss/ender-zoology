package fuzs.enderzoology.common.world.effect;

import fuzs.enderzoology.common.world.level.EnderTeleportHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.InstantaneousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class DisplacementMobEffect extends InstantaneousMobEffect {

    public DisplacementMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity mob, int amplification) {
        EnderTeleportHelper.teleportEntity(level, mob, (amplification + 1) * 8, true);
        return super.applyEffectTick(level, mob, amplification);
    }
}

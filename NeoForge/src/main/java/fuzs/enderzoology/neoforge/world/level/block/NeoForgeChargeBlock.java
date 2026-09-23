package fuzs.enderzoology.neoforge.world.level.block;

import fuzs.enderzoology.common.world.level.EnderExplosionHelper;
import fuzs.enderzoology.common.world.level.EnderExplosionType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class NeoForgeChargeBlock extends TntBlock {
    private final EnderExplosionType type;

    public NeoForgeChargeBlock(EnderExplosionType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    @Override
    public boolean onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction direction, @Nullable LivingEntity source, ItemStack itemStack) {
        return EnderExplosionHelper.primeCharge(level, pos, source, itemStack, this.type);
    }

    @Override
    public void wasExploded(ServerLevel level, BlockPos pos, Explosion explosion) {
        EnderExplosionHelper.chargeWasExploded(level, pos, explosion, this.type);
    }
}

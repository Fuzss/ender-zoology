package fuzs.enderzoology.fabric.world.level.block;

import fuzs.enderzoology.common.world.level.EnderExplosionHelper;
import fuzs.enderzoology.common.world.level.EnderExplosionType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

/**
 * Basically a copy of the {@link TntBlock} class on NeoForge, which patches in additional methods that support
 * customising explosion behaviour.
 */
public class FabricChargeBlock extends TntBlock {
    private final EnderExplosionType type;

    public FabricChargeBlock(EnderExplosionType type, Properties properties) {
        super(properties);
        this.type = type;
    }

    public boolean onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction direction, @Nullable LivingEntity source, ItemStack itemStack) {
        return EnderExplosionHelper.primeCharge(level, pos, source, itemStack, this.type);
    }

    @Override
    public void wasExploded(ServerLevel level, BlockPos pos, Explosion explosion) {
        EnderExplosionHelper.chargeWasExploded(level, pos, explosion, this.type);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!oldState.is(state.getBlock())) {
            if (level.hasNeighborSignal(pos) && this.onCaughtFire(state, level, pos, null, null, ItemStack.EMPTY)) {
                level.removeBlock(pos, false);
            }
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston) {
        if (level.hasNeighborSignal(pos) && this.onCaughtFire(state, level, pos, null, null, ItemStack.EMPTY)) {
            level.removeBlock(pos, false);
        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide() && !player.isCreative() && state.getValue(UNSTABLE)) {
            this.onCaughtFire(state, level, pos, null, null, ItemStack.EMPTY);
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!itemStack.is(Items.FLINT_AND_STEEL) && !itemStack.is(Items.FIRE_CHARGE)) {
            return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
        } else {
            if (this.onCaughtFire(state, level, pos, hitResult.getDirection(), player, itemStack)) {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
                Item item = itemStack.getItem();
                if (!player.isCreative()) {
                    if (itemStack.is(Items.FLINT_AND_STEEL)) {
                        itemStack.hurtAndBreak(1, player, hand.asEquipmentSlot());
                    } else {
                        itemStack.consume(1, player);
                    }
                }

                player.awardStat(Stats.ITEM_USED.get(item));
            } else if (level instanceof ServerLevel serverLevel) {
                if (!serverLevel.getGameRules().get(GameRules.TNT_EXPLODES)) {
                    player.sendOverlayMessage(Component.translatable("block.minecraft.tnt.disabled"));
                    return InteractionResult.PASS;
                }
            }

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public void onProjectileHit(Level level, BlockState state, BlockHitResult hitResult, Projectile projectile) {
        if (level instanceof ServerLevel serverLevel) {
            BlockPos pos = hitResult.getBlockPos();
            Entity owner = projectile.getOwner();
            if (projectile.isOnFire() && projectile.mayInteract(serverLevel, pos) && this.onCaughtFire(state,
                    serverLevel,
                    pos,
                    null,
                    owner instanceof LivingEntity livingEntity ? livingEntity : null,
                    projectile instanceof AbstractArrow arr ? arr.getPickupItemStackOrigin() : ItemStack.EMPTY)) {
                serverLevel.removeBlock(pos, false);
            }
        }
    }
}

package fuzs.enderzoology.common.client.handler;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.enderzoology.common.init.ModItems;
import fuzs.puzzleslib.common.api.event.v1.core.EventResult;
import fuzs.puzzleslib.common.api.event.v1.data.MutableFloat;
import net.minecraft.client.renderer.FirstPersonHandsAndItemsRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.FirstPersonHandsAndItemsRenderState;
import net.minecraft.client.renderer.state.level.PlayerRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class FovModifierHandler {

    /**
     * @see net.minecraft.client.player.AbstractClientPlayer#getFieldOfViewModifier(boolean, float)
     */
    public static void onComputeFovModifier(Player player, MutableFloat fieldOfViewModifier) {
        if (player.isUsingItem()) {
            ItemStack itemStack = player.getUseItem();
            if (itemStack.is(ModItems.HUNTING_BOW_ITEM.value())) {
                float scale = Math.min(player.getTicksUsingItem() / 20.0F, 1.0F);
                float modifier = 1.0F - Mth.square(scale) * 0.15F;
                fieldOfViewModifier.mapAsFloat((Float value) -> value * modifier);
            }
        }
    }

    public static EventResult onSubmitArmWithItem(FirstPersonHandsAndItemsRenderer handsAndItemsRenderer, PlayerRenderState playerState, FirstPersonHandsAndItemsRenderState state, float partialTicks, float xRot, InteractionHand hand, float attack, ItemStack itemStack, float inverseArmHeight, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords) {
        if (state.useItemRemainingTicks <= 0) {
            return EventResult.PASS;
        }

        if (hand == InteractionHand.MAIN_HAND && state.offHandItem.is(ModItems.HUNTING_BOW_ITEM)) {
            return EventResult.INTERRUPT;
        }

        if (hand == InteractionHand.OFF_HAND && state.mainHandItem.is(ModItems.HUNTING_BOW_ITEM)) {
            return EventResult.INTERRUPT;
        }

        return EventResult.PASS;
    }
}

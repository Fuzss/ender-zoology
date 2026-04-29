package fuzs.enderzoology.common.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import fuzs.enderzoology.common.EnderZoology;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;

public class InfestedZombieOuterLayer<T extends ZombieRenderState> extends RenderLayer<T, ZombieModel<T>> {
    private static final Identifier TEXTURE_LOCATION = EnderZoology.id(
            "textures/entity/zombie/infested_zombie_outer_layer.png");

    public InfestedZombieOuterLayer(RenderLayerParent<T, ZombieModel<T>> renderer, EntityModelSet entityModelSet) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector nodeCollector, int packedLight, T renderState, float yRot, float xRot) {
        coloredCutoutModelCopyLayerRender(this.getParentModel(),
                TEXTURE_LOCATION,
                poseStack,
                nodeCollector,
                packedLight,
                renderState,
                -1,
                renderState.outlineColor);
    }
}

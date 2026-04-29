package fuzs.enderzoology.common.client.renderer.entity;

import fuzs.enderzoology.common.EnderZoology;
import fuzs.enderzoology.common.client.model.geom.ModModelLayers;
import fuzs.enderzoology.common.world.entity.monster.FallenKnight;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;

public class FallenKnightRenderer extends AbstractSkeletonRenderer<FallenKnight, SkeletonRenderState> {
    private static final Identifier TEXTURE_LOCATION = EnderZoology.id(
            "textures/entity/skeleton/fallen_knight.png");

    public FallenKnightRenderer(EntityRendererProvider.Context context) {
        super(context, ModModelLayers.FALLEN_KNIGHT, ModModelLayers.FALLEN_KNIGHT_ARMOR);
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState();
    }

    @Override
    public Identifier getTextureLocation(SkeletonRenderState skeletonRenderState) {
        return TEXTURE_LOCATION;
    }
}

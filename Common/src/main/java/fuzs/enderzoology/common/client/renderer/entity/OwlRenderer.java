package fuzs.enderzoology.common.client.renderer.entity;

import fuzs.enderzoology.common.EnderZoology;
import fuzs.enderzoology.common.client.model.geom.ModModelLayers;
import fuzs.enderzoology.common.client.model.animal.owl.OwlModel;
import fuzs.enderzoology.common.client.renderer.entity.state.OwlRenderState;
import fuzs.enderzoology.common.world.entity.animal.Owl;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.LivingEntityEmissiveLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class OwlRenderer extends AgeableMobRenderer<Owl, OwlRenderState, OwlModel> {
    private static final Identifier TEXTURE_LOCATION = EnderZoology.id("textures/entity/owl/owl.png");
    private static final Identifier EYES_TEXTURE_LOCATION = EnderZoology.id("textures/entity/owl/owl_eyes.png");

    public OwlRenderer(EntityRendererProvider.Context context) {
        super(context,
                new OwlModel(context.bakeLayer(ModModelLayers.OWL)),
                new OwlModel(context.bakeLayer(ModModelLayers.OWL_BABY)),
                0.3F);
        this.addLayer(new LivingEntityEmissiveLayer<>(this,
                (OwlRenderState renderState) -> EYES_TEXTURE_LOCATION,
                (OwlRenderState renderState, float ageInTicks) -> {
                    return renderState.isBaby ? 0.0F : 1.0F;
                },
                new OwlModel(context.bakeLayer(ModModelLayers.OWL_EYES)),
                RenderTypes::eyes,
                true));
        this.addLayer(new LivingEntityEmissiveLayer<>(this,
                (OwlRenderState renderState) -> EYES_TEXTURE_LOCATION,
                (OwlRenderState renderState, float ageInTicks) -> {
                    return renderState.isBaby ? 1.0F : 0.0F;
                },
                new OwlModel(context.bakeLayer(ModModelLayers.OWL_BABY_EYES)),
                RenderTypes::eyes,
                true));
    }

    @Override
    public OwlRenderState createRenderState() {
        return new OwlRenderState();
    }

    @Override
    public Identifier getTextureLocation(OwlRenderState renderState) {
        return TEXTURE_LOCATION;
    }

    @Override
    public void extractRenderState(Owl owl, OwlRenderState renderState, float partialTick) {
        super.extractRenderState(owl, renderState, partialTick);
        renderState.isFlying = owl.isFlying();
        float flap = Mth.lerp(partialTick, owl.oFlap, owl.flap);
        float flapSpeed = Mth.lerp(partialTick, owl.oFlapSpeed, owl.flapSpeed);
        renderState.flapAngle = (Mth.sin(flap) + 1.0F) * flapSpeed;
    }
}

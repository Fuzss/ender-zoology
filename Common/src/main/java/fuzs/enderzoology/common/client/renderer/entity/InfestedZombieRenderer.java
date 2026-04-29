package fuzs.enderzoology.common.client.renderer.entity;

import fuzs.enderzoology.common.EnderZoology;
import fuzs.enderzoology.common.client.model.geom.ModModelLayers;
import fuzs.enderzoology.common.client.renderer.entity.layers.InfestedZombieEyeLayer;
import fuzs.enderzoology.common.client.renderer.entity.layers.InfestedZombieOuterLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;

public class InfestedZombieRenderer extends ZombieRenderer {
    private static final Identifier TEXTURE_LOCATION = EnderZoology.id("textures/entity/zombie/infested_zombie.png");

    public InfestedZombieRenderer(EntityRendererProvider.Context context) {
        super(context,
                ModModelLayers.INFESTED_ZOMBIE,
                ModModelLayers.INFESTED_ZOMBIE_BABY,
                ModModelLayers.INFESTED_ZOMBIE_ARMOR,
                ModModelLayers.INFESTED_ZOMBIE_BABY_ARMOR);
        this.addLayer(new InfestedZombieOuterLayer<>(this, context.getModelSet()));
        this.addLayer(new InfestedZombieEyeLayer<>(this));
    }

    @Override
    public Identifier getTextureLocation(ZombieRenderState renderState) {
        return TEXTURE_LOCATION;
    }
}

package fuzs.enderzoology.common.client.renderer.entity.layers;

import fuzs.enderzoology.common.EnderZoology;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class InfestedZombieEyeLayer<T extends ZombieRenderState> extends EyesLayer<T, ZombieModel<T>> {
    private static final RenderType TEXTURE_LOCATION = RenderTypes.eyes(EnderZoology.id(
            "textures/entity/zombie/infested_zombie_eye.png"));

    public InfestedZombieEyeLayer(RenderLayerParent<T, ZombieModel<T>> renderer) {
        super(renderer);
    }

    @Override
    public RenderType renderType() {
        return TEXTURE_LOCATION;
    }
}

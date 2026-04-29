package fuzs.enderzoology.common.client.renderer.entity;

import fuzs.enderzoology.common.EnderZoology;
import fuzs.enderzoology.common.client.model.geom.ModModelLayers;
import fuzs.enderzoology.common.client.renderer.entity.state.FallenMountRenderState;
import fuzs.enderzoology.common.world.entity.monster.FallenMount;
import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.animal.equine.AbstractEquineModel;
import net.minecraft.client.model.animal.equine.HorseModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.SimpleEquipmentLayer;
import net.minecraft.client.renderer.entity.state.HorseRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.Identifier;

import java.util.Set;

/**
 * Copied baby models from Minecraft 1.21.11.
 */
public class FallenMountRenderer extends AbstractHorseRenderer<FallenMount, HorseRenderState, HorseModel> {
    protected static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(true,
            16.2F,
            1.36F,
            2.7272F,
            2.0F,
            20.0F,
            Set.of("head_parts"));
    public static final Identifier TEXTURE_LOCATION = EnderZoology.id("textures/entity/horse/fallen_mount.png");

    public FallenMountRenderer(EntityRendererProvider.Context context) {
        super(context,
                new HorseModel(context.bakeLayer(ModModelLayers.FALLEN_MOUNT)),
                new HorseModel(context.bakeLayer(ModModelLayers.FALLEN_MOUNT_BABY)));
        this.addLayer(new SimpleEquipmentLayer<>(this,
                context.getEquipmentRenderer(),
                EquipmentClientInfo.LayerType.HORSE_BODY,
                (HorseRenderState horseRenderState) -> horseRenderState.bodyArmorItem,
                new HorseModel(context.bakeLayer(ModModelLayers.FALLEN_MOUNT_ARMOR)),
                new HorseModel(context.bakeLayer(ModModelLayers.FALLEN_MOUNT_BABY_ARMOR))));
    }

    public static MeshDefinition createBabyMesh(CubeDeformation cubeDeformation) {
        return BABY_TRANSFORMER.apply(createFullScaleBabyMesh(cubeDeformation));
    }

    protected static MeshDefinition createFullScaleBabyMesh(CubeDeformation cubeDeformation) {
        MeshDefinition meshDefinition = AbstractEquineModel.createBodyMesh(cubeDeformation);
        PartDefinition partDefinition = meshDefinition.getRoot();
        CubeDeformation cubeDeformation2 = cubeDeformation.extend(0.0F, 5.5F, 0.0F);
        partDefinition.addOrReplaceChild("left_hind_leg",
                CubeListBuilder.create()
                        .texOffs(48, 21)
                        .mirror()
                        .addBox(-3.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, cubeDeformation2),
                PartPose.offset(4.0F, 14.0F, 7.0F));
        partDefinition.addOrReplaceChild("right_hind_leg",
                CubeListBuilder.create()
                        .texOffs(48, 21)
                        .addBox(-1.0F, -1.01F, -1.0F, 4.0F, 11.0F, 4.0F, cubeDeformation2),
                PartPose.offset(-4.0F, 14.0F, 7.0F));
        partDefinition.addOrReplaceChild("left_front_leg",
                CubeListBuilder.create()
                        .texOffs(48, 21)
                        .mirror()
                        .addBox(-3.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, cubeDeformation2),
                PartPose.offset(4.0F, 14.0F, -10.0F));
        partDefinition.addOrReplaceChild("right_front_leg",
                CubeListBuilder.create()
                        .texOffs(48, 21)
                        .addBox(-1.0F, -1.01F, -1.9F, 4.0F, 11.0F, 4.0F, cubeDeformation2),
                PartPose.offset(-4.0F, 14.0F, -10.0F));
        return meshDefinition;
    }

    @Override
    public HorseRenderState createRenderState() {
        return new FallenMountRenderState();
    }

    @Override
    public void extractRenderState(FallenMount entity, HorseRenderState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);
        reusedState.bodyArmorItem = entity.getBodyArmorItem().copy();
        ((FallenMountRenderState) reusedState).isShaking = entity.isConverting();
    }

    @Override
    public Identifier getTextureLocation(HorseRenderState renderState) {
        return TEXTURE_LOCATION;
    }

    @Override
    protected boolean isShaking(HorseRenderState renderState) {
        return super.isShaking(renderState) || ((FallenMountRenderState) renderState).isShaking;
    }
}

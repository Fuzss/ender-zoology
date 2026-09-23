package fuzs.enderzoology.common.init;

import com.mojang.serialization.MapCodec;
import fuzs.enderzoology.common.EnderZoology;
import fuzs.enderzoology.common.attachment.SoulboundItems;
import fuzs.enderzoology.common.world.effect.DisplacementMobEffect;
import fuzs.enderzoology.common.world.item.enchantment.effects.TeleportEntity;
import fuzs.puzzleslib.common.api.attachment.v4.DataAttachmentRegistry;
import fuzs.puzzleslib.common.api.attachment.v4.DataAttachmentType;
import fuzs.puzzleslib.common.api.init.v3.registry.RegistryManager;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(EnderZoology.MOD_ID);
    public static final Holder.Reference<MobEffect> DISPLACEMENT_MOB_EFFECT = REGISTRIES.registerMobEffect(
            "displacement",
            () -> new DisplacementMobEffect(MobEffectCategory.HARMFUL, 0X932423));
    public static final Holder.Reference<MapCodec<TeleportEntity>> TELEPORT_ENTITY_ENCHANTMENT_ENTITY_EFFECT_TYPE = REGISTRIES.register(
            Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE,
            "teleport_entity",
            () -> TeleportEntity.CODEC);
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab(
            ModItems.ENDER_FRAGMENT_ITEM);

    public static final DataAttachmentType<Entity, SoulboundItems> SOULBOUND_ITEMS_ATTACHMENT_TYPE = DataAttachmentRegistry.<SoulboundItems>entityBuilder()
            .persistent(SoulboundItems.CODEC)
            .build(EnderZoology.id("soulbound_items"));

    public static void bootstrap() {
        ModBlocks.bootstrap();
        ModItems.bootstrap();
        ModEntityTypes.bootstrap();
        ModPotions.bootstrap();
        ModSoundEvents.bootstrap();
    }
}

package fuzs.enderzoology.neoforge;

import fuzs.enderzoology.common.EnderZoology;
import fuzs.enderzoology.common.data.ModRecipeProvider;
import fuzs.enderzoology.common.data.loot.ModBlockLootProvider;
import fuzs.enderzoology.common.data.loot.ModEntityTypeLootProvider;
import fuzs.enderzoology.common.data.tags.ModBlockTagProvider;
import fuzs.enderzoology.common.data.tags.ModEnchantmentTagProvider;
import fuzs.enderzoology.common.data.tags.ModEntityTypeTagProvider;
import fuzs.enderzoology.common.data.tags.ModItemTagProvider;
import fuzs.enderzoology.common.init.ModRegistry;
import fuzs.enderzoology.neoforge.data.ModDataMapProvider;
import fuzs.enderzoology.neoforge.init.NeoForgeModRegistry;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.fml.common.Mod;

@Mod(EnderZoology.MOD_ID)
public class EnderZoologyNeoForge {

    public EnderZoologyNeoForge() {
        NeoForgeModRegistry.bootstrap();
        ModConstructor.construct(EnderZoology.MOD_ID, EnderZoology::new);
        DataProviderHelper.registerDataProviders(EnderZoology.MOD_ID,
                ModRegistry.REGISTRY_SET_BUILDER,
                ModBlockLootProvider::new,
                ModBlockTagProvider::new,
                ModItemTagProvider::new,
                ModEnchantmentTagProvider::new,
                ModEntityTypeLootProvider::new,
                ModEntityTypeTagProvider::new,
                ModRecipeProvider::new,
                ModDataMapProvider::new);
    }
}

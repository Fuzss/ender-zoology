package fuzs.enderzoology.neoforge;

import fuzs.enderzoology.common.EnderZoology;
import fuzs.enderzoology.common.data.loot.ModBlockLootProvider;
import fuzs.enderzoology.common.data.loot.ModEntityLootProvider;
import fuzs.enderzoology.common.data.recipes.ModBrewingProvider;
import fuzs.enderzoology.common.data.recipes.ModRecipeProvider;
import fuzs.enderzoology.common.data.tags.ModBlockTagsProvider;
import fuzs.enderzoology.common.data.tags.ModEnchantmentTagsProvider;
import fuzs.enderzoology.common.data.tags.ModEntityTypeTagsProvider;
import fuzs.enderzoology.common.data.tags.ModItemTagsProvider;
import fuzs.enderzoology.common.init.ModEnchantments;
import fuzs.enderzoology.neoforge.data.ModDataMapProvider;
import fuzs.enderzoology.neoforge.init.NeoForgeModRegistry;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.fml.common.Mod;

@Mod(EnderZoology.MOD_ID)
public class EnderZoologyNeoForge {

    public EnderZoologyNeoForge() {
        NeoForgeModRegistry.bootstrap();
        ModConstructor.construct(EnderZoology.MOD_ID, EnderZoology::new);
        DataProviderBuilder.of(EnderZoology.MOD_ID)
                .add(Registries.ENCHANTMENT, ModEnchantments::boostrap)
                .addLootProvider(ModBlockLootProvider::new, LootContextParamSets.BLOCK)
                .addLootProvider(ModEntityLootProvider::new, LootContextParamSets.ENTITY)
                .addProvider(ModBlockTagsProvider::new,
                        ModItemTagsProvider::new,
                        ModEnchantmentTagsProvider::new,
                        ModEntityTypeTagsProvider::new,
                        ModDataMapProvider::new)
                .addRecipeProvider(ModRecipeProvider::new)
                .addRecipeProvider(ModBrewingProvider::new);
    }
}

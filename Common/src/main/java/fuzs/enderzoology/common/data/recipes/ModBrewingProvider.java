package fuzs.enderzoology.common.data.recipes;

import fuzs.enderzoology.common.init.ModItems;
import fuzs.enderzoology.common.init.ModPotions;
import fuzs.puzzleslib.common.api.data.v3.recipes.AbstractBrewingProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Recipe;

public class ModBrewingProvider extends AbstractBrewingProvider {

    public ModBrewingProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    protected void buildMixes() {
        this.buildMix(Potions.AWKWARD, ModItems.ENDER_FRAGMENT_ITEM.value(), ModPotions.DISPLACEMENT_POTION);
        this.buildMix(ModPotions.DISPLACEMENT_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_DISPLACEMENT_POTION);
        this.buildMix(Potions.AWKWARD, ModItems.WITHERING_DUST_ITEM.value(), ModPotions.DECAY_POTION);
        this.buildMix(ModPotions.DECAY_POTION, Items.REDSTONE, ModPotions.LONG_DECAY_POTION);
        this.buildMix(ModPotions.DECAY_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_DECAY_POTION);
        this.buildMix(Potions.AWKWARD, ModItems.CONFUSING_POWDER_ITEM.value(), ModPotions.CONFUSION_POTION);
        this.buildMix(ModPotions.CONFUSION_POTION, Items.REDSTONE, ModPotions.LONG_CONFUSION_POTION);
        this.buildMix(ModPotions.CONFUSION_POTION, Items.GLOWSTONE_DUST, ModPotions.STRONG_CONFUSION_POTION);
        this.buildMix(Potions.AWKWARD, ModItems.OWL_EGG_ITEM.value(), ModPotions.RISING_POTION);
        this.buildMix(ModPotions.RISING_POTION, Items.REDSTONE, ModPotions.LONG_RISING_POTION);
    }
}

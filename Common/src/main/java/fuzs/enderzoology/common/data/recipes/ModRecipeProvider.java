package fuzs.enderzoology.common.data.recipes;

import fuzs.enderzoology.common.init.ModBlocks;
import fuzs.enderzoology.common.init.ModItems;
import fuzs.puzzleslib.common.api.data.v3.recipes.AbstractRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;

public class ModRecipeProvider extends AbstractRecipeProvider {
    private final HolderGetter<Item> itemLookup;

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
        this.itemLookup = recipeOutput.lookup(Registries.ITEM);
    }

    @Override
    public void buildRecipes() {
        ShapedRecipeBuilder.shaped(this.itemLookup, RecipeCategory.MISC, Items.ENDER_PEARL)
                .define('#', ModItems.ENDER_FRAGMENT_ITEM.value())
                .pattern(" # ")
                .pattern("###")
                .pattern(" # ")
                .unlockedBy(getHasName(ModItems.ENDER_FRAGMENT_ITEM.value()),
                        this.has(ModItems.ENDER_FRAGMENT_ITEM.value()))
                .save(this.output);
        ShapedRecipeBuilder.shaped(this.itemLookup, RecipeCategory.REDSTONE, ModBlocks.CONFUSING_CHARGE_BLOCK.value())
                .define('#', ModItems.CONFUSING_POWDER_ITEM.value())
                .define('X', Items.GUNPOWDER)
                .define('S', Ingredient.of(Blocks.SAND, Blocks.RED_SAND))
                .pattern("#S#")
                .pattern("SXS")
                .pattern("#S#")
                .unlockedBy(getHasName(ModItems.CONFUSING_POWDER_ITEM.value()),
                        this.has(ModItems.CONFUSING_POWDER_ITEM.value()))
                .save(this.output);
        ShapedRecipeBuilder.shaped(this.itemLookup, RecipeCategory.REDSTONE, ModBlocks.ENDER_CHARGE_BLOCK.value())
                .define('#', ModItems.ENDER_FRAGMENT_ITEM.value())
                .define('X', Items.GUNPOWDER)
                .define('S', Ingredient.of(Blocks.SAND, Blocks.RED_SAND))
                .pattern("#S#")
                .pattern("SXS")
                .pattern("#S#")
                .unlockedBy(getHasName(ModItems.ENDER_FRAGMENT_ITEM.value()),
                        this.has(ModItems.ENDER_FRAGMENT_ITEM.value()))
                .save(this.output);
        ShapedRecipeBuilder.shaped(this.itemLookup, RecipeCategory.REDSTONE, ModBlocks.CONCUSSION_CHARGE_BLOCK.value())
                .define('#', ModItems.ENDER_FRAGMENT_ITEM.value())
                .define('C', ModItems.CONFUSING_POWDER_ITEM.value())
                .define('X', Items.GUNPOWDER)
                .define('S', Ingredient.of(Blocks.SAND, Blocks.RED_SAND))
                .pattern("###")
                .pattern("SXS")
                .pattern("CCC")
                .unlockedBy(getHasName(ModItems.CONFUSING_POWDER_ITEM.value()),
                        this.has(ModItems.CONFUSING_POWDER_ITEM.value()))
                .save(this.output);
        ShapelessRecipeBuilder.shapeless(this.itemLookup,
                        RecipeCategory.TRANSPORTATION,
                        ModItems.ENDER_CHARGE_MINECART_ITEM.value())
                .requires(ModItems.ENDER_CHARGE_ITEM.value())
                .requires(Items.MINECART)
                .unlockedBy(getHasName(Items.MINECART), this.has(Items.MINECART))
                .save(this.output);
        ShapelessRecipeBuilder.shapeless(this.itemLookup,
                        RecipeCategory.TRANSPORTATION,
                        ModItems.CONFUSING_CHARGE_MINECART_ITEM.value())
                .requires(ModItems.CONFUSING_CHARGE_ITEM.value())
                .requires(Items.MINECART)
                .unlockedBy(getHasName(Items.MINECART), this.has(Items.MINECART))
                .save(this.output);
        ShapelessRecipeBuilder.shapeless(this.itemLookup,
                        RecipeCategory.TRANSPORTATION,
                        ModItems.CONCUSSION_CHARGE_MINECART_ITEM.value())
                .requires(ModItems.CONCUSSION_CHARGE_ITEM.value())
                .requires(Items.MINECART)
                .unlockedBy(getHasName(Items.MINECART), this.has(Items.MINECART))
                .save(this.output);
        ShapelessRecipeBuilder.shapeless(this.itemLookup, RecipeCategory.FOOD, ModItems.ENDERIOS_ITEM.value())
                .requires(Items.BOWL)
                .requires(Items.MILK_BUCKET)
                .requires(Items.WHEAT)
                .requires(ModItems.ENDER_FRAGMENT_ITEM.value())
                .unlockedBy(getHasName(ModItems.ENDER_FRAGMENT_ITEM.value()),
                        this.has(ModItems.ENDER_FRAGMENT_ITEM.value()))
                .save(this.output);
    }
}

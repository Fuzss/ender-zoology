package fuzs.enderzoology.common.data.loot;

import fuzs.enderzoology.common.init.ModBlocks;
import fuzs.puzzleslib.common.api.data.v3.loot.AbstractBlockLootSubProvider;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class ModBlockLootProvider extends AbstractBlockLootSubProvider {

    public ModBlockLootProvider(LootTableSubProvider.Context output) {
        super(output);
    }

    @Override
    public void generate() {
        this.dropExplosive(ModBlocks.ENDER_CHARGE_BLOCK.value());
        this.dropExplosive(ModBlocks.CONFUSING_CHARGE_BLOCK.value());
        this.dropExplosive(ModBlocks.CONCUSSION_CHARGE_BLOCK.value());
    }

    public void dropExplosive(Block block) {
        this.add(block,
                LootTable.lootTable()
                        .withPool(this.applyExplosionCondition(block,
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(LootItem.lootTableItem(block)
                                                .when(MatchBlock.blockMatches(this.blocks,
                                                        block,
                                                        StatePropertiesPredicate.Builder.properties()
                                                                .hasProperty(TntBlock.UNSTABLE, false)))))));
    }
}

package fuzs.enderzoology.common.data.loot;

import fuzs.enderzoology.common.init.ModEnchantments;
import fuzs.enderzoology.common.init.ModEntityTypes;
import fuzs.enderzoology.common.init.ModItems;
import fuzs.puzzleslib.common.api.data.v3.loot.AbstractEntityLootSubProvider;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class ModEntityLootProvider extends AbstractEntityLootSubProvider {

    public ModEntityLootProvider(LootTableSubProvider.Context output) {
        super(output);
    }

    @Override
    public void generate() {
        this.add(ModEntityTypes.CONCUSSION_CREEPER_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.GUNPOWDER)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))
                                .add(LootItem.lootTableItem(ModItems.CONFUSING_POWDER_ITEM.value())
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(ModItems.ENDER_FRAGMENT_ITEM.value())
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.ENDER_PEARL)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .add(TagEntry.expandTag(this.items.getOrThrow(ItemTags.CREEPER_DROP_MUSIC_DISCS)))
                                .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER,
                                        EntityPredicate.Builder.entity()
                                                .of(this.entityTypes,
                                                        EntityTypeTags.SKELETONS)))));
        this.add(ModEntityTypes.INFESTED_ZOMBIE_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(ModItems.ENDER_FRAGMENT_ITEM.value())
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.IRON_INGOT))
                                .add(LootItem.lootTableItem(Items.CARROT))
                                .add(LootItem.lootTableItem(Items.POTATO)
                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
                                .add(LootItem.lootTableItem(Items.BOOK)
                                        .apply((new EnchantRandomlyFunction.Builder()).withEnchantment(this.enchantments.getOrThrow(
                                                ModEnchantments.REPELLENT_ENCHANTMENT))))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments,
                                        0.025F,
                                        0.01F))));
        this.add(ModEntityTypes.ENDERMINY_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.ENDER_PEARL)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(ModItems.ENDER_FRAGMENT_ITEM.value())
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))));
        this.add(ModEntityTypes.DIRE_WOLF_ENTITY_TYPE.value(), LootTable.lootTable());
        this.add(ModEntityTypes.FALLEN_MOUNT_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))));
        this.add(ModEntityTypes.WITHER_CAT_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.STRING)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(ModItems.WITHERING_DUST_ITEM.value())
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))));
        this.add(ModEntityTypes.WITHER_WITCH_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(ModItems.WITHERING_DUST_ITEM.value())
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))
                                .add(LootItem.lootTableItem(Items.GLASS_BOTTLE)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))
                                .add(LootItem.lootTableItem(Items.STICK)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))));
        this.add(ModEntityTypes.OWL_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.FEATHER)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))));
        this.add(ModEntityTypes.FALLEN_KNIGHT_ENTITY_TYPE.value(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(LootItem.lootTableItem(Items.BONE)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments,
                                                ContextFloatProviders.between(0.0F, 1.0F))))));
    }
}

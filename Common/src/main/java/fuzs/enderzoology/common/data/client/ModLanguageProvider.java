package fuzs.enderzoology.common.data.client;

import fuzs.enderzoology.common.EnderZoology;
import fuzs.enderzoology.common.init.*;
import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.add(ModRegistry.CREATIVE_MODE_TAB.value(), EnderZoology.MOD_NAME);
        this.add(ModBlocks.ENDER_CHARGE_BLOCK.value(), "Ender Charge");
        this.add(ModBlocks.CONFUSING_CHARGE_BLOCK.value(), "Confusing Charge");
        this.add(ModBlocks.CONCUSSION_CHARGE_BLOCK.value(), "Concussion Charge");
        this.add(ModItems.CONFUSING_POWDER_ITEM.value(), "Confusing Powder");
        this.add(ModItems.ENDER_FRAGMENT_ITEM.value(), "Ender Fragment");
        this.add(ModItems.HUNTING_BOW_ITEM.value(), "Hunting Bow");
        this.add(ModItems.OWL_EGG_ITEM.value(), "Owl Egg");
        this.add(ModItems.WITHERING_DUST_ITEM.value(), "Withering Dust");
        this.add(ModItems.ENDER_CHARGE_MINECART_ITEM.value(), "Ender Charge Minecart");
        this.add(ModItems.CONFUSING_CHARGE_MINECART_ITEM.value(), "Confusing Charge Minecart");
        this.add(ModItems.CONCUSSION_CHARGE_MINECART_ITEM.value(), "Concussion Charge Minecart");
        this.add(ModItems.ENDERIOS_ITEM.value(), "Enderios");
        this.addSpawnEgg(ModItems.CONCUSSION_CREEPER_SPAWN_EGG_ITEM.value(), "Concussion Creeper");
        this.addSpawnEgg(ModItems.INFESTED_ZOMBIE_SPAWN_EGG_ITEM.value(), "Infested Zombie");
        this.addSpawnEgg(ModItems.ENDERMINY_SPAWN_EGG_ITEM.value(), "Enderminy");
        this.addSpawnEgg(ModItems.DIRE_WOLF_SPAWN_EGG_ITEM.value(), "Dire Wolf");
        this.addSpawnEgg(ModItems.FALLEN_MOUNT_SPAWN_EGG_ITEM.value(), "Fallen Mount");
        this.addSpawnEgg(ModItems.WITHER_CAT_SPAWN_EGG_ITEM.value(), "Wither Cat");
        this.addSpawnEgg(ModItems.WITHER_WITCH_SPAWN_EGG_ITEM.value(), "Wither Witch");
        this.addSpawnEgg(ModItems.OWL_SPAWN_EGG_ITEM.value(), "Owl");
        this.addSpawnEgg(ModItems.FALLEN_KNIGHT_SPAWN_EGG_ITEM.value(), "Fallen Knight");
        this.add(ModEntityTypes.OWL_EGG_ENTITY_TYPE.value(), "Thrown Owl Egg");
        this.add(ModEntityTypes.CONCUSSION_CREEPER_ENTITY_TYPE.value(), "Concussion Creeper");
        this.add(ModEntityTypes.INFESTED_ZOMBIE_ENTITY_TYPE.value(), "Infested Zombie");
        this.add(ModEntityTypes.ENDERMINY_ENTITY_TYPE.value(), "Enderminy");
        this.add(ModEntityTypes.DIRE_WOLF_ENTITY_TYPE.value(), "Dire Wolf");
        this.add(ModEntityTypes.FALLEN_MOUNT_ENTITY_TYPE.value(), "Fallen Mount");
        this.add(ModEntityTypes.WITHER_CAT_ENTITY_TYPE.value(), "Wither Cat");
        this.add(ModEntityTypes.WITHER_WITCH_ENTITY_TYPE.value(), "Wither Witch");
        this.add(ModEntityTypes.OWL_ENTITY_TYPE.value(), "Owl");
        this.add(ModEntityTypes.FALLEN_KNIGHT_ENTITY_TYPE.value(), "Fallen Knight");
        this.add(ModEntityTypes.PRIMED_CHARGE_ENTITY_TYPE.value(), "Primed Charge");
        this.add(ModEntityTypes.ENDER_CHARGE_MINECART_ENTITY_TYPE.value(), "Minecart with Ender Charge");
        this.add(ModEntityTypes.CONFUSING_CHARGE_MINECART_ENTITY_TYPE.value(), "Minecart with Confusing Charge");
        this.add(ModEntityTypes.CONCUSSION_CHARGE_MINECART_ENTITY_TYPE.value(), "Minecart with Concussion Charge");
        this.add(ModEnchantments.DECAY_ENCHANTMENT, "Decay");
        this.add(ModEnchantments.REPELLENT_ENCHANTMENT, "Repellent");
        this.add(ModEnchantments.SOULBOUND_ENCHANTMENT, "Soulbound");
        this.add(ModEnchantments.WITHERING_ENCHANTMENT, "Withering");
        this.add(ModEnchantments.DECAY_ENCHANTMENT, "desc", "Applies the Wither effect to attacked enemies.");
        this.add(ModEnchantments.REPELLENT_ENCHANTMENT, "desc", "Randomly teleports enemies when they attack you.");
        this.add(ModEnchantments.SOULBOUND_ENCHANTMENT,
                "desc",
                "On death an item will be kept in the inventory and the level may randomly decrease.");
        this.add(ModEnchantments.WITHERING_ENCHANTMENT,
                "desc",
                "Applies the Wither effect to enemies hit by arrows.");
        this.add(ModRegistry.DISPLACEMENT_MOB_EFFECT.value(), "Displacement");
        this.addPotion(ModPotions.DISPLACEMENT_POTION, "Displacement");
        this.addPotion(ModPotions.DECAY_POTION, "Decay");
        this.addPotion(ModPotions.CONFUSION_POTION, "Confusion");
        this.addPotion(ModPotions.RISING_POTION, "Rising");
        this.add(ModSoundEvents.DIRE_WOLF_HURT_SOUND_EVENT.value(), "Dire Wolf hurts");
        this.add(ModSoundEvents.DIRE_WOLF_DEATH_SOUND_EVENT.value(), "Dire Wolf dies");
        this.add(ModSoundEvents.DIRE_WOLF_GROWL_SOUND_EVENT.value(), "Dire Wolf growls");
        this.add(ModSoundEvents.DIRE_WOLF_HOWL_SOUND_EVENT.value(), "Dire Wolf howls");
        this.add(ModSoundEvents.OWL_HOOT_SOUND_EVENT.value(), "Owl hoots");
        this.add(ModSoundEvents.OWL_HURT_SOUND_EVENT.value(), "Owl hurts");
        this.add(ModSoundEvents.OWL_DEATH_SOUND_EVENT.value(), "Owl dies");
        this.add(ModSoundEvents.OWL_EGG_THROW_SOUND_EVENT.value(), "Owl Egg flies");
    }
}

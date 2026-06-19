package fuzs.enderzoology.common.data.tags;

import fuzs.enderzoology.common.init.ModEnchantments;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantmentTagsProvider extends AbstractTagProvider<Enchantment> {

    public ModEnchantmentTagsProvider(DataProviderContext context) {
        super(Registries.ENCHANTMENT, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(EnchantmentTags.NON_TREASURE)
                .add(ModEnchantments.DECAY_ENCHANTMENT,
                        ModEnchantments.SOULBOUND_ENCHANTMENT,
                        ModEnchantments.WITHERING_ENCHANTMENT);
        this.tag(EnchantmentTags.TREASURE).add(ModEnchantments.REPELLENT_ENCHANTMENT);
    }
}

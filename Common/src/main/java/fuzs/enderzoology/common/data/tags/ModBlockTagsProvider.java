package fuzs.enderzoology.common.data.tags;

import fuzs.enderzoology.common.init.ModBlocks;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class ModBlockTagsProvider extends AbstractTagProvider<Block> {

    public ModBlockTagsProvider(DataProviderContext context) {
        super(Registries.BLOCK, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.ENDERMAN_HOLDABLE)
                .add(ModBlocks.ENDER_CHARGE_BLOCK, ModBlocks.CONFUSING_CHARGE_BLOCK, ModBlocks.CONCUSSION_CHARGE_BLOCK);
    }
}

package fuzs.enderzoology.common.init;

import fuzs.enderzoology.common.EnderZoology;
import fuzs.puzzleslib.common.api.init.v3.tags.TagFactory;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;

public class ModTags {
    private static final TagFactory TAGS = TagFactory.make(EnderZoology.MOD_ID);

    public static class Entities {
        public static final TagKey<EntityType<?>> CONCUSSION_IMMUNE_ENTITY_TYPE_TAG = register("concussion_immune");
        public static final TagKey<EntityType<?>> FALLEN_MOUNT_TARGETS_ENTITY_TYPE_TAG = register("fallen_mount_targets");

        private static TagKey<EntityType<?>> register(String name) {
            return TAGS.registerEntityTypeTag(name);
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> IS_OVERWORLD = TagFactory.COMMON.registerBiomeTag("is_overworld");
    }
}

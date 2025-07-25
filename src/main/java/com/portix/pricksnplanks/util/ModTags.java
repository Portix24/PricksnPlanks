package com.portix.pricksnplanks.util;

import com.portix.pricksnplanks.PricksnPlanks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> CACTUSES = createTag("cactuses");
        public static final TagKey<Block> CACTUS_TNT = createTag("cactus_tnt");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(PricksnPlanks.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> CACTUS_PLANK_CRAFTABLES = createTag("cactus_plank_craftables");
        public static final TagKey<Item> CACTUS_STEW_INGREDIENTS = createTag("cactus_stew_ingredients");
        public static final TagKey<Item> DRY_GRASS = createTag("dry_grass");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(PricksnPlanks.MOD_ID, name));
        }
    }
}

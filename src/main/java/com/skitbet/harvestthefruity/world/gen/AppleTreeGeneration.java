package com.skitbet.harvestthefruity.world.gen;

import com.skitbet.harvestthefruity.world.ConfiguredFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.function.Supplier;

public class AppleTreeGeneration {

    public static void generateTree(final BiomeLoadingEvent event) {
        Biome.Category biomeCategory = event.getCategory();

        if(biomeCategory == Biome.Category.PLAINS || biomeCategory == Biome.Category.FOREST) {
            List<Supplier<ConfiguredFeature<?, ?>>> base =
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

            base.add(() -> ConfiguredFeatures.APPLE_TREE
                    .decorated(Features.Placements.HEIGHTMAP)
                    .decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.25f, 2))));
        }
    }

}

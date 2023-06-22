package com.skitbet.harvestthefruity.world;

import com.skitbet.harvestthefruity.registry.BlockRegistry;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;

public class ConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> RASPBERRY_BUSH = Feature.FLOWER.configured((
                    new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.RASPBERRY_BUSH.get().defaultBlockState()),
                            SimpleBlockPlacer.INSTANCE)).tries(12).build())
            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE).count(3);

}

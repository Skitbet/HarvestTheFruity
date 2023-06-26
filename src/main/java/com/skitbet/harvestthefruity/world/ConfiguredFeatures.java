package com.skitbet.harvestthefruity.world;

import com.skitbet.harvestthefruity.registry.BlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.OakTree;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> RASPBERRY_BUSH = Feature.FLOWER.configured((
                    new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.RASPBERRY_BUSH.get().defaultBlockState()),
                            SimpleBlockPlacer.INSTANCE)).tries(3).build())
            .decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE).count(3);

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> APPLE_TREE =
            register("appletree", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()),
                            new SimpleBlockStateProvider(BlockRegistry.APPLE_LEAVES.get().defaultBlockState()),
                            new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3),
                            new StraightTrunkPlacer(4, 2, 1),
                            new TwoLayerFeature(1, 0, 1))).ignoreVines().build()));


    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }

}

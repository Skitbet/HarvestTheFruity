package com.skitbet.harvestthefruity.world;

import net.minecraft.client.Minecraft;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKeyCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class RaspberryBushGeneration {

    public static void generateBush(final BiomeLoadingEvent event) {
        Biome.Category biomeCategory = event.getCategory();

        if(biomeCategory == Biome.Category.PLAINS || biomeCategory == Biome.Category.FOREST || biomeCategory == Biome.Category.TAIGA) {
            List<Supplier<ConfiguredFeature<?, ?>>> base =
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

            base.add(() -> ConfiguredFeatures.RASPBERRY_BUSH);
        }
    }


}

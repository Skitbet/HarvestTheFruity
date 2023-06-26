package com.skitbet.harvestthefruity.world.gen;

import com.skitbet.harvestthefruity.world.ConfiguredFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
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

package com.skitbet.harvestthefruity.registry;

import com.skitbet.harvestthefruity.HarvestTheFruity;
import com.skitbet.harvestthefruity.block.MicrowaveBlock;
import com.skitbet.harvestthefruity.block.RaspberryBushBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HarvestTheFruity.MODID);

    public static final RegistryObject<Block> RASPBERRY_BUSH = BLOCKS.register("raspberry_bush",
            () -> new RaspberryBushBlock(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> MICROWAVE = BLOCKS.register("microwave",
            () -> new MicrowaveBlock(AbstractBlock.Properties.of(Material.STONE).randomTicks().sound(SoundType.STONE)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

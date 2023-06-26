package com.skitbet.harvestthefruity.registry;

import com.skitbet.harvestthefruity.HarvestTheFruity;
import com.skitbet.harvestthefruity.block.AppleLeaveBlock;
import com.skitbet.harvestthefruity.block.RaspberryBushBlock;
import com.skitbet.harvestthefruity.world.tree.AppleTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HarvestTheFruity.MODID);

    public static final RegistryObject<Block> RASPBERRY_BUSH = BLOCKS.register("raspberry_bush",
            () -> new RaspberryBushBlock(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission().sound(SoundType.GRASS)));

    public static final RegistryObject<Block> APPLE_LEAVES = BLOCKS.register("apple_leaves",
            () -> new AppleLeaveBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> APPLE_SAPLING = BLOCKS.register("apple_tree_sapling",
            () -> new SaplingBlock(new AppleTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

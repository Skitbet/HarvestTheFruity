package com.skitbet.harvestthefruity.registry;

import com.skitbet.harvestthefruity.HarvestTheFruity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HarvestTheFruity.MODID);

    public static final RegistryObject<Item> RASPBERRY = ITEMS.register("raspberry",
            () -> new BlockItem(BlockRegistry.RASPBERRY_BUSH.get(), new Item.Properties()
                            .food(new Food.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.6F)
                                    .build())
                            .tab(ModItemGroup.HARVEST_THE_FRUIT_GROUP)));

    public static final RegistryObject<BlockItem> MICROWAVE = ITEMS.register("microwave",
            () -> new BlockItem(BlockRegistry.MICROWAVE.get(), new Item.Properties()
                    .tab(ModItemGroup.HARVEST_THE_FRUIT_GROUP)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}

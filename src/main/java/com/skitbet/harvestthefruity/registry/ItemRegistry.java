package com.skitbet.harvestthefruity.registry;

import com.skitbet.harvestthefruity.HarvestTheFruity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HarvestTheFruity.MODID);

    public static final RegistryObject<Item> RASPBERRY = ITEMS.register("raspberry",
            () -> new BlockItem(BlockRegistry.RASPBERRY_BUSH.get(), new Item.Properties()
                            .food(new Food.Builder()
                                    .nutrition(2)
                                    .saturationMod(0.6F)
                                    .build())
                            .tab(ItemGroup.TAB_FOOD)));

    public static final RegistryObject<Item> MICROWAVE = ITEMS.register("raspberry",
            () -> new BlockItem(BlockRegistry.MICROWAVE.get(), new Item.Properties()
                    .tab(ItemGroup.TAB_BREWING)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}

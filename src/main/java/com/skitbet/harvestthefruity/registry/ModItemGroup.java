package com.skitbet.harvestthefruity.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup HARVEST_THE_FRUIT_GROUP = new ItemGroup("harvestthegfruityTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.RASPBERRY.get());
        }
    };
}

package com.skitbet.harvestthefruity.world;

import com.skitbet.harvestthefruity.HarvestTheFruity;
import com.skitbet.harvestthefruity.world.gen.AppleTreeGeneration;
import com.skitbet.harvestthefruity.world.gen.RaspberryBushGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HarvestTheFruity.MODID)
public class WorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        RaspberryBushGeneration.generateBush(event);
        AppleTreeGeneration.generateTree(event);
    }

}

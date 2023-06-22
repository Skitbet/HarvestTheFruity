package com.skitbet.harvestthefruity;

import com.skitbet.harvestthefruity.registry.BlockRegistry;
import com.skitbet.harvestthefruity.registry.ItemRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(HarvestTheFruity.MODID)
public class HarvestTheFruity
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "harvestthefruity";

    public HarvestTheFruity() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::commonSetup);

        ItemRegistry.register(eventBus);
        BlockRegistry.register(eventBus);

    }

    public void commonSetup(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(BlockRegistry.RASPBERRY_BUSH.get(), RenderType.cutout());
    }
}

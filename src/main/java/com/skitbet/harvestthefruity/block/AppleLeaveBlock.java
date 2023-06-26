package com.skitbet.harvestthefruity.block;

import com.skitbet.harvestthefruity.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class AppleLeaveBlock extends LeavesBlock {

    public static final BooleanProperty HAS_APPLES = BooleanProperty.create("has_apples");

    public AppleLeaveBlock(Properties p_i48370_1_) {
        super(p_i48370_1_);
        this.registerDefaultState(this.getStateDefinition().any().setValue(HAS_APPLES, true));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (state.getValue(HAS_APPLES) == Boolean.FALSE) {
            if (world.getRawBrightness(pos, 0) >= 9 && random.nextInt(5) == 0) {
                world.setBlock(pos, state.setValue(HAS_APPLES, true), 2);
            }
        }
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader p_185473_1_, BlockPos p_185473_2_, BlockState p_185473_3_) {
        return new ItemStack(ItemRegistry.APPLE_SAPLING.get());
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult blockRayTraceResult) {

        if (state.getValue(HAS_APPLES) == Boolean.TRUE) {
            // Make it so apples have a 30% chance of dropping every right click
            if (new Random().nextInt(10) >= 7) {
                world.playSound(null, pos, SoundEvents.CROP_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
                entity.sendMessage(new StringTextComponent("Hmm, no apples seem to have fallen."), entity.getUUID());
                world.setBlock(pos, state.setValue(HAS_APPLES, Boolean.FALSE), 2);
                return ActionResultType.SUCCESS;
            }
            popResource(world, pos, new ItemStack(Items.APPLE, new Random().nextInt(2)));
            world.playSound(null, pos, SoundEvents.CROP_PLANTED, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            world.setBlock(pos, state.setValue(HAS_APPLES, Boolean.FALSE), 2);
            return ActionResultType.SUCCESS;
        }

        return super.use(state, world, pos, entity, hand, blockRayTraceResult);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(HAS_APPLES);
        super.createBlockStateDefinition(p_206840_1_);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return true;
    }
}

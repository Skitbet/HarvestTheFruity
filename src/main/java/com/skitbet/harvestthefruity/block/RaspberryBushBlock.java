package com.skitbet.harvestthefruity.block;

import com.skitbet.harvestthefruity.registry.ItemRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

import java.util.Random;

public class RaspberryBushBlock extends BushBlock implements IGrowable {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    private static final VoxelShape SAPLING_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
    private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public RaspberryBushBlock(Properties p_i48437_1_) {
        super(p_i48437_1_);
        this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 0));
    }



    @Override
    public ItemStack getCloneItemStack(IBlockReader p_185473_1_, BlockPos p_185473_2_, BlockState p_185473_3_) {
        return new ItemStack(ItemRegistry.RASPBERRY.get());
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        if (state.getValue(AGE) == 0) {
            return SAPLING_SHAPE;
        }else{
            return state.getValue(AGE) < 3 ? MID_GROWTH_SHAPE : super.getShape(state, reader, pos, context);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_149653_1_) {
        return p_149653_1_.getValue(AGE) < 3;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos blockPos, Random random) {
        int i = state.getValue(AGE);
        if (i < 3 && world.getRawBrightness(blockPos.above(), 0) >= 9 && ForgeHooks.onCropsGrowPre(world, blockPos, state,random.nextInt(5) == 0)) {
            world.setBlock(blockPos, state.setValue(AGE, i + 1), 2);
            ForgeHooks.onCropsGrowPost(world, blockPos, state);
        }
    }

    @Override
    public void entityInside(BlockState state, World world, BlockPos blockPos, Entity entity) {
        if (entity instanceof LivingEntity) {
            entity.makeStuckInBlock(state, new Vector3d(0.8F, 0.75D, 0.8F));
        }
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        int i = state.getValue(AGE);
        boolean flag = i == 3;
        if (!flag && player.getItemInHand(hand).getItem() == Items.BONE_MEAL) {
            return ActionResultType.PASS;
        }else if (i > 1) {
            int j = i + world.random.nextInt(3);
            popResource(world, pos, new ItemStack(ItemRegistry.RASPBERRY.get(), j + (flag ? 1 : 0)));
            world.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            world.setBlock(pos, state.setValue(AGE, 0), 2);
            return ActionResultType.sidedSuccess(world.isClientSide);
        }
        return super.use(state, world, pos, player, hand, rayTraceResult);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(AGE);
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pState.getValue(AGE) < 3;
    }

    @Override
    public boolean isBonemealSuccess(World pLevel, Random pRand, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerWorld pLevel, Random pRand, BlockPos pPos, BlockState pState) {
        int i = Math.min(3, pState.getValue(AGE) + 1);
        pLevel.setBlock(pPos, pState.setValue(AGE, i), 2);
    }
}

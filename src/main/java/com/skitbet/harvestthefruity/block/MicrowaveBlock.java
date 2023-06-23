package com.skitbet.harvestthefruity.block;

import com.skitbet.harvestthefruity.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class MicrowaveBlock extends Block {

    private static final VoxelShape SHAPE =  Block.box(1, 0, 3, 15, 9, 13);


    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    // Wanted to make boolean property like a status one where if the microwave is on its true, but for some reason it keeps fucking defaulting to true...
    public static final BooleanProperty OFF_STATUS = BooleanProperty.create("off");

    public MicrowaveBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
        this.registerDefaultState(this.defaultBlockState().setValue(OFF_STATUS, false));
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader p_185473_1_, BlockPos p_185473_2_, BlockState p_185473_3_) {
        return new ItemStack(ItemRegistry.MICROWAVE.get());
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_149653_1_) {
        return p_149653_1_.getValue(OFF_STATUS);
    }

    @Override
    public void randomTick(BlockState p_225542_1_, ServerWorld p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_) {

    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(OFF_STATUS);
        p_206840_1_.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        return this.defaultBlockState().setValue(FACING, p_196258_1_.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
        return p_185471_1_.rotate(p_185471_2_.getRotation(p_185471_1_.getValue(FACING)));
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        boolean statusValue = state.getValue(OFF_STATUS);
        world.setBlock(pos, state.setValue(OFF_STATUS, !statusValue), 2);
        return ActionResultType.SUCCESS;
    }

}

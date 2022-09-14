package net.mehvahdjukaar.betterlily;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.function.Supplier;

public class WaterloggedLilyBlock extends WaterlilyBlock implements LiquidBlockContainer {

    protected static final VoxelShape AABB = Block.box(1.0D, 15.0D, 1.0D, 15.0D, 16D, 15.0D);
    protected static final VoxelShape AABB_SUPPORT = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16D, 16.0D);

    protected final Supplier<Block> baseLily;

    public WaterloggedLilyBlock(Properties properties, Supplier<Block> baseLily) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState());
        this.baseLily = baseLily;
    }

    @Override
    public VoxelShape getShape(BlockState p_58169_, BlockGetter p_58170_, BlockPos p_58171_, CollisionContext p_58172_) {
        return AABB;
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState p_60581_, BlockGetter p_60582_, BlockPos p_60583_) {
        return AABB_SUPPORT;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return Fluids.WATER.getSource(false);
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        return stateIn;
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
        super.neighborChanged(state, world, pos, neighborBlock, fromPos, moving);
        if (pos.above().equals(fromPos)) {
            maybeConvertToVanilla(world, pos);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource random) {
        maybeConvertToVanilla(serverLevel, pos);
        super.tick(state, serverLevel, pos, random);
    }

    private void maybeConvertToVanilla(LevelAccessor serverLevel, BlockPos pos) {
        if (serverLevel.getBlockState(pos.above()).isAir()) {
            serverLevel.setBlock(pos, Blocks.WATER.defaultBlockState(), 3);
            serverLevel.setBlock(pos.above(), Blocks.LILY_PAD.defaultBlockState(), 3);
        }
    }

    @Override
    public Item asItem() {
        return baseLily.get().asItem();
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return baseLily.get().defaultBlockState().getDrops(builder);
    }

    @Override
    public MutableComponent getName() {
        return baseLily.get().getName();
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter p_54304_, BlockPos p_54305_, BlockState p_54306_, Fluid p_54307_) {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor p_54309_, BlockPos p_54310_, BlockState p_54311_, FluidState p_54312_) {
        return false;
    }

    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        return InteractionResult.PASS;
    }

    /**
     * Return a random long to be passed to {@link net.minecraft.client.resources.model.BakedModel#getQuads}, used for
     * random model rotations
     */
    @Override
    public long getSeed(BlockState pState, BlockPos pPos) {
        return Mth.getSeed(pPos.getX(), pPos.above().getY(), pPos.getZ());
    }
}

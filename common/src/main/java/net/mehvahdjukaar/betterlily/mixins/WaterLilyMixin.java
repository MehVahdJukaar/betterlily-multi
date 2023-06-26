package net.mehvahdjukaar.betterlily.mixins;

import net.mehvahdjukaar.betterlily.BetterLily;
import net.mehvahdjukaar.betterlily.WaterloggedLilyBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WaterlilyBlock.class)
public class WaterLilyMixin extends Block {
    public WaterLilyMixin(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!player.mayBuild()) return InteractionResult.PASS;
        ItemStack stack = player.getItemInHand(hand);
        Item item = stack.getItem();
        if (!stack.isEmpty() && !(item instanceof PlaceOnWaterBlockItem)) {
            BlockPos below = pos.below();
            if (level.getBlockState(below).is(Blocks.WATER)) {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                level.setBlock(below, BetterLily.WATERLILY_BLOCK.get().defaultBlockState(), 3);
                level.scheduleTick(below, BetterLily.WATERLILY_BLOCK.get(), 1);
                if (level.getBlockEntity(below) instanceof WaterloggedLilyBlockEntity te) {
                    te.setHeldBlock(state);
                }
            }
        }
        return InteractionResult.PASS;
    }
}

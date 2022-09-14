package net.mehvahdjukaar.betterlily.mixins;

import net.mehvahdjukaar.betterlily.BetterLily;
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
        if(!player.mayBuild())return InteractionResult.PASS;
        ItemStack stack = player.getItemInHand(hand);
        Item item = stack.getItem();
        if(!stack.isEmpty() && !(item instanceof PlaceOnWaterBlockItem)){
            if(level.getBlockState(pos.below()).is(Blocks.WATER)){

                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                level.setBlock(pos.below(), BetterLily.getBetterLily().defaultBlockState(), 2);
                level.scheduleTick(pos.below(), BetterLily.getBetterLily(),1);
            }
        }
        return InteractionResult.PASS;
    }
}

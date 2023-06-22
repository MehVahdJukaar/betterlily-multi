package net.mehvahdjukaar.betterlily.mixins.forge;

import net.mehvahdjukaar.betterlily.WaterloggedLilyBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WaterloggedLilyBlock.class)
public abstract class SelfWaterloggedLilyBlockMixin extends Block {

    protected SelfWaterloggedLilyBlockMixin(Properties arg) {
        super(arg);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        if(plantable instanceof FlowerBlock){
            return true;
        }
        return super.canSustainPlant(state, world, pos, facing, plantable);
    }
}

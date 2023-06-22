package net.mehvahdjukaar.betterlily;

import net.mehvahdjukaar.moonlight.api.block.MimicBlockTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class WaterloggedLilyBlockEntity extends MimicBlockTile {
    public WaterloggedLilyBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BetterLily.WATERLILY_TILE.get(), blockPos, blockState);
    }
}

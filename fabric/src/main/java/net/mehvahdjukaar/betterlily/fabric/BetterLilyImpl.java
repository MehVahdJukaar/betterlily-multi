package net.mehvahdjukaar.betterlily.fabric;

import net.fabricmc.api.ModInitializer;
import net.mehvahdjukaar.betterlily.BetterLily;
import net.mehvahdjukaar.betterlily.WaterloggedLilyBlock;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class BetterLilyImpl implements ModInitializer {


    public static Block LILY_BLOCK = new WaterloggedLilyBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak()
            .sound(SoundType.LILY_PAD).noOcclusion(), () -> Blocks.LILY_PAD);

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, BetterLily.res("water_lily_pad"), LILY_BLOCK);
    }

    public static Block getBetterLily() {
        return LILY_BLOCK;
    }
}

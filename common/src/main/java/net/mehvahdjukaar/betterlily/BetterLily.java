package net.mehvahdjukaar.betterlily;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.List;
import java.util.function.Supplier;


public class BetterLily {
    public static final String MOD_ID = "betterlily";


    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }


    public static final Supplier<Block> WATERLILY_BLOCK = RegHelper.registerBlock(
            res("water_lily_pad"),
            () -> new WaterloggedLilyBlock(BlockBehaviour.Properties.copy(Blocks.LILY_PAD).instabreak()
                    .sound(SoundType.LILY_PAD).noOcclusion())
    );

    public static final Supplier<BlockEntityType<WaterloggedLilyBlockEntity>> WATERLILY_TILE = RegHelper.registerBlockEntityType(
            res("water_lily_pad"),
            () -> PlatHelper.newBlockEntityType(WaterloggedLilyBlockEntity::new, WATERLILY_BLOCK.get())
    );

    public static void init() {

    }
}

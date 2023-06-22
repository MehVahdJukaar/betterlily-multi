package net.mehvahdjukaar.betterlily;

import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;


public class BetterLily {
    public static final String MOD_ID = "betterlily";

    public static ResourceLocation res(String name) {
        return new ResourceLocation(MOD_ID, name);
    }


    public static final Supplier<Block> WATERLILY_BLOCK = RegHelper.registerBlock(
            res("water_lily_pad"),
            () -> new WaterloggedLilyBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak()
                    .sound(SoundType.LILY_PAD).noOcclusion())
    );

    public static final Supplier<BlockEntityType<WaterloggedLilyBlockEntity>> WATERLILY_TILE = RegHelper.registerBlockEntityType(
            res("water_lily_pad"),
            () -> PlatformHelper.newBlockEntityType(WaterloggedLilyBlockEntity::new, WATERLILY_BLOCK.get())
    );

    public static void init() {
    }
}

package net.mehvahdjukaar.betterlily.forge;

import net.mehvahdjukaar.betterlily.WaterloggedLilyBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.mehvahdjukaar.betterlily.BetterLily.MOD_ID;

/**
 * Author: MehVahdJukaar
 */
@Mod(MOD_ID)
public class BetterLilyImpl {

    public static Block getBetterLily() {
        return LILY_BLOCK.get();
    }

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public BetterLilyImpl() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
    }

    public static final RegistryObject<Block> LILY_BLOCK = BLOCKS.register("water_lily_pad",
            () -> new WaterloggedLilyBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak()
                    .sound(SoundType.LILY_PAD).noOcclusion(), () -> Blocks.LILY_PAD) {

                @Override
                public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing,
                                               IPlantable plantable) {
                    PlantType type = plantable.getPlantType(world, pos.relative(facing));
                    return type == PlantType.PLAINS;
                }
            });
}

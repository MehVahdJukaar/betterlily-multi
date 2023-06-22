package net.mehvahdjukaar.betterlily.forge;

import net.mehvahdjukaar.betterlily.BetterLily;
import net.mehvahdjukaar.betterlily.BetterLilyClient;
import net.mehvahdjukaar.betterlily.WaterloggedLilyBlock;
import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
public class BetterLilyForge {


    public BetterLilyForge() {
        BetterLily.init();
        if(PlatformHelper.getEnv().isClient()){
            BetterLilyClient.init();
        }
    }
}

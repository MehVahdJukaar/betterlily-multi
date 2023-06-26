package net.mehvahdjukaar.betterlily;

import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.state.BlockState;

public class BetterLilyClient {

    public static void init() {
        ClientHelper.addClientSetup(BetterLilyClient::setup);
        ClientHelper.addBlockColorsRegistration(BetterLilyClient::registerBlockColors);
        ClientHelper.addModelLoaderRegistration(BetterLilyClient::registerModelLoaders);
    }

    public static void setup() {
        ClientHelper.registerRenderType(BetterLily.WATERLILY_BLOCK.get(), RenderType.cutout());
    }

    private static void registerModelLoaders(ClientHelper.ModelLoaderEvent event) {
        event.register(BetterLily.res("waterlogged_lily"), WaterloggedLilyModel::new);
    }

    private static void registerBlockColors(ClientHelper.BlockColorEvent event) {
        event.register((state, getter, pos, i) -> {
                    if (getter != null && pos != null) {
                        if (getter.getBlockEntity(pos) instanceof WaterloggedLilyBlockEntity te) {
                            BlockState mimic = te.getHeldBlock();
                            if (mimic != null) {
                                return Minecraft.getInstance().getBlockColors().getColor(mimic, getter, pos, 1);
                            }
                        }
                        return 2129968;
                    }
                    return 7455580;
                },
                BetterLily.WATERLILY_BLOCK.get());
    }

}

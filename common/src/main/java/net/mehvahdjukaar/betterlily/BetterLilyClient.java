package net.mehvahdjukaar.betterlily;

import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.minecraft.client.renderer.RenderType;

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
        event.register((state, getter, pos, i) ->
                        getter != null && pos != null ? 2129968 : 7455580,
                BetterLily.WATERLILY_BLOCK.get());
    }

}

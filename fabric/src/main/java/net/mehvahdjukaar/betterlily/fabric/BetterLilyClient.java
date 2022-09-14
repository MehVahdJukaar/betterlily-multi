package net.mehvahdjukaar.betterlily.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.mehvahdjukaar.betterlily.BetterLily;
import net.minecraft.client.renderer.RenderType;

public class BetterLilyClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register((state, getter, pos, i) ->
                        getter != null && pos != null ? 2129968 : 7455580,
                BetterLily.getBetterLily());
        BlockRenderLayerMap.INSTANCE.putBlock(BetterLily.getBetterLily(), RenderType.cutout());
    }
}

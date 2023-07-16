package net.mehvahdjukaar.betterlily;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.moonlight.api.client.model.CustomBakedModel;
import net.mehvahdjukaar.moonlight.api.client.model.CustomGeometry;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class BetterLilyClient {

    public static void init() {
        ClientPlatformHelper.addClientSetup(BetterLilyClient::setup);
        ClientPlatformHelper.addBlockColorsRegistration(BetterLilyClient::registerBlockColors);
        ClientPlatformHelper.addModelLoaderRegistration(BetterLilyClient::registerModelLoaders);
    }

    public static void setup(){
        ClientPlatformHelper.registerRenderType(BetterLily.WATERLILY_BLOCK.get(), RenderType.cutout());
    }

    private static void registerModelLoaders(ClientPlatformHelper.ModelLoaderEvent event) {
        event.register(BetterLily.res("waterlogged_lily"), (json, context) -> new CustomGeometry() {
            @Override
            public Collection<Material> getMaterials(Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
                return List.of();
            }

            @Override
            public CustomBakedModel bake(ModelBakery modelBakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState transform, ResourceLocation location) {
                return new WaterloggedLilyModel();
            }
        });
    }

    private static void registerBlockColors(ClientPlatformHelper.BlockColorEvent event) {
        event.register((state, getter, pos, i) -> {
                    if (getter != null && pos != null) {
                        if (getter.getBlockEntity(pos) instanceof WaterloggedLilyBlockEntity te) {
                            BlockState mimic = te.getHeldBlock();
                            if (mimic != null) {
                                return Minecraft.getInstance().getBlockColors().getColor(mimic, getter, pos, i);
                            }
                        }
                        return 2129968;
                    }
                    return 7455580;
                },
                BetterLily.WATERLILY_BLOCK.get());
    }

}

package net.mehvahdjukaar.betterlily;

import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Supplier;

import static net.mehvahdjukaar.betterlily.BetterLily.MOD_ID;

public class BetterLilyClient {

    public static final Supplier<Double> OFFSET;
    //public static final Supplier<List<String>> COLOR_LISt;

    static {
        ConfigBuilder config = ConfigBuilder.create(MOD_ID, ConfigType.CLIENT);
        OFFSET = config.comment("set to 0 tho have lilypads at the same exact position as vanilla." +
                        "negative numbers will place them in their own blockspace right below avoiding any clipping." +
                        "best of both worlds at default as its barely within its space")
                .define("y_offset", -0.25 / 16f - 0.001, -1, 1);
        //COLOR_LISt = config.comment("Add here block ids of lilypads that should just use the default color. Added since some mod combinations can cause them to appear gray")
        //        .define("use_default_color", List.of());
        config.buildAndRegister();
    }

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

package net.mehvahdjukaar.betterlily.forge;

import net.mehvahdjukaar.betterlily.BetterLily;
import net.mehvahdjukaar.betterlily.BetterLilyClient;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.minecraftforge.fml.common.Mod;

import static net.mehvahdjukaar.betterlily.BetterLily.MOD_ID;

/**
 * Author: MehVahdJukaar
 */
@Mod(MOD_ID)
public class BetterLilyForge {


    public BetterLilyForge() {
        BetterLily.init();
        if (PlatHelper.getPhysicalSide().isClient()) {
            BetterLilyClient.init();
        }
    }
}

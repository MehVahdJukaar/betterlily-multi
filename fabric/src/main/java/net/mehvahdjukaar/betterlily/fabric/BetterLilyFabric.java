package net.mehvahdjukaar.betterlily.fabric;

import net.fabricmc.api.ModInitializer;
import net.mehvahdjukaar.betterlily.BetterLily;
import net.mehvahdjukaar.betterlily.BetterLilyClient;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;

public class BetterLilyFabric implements ModInitializer {

    public void onInitialize() {
        BetterLily.init();
        if (PlatHelper.getPhysicalSide().isClient()) {
            ClientHelper.addClientSetup(BetterLilyClient::init);
        }
    }

}

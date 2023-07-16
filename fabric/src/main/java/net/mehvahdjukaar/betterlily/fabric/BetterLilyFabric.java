package net.mehvahdjukaar.betterlily.fabric;

import net.fabricmc.api.ModInitializer;
import net.mehvahdjukaar.betterlily.BetterLily;
import net.mehvahdjukaar.betterlily.BetterLilyClient;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatformHelper;

public class BetterLilyFabric implements ModInitializer {

    public void onInitialize() {
        BetterLily.init();
        if(PlatformHelper.getEnv().isClient()){
            ClientPlatformHelper.addClientSetup(BetterLilyClient::init);
        }
    }

}

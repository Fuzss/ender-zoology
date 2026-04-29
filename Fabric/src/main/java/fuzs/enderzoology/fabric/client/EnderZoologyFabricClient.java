package fuzs.enderzoology.fabric.client;

import fuzs.enderzoology.common.EnderZoology;
import fuzs.enderzoology.common.client.EnderZoologyClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class EnderZoologyFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(EnderZoology.MOD_ID, EnderZoologyClient::new);
    }
}

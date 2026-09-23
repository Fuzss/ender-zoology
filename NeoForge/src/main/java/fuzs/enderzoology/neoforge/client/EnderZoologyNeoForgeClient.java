package fuzs.enderzoology.neoforge.client;

import fuzs.enderzoology.common.EnderZoology;
import fuzs.enderzoology.common.client.EnderZoologyClient;
import fuzs.enderzoology.common.data.client.ModLanguageProvider;
import fuzs.enderzoology.common.data.client.ModModelProvider;
import fuzs.enderzoology.neoforge.data.client.ModSoundDefinitionProvider;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = EnderZoology.MOD_ID, dist = Dist.CLIENT)
public class EnderZoologyNeoForgeClient {

    public EnderZoologyNeoForgeClient() {
        ClientModConstructor.construct(EnderZoology.MOD_ID, EnderZoologyClient::new);
        DataProviderBuilder.of(EnderZoology.MOD_ID)
                .addProvider(ModLanguageProvider::new, ModModelProvider::new, ModSoundDefinitionProvider::new);
    }
}

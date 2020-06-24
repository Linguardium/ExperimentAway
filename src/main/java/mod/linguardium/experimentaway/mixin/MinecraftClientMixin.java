package mod.linguardium.experimentaway.mixin;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.SaveProperties;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Redirect(at=@At(value="INVOKE", target="Lnet/minecraft/world/SaveProperties;method_29588()Lcom/mojang/serialization/Lifecycle;"), method="Lnet/minecraft/client/MinecraftClient;startIntegratedServer(Ljava/lang/String;Lnet/minecraft/util/registry/RegistryTracker$Modifiable;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function4;ZLnet/minecraft/client/MinecraftClient$WorldLoadAction;)V")
    private Lifecycle pretendWeAreStable(SaveProperties saveProperties) {
        Lifecycle ret = saveProperties.method_29588();
        if (ret.equals(Lifecycle.experimental()))
            return Lifecycle.stable();
        return ret;
    }
}

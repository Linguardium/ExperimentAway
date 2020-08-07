package mod.linguardium.experimentaway.mixin;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.Lifecycle;
import net.minecraft.client.gui.screen.world.MoreOptionsDialog;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MoreOptionsDialog.class)
public class MoreOptionsDialogMixin {

    @ModifyVariable(at=@At(value="FIELD",target="Lnet/minecraft/client/gui/screen/world/MoreOptionsDialog;field_25046:Lorg/apache/logging/log4j/Logger;", opcode = Opcodes.GETSTATIC),method="method_29071(Lnet/minecraft/client/gui/screen/world/CreateWorldScreen;Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/gui/widget/ButtonWidget;)V", name="lifecycle")
    private Lifecycle returnIsStable(Lifecycle lifecycle) {
        if (lifecycle.equals(Lifecycle.experimental()))
            return Lifecycle.stable();
        return lifecycle;
    }
}

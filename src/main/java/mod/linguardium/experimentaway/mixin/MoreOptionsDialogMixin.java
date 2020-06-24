package mod.linguardium.experimentaway.mixin;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.Lifecycle;
import net.minecraft.client.gui.screen.world.MoreOptionsDialog;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MoreOptionsDialog.class)
public class MoreOptionsDialogMixin {

    @Redirect(at=@At(value="INVOKE",target="com/mojang/serialization/DataResult.lifecycle()Lcom/mojang/serialization/Lifecycle;"),method="method_29071")
    private Lifecycle returnIsStable(DataResult dataResult) {
        Lifecycle ret = dataResult.lifecycle();
        if (ret.equals(Lifecycle.experimental()))
            return Lifecycle.stable();
        return ret;
    }
}

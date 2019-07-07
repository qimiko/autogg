package dev.xyze.autogg.mixin;

import net.minecraft.client.gui.screen.ConnectScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw._2pi.autogg.gg.AutoGG;

@Mixin(ConnectScreen.class)
public class MixinConnectScreen {
    @Inject(method = "connect", at = @At("TAIL"))
    private void onConnect(final String address, final int port, CallbackInfo callback) {
        AutoGG.getInstance().setOnHypixel(true);
    }
}

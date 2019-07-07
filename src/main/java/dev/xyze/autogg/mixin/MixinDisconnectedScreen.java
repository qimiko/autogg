package dev.xyze.autogg.mixin;

import net.minecraft.client.gui.screen.DisconnectedScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw._2pi.autogg.gg.AutoGG;

@Mixin(DisconnectedScreen.class)
public class MixinDisconnectedScreen {
    @Inject(method = "render", at = @At("HEAD"))
    public void onRender(int mouseX, int mouseY, float delta, CallbackInfo callback) {
        AutoGG.getInstance().setOnHypixel(false);
    }
}

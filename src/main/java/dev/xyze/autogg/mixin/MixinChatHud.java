package dev.xyze.autogg.mixin;

import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw._2pi.autogg.gg.AutoGG;
import net.minecraft.client.gui.hud.ChatHud;
import pw._2pi.autogg.gg.GGThread;

@Mixin(ChatHud.class)
public class MixinChatHud {
    @Inject(method = "addMessage(Lnet/minecraft/text/Text;IIZ)V", at= @At("TAIL"))
    public void onAddMessage(Text message, int id, int timestamp, boolean longMessage, CallbackInfo callbackInfo) {
        if (!AutoGG.getInstance().isHypixel() || !AutoGG.getInstance().isToggled() || AutoGG.getInstance().isRunning() || AutoGG.getInstance().getTriggers().isEmpty()) {
            return;
        }

        if (AutoGG.getInstance().getTriggers().stream().anyMatch(trigger -> message.getString().contains(trigger.toString())) && message.getString().startsWith(" ")) {
            AutoGG.getInstance().setRunning(true);
            AutoGG.THREAD_POOL.submit(new GGThread());
        }
    }
}

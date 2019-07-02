package dev.xyze.autogg.mixin;

import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pw._2pi.autogg.gg.AutoGG;
import net.minecraft.client.gui.hud.ChatHud;

@Mixin(ChatHud.class)
public class MixinChatHud {
    @Inject(method = "addMessage(Lnet/minecraft/text/Text;IIZ)V", at= @At("TAIL"))
    public void onAddMessage(Text message, int id, int timestamp, boolean what, CallbackInfo callbackInfo) {

        System.out.println(message.asString());

        if (!AutoGG.getInstance().isHypixel() || !AutoGG.getInstance().isToggled() || AutoGG.getInstance().isRunning() || AutoGG.getInstance().getTriggers().isEmpty()) {
            return;
        }


        if (AutoGG.getInstance().getTriggers().stream().anyMatch(trigger -> message.asString().contains(trigger.toString())) && message.asString().startsWith(" ")) {
            AutoGG.getInstance().setRunning(true);
            // there is no way i'm going to let this run at this point
            System.out.println("gg");
            //AutoGG.THREAD_POOL.submit(new GGThread());
            AutoGG.getInstance().setRunning(false);
        }
    }
}

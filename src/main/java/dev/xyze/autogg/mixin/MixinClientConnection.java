package dev.xyze.autogg.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.net.InetAddress;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pw._2pi.autogg.gg.AutoGG;

@Mixin(ClientConnection.class)
public class MixinClientConnection {
    @Inject(method = "connect(Ljava/net/InetAddress;IZ)Lnet/minecraft/network/ClientConnection;", at = @At("HEAD"))
    private static void onConnect(InetAddress address, int port, boolean shouldUseNativeTransport, CallbackInfoReturnable<ClientConnection> callbackInfo) {
        System.out.println("Player connected to Hypixel!");
        AutoGG.getInstance().setOnHypixel(address.toString().toLowerCase().contains("hypixel.net"));
    }
    @Inject(method = "disconnect", at = @At("TAIL"))
    private void onDisconnect(Text reason, CallbackInfo callbackInfo) {
        if (reason == null) {
            return;
        }

        // i personally do not know all the reasons of disconnection, but these are the ones that i see the most
        switch (reason.asString()){
            case ("Finished"):
            case ("End of stream"):
                return;
        }
        System.out.println("Player disconnected from Hypixel for reason " + reason.asString());
        AutoGG.getInstance().setOnHypixel(false);
    }
}
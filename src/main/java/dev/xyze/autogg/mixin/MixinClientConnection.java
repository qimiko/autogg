package dev.xyze.autogg.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.net.InetAddress;
import java.net.SocketAddress;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pw._2pi.autogg.gg.AutoGG;

@Mixin(ClientConnection.class)
public class MixinClientConnection {
    @Inject(method = "connect(Ljava/net/InetAddress;IZ)Lnet/minecraft/network/ClientConnection;", at = @At("HEAD"))
    private static void onConnect(InetAddress address, int port, boolean shouldUseNativeTransport, CallbackInfoReturnable<ClientConnection> callbackInfo) {
        AutoGG.getInstance().setOnHypixel(address.toString().toLowerCase().contains("hypixel.net"));
    }
    @Inject(method = "disconnect", at = @At("TAIL"))
    private void onDisconnect(Text reason, CallbackInfo callbackInfo) {
        AutoGG.getInstance().setOnHypixel(false);
    }
}
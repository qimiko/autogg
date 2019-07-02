/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 */
package pw._2pi.autogg.gg;

import pw._2pi.autogg.gg.AutoGG;
import net.minecraft.client.MinecraftClient;

public class GGThread
implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(AutoGG.getInstance().getDelay() * 1000);
            AutoGG.getInstance().getMinecraft().player.sendChatMessage("/achat " + AutoGG.getInstance().getMessage());
            Thread.sleep(2000L);
            AutoGG.getInstance().setRunning(false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}


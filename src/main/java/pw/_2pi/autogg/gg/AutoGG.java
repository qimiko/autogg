/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.command.ICommand
 *  net.minecraft.network.NetworkManager
 *  net.minecraftforge.client.ClientCommandHandler
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.eventhandler.EventBus
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientConnectedToServerEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientDisconnectionFromServerEvent
 */
package pw._2pi.autogg.gg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.client.MinecraftClient;
import net.fabricmc.api.ClientModInitializer;
import pw._2pi.autogg.util.AutoGGThreadFactory;
import pw._2pi.autogg.util.ConfigUtil;
import pw._2pi.autogg.util.GetTriggers;
import dev.xyze.autogg.commands.GGCommand;

public class AutoGG implements ClientModInitializer {
    public static final ExecutorService THREAD_POOL = Executors.newCachedThreadPool(new AutoGGThreadFactory());
    private static AutoGG instance;
    private final MinecraftClient mc = MinecraftClient.getInstance();
    private boolean onHypixel = false;
    private boolean toggle = true;
    private int delay = 1;
    private String message = "gg";
    private List<String> triggers;
    private Boolean running = false;

    public static AutoGG getInstance() {
        return instance;
    }

    @Override
    public void onInitializeClient() {
        instance = this;
        CommandRegistry.INSTANCE.register(false, (dispatcher) -> GGCommand.register(dispatcher));
        THREAD_POOL.submit(new GetTriggers());
        this.delay = ConfigUtil.getConfigDelay();
    }

    public void setOnHypixel(boolean onHypixel) { this.onHypixel = onHypixel; }

    public boolean isHypixel() {
        return this.onHypixel;
    }

    public List getTriggers() {
        return this.triggers;
    }

    public void setTriggers(ArrayList triggers) {
        this.triggers = triggers;
    }

    public boolean isToggled() {
        return this.toggle;
    }

    public void setToggled() {
        this.toggle = !this.toggle;
    }

    public int getDelay() {
        return this.delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getMessage() { return this.message; }

    public void setMessage(String message) { this.message = message; }

    public MinecraftClient getMinecraft() { return this.mc; }

    public boolean isRunning() {
        return this.running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}


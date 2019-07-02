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

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.MinecraftClient;
import net.fabricmc.api.ClientModInitializer;
import pw._2pi.autogg.util.AutoGGThreadFactory;
import dev.xyze.autogg.util.ConfigUtil;
import dev.xyze.autogg.util.ConfigUtil.AutoGGConfig;

import pw._2pi.autogg.util.GetTriggers;
import dev.xyze.autogg.commands.GGCommand;
import io.github.cottonmc.clientcommands.ClientCommandPlugin;
import io.github.cottonmc.clientcommands.CottonClientCommandSource;

public class AutoGG implements ClientModInitializer, ClientCommandPlugin {
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
        THREAD_POOL.submit(new GetTriggers());
        AutoGGConfig config = ConfigUtil.getConfig();
        this.delay = config.delay;
        this.message = config.message;
    }

    @Override
    public void registerCommands(CommandDispatcher<CottonClientCommandSource> cd) {
        GGCommand.register(cd);
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


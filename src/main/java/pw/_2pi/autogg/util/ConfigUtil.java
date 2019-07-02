/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package pw._2pi.autogg.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import net.minecraft.client.MinecraftClient;
import pw._2pi.autogg.gg.AutoGG;

public class ConfigUtil {
    private static File CONFIG_FILE = new File(AutoGG.getInstance().getMinecraft().runDirectory + "/config/autogg_delay.cfg");

    public static void setConfigDelay() {
        try {
            if (!CONFIG_FILE.exists()) {
                CONFIG_FILE.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(CONFIG_FILE));
            bw.write(Integer.toString(AutoGG.getInstance().getDelay()));
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getConfigDelay() {
        if (!CONFIG_FILE.exists()) {
            ConfigUtil.setConfigDelay();
        }
        try {
            String delayString = new String(Files.readAllBytes(CONFIG_FILE.toPath()), StandardCharsets.UTF_8);
            System.out.println(delayString);
            int delay = Integer.parseInt(delayString);
            if (delay < 0 || delay > 5) {
                throw new NumberFormatException("Invalid integer");
            }
            return delay;
        }
        catch (NumberFormatException nfe) {
            CONFIG_FILE.delete();
            ConfigUtil.setConfigDelay();
            nfe.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return AutoGG.getInstance().getDelay();
    }
}


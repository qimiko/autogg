package dev.xyze.autogg.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.fabricmc.loader.FabricLoader;
import pw._2pi.autogg.gg.AutoGG;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigUtil {

    private  static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
    private static File CONFIG_FILE = new File(AutoGG.getInstance().getMinecraft().runDirectory + "/config/autogg.json");

    // creates config if it doesn't exist
    public static void initConfig() {
        try {
            if (!CONFIG_FILE.exists() && CONFIG_FILE.createNewFile()) {
                AutoGGConfig defaultConfig = getDefault();
                String json = gson.toJson(defaultConfig, AutoGGConfig.class);
                FileWriter writer = new FileWriter(CONFIG_FILE);
                writer.write(json);
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Error creating default configuration.");
        }
    }

    public static AutoGGConfig getConfig() {
        if (!CONFIG_FILE.exists()) {
            initConfig();
        }
        try {
            return gson.fromJson(new FileReader(CONFIG_FILE), AutoGGConfig.class);
        } catch (IOException e) {
            initConfig();
            System.out.println("Error getting configuration.");
            return getDefault();
        }
    }

    public static void setConfig(AutoGGConfig newConfig) {
        // this also writes and such, basically
        // getconfig -> set message -> setconfig
        String json = gson.toJson(newConfig, AutoGGConfig.class);

        try {
            FileWriter writer = new FileWriter(CONFIG_FILE);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to configuration.");
        }
    }

    private static AutoGGConfig getDefault() {
        return new AutoGGConfig("gg", 1);
    }

    public static class AutoGGConfig {
        public String message;
        public int delay;

        public AutoGGConfig(String message, int delay) {
            this.message = message;
            this.delay = delay;
        }
    }
}

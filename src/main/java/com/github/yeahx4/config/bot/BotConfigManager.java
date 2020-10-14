package com.github.yeahx4.config.bot;

import com.github.yeahx4.Main;
import com.github.yeahx4.util.Logger;

import java.io.*;
import java.util.Properties;

public class BotConfigManager {
    final static public String path = "config.properties";

    public static void writeNewConfig() {
        Logger logger = new Logger("ConfigManager");

        try (OutputStream output = new FileOutputStream(path)) {
            Properties props = new Properties();

            props.setProperty("bot.token", "YOUR_TOKEN_HERE");
            props.setProperty("bot.prefix", "!");

            props.store(output, null); // Write properties into local file
            logger.log("New config.properties has been set up");
        } catch (IOException io) {
            logger.err(io.getMessage());
        }
    }

    public static boolean isConfigExists() {
        File file = new File(path);
        return file.exists();
    }

    public static Properties loadConfig() {
        try(InputStream input = new FileInputStream(path)) {
            if (!isConfigExists()) {
                writeNewConfig();
            }

            Properties props = new Properties();

            props.load(input);
            return props;
        } catch (IOException io) {
            io.printStackTrace();
            return null;
        }
    }
}

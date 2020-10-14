package com.github.yeahx4;

import com.github.yeahx4.config.bot.BotConfigManager;
import com.github.yeahx4.listener.MessageReceiveListener;
import com.github.yeahx4.util.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger("Main");

        try {
            if (!BotConfigManager.isConfigExists()) {
                System.out.println("cannot find config.properties, creating new one");
                BotConfigManager.writeNewConfig();
            }

            Properties config = BotConfigManager.loadConfig();
            if (config == null) {
                throw new NullPointerException();
            }

            JDA api = JDABuilder.createDefault(config.getProperty("bot.token")).build();
            api.addEventListener(new MessageReceiveListener());
            logger.success("Bot started");
        } catch (LoginException ex1) {
            logger.err("Fail to log in Discord");
        } catch (NullPointerException ex) {
            logger.err("Config is null");
            System.exit(-1);
        }
    }
}

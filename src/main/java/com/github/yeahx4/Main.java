package com.github.yeahx4;

import com.github.yeahx4.config.bot.BotConfigManager;
import com.github.yeahx4.listener.MessageReceiveListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
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
        } catch (LoginException ex1) {
            System.out.println("Fail to log in Discord");
        } catch (NullPointerException ex) {
            System.out.println("Config is null");
            System.exit(-1);
        }
    }
}

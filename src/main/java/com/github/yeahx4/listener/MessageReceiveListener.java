package com.github.yeahx4.listener;

import com.github.yeahx4.command.CommandManager;
import com.github.yeahx4.config.bot.BotConfigManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Properties;

public class MessageReceiveListener extends ListenerAdapter {
    private JDA api;

    public MessageReceiveListener(JDA api) {
        this.api = api;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) throws NullPointerException {
        if (event.getAuthor().isBot()) return;

        Properties config = BotConfigManager.loadConfig();
        if (config == null) throw new NullPointerException();
        String prefix = config.getProperty("bot.prefix");

        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (!content.startsWith(prefix)) return;

        CommandManager.executeCommand(this.api, message);
    }
}

package dev.ammar.ezspawn.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static dev.ammar.ezspawn.EzSpawn.config;

public class Reload implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String cmd, @NotNull String[] args) {
        if (cmd.equalsIgnoreCase("ezspawn") && args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            try {
                config.reload();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                player.sendMessage(coloredMessage(config.getString("prefix")) + ChatColor.GREEN + " Config has been reloaded");
            } else {
                commandSender.sendMessage("Config has been reloaded");
            }
        }
        return false;
    }

    public String coloredMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}

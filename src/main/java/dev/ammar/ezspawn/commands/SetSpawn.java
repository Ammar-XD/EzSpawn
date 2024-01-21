package dev.ammar.ezspawn.commands;

import dev.ammar.ezspawn.managers.LocationSaver;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static dev.ammar.ezspawn.EzSpawn.config;

public class SetSpawn implements CommandExecutor {

    private final String prefix = config.getString("prefix");
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String cmd, @NotNull String[] args) {
        if (cmd.equalsIgnoreCase("setspawn")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                // Save the spawn location
                // Method called from LocationSaver
                try {
                    new LocationSaver().saveSpawn(player.getLocation());
                    player.sendMessage(coloredMessage(prefix) + ChatColor.GREEN + " Spawn location has been set.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                sender.sendMessage(prefix + ChatColor.RED + " Only players can run this command.");
            }
        }
        return false;
    }

    public String coloredMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}

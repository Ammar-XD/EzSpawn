package dev.ammar.ezspawn.commands;

import dev.ammar.ezspawn.Teleport.TeleportCommands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String cmd, @NotNull String[] args) {
        if (cmd.equalsIgnoreCase("spawn")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                new TeleportCommands().teleport(player);
                return true;
            }
        }
        return false;
    }
}

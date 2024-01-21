package dev.ammar.ezspawn.listeners;

import dev.ammar.ezspawn.Teleport.TeleportCommands;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static dev.ammar.ezspawn.EzSpawn.config;

public class JoinListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        if(config.getBoolean("teleportOnJoin", true)) {
            Player player = e.getPlayer();
            new TeleportCommands().teleport(player);
        }
    }
}

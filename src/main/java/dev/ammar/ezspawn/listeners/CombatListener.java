package dev.ammar.ezspawn.listeners;

import dev.ammar.ezspawn.managers.LocationSaver;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static dev.ammar.ezspawn.EzSpawn.config;

public class CombatListener implements Listener {

    private final String prefix = config.getString("prefix");

    private final String spawnMessage = config.getString("message");
    private final Map<UUID, Long> combatTag = new HashMap<>();
    private final int combatDuration = config.getInt("combat-timer");

    public void checkCombat(Player player) {
        if (!isInCombat(player)) {
            Location teleportLocation = new LocationSaver().loadLocation();
            if (teleportLocation != null) {
                player.teleport(teleportLocation);
                player.sendMessage(colorMessage(prefix) + ChatColor.GREEN +  " " + colorMessage(spawnMessage));
            }
        }
    }

    public void handleCombatTag(Player player) {
        combatTag.put(player.getUniqueId(), System.currentTimeMillis() + (combatDuration * 1000L));
    }

    private boolean isInCombat(Player player) {
        Long combatTagExpiration = combatTag.get(player.getUniqueId());
        return combatTagExpiration != null && combatTagExpiration > System.currentTimeMillis();
    }

    @EventHandler
    public void onCommandRun(PlayerCommandPreprocessEvent e) {

        Player player = e.getPlayer();
        if (isInCombat(player)) {
            e.setCancelled(true);
            player.sendMessage(colorMessage(prefix) + " You are currently in combat");
        }
    }

    private String colorMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}

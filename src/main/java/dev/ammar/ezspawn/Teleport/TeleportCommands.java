package dev.ammar.ezspawn.Teleport;

import dev.ammar.ezspawn.listeners.CombatListener;
import dev.ammar.ezspawn.managers.LocationSaver;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import static dev.ammar.ezspawn.EzSpawn.config;

public class TeleportCommands {

    public void teleport(Player player) {

        Location spawnLocation = new LocationSaver().loadLocation();

        if (spawnLocation != null) {

            if (config.getBoolean("clearInventory", true)) {
                clearInv(player);
            }

            if (config.getBoolean("clearPotionEffects", true)) {
                clearEffects(player);
            }

            if (config.getBoolean("heal", true)) {
                heal(player);
            }

            new CombatListener().checkCombat(player);

        }

    }

    // Clear the player Inventory
    public void clearInv(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.updateInventory();
    }

    // Clear the potion effects
    public void clearEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }

    // Heal & feed
    public void heal(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
    }

    // Play a sound effect
    public void soundEffect(Player player) {

        // This is still being worked on

    }

}

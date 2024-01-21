package dev.ammar.ezspawn.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.IOException;

import static dev.ammar.ezspawn.EzSpawn.config;

public class LocationSaver {

    // Load the spawn location
    // This includes [Coordinates / Yaw / Pitch]
    public Location loadLocation() {
        String worldName = config.getString("spawn.world", "world");
        double x = config.getDouble("spawn.x", Double.valueOf("0"));
        double y = config.getDouble("spawn.y", Double.valueOf("0"));
        double z = config.getDouble("spawn.z", Double.valueOf("0"));
        float yaw = config.getFloat("spawn.yaw", Float.valueOf("0"));
        float pitch = config.getFloat("spawn.pitch", Float.valueOf("0"));

        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            return new Location(world, x, y, z, yaw, pitch);
        }
        return null;
    }

    public void saveSpawn(Location location) throws IOException {
        config.set("spawn.world", location.getWorld().getName());
        config.set("spawn.x", location.getX());
        config.set("spawn.y", location.getY());
        config.set("spawn.z", location.getZ());
        config.set("spawn.pitch", location.getPitch());
        config.set("spawn.yaw", location.getYaw());

        config.save();
        config.reload();
    }

}

package dev.ammar.ezspawn;

import dev.ammar.ezspawn.WarpSystem.WarpHandler;
import dev.ammar.ezspawn.commands.Reload;
import dev.ammar.ezspawn.commands.SetSpawn;
import dev.ammar.ezspawn.commands.Spawn;
import dev.ammar.ezspawn.listeners.CombatListener;
import dev.ammar.ezspawn.listeners.DamageListener;
import dev.ammar.ezspawn.listeners.JoinListener;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class EzSpawn extends JavaPlugin {

    public static YamlDocument config;
    private WarpHandler warpHandler;

    @Override
    public void onEnable() {

        try {
            config = YamlDocument.create(new File(getDataFolder(), "config.yml"), getResource("config.yml"),
                    GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("file-version")).build());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        getLogger().info("Loaded config.yml");

        // Register commands and Listeners
        // Commands
        getCommand("setspawn").setExecutor(new SetSpawn());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("ezspawn").setExecutor(new Reload());

        getLogger().info("Registered commands");


        // Listeners
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new CombatListener(), this);

        getLogger().info("Registered listeners");

        // Warp System

        warpHandler = new WarpHandler();
        getCommand("warp").setExecutor(warpHandler);
        getCommand("setwarp").setExecutor(warpHandler);
        getCommand("warplist").setExecutor(warpHandler);
        getCommand("delwarp").setExecutor(warpHandler);
        getCommand("warprename").setExecutor(warpHandler);
        getCommand("warpUpdate").setExecutor(warpHandler);
        getCommand("warpshare").setExecutor(warpHandler);
        getLogger().info("WarpSystem loaded");

    }

    @Override
    public void onDisable() {
        try {
            config.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return warpHandler.onCommand(sender, cmd, label, args);
    }
}

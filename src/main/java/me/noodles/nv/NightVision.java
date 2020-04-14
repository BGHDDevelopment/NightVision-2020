package me.noodles.nv;

import me.noodles.nv.listeners.UpdateJoinEvent;
import me.noodles.nv.utilities.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class NightVision extends JavaPlugin implements Listener {
    public static NightVision plugin;
    public static Plugin plugin2;
    private UpdateChecker checker;

    public void onEnable() {
        NightVision.plugin = this;
        final PluginDescriptionFile VarUtilType = this.getDescription();
        this.getLogger().info("NightVision  V" + VarUtilType.getVersion() + " starting...");
        this.saveDefaultConfig();
        this.reloadConfig();
        registerEvents((Plugin)this, new UpdateJoinEvent(this));
        registerEvents(this, this);
        this.getCommand("nv").setExecutor((CommandExecutor)new CommandNV());
        this.getLogger().info("NightVision  V" + VarUtilType.getVersion() + " started!");
        this.setEnabled(true);
        this.getLogger().info("NightVision V" + VarUtilType.getVersion() + " checking for updates...");

        if (getConfig().getBoolean("CheckForUpdates.Enabled", true)) {
            new UpdateChecker(this, 46693).getLatestVersion(remoteVersion -> {
                getLogger().info("Checking for Updates ...");

                if (getDescription().getVersion().equalsIgnoreCase(remoteVersion)) {
                    getLogger().info("No new version available");
                } else {
                    getLogger().warning(String.format("Newest version: %s it out! You are running version: %s", remoteVersion, getDescription().getVersion()));
                    getLogger().warning("Please Update Here: http://www.spigotmc.org/resources/46693");
                }
            });
        }
    }

    public static void registerEvents(final Plugin plugin, final Listener... listeners) {
        for (final Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes"})
    public static NightVision getPlugin() {
        return (NightVision)getPlugin((Class)NightVision.class);
    }

    public static Plugin getPlugin2() {
        return (Plugin)NightVision.plugin;
    }

}

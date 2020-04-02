package me.noodles.nv;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MainNightV extends JavaPlugin implements Listener  {
	
	
	    public static MainNightV plugin;
	    public static Plugin plugin2;
	    private UpdateChecker checker;
	    String DownloaderType;
	    String GsonEntry;
	    String SpigotHost;
	   
		
		
	    public void onEnable() {
	        MainNightV.plugin = this;
	        final PluginDescriptionFile VarUtilType = this.getDescription();
	        this.getLogger().info("NightVision  V" + VarUtilType.getVersion() + " starting...");
	        this.saveDefaultConfig();
	        this.reloadConfig();
	        registerEvents((Plugin)this, new UpdateJoinEvent());
	        registerEvents(this, this);
	        this.getCommand("nv").setExecutor((CommandExecutor)new CommandNV());
	        this.getLogger().info("NightVision  V" + VarUtilType.getVersion() + " started!");
	        this.setEnabled(true);
	        this.getLogger().info("NightVision V" + VarUtilType.getVersion() + " checking for updates...");
	        this.checker = new UpdateChecker(this);
	        if (this.checker.isConnected()) {
	            if (this.checker.hasUpdate()) {
	                getServer().getConsoleSender().sendMessage("------------------------");
	                getServer().getConsoleSender().sendMessage("NightVision is outdated!");
	                getServer().getConsoleSender().sendMessage("Newest version: " + this.checker.getLatestVersion());
	                getServer().getConsoleSender().sendMessage("Your version: " + MainNightV.plugin.getDescription().getVersion());
	                getServer().getConsoleSender().sendMessage("Please Update Here: https://www.spigotmc.org/resources/46693");
	                getServer().getConsoleSender().sendMessage("------------------------");
	            }
	            else {
	                getServer().getConsoleSender().sendMessage("------------------------");
	                getServer().getConsoleSender().sendMessage("NightVision is up to date!");
	                getServer().getConsoleSender().sendMessage("------------------------");            }
	        }
	    }
	    
	    public static void registerEvents(final Plugin plugin, final Listener... listeners) {
	        for (final Listener listener : listeners) {
	            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
	        }
	    }
	    
	    @SuppressWarnings({ "unchecked", "rawtypes"})
		public static MainNightV getPlugin() {
	        return (MainNightV)getPlugin((Class)MainNightV.class);
	    }
	    
	    public static Plugin getPlugin2() {
	        return (Plugin)MainNightV.plugin;
	    }
	
	
}


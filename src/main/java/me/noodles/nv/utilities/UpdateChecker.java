package me.noodles.nv.utilities;

import me.noodles.nv.NightVision;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public final class UpdateChecker {
    private final String URL = "https://api.spigotmc.org/legacy/update.php?resouce=";

    private final NightVision plugin;
    private final int resourceId;

    public UpdateChecker(NightVision plugin, int resourceId) {
        this.resourceId = resourceId;
        this.plugin = plugin;
    }

    public void getLatestVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), () -> {
           try (InputStream inputStream = new URL(getURL() + getResourceId()).openStream(); Scanner scanner = new Scanner(inputStream)) {
               if (scanner.hasNext()) {
                   consumer.accept(scanner.next());
               }
           } catch (IOException exception) {
               getPlugin().getLogger().info("Cannot look for updates: " + exception.getMessage());
           }
        });
    }

    private String getURL() {
        return URL;
    }

    private NightVision getPlugin() {
        return plugin;
    }

    private int getResourceId() {
        return resourceId;
    }

}

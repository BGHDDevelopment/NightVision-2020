package me.noodles.nv;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;

public class CommandNV implements CommandExecutor {
    String Enabled;
    String Disabled;

    public CommandNV() {
        this.Enabled = ChatColor.translateAlternateColorCodes('&', NightVision.getPlugin().getConfig().getString("Messages.Enabled"));
        this.Disabled = ChatColor.translateAlternateColorCodes('&', NightVision.getPlugin().getConfig().getString("Messages.Disabled"));

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)){
            Bukkit.getServer().getLogger().info("Only players can do this!");
            return true;
        }
        Player player = (Player)sender;
        if (player.hasPermission("nv.use")) {
            if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                player.sendMessage(String.valueOf(this.Disabled));
            }
            else {
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
                player.sendMessage(String.valueOf(this.Enabled));
            }
        }
        return true;
    }

}

package me.noodles.nv.commands;

import com.google.common.collect.ImmutableList;
import me.noodles.nv.NightVision;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class NVCommand implements TabExecutor {
    private final ImmutableList<String> keywords = ImmutableList.of("reload");
    private final NightVision plugin;

    public NVCommand(NightVision plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;

            if (args.length == 1 && player.hasPermission("nv.reload")) {
                if (getKeywords().contains(args[0])) {
                    final String keyword = args[0];

                    if (keyword.equalsIgnoreCase("reload")) {
                        getPlugin().reloadConfig();

                        sender.sendMessage(translate("&aConfig has been reloaded."));

                        return true;
                    }
                }
            }

            if (player.hasPermission("nv.use")) {
                if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    player.sendMessage(translate(getPlugin().getConfig().getString("Messages.Disabled")));
                } else {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
                    player.sendMessage(translate(getPlugin().getConfig().getString("Messages.Enabled")));
                }
            } else {
                player.sendMessage(translate("&cYou don't have permission to run that command."));
            }

            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return (args.length == 1 && sender.hasPermission("nv.reload")) ? StringUtil.copyPartialMatches(args[0], getKeywords(), new ArrayList<>()) : Collections.emptyList();
    }

    private String translate(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    private ImmutableList<String> getKeywords() {
        return keywords;
    }

    private NightVision getPlugin() {
        return plugin;
    }

}

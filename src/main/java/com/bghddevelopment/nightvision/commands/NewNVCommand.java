package com.bghddevelopment.nightvision.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.bghddevelopment.nightvision.NightVision;
import com.bghddevelopment.nightvision.utils.Color;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@CommandAlias("nightvision|nv")
@Description("Night Vision Command.")
@CommandPermission("nv.use")
@Conditions("noconsole")
public class NewNVCommand extends BaseCommand {

    @Dependency
    private NightVision plugin;

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.sendMessage(Color.translate(NightVision.getInstance().getConfig().getString("Messages.Disabled")));
        } else {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
            player.sendMessage(Color.translate(NightVision.getInstance().getConfig().getString("Messages.Enabled")));
        }
    }
}

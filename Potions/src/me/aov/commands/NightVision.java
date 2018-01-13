package me.aov.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.aov.PotionsMain;

public class NightVision implements CommandExecutor {
	private PotionsMain plugin;

	public NightVision() {
	}

	public NightVision(PotionsMain instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		p = p.getPlayer();
		if (command.getName().equalsIgnoreCase("nv")) {
			if (!p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000,
						plugin.getConfig().getInt("Potions.Levels.NV")), true);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("Potions.Messages.NVOn")));
				return true;
			} else {
				p.removePotionEffect(PotionEffectType.NIGHT_VISION);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("Potions.Messages.NVOff")));
				return true;
			}

		}
		return false;
	}
}

package me.aov.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.aov.PotionsMain;

public class Speed implements CommandExecutor {
	private PotionsMain plugin;

	public Speed() {
	}

	public Speed(PotionsMain instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		p = p.getPlayer();
		if (command.getName().equalsIgnoreCase("speedp")) {
			if (!p.hasPotionEffect(PotionEffectType.SPEED)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000,
						plugin.getConfig().getInt("Potions.Levels.Speed")), true);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("Potions.Messages.SpeedOn")));
				return true;
			} else {
				p.removePotionEffect(PotionEffectType.SPEED);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("Potions.Messages.SpeedOff")));
				return true;
			}

		}
		return false;
	}
}

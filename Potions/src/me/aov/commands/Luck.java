package me.aov.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.aov.PotionsMain;

public class Luck implements CommandExecutor {
	private PotionsMain plugin;

	public Luck() {
	}

	public Luck(PotionsMain instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		p = p.getPlayer();
		if (command.getName().equalsIgnoreCase("luck")) {
			if (!p.hasPotionEffect(PotionEffectType.LUCK)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 1000000,
						plugin.getConfig().getInt("Potions.Levels.Luck")), true);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("Potions.Messages.LuckOn")));
				return true;
			} else {
				p.removePotionEffect(PotionEffectType.LUCK);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("Potions.Messages.LuckOff")));
				return true;
			}

		}
		return false;
	}
}

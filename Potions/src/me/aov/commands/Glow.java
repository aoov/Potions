package me.aov.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.aov.PotionsMain;

public class Glow implements CommandExecutor {
	private PotionsMain plugin;

	public Glow() {
	}

	public Glow(PotionsMain instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		p = p.getPlayer();
		if (command.getName().equalsIgnoreCase("glow")) {
			if (!p.hasPotionEffect(PotionEffectType.GLOWING)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000000,
						plugin.getConfig().getInt("Potions.Levels.Glow")), true);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("Potions.Messages.GlowOn")));
				return true;
			} else {
				p.removePotionEffect(PotionEffectType.GLOWING);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("Potions.Messages.GlowOff")));
				return true;
			}

		}
		return false;
	}
}

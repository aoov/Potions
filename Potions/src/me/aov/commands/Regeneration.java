package me.aov.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.aov.PotionsMain;

public class Regeneration implements CommandExecutor {
	private PotionsMain plugin;

	public Regeneration() {
	}

	public Regeneration(PotionsMain instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		p = p.getPlayer();
		if (command.getName().equalsIgnoreCase("regeneration")) {
			if (!p.hasPotionEffect(PotionEffectType.REGENERATION)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000,
						plugin.getConfig().getInt("Potions.Levels.Regeneration")), true);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("Potions.Messages.RegenerationOn")));
				return true;
			} else {
				p.removePotionEffect(PotionEffectType.REGENERATION);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("Potions.Messages.RegenerationOff")));
				return true;
			}

		}
		return false;
	}
}

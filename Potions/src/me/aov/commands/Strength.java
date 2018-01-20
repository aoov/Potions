package me.aov.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.aov.PotionsMain;

public class Strength implements CommandExecutor {
	private PotionsMain plugin;

	public Strength() {
	}

	public Strength(PotionsMain instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		p = p.getPlayer();
		if (command.getName().equalsIgnoreCase("strength")) {
			if (!p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000,
						plugin.getConfig().getInt("Potions.Levels.Strength")), true);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("Potions.Messages.StrengthOn")));
				return true;
			} else {
				p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("Potions.Messages.StrengthOff")));
				return true;
			}

		}
		return false;
	}
}

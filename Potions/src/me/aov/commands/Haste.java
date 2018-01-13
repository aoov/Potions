package me.aov.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.aov.PotionsMain;

public class Haste implements CommandExecutor {
	private PotionsMain plugin;

	public Haste() {
	}

	public Haste(PotionsMain instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		p = p.getPlayer();
		if (command.getName().equalsIgnoreCase("haste")) {
			if (!p.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000,
						plugin.getConfig().getInt("Potions.Levels.Haste")), true);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("Potions.Messages.HasteOn")));
				return true;
			} else {
				p.removePotionEffect(PotionEffectType.FAST_DIGGING);
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("Potions.Messages.HasteOff")));
				return true;
			}

		}
		return false;
	}
}

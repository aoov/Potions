package me.aov.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import me.aov.PotionsMain;

public class Milk implements CommandExecutor {
	private PotionsMain plugin;

	public Milk() {
	}

	public Milk(PotionsMain instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		p = p.getPlayer();
		if(args.length != 0 && args[0] != null && p.hasPermission("pot.milk.others")) {
			for(Player pl : plugin.getServer().getOnlinePlayers()) {
				if(args[0].equalsIgnoreCase(pl.getName())) {
					p = pl;
				}
			}
		}
		if (command.getName().equalsIgnoreCase("Milk")) {
			for (PotionEffect pot : p.getActivePotionEffects()) {
				p.removePotionEffect(pot.getType());
			}
			p.sendMessage(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfig().getString("Potions.Messages.Milk")));
				return true;
		}
		return false;
	}
	public static void removeAll(Player p) {
		for (PotionEffect pot : p.getActivePotionEffects()) {
			p.removePotionEffect(pot.getType());
		}
	}
}

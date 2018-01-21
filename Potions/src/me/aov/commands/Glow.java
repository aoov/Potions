package me.aov.commands;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.aov.PotionsMain;

public class Glow implements CommandExecutor {
	private PotionsMain plugin;
	private static HashMap<String, Long> cds = new HashMap<String, Long>();

	public Glow() {
	}

	public Glow(PotionsMain instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		p = p.getPlayer();
		// Messages
		final String glowOn = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Potions.Messages.GlowOn"));
		final String glowOff = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Potions.Messages.GlowOff"));
		final String prefix = plugin.getPrefix();

		int highestLevel = plugin.getConfig().getInt("Potions.Levels.Glow");

		// Gets the highest level of glow available
		for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
			if (perms.getPermission().startsWith("pot.glow.")) {
				String permission = perms.getPermission().replaceAll("pot.glow.", "");
				if (Integer.parseInt(permission) > highestLevel) {
					highestLevel = Integer.parseInt(permission);
				}
			}
		}
		// End of loop
		if (command.getName().equalsIgnoreCase("glow")) {
			int cd = plugin.getConfig().getInt("Potions.Cooldown") * 1000;
			if (!cds.containsKey(p.getName()) || System.currentTimeMillis() - cd >= cds.get(p.getName())) {
				if (args.length == 0) {

					if (!p.hasPotionEffect(PotionEffectType.GLOWING)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000000, highestLevel - 1), true);
						p.sendMessage(prefix + glowOn);
						cds.put(p.getName(), System.currentTimeMillis());
					} else {
						p.removePotionEffect(PotionEffectType.GLOWING);
						p.sendMessage(prefix + glowOff);
					}
					return true;
				}

				else if (args.length > 0 && Integer.parseInt(args[0]) > 0) {

					if (Integer.parseInt(args[0]) <= highestLevel && !p.hasPotionEffect(PotionEffectType.GLOWING)) {
						p.addPotionEffect(
								new PotionEffect(PotionEffectType.GLOWING, 1000000, Integer.parseInt(args[0]) - 1),
								true);
						p.sendMessage(prefix + glowOn);
						cds.put(p.getName(), System.currentTimeMillis());
					} else if (p.hasPotionEffect(PotionEffectType.GLOWING)) {
						p.removePotionEffect(PotionEffectType.GLOWING);
						p.sendMessage(prefix + glowOff);
					} else {
						p.sendMessage(prefix + ChatColor.GRAY + "You do not have permission for this level!");
					}
					return true;
				} 
			}else {
				String send = Long
						.toString((Math.abs((System.currentTimeMillis() - cd) - cds.get(p.getName()))) / 1000);
				p.sendMessage(prefix + ChatColor.GRAY + "You're still on cooldown! Please wait " + send
						+ " seconds before trying again");
				return true;
			}
		}
		return false;
	}
}

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

public class Blind implements CommandExecutor {
	private PotionsMain plugin;
	private static HashMap<String, Long> cds = new HashMap<String, Long>();

	public Blind() {
	}

	public Blind(PotionsMain instance) {
		this.plugin = instance;
	}

	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		p = p.getPlayer();
		// Messages
		final String blindnessOn = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Potions.Messages.BlindnessOn"));
		final String blindnessOff = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Potions.Messages.BlindnessOff"));
		final String prefix = plugin.getPrefix();

		int highestLevel = plugin.getConfig().getInt("Potions.Levels.Blindness");

		// Gets the highest level of blindness available
		for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
			if (perms.getPermission().startsWith("pot.blindness.")) {
				String permission = perms.getPermission().replaceAll("pot.blindness.", "");
				if (Integer.parseInt(permission) > highestLevel) {
					highestLevel = Integer.parseInt(permission);
				}
			}
		}
		// End of loop
		if (command.getName().equalsIgnoreCase("blind")) {
			int cd = plugin.getConfig().getInt("Potions.Cooldown") * 1000;
			if (args.length == 2 && p.hasPermission("pot.blindness.others")) {
				try {
					int level = Integer.parseInt(args[0]);
				} catch (NumberFormatException e) {
					p.sendMessage(ChatColor.GRAY + "Incorrect format arguments!");
				}
				int level = Integer.parseInt(args[0]);
				Player target = null;
				for (Player pl : plugin.getServer().getOnlinePlayers()) {
					if (pl.getName().equalsIgnoreCase(args[1])) {
						target = pl;
					}
				}
				if ((target.hasPotionEffect(PotionEffectType.BLINDNESS) && target != null)) {
					target.removePotionEffect(PotionEffectType.BLINDNESS);
					p.sendMessage(prefix + blindnessOff);
				} else if (target != null && !(level == 0 && level >= 0)) {
					target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000000, level - 1), true);
					p.sendMessage(prefix + blindnessOn);
				} else if (target == null) {
					p.sendMessage(ChatColor.GRAY + "Player not found!");
				} else if (level == 0 || level <= 0) {
					p.sendMessage(ChatColor.GRAY + "Incorrect Level!");
				}
				return true;
			} else if (!cds.containsKey(p.getName()) || System.currentTimeMillis() - cd >= cds.get(p.getName())) {
				if (args.length == 0) {

					if (!p.hasPotionEffect(PotionEffectType.BLINDNESS)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000000, highestLevel - 1),
								true);
						p.sendMessage(prefix + blindnessOn);
						cds.put(p.getName(), System.currentTimeMillis());
					} else {
						p.removePotionEffect(PotionEffectType.BLINDNESS);
						p.sendMessage(prefix + blindnessOff);
					}
					return true;
				}

				else if (args.length > 0 && Integer.parseInt(args[0]) > 0) {

					if (Integer.parseInt(args[0]) <= highestLevel && !p.hasPotionEffect(PotionEffectType.BLINDNESS)) {
						p.addPotionEffect(
								new PotionEffect(PotionEffectType.BLINDNESS, 1000000, Integer.parseInt(args[0]) - 1),
								true);
						p.sendMessage(prefix + blindnessOn);
						cds.put(p.getName(), System.currentTimeMillis());
					} else if (p.hasPotionEffect(PotionEffectType.BLINDNESS)) {
						p.removePotionEffect(PotionEffectType.BLINDNESS);
						p.sendMessage(prefix + blindnessOff);
					} else {
						p.sendMessage(prefix + ChatColor.GRAY + "You do not have permission for this level!");
					}
					return true;
				}
			} else {
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

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

public class Regeneration implements CommandExecutor {
	private static PotionsMain plugin;
	private static HashMap<String, Long> cds = new HashMap<String, Long>();

	public Regeneration() {
	}

	@SuppressWarnings("static-access")
	public Regeneration(PotionsMain instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		p = p.getPlayer();
		// Messages
		final String regenerationOn = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Potions.Messages.RegenerationOn"));
		final String regenerationOff = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("Potions.Messages.RegenerationOff"));
		final String prefix = plugin.getPrefix();

		int highestLevel = plugin.getConfig().getInt("Potions.Levels.Regeneration");

		// Gets the highest level of regeneration available
		for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
			if (perms.getPermission().startsWith("pot.regeneration.")) {
				String permission = perms.getPermission().replaceAll("pot.regeneration.", "");
				if (Integer.parseInt(permission) > highestLevel) {
					highestLevel = Integer.parseInt(permission);
				}
			}
		}
		// End of loop
		if (command.getName().equalsIgnoreCase("regeneration")) {
			int cd = plugin.getConfig().getInt("Potions.Cooldown") * 1000;
			if (args.length == 2 && p.hasPermission("pot.regeneration.others")) {
				try {
					@SuppressWarnings("unused")
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
				if ((target.hasPotionEffect(PotionEffectType.REGENERATION) && target != null)) {
					target.removePotionEffect(PotionEffectType.REGENERATION);
					p.sendMessage(prefix + regenerationOff);
				} else if (target != null && !(level == 0 && level >= 0)) {
					target.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000, level - 1), true);
					p.sendMessage(prefix + regenerationOn);
				} else if (target == null) {
					p.sendMessage(ChatColor.GRAY + "Player not found!");
				} else if (level == 0 || level <= 0) {
					p.sendMessage(ChatColor.GRAY + "Incorrect Level!");
				}
				return true;
			} else if (!cds.containsKey(p.getName()) || System.currentTimeMillis() - cd >= cds.get(p.getName())) {
				if (args.length == 0) {

					if (!p.hasPotionEffect(PotionEffectType.REGENERATION)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000, highestLevel - 1),
								true);
						p.sendMessage(prefix + regenerationOn);
						cds.put(p.getName(), System.currentTimeMillis());
					} else {
						p.removePotionEffect(PotionEffectType.REGENERATION);
						p.sendMessage(prefix + regenerationOff);

					}
					return true;
				}

				else if (args.length > 0 && Integer.parseInt(args[0]) > 0) {

					if (Integer.parseInt(args[0]) <= highestLevel
							&& !p.hasPotionEffect(PotionEffectType.REGENERATION)) {
						p.addPotionEffect(
								new PotionEffect(PotionEffectType.REGENERATION, 1000000, Integer.parseInt(args[0]) - 1),
								true);
						p.sendMessage(prefix + regenerationOn);
						cds.put(p.getName(), System.currentTimeMillis());
					} else if (p.hasPotionEffect(PotionEffectType.REGENERATION)) {
						p.removePotionEffect(PotionEffectType.REGENERATION);
						p.sendMessage(prefix + regenerationOff);
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
	public static int getLevel(Player p) {

		int highestLevel = plugin.getConfig().getInt("Potions.Levels.Regeneration");
		// Gets the highest level of haste available
		for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
			if (perms.getPermission().startsWith("pot.regeneration.")) {
				String permission = perms.getPermission().replaceAll("pot.regeneration.", "");
				if (Integer.parseInt(permission) > highestLevel) {
					highestLevel = Integer.parseInt(permission);
				}
			}
		}
		return highestLevel;

	}

	public static void giveRegeneration(Player p) {
		p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000, getLevel(p)-1, true));
	}
}

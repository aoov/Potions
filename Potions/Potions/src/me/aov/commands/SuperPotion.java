package me.aov.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.aov.PotionsMain;

public class SuperPotion implements CommandExecutor {
	private PotionsMain plugin;

	public SuperPotion() {
	}

	public SuperPotion(PotionsMain instance) {
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		p = p.getPlayer();


		
		int strLevel = plugin.getConfig().getInt("Potions.Levels.Strength");
		int speedLevel = plugin.getConfig().getInt("Potions.Levels.Speed");
		int luckLevel = plugin.getConfig().getInt("Potions.Levels.Luck");
		int regenLevel = plugin.getConfig().getInt("Potions.Levels.Regeneration");
		int hasteLevel = plugin.getConfig().getInt("Potions.Levels.Haste");
		int nvLevel = plugin.getConfig().getInt("Potions.Levels.NV");
		int glowLevel = plugin.getConfig().getInt("Potions.Levels.Glow");
		
		if (command.getName().equalsIgnoreCase("superpotion")) {
			if(args.length != 0 && args[0] != null && p.hasPermission("pot.superpotion.others")) {
				for(Player pl : plugin.getServer().getOnlinePlayers()) {
					if(args[0].equalsIgnoreCase(pl.getName())) {
						p = pl;
					}
				}
			}
			for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
				if (perms.getPermission().startsWith("pot.strength.")) {
					String permission = perms.getPermission().replaceAll("pot.strength.", "");
					if (Integer.parseInt(permission) > strLevel) {
						strLevel = Integer.parseInt(permission);
					}
				}
			}
			for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
				if (perms.getPermission().startsWith("pot.speed.")) {
					String permission = perms.getPermission().replaceAll("pot.speed.", "");
					if (Integer.parseInt(permission) > speedLevel) {
						speedLevel = Integer.parseInt(permission);
					}
				}
			}
			for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
				if (perms.getPermission().startsWith("pot.luck.")) {
					String permission = perms.getPermission().replaceAll("pot.luck.", "");
					if (Integer.parseInt(permission) > luckLevel) {
						luckLevel = Integer.parseInt(permission);
					}
				}
			}

			for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
				if (perms.getPermission().startsWith("pot.haste.")) {
					String permission = perms.getPermission().replaceAll("pot.haste.", "");
					if (Integer.parseInt(permission) > hasteLevel) {
						hasteLevel = Integer.parseInt(permission);
					}
				}
			}

			for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
				if (perms.getPermission().startsWith("pot.regeneration.")) {
					String permission = perms.getPermission().replaceAll("pot.regeneration.", "");
					if (Integer.parseInt(permission) > regenLevel) {
						regenLevel = Integer.parseInt(permission);
					}
				}
			}

			for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
				if (perms.getPermission().startsWith("pot.nv.")) {
					String permission = perms.getPermission().replaceAll("pot.nv.", "");
					if (Integer.parseInt(permission) > nvLevel) {
						nvLevel = Integer.parseInt(permission);
					}
				}
			}

			for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
				if (perms.getPermission().startsWith("pot.glow.")) {
					String permission = perms.getPermission().replaceAll("pot.glow.", "");
					if (Integer.parseInt(permission) > glowLevel) {
						glowLevel = Integer.parseInt(permission);
					}
				}
			}
			
			p.addPotionEffect(
					new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000, hasteLevel - 1, true));
			p.addPotionEffect(
					new PotionEffect(PotionEffectType.SPEED, 1000000, speedLevel - 1, true));
			p.addPotionEffect(
					new PotionEffect(PotionEffectType.LUCK, 1000000, luckLevel - 1, true));
			p.addPotionEffect(
					new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, strLevel - 1, true));
			p.addPotionEffect(
					new PotionEffect(PotionEffectType.GLOWING, 1000000, glowLevel - 1, true));
			p.addPotionEffect(
					new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, nvLevel - 1, true));
			p.addPotionEffect(
					new PotionEffect(PotionEffectType.REGENERATION, 1000000, regenLevel - 1, true));
		
		
		
		p.sendMessage(ChatColor.GRAY + "Given Super Potion!");
		
		
		
		
		return true;
		}
		return false;
	}
	public static void giveSuper(Player p) {
		p.addPotionEffect(
				new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000, Haste.getHasteLevel(p) - 1, true));
		p.addPotionEffect(
				new PotionEffect(PotionEffectType.SPEED, 1000000, Speed.getLevel(p) - 1, true));
		p.addPotionEffect(
				new PotionEffect(PotionEffectType.LUCK, 1000000, Luck.getLevel(p) - 1, true));
		p.addPotionEffect(
				new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, Strength.getLevel(p) - 1, true));
		p.addPotionEffect(
				new PotionEffect(PotionEffectType.GLOWING, 1000000, Glow.getLevel(p) - 1, true));
		p.addPotionEffect(
				new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, NightVision.getLevel(p) - 1, true));
		p.addPotionEffect(
				new PotionEffect(PotionEffectType.REGENERATION, 1000000, Regeneration.getLevel(p) - 1, true));
	}
}

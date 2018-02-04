package me.aov;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionListeners implements Listener {
	private PotionsMain plugin;

	PotionListeners(PotionsMain main) {
		this.plugin = main;
	}

	
	
	
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		boolean enabled = plugin.getConfig().getBoolean("Potions.PermPotions");

		ArrayList<String> permissionsList = new ArrayList<String>();

		for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
			if (perms.getPermission().startsWith("pot.perm.")) {
				String permission = perms.getPermission().replaceAll("pot.perm.", "");
				permissionsList.add(permission);
			}
		}
		if (enabled) {
			if (permissionsList.size() != 0) {
				for (String s : permissionsList) {
					switch (s) {
					case "haste":
						int highestHaste = plugin.getConfig().getInt("Potions.Levels.Haste");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.haste.")) {
								String permission = perms.getPermission().replaceAll("pot.haste.", "");
								if (Integer.parseInt(permission) > highestHaste) {
									highestHaste = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(
								new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000, highestHaste - 1, true));
						break;

					case "speed":
						int highestSpeed = plugin.getConfig().getInt("Potions.Levels.Speed");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.speed.")) {
								String permission = perms.getPermission().replaceAll("pot.speed.", "");
								if (Integer.parseInt(permission) > highestSpeed) {
									highestSpeed = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, highestSpeed - 1, true));
						break;
					case "luck":
						int highestLuck = plugin.getConfig().getInt("Potions.Levels.Luck");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.luck.")) {
								String permission = perms.getPermission().replaceAll("pot.luck.", "");
								if (Integer.parseInt(permission) > highestLuck) {
									highestLuck = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 1000000, highestLuck - 1, true));
						break;
					case "strength":
						int highestStrength = plugin.getConfig().getInt("Potions.Levels.Strength");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.strength.")) {
								String permission = perms.getPermission().replaceAll("pot.strength.", "");
								if (Integer.parseInt(permission) > highestStrength) {
									highestStrength = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(
								new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, highestStrength - 1, true));
						break;
					case "nv":
						int highestNV = plugin.getConfig().getInt("Potions.Levels.NV");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.nv.")) {
								String permission = perms.getPermission().replaceAll("pot.nv.", "");
								if (Integer.parseInt(permission) > highestNV) {
									highestNV = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(
								new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, highestNV - 1, true));
						break;
					case "regeneration":
						int highestRegeneration = plugin.getConfig().getInt("Potions.Levels.Regeneration");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.regeneration.")) {
								String permission = perms.getPermission().replaceAll("pot.regeneration.", "");
								if (Integer.parseInt(permission) > highestRegeneration) {
									highestRegeneration = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000,
								highestRegeneration - 1, true));
						break;
					case "glow":
						int highestGlow = plugin.getConfig().getInt("Potions.Levels.Glow");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.glow.")) {
								String permission = perms.getPermission().replaceAll("pot.glow.", "");
								if (Integer.parseInt(permission) > highestGlow) {
									highestGlow = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000000, highestGlow - 1, true));
						break;
					case "confusion":
						int highestConfusion = plugin.getConfig().getInt("Potions.Levels.Confusion");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.confusion.")) {
								String permission = perms.getPermission().replaceAll("pot.confusion.", "");
								if (Integer.parseInt(permission) > highestConfusion) {
									highestConfusion = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1000000, highestConfusion - 1, true));
						break;
					case "slow":
						int highestSlow = plugin.getConfig().getInt("Potions.Levels.Slow");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.slow.")) {
								String permission = perms.getPermission().replaceAll("pot.slow.", "");
								if (Integer.parseInt(permission) > highestSlow) {
									highestSlow = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, highestSlow - 1, true));
						break;
					default:
						plugin.getConsole()
								.sendMessage("Error in permissions for pemernant potions for player " + p.getName());
						plugin.getConsole().sendMessage("Check " + p.getName() + "'s permissions");
					}
				}
			}
		}

	}
	
	
	
	
	
	
	
	@EventHandler
	public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
		Player p = event.getPlayer();
		boolean enabled = plugin.getConfig().getBoolean("Potions.PermPotions");
		ArrayList<String> permissionsList = new ArrayList<String>();

		for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
			if (perms.getPermission().startsWith("pot.perm.")) {
				String permission = perms.getPermission().replaceAll("pot.perm.", "");
				permissionsList.add(permission);
			}
		}

		if (enabled) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin , new Runnable()
			{
			 
			public void run()
			{
			if (permissionsList.size() != 0) {
				for (String s : permissionsList) {
					switch (s) {
					case "haste":
						int highestHaste = plugin.getConfig().getInt("Potions.Levels.Haste");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.haste.")) {
								String permission = perms.getPermission().replaceAll("pot.haste.", "");
								if (Integer.parseInt(permission) > highestHaste) {
									highestHaste = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(
								new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000, highestHaste - 1, true));
						break;

					case "speed":
						int highestSpeed = plugin.getConfig().getInt("Potions.Levels.Speed");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.speed.")) {
								String permission = perms.getPermission().replaceAll("pot.speed.", "");
								if (Integer.parseInt(permission) > highestSpeed) {
									highestSpeed = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, highestSpeed - 1, true));
						break;
					case "luck":
						int highestLuck = plugin.getConfig().getInt("Potions.Levels.Luck");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.luck.")) {
								String permission = perms.getPermission().replaceAll("pot.luck.", "");
								if (Integer.parseInt(permission) > highestLuck) {
									highestLuck = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 1000000, highestLuck - 1, true));
						break;
					case "strength":
						int highestStrength = plugin.getConfig().getInt("Potions.Levels.Strength");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.strength.")) {
								String permission = perms.getPermission().replaceAll("pot.strength.", "");
								if (Integer.parseInt(permission) > highestStrength) {
									highestStrength = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(
								new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, highestStrength - 1, true));
						break;
					case "nv":
						int highestNV = plugin.getConfig().getInt("Potions.Levels.NV");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.nv.")) {
								String permission = perms.getPermission().replaceAll("pot.nv.", "");
								if (Integer.parseInt(permission) > highestNV) {
									highestNV = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(
								new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, highestNV - 1, true));
						break;
					case "confusion":
						int highestConfusion = plugin.getConfig().getInt("Potions.Levels.Confusion");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.confusion.")) {
								String permission = perms.getPermission().replaceAll("pot.confusion.", "");
								if (Integer.parseInt(permission) > highestConfusion) {
									highestConfusion = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1000000, highestConfusion - 1, true));
						break;
					case "slow":
						int highestSlow = plugin.getConfig().getInt("Potions.Levels.Slow");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.slow.")) {
								String permission = perms.getPermission().replaceAll("pot.slow.", "");
								if (Integer.parseInt(permission) > highestSlow) {
									highestSlow = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, highestSlow - 1, true));
						break;
					case "regeneration":
						int highestRegeneration = plugin.getConfig().getInt("Potions.Levels.Regeneration");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.regeneration.")) {
								String permission = perms.getPermission().replaceAll("pot.regeneration.", "");
								if (Integer.parseInt(permission) > highestRegeneration) {
									highestRegeneration = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000,
								highestRegeneration - 1, true));
						break;
					case "glow":
						int highestGlow = plugin.getConfig().getInt("Potions.Levels.Glow");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.glow.")) {
								String permission = perms.getPermission().replaceAll("pot.glow.", "");
								if (Integer.parseInt(permission) > highestGlow) {
									highestGlow = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000000, highestGlow - 1, true));
						break;
					default:
						plugin.getConsole()
								.sendMessage("Error in permissions for pemernant potions for player " + p.getName());
						plugin.getConsole().sendMessage("Check " + p.getName() + "'s permissions");
					}
					
				}
				
			}
			}
			 
			},1 );
		}
	}

	
	

	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
		Player p = event.getPlayer();
		boolean enabled = plugin.getConfig().getBoolean("Potions.PermPotions");
		ArrayList<String> permissionsList = new ArrayList<String>();

		for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
			if (perms.getPermission().startsWith("pot.perm.")) {
				String permission = perms.getPermission().replaceAll("pot.perm.", "");
				permissionsList.add(permission);
			}
		}

		if (enabled) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin , new Runnable()
			{
			 
			public void run()
			{
			if (permissionsList.size() != 0) {
				for (String s : permissionsList) {
					switch (s) {
					case "haste":
						int highestHaste = plugin.getConfig().getInt("Potions.Levels.Haste");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.haste.")) {
								String permission = perms.getPermission().replaceAll("pot.haste.", "");
								if (Integer.parseInt(permission) > highestHaste) {
									highestHaste = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(
								new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000, highestHaste - 1, true));
						break;

					case "speed":
						int highestSpeed = plugin.getConfig().getInt("Potions.Levels.Speed");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.speed.")) {
								String permission = perms.getPermission().replaceAll("pot.speed.", "");
								if (Integer.parseInt(permission) > highestSpeed) {
									highestSpeed = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, highestSpeed - 1, true));
						break;
					case "luck":
						int highestLuck = plugin.getConfig().getInt("Potions.Levels.Luck");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.luck.")) {
								String permission = perms.getPermission().replaceAll("pot.luck.", "");
								if (Integer.parseInt(permission) > highestLuck) {
									highestLuck = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 1000000, highestLuck - 1, true));
						break;
					case "confusion":
						int highestConfusion = plugin.getConfig().getInt("Potions.Levels.Confusion");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.confusion.")) {
								String permission = perms.getPermission().replaceAll("pot.confusion.", "");
								if (Integer.parseInt(permission) > highestConfusion) {
									highestConfusion = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1000000, highestConfusion - 1, true));
						break;
					case "slow":
						int highestSlow = plugin.getConfig().getInt("Potions.Levels.Slow");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.slow.")) {
								String permission = perms.getPermission().replaceAll("pot.slow.", "");
								if (Integer.parseInt(permission) > highestSlow) {
									highestSlow = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, highestSlow - 1, true));
						break;
					case "strength":
						int highestStrength = plugin.getConfig().getInt("Potions.Levels.Strength");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.strength.")) {
								String permission = perms.getPermission().replaceAll("pot.strength.", "");
								if (Integer.parseInt(permission) > highestStrength) {
									highestStrength = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(
								new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, highestStrength - 1, true));
						break;
					case "nv":
						int highestNV = plugin.getConfig().getInt("Potions.Levels.NV");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.nv.")) {
								String permission = perms.getPermission().replaceAll("pot.nv.", "");
								if (Integer.parseInt(permission) > highestNV) {
									highestNV = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(
								new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, highestNV - 1, true));
						break;
					case "regeneration":
						int highestRegeneration = plugin.getConfig().getInt("Potions.Levels.Regeneration");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.regeneration.")) {
								String permission = perms.getPermission().replaceAll("pot.regeneration.", "");
								if (Integer.parseInt(permission) > highestRegeneration) {
									highestRegeneration = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000,
								highestRegeneration - 1, true));
						break;
					case "glow":
						int highestGlow = plugin.getConfig().getInt("Potions.Levels.Glow");
						// Gets the highest level of speed available
						for (PermissionAttachmentInfo perms : p.getEffectivePermissions()) {
							if (perms.getPermission().startsWith("pot.glow.")) {
								String permission = perms.getPermission().replaceAll("pot.glow.", "");
								if (Integer.parseInt(permission) > highestGlow) {
									highestGlow = Integer.parseInt(permission);
								}
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000000, highestGlow - 1, true));
						break;
					default:
						plugin.getConsole()
								.sendMessage("Error in permissions for pemernant potions for player " + p.getName());
						plugin.getConsole().sendMessage("Check " + p.getName() + "'s permissions");
					}
					
				}
				
			}
			}
			 
			},1 );
		}
	}
}

//Copyright 2018, Aaron Ng, All rights reserved.
package me.aov;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin {
	ConsoleCommandSender console = getServer().getConsoleSender();

	@Override
	public void onEnable() {
		console.sendMessage("[Potions]" + ChatColor.GREEN + " Potions Enabled!");
		registerConfig();
	}

	@Override
	public void onDisable() {
		console.sendMessage("\n[Potions]" + ChatColor.RED + "Potions Disabled!\n");
	}

	private void registerConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		getLogger();
	}

	final String msg = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Potions.Messages.HasteOn"));
	final String msg2 = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Potions.Messages.HasteOff"));
	HashMap<Player, Long> cooldown = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		p = p.getPlayer();
		cooldown.put(p, (System.currentTimeMillis() / 1000));
		int cdtime = getConfig().getInt("Potions.Settings.HasteCooldown");
		if (command.getName().equalsIgnoreCase("haste")) {
			if (p.hasPermission("p.cdbypass") || (cooldown.get(p) + cdtime) >= (System.currentTimeMillis() / 1000)) {
				if (!p.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000,
							getConfig().getInt("Potions.Levels.Haste")), true);
	;
					p.sendMessage(msg);
					return true;
				} else {
					p.removePotionEffect(PotionEffectType.FAST_DIGGING);
					p.sendMessage(msg2);
					return true;
				}
			}
			
		}
		return false;
	}
}

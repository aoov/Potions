//Copyright 2018, Aaron Ng, All rights reserved.
package me.aov;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.aov.commands.Haste;
import me.aov.commands.NightVision;
import me.aov.commands.Speed;

public class PotionsMain extends JavaPlugin {
	ConsoleCommandSender console = getServer().getConsoleSender();

	@Override
	public void onEnable() {
		console.sendMessage("[Potions]" + ChatColor.GREEN + " Potions Enabled!");
		registerConfig();
		this.getCommand("haste").setExecutor(new Haste(this));
		this.getCommand("speedp").setExecutor(new Speed(this));
		this.getCommand("nv").setExecutor(new NightVision(this));

	}

	@Override
	public void onDisable() {
		console.sendMessage("[Potions]" + ChatColor.RED + "Potions Disabled!");
	}

	private void registerConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		getLogger();
	}

}
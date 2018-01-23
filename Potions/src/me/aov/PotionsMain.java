//Copyright 2018, Aaron Ng, All rights reserved.
package me.aov;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import me.aov.commands.Glow;
import me.aov.commands.Haste;
import me.aov.commands.Luck;
import me.aov.commands.Milk;
import me.aov.commands.NightVision;
import me.aov.commands.Potions;
import me.aov.commands.Regeneration;
import me.aov.commands.Speed;
import me.aov.commands.Strength;
import me.aov.commands.SuperPotion;

public class PotionsMain extends JavaPlugin {
	ConsoleCommandSender console = getServer().getConsoleSender();
	private final String prefix = ChatColor.translateAlternateColorCodes('&' ,getConfig().getString("Potions.Prefix"));
	
	
	private final PotionsMain main = this;
	
	
	
	@Override
	public void onEnable() {
		console.sendMessage("[Potions]" + ChatColor.GREEN + " Potions Enabled!");
		registerConfig();
		this.getCommand("haste").setExecutor(new Haste(this));
		this.getCommand("speedp").setExecutor(new Speed(this));
		this.getCommand("nv").setExecutor(new NightVision(this));
		this.getCommand("glow").setExecutor(new Glow(this));
		this.getCommand("strength").setExecutor(new Strength(this));
		this.getCommand("regeneration").setExecutor(new Regeneration(this));
		this.getCommand("superpotion").setExecutor(new SuperPotion(this));
		this.getCommand("milk").setExecutor(new Milk(this));
		this.getCommand("luck").setExecutor(new Luck(this));
		this.getCommand("potions").setExecutor(new Potions(this));
		getServer().getPluginManager().registerEvents(new PermListener(this), this);
	}

	@Override
	public void onDisable() {	
		console.sendMessage("[Potions]" + ChatColor.RED + "Potions Disabled!");
	}

	private void registerConfig() {
		getConfig().options().copyDefaults(true);
		this.saveDefaultConfig();
		getLogger();
	}
	
	public void reload() {
		reloadConfig();
		saveConfig();

	}

	
	public  PotionsMain getMain() {
		return this.main;
	}
	
	public String getPrefix() {
		return prefix;
	}
}
package me.aov.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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

	PotionEffectType pots[] = { PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.INCREASE_DAMAGE,
			PotionEffectType.REGENERATION, PotionEffectType.SPEED, PotionEffectType.FAST_DIGGING,
			PotionEffectType.SATURATION, PotionEffectType.LUCK, PotionEffectType.ABSORPTION };

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		p = p.getPlayer();
		if (command.getName().equalsIgnoreCase("superpotion")) {
			if (!(p.getActivePotionEffects().size() >= 2)) {
				for (PotionEffectType potions : pots) {
					p.addPotionEffect(
							new PotionEffect(potions, 1000000, plugin.getConfig().getInt("Potions.Levels.SuperPotion")),
							true);
				}
				p.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("Potions.Messages.SuperPotionOn")));
				return true;

			} else {
				for (PotionEffect pot : p.getActivePotionEffects()) {
					p.removePotionEffect(pot.getType());
				}
				p.sendMessage(ChatColor.GRAY + "SuperPotion Off!");
				return true;
			}
		}
		return false;
	}
}

package me.aov.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.aov.PotionsMain;

public class Potions implements CommandExecutor {
	private PotionsMain plugin;

	public Potions() {

	}

	public Potions(PotionsMain instance) {
		this.plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		p = p.getPlayer();
		ChatColor LP = ChatColor.LIGHT_PURPLE;
		ChatColor Green = ChatColor.GREEN;
		final String prefix = plugin.getPrefix();
		if (cmd.getName().equalsIgnoreCase("potions")) {
			if (args.length != 0 && args[0].equalsIgnoreCase("reload")) {
				if (p.hasPermission("pot.reload")) {
					plugin.reload();
					p.sendMessage(prefix + ChatColor.GREEN + "Plugin Reloaded!");

				} else {
					p.sendMessage(prefix + ChatColor.GRAY + "You don't have permission to reload!");
				}
				return true;
			} else if (args.length != 0 && args[0].equalsIgnoreCase("help")) {
				p.sendMessage(ChatColor.GRAY + "<-------------------->\n "
						+ Green + "/Potions "
						+ LP
						+ "- Displays Plugin Info\n"
						+ Green
						+ "/Potions Help "
						+ LP
						+ "- Displays This!\n"
						+ Green
						+ "/Potions Reload"
						+ LP
						+ "- Reloads Config!"
						+ Green
						+ "/Milk "
						+ LP
						+ "- Removes All Potion Effects\n"
						+ Green
						+ "/*PotionName* <Optional Level> "
						+ LP
						+ "- Toggles the potion effect!\n"
						+ Green
						+ "/Potions List "
						+ LP
						+ "- Displays Possible Potion Effects\n");
				p.sendMessage(ChatColor.GRAY + "<-------------------->");
				return true;
			}
			else if(args.length != 0 && args[0].equalsIgnoreCase("list")) {
				p.sendMessage(ChatColor.GRAY + "<-------------------->");
				p.sendMessage("" + Green + ChatColor.UNDERLINE + "Possible Potion Effects:\n"
						+ ChatColor.RESET
						+ " \n"
						+ ChatColor.RESET
						+ LP
						+ "Haste\nSpeed\nLuck\nStrength or Str or St\nNight Vision or NV\nRegeneration or Regen\nGlow"
						);
				p.sendMessage(ChatColor.GRAY + "<-------------------->");
				return true;
			}
			else {
				p.sendMessage(ChatColor.GRAY + "<-------------------->\n" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD
						+ "Potions" + ChatColor.GREEN + " v2.1 by Aov\n" + ChatColor.GRAY + "Use " + ChatColor.YELLOW
						+ "\"/potions help\" " + ChatColor.GRAY + " for commands" + ChatColor.GRAY
						+ "\n<-------------------->");
				return true;
			}

		}
		return false;
	}
}

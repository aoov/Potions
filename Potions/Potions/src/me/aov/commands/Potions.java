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
		ChatColor LP = ChatColor.DARK_AQUA;
		ChatColor Green = ChatColor.GRAY;
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
				p.sendMessage(ChatColor.DARK_GRAY + "<-------------------->\n"
						+ Green + "<> = Optional, [] = Required\n"
						+ Green + "/Potions "
						+ LP
						+ "- Displays Plugin Info\n"
						+ Green
						+ "/Potions Help "
						+ LP
						+ "- Displays This!\n"
						+ Green
						+ "/Potions Reload "
						+ LP
						+ "- Reloads Config!\n"
						+ Green
						+ "/Milk <Player> "
						+ LP
						+ "- Removes All Potion Effects\n"
						+ Green
						+ "/*PotionName* <Level> "
						+ LP
						+ "- Toggles the potion effect!\n"
						+ Green
						+ "/*PotionName* [Level] <Player>"
						+ LP
						+ "- Gives player the potion effect!\n"
						+ Green
						+ "/Potions List "
						+ LP
						+ "- Displays Possible Potion Effects\n"
						+ Green
						+ "/PotionsGUI "
						+ LP
						+ "- Opens up a menu for potions!\n");
				p.sendMessage(ChatColor.DARK_GRAY + "<-------------------->");
				return true;
			}
			else if(args.length != 0 && args[0].equalsIgnoreCase("list")) {
				p.sendMessage(ChatColor.GRAY + "<-------------------->");
				p.sendMessage("" + Green + ChatColor.UNDERLINE + "Possible Potion Effects:\n"
						+ ChatColor.RESET
						+ " \n"
						+ ChatColor.RESET
						+ LP
						+ "Haste\nSpeed\nLuck\nStrength or Str or St\nNight Vision or NV\nRegeneration or Regen\nGlow\nSuperPotion or Sp"
						);
				p.sendMessage(ChatColor.GRAY + "<-------------------->");
				return true;
			}
			else {
				p.sendMessage(ChatColor.GRAY + "<-------------------->\n" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD
						+ "Potions" + ChatColor.GREEN + " v2.3 by Aov\n" + ChatColor.GRAY + "Use " + ChatColor.YELLOW
						+ "\"/potions help\" " + ChatColor.GRAY + " for commands" + ChatColor.GRAY
						+ "\n<-------------------->");
				return true;
			}

		}
		return false;
	}
}

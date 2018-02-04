package me.aov.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import me.aov.PotionsMain;

public class PotionsGUI implements CommandExecutor {

	private PotionsMain plugin;

	public PotionsGUI(PotionsMain e) {
		plugin = e;
	}

	public static Inventory inventory;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("potionsgui")) {
			if (!(sender instanceof Player)) {
				plugin.getServer().getConsoleSender().sendMessage("You must be a player to use this command!");
			}
			Player player = (Player) sender;
			if (player.hasPermission("pot.gui")) {
				ItemStack glassPane = new ItemStack(Material.STAINED_GLASS_PANE);
				ItemMeta tempMeta = glassPane.getItemMeta();
				tempMeta.setDisplayName(ChatColor.DARK_GRAY + "Inventory Item");
				glassPane.setItemMeta(tempMeta);

				inventory = Bukkit.createInventory(null, 3 * 9, ChatColor.GREEN + "Potions");
				for (int i = 0; i < 9; i++) {
					inventory.setItem(i, glassPane);
				}
				for (int i = 18; i < 27; i++) {
					inventory.setItem(i, glassPane);
				}

				ItemStack hasteItem = createPot(Color.ORANGE, ChatColor.GOLD + "Haste",
						ChatColor.GOLD + "Haste " + ChatColor.GREEN + "Level - ", Haste.getHasteLevel(player));
				ItemStack speedItem = createPot(Color.BLUE, ChatColor.AQUA + "Speed",
						ChatColor.AQUA + "Speed " + ChatColor.GREEN + "Level - ", Speed.getLevel(player));
				ItemStack strengthItem = createPot(Color.RED, ChatColor.RED + "Strength",
						ChatColor.RED + "Strength " + ChatColor.GREEN + "Level - ", Strength.getLevel(player));
				ItemStack luckItem = createPot(Color.GREEN, ChatColor.GREEN + "Luck",
						ChatColor.GREEN + "Luck " + ChatColor.GREEN + "Level - ", Luck.getLevel(player));
				ItemStack regenerationItem = createPot(Color.FUCHSIA, ChatColor.LIGHT_PURPLE + "Regeneration",
						ChatColor.LIGHT_PURPLE + "Regeneration " + ChatColor.GREEN + "Level - ",
						Regeneration.getLevel(player));
				ItemStack nvItem = createPot(Color.PURPLE, ChatColor.DARK_PURPLE + "Night Vision",
						ChatColor.DARK_PURPLE + "Night Vision " + ChatColor.GREEN + "Level - ",
						Regeneration.getLevel(player));
				ItemStack glowItem = createPot(Color.WHITE, ChatColor.WHITE + "Glow",
						ChatColor.WHITE + "Glow " + ChatColor.GREEN + "Level - ", Glow.getLevel(player));
				ItemStack getAll = new ItemStack(Material.NETHER_STAR);
				ItemMeta meta = getAll.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "All Potions");
				List<String> lore = new ArrayList<String>();
				lore.add(ChatColor.GRAY + "Click to get all potion effects!");
				meta.setLore(lore);
				getAll.setItemMeta(meta);

				ItemStack clear = new ItemStack(Material.MILK_BUCKET);
				ItemMeta milkMeta = clear.getItemMeta();
				milkMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Clear Effects");
				List<String> lore2 = new ArrayList<String>();
				lore.add(ChatColor.GRAY + "Click to clear all potion effects");
				milkMeta.setLore(lore2);
				clear.setItemMeta(milkMeta);

				ItemStack close = new ItemStack(Material.BARRIER);
				ItemMeta closeMeta = close.getItemMeta();
				closeMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Close Menu");
				close.setItemMeta(closeMeta);

				inventory.setItem(22, close);
				inventory.setItem(17, clear);
				inventory.setItem(9, getAll);
				inventory.setItem(16, glowItem);
				inventory.setItem(13, hasteItem);
				inventory.setItem(14, speedItem);
				inventory.setItem(12, strengthItem);
				inventory.setItem(11, luckItem);
				inventory.setItem(10, regenerationItem);
				inventory.setItem(15, nvItem);

				player.openInventory(inventory);
				return true;
			}
			else {
				player.sendMessage(plugin.getConfig().getString(ChatColor.GRAY + "You don't have permission!"));
			}
		}
		return false;
	}

	public static ItemStack createPot(Color c, String itemName, String potionEffect, int level) {
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Click to get the potion effect!");
		lore.add(potionEffect + level);
		ItemStack item = new ItemStack(Material.POTION);
		PotionMeta itemMeta = (PotionMeta) item.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		itemMeta.setLore(lore);
		itemMeta.setColor(c);
		itemMeta.setDisplayName(itemName);
		item.setItemMeta(itemMeta);
		return item;
	}

}

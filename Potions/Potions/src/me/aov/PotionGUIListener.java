package me.aov;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffectType;

import me.aov.commands.Glow;
import me.aov.commands.Haste;
import me.aov.commands.Luck;
import me.aov.commands.Milk;
import me.aov.commands.NightVision;
import me.aov.commands.Regeneration;
import me.aov.commands.Speed;
import me.aov.commands.Strength;
import me.aov.commands.SuperPotion;

public class PotionGUIListener implements Listener {

	@SuppressWarnings("unused")
	private PotionsMain plugin;

	PotionGUIListener(PotionsMain main) {
		this.plugin = main;
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		String itemTitle;
		Player p = (Player) event.getWhoClicked();

		if (event.getInventory().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Potions")) {
			if (event.getCurrentItem() != null && event.getCurrentItem().getItemMeta() != null) {
				itemTitle = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
				switch (itemTitle) {
				case "Haste":
					if (p.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
						p.removePotionEffect(PotionEffectType.FAST_DIGGING);
					} else {
						Haste.giveHaste(p);
					}
					break;
				case "Speed":
					if(p.hasPotionEffect(PotionEffectType.SPEED)) {
						p.removePotionEffect(PotionEffectType.SPEED);
					}
					else {
						Speed.giveSpeed(p);
					}
					break;
				case "Strength":
					if(p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
						p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
					}
					else {
						Strength.giveStrength(p);
					}
					break;
				case "Luck":
					if(p.hasPotionEffect(PotionEffectType.LUCK)) {
						p.removePotionEffect(PotionEffectType.LUCK);
					}
					else {
						Luck.giveLuck(p);
					}
					break;
				case "Regeneration":
					if(p.hasPotionEffect(PotionEffectType.REGENERATION)) {
						p.removePotionEffect(PotionEffectType.REGENERATION);
					}
					else {
						Regeneration.giveRegeneration(p);
					}
					break;
				case "Night Vision":
					if(p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
						p.removePotionEffect(PotionEffectType.NIGHT_VISION);
					}
					else {
						NightVision.giveNV(p);
					}
					break;
				case "Glow":
					if(p.hasPotionEffect(PotionEffectType.GLOWING)) {
						p.removePotionEffect(PotionEffectType.GLOWING);
					}
					else {
						Glow.giveGlow(p);
					}
					break;
				case "All Potions":
					SuperPotion.giveSuper(p);
					break;
				case "Clear Effects":
					Milk.removeAll(p);;
					break;
				case "Close Menu":
					p.closeInventory();
				default:
					break;
				}
				
				
			}
			event.setCancelled(true);
			event.setResult(Result.DENY);
		}
	}
}

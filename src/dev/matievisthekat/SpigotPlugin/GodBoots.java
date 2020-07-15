package dev.matievisthekat.SpigotPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GodBoots implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You cannot use this in the console");
			return true;
		}
		
		Player player = (Player) sender;
		
		if (!player.hasPermission("godboots.use")) {
			player.sendMessage(ChatColor.RED + "You do not have permission to use that!");
			return true;
		}
		
		Inventory inv = player.getInventory();
		ItemStack boots = getItem();
		
		if (inv.firstEmpty() == -1) {
			Location loc = player.getLocation();
			World world = player.getWorld();
			world.dropItemNaturally(loc, boots);
		} else {
			inv.addItem(boots);
		}
		
		player.sendMessage(ChatColor.GOLD + "The Minecraft legends have given you a gift!");
		return true;
	}
	
	public ItemStack getItem() {
		ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta meta = boots.getItemMeta();
		
		meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Boots of Leaping");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Boots worthy of the Minecraft Gods");
		
		meta.setLore(lore);
		meta.addEnchant(Enchantment.PROTECTION_FALL, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		
		boots.setItemMeta(meta);
		
		return boots;
	}
}

















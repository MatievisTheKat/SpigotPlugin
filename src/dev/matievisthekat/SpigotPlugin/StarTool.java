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

public class StarTool implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You cannot use this in the console");
			return true;
		}
		
		Player player = (Player) sender;
		
		if (!player.hasPermission("startool.use")) {
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
		ItemStack trident = new ItemStack(Material.TRIDENT);
		ItemMeta meta = trident.getItemMeta();
		
		meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Star Tool");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7(Right click) &a&oSpawn Minions"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7(Leftclick) &a&oShoot Explosives"));
		
		meta.setLore(lore);
		meta.addEnchant(Enchantment.LOYALTY, 10, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		
		trident.setItemMeta(meta);
		
		return trident;
	}

}

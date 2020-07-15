package dev.matievisthekat.SpigotPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Launch implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You cannot use this in the console");
			return true;
		}
		
		Player player = (Player) sender;
		
		if (args.length == 0) {
			player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Zooooooommmmmm");
			player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
			return true;
		} else {
			if (!isNum(args[0]) || !isNum(args[1])) {
				player.sendMessage(ChatColor.RED + "Usage is /launch <number>");
				return true;
			}
			
			player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Zooooooommmmmm");
			player.setVelocity(player.getLocation().getDirection().multiply(Integer.parseInt(args[0])).setY(2));
			return true;
		}
	}
	
	public boolean isNum(String num) {
		try {
			Integer.parseInt(num);
		} catch(Exception e) {
			return false;
		}
		return true;
	}

}
package dev.matievisthekat.SpigotPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public List<String> startoolThrowList = new ArrayList<String>();

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new EventManager(this), this);
		
		this.getCommand("Launch").setExecutor((new Launch()));
		this.getCommand("Doctor").setExecutor(new Doctor());
		this.getCommand("Test").setExecutor(new Test());
		this.getCommand("GodBoots").setExecutor(new GodBoots());
		this.getCommand("StarTool").setExecutor(new StarTool());
	}
	
	@Override
	public void onDisable() {
		
	}
}


















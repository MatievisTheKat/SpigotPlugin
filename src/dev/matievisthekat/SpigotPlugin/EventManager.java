package dev.matievisthekat.SpigotPlugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class EventManager implements Listener {

	static Main main;
	public EventManager(Main instance) {
		main = instance;
	}
	
	@EventHandler
	public void onJump(PlayerMoveEvent event) {
		Player player = (Player) event.getPlayer();
		
		if (player.getInventory().getBoots() != null)
			if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Boots of Leaping"))
				if (player.getInventory().getBoots().getItemMeta().hasLore())
					if (event.getFrom().getY() < event.getTo().getY() && player.getLocation().subtract(0,1,0).getBlock().getType() != Material.AIR) {
						player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2));
					}
	}
	
	@EventHandler
	public void onFall(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (event.getCause() == DamageCause.FALL)
				if (player.getInventory().getBoots() != null)
					if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Boots of Leaping"))
						if (player.getInventory().getBoots().getItemMeta().hasLore()) {
							event.setCancelled(true);
						}
		}
	}
	
	@EventHandler
	public void onLand(ProjectileHitEvent event) {
		if (event.getEntityType() == EntityType.TRIDENT)
			if (event.getEntity().getShooter() instanceof Player) {
				Log.info("Player threw trident");
				Player player = (Player) event.getEntity().getShooter();
				if (main.startoolThrowList.contains(player.getName())) {
					if (main.startoolThrowList.contains(player.getName())) main.startoolThrowList.remove(player.getName());
					Log.info("Is star tool");
					Location loc = event.getEntity().getLocation();
					loc.setY(loc.getY() + 1);
					
					for (int i = 1; i < 4; i++) {
						loc.getWorld().spawnEntity(loc, EntityType.DROWNED);
						loc.setX(loc.getX() + i);
					}
				}
			}
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
		if (item.getType().equals(Material.TRIDENT))
			if (item.getItemMeta().hasLore()) {
				Player player = (Player) event.getPlayer();
				
				// Right click
				if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if (!main.startoolThrowList.contains(player.getName())) main.startoolThrowList.add(player.getName());
				}
				
				// Left click
				if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
					player.launchProjectile(Fireball.class);
				}
			}
	}
}
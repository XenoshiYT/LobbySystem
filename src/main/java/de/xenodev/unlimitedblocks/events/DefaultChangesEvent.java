package de.xenodev.unlimitedblocks.events;

import de.xenodev.unlimitedblocks.LobbySystem;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class DefaultChangesEvent implements Listener {

    @EventHandler
    public void handleBlockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(LobbySystem.getBuildArray().contains(p)){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void handleBockBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(LobbySystem.getBuildArray().contains(p)){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void handleDamageByEntity(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void handleEntityDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void handlePlayerDeath(PlayerDeathEvent e){
        if(e.getEntity() instanceof Player){
            Player p = e.getEntity();
            if(LobbySystem.getInstance().getConfig().get("Locations.Spawn") == null){
                p.sendMessage(LobbySystem.getInstance().getPrefix() + " §7Der Spawn wurde noch nicht gesetzt! Kontaktiere bitte einen Administator");
                return;
            }

            p.spigot().respawn();
            Location locationSpawn = LobbySystem.getInstance().getConfig().getLocation("Locations.Spawn");
            p.teleport(locationSpawn);
        }
    }

    @EventHandler
    public void handleFoodLevel(FoodLevelChangeEvent e){
        Player p = (Player) e.getEntity();
        p.setFoodLevel(20);
        p.setSaturation(20);
        e.setCancelled(true);
    }

    @EventHandler
    public void handleInventoryClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(LobbySystem.getBuildArray().contains(p)){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void handleDropItem(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if(LobbySystem.getBuildArray().contains(p)){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void handlePickItem(PlayerPickupItemEvent e){
        Player p = e.getPlayer();
        if(LobbySystem.getBuildArray().contains(p)){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void handleSwapHand(PlayerSwapHandItemsEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void handleWeatherChange(WeatherChangeEvent e){
        World world = e.getWorld();
        world.setStorm(false);
        world.setThundering(false);
        e.setCancelled(true);
    }

}

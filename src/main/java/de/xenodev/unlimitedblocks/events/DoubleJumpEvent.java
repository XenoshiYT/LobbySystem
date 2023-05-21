package de.xenodev.unlimitedblocks.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.ArrayList;

public class DoubleJumpEvent implements Listener {

    private ArrayList<Player> cooldown = new ArrayList<>();

    @EventHandler
    public void handleDoubleJumpJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        p.setAllowFlight(true);
        if(cooldown.contains(p)){
            cooldown.remove(p);
        }
    }

    @EventHandler
    public void handleDoubleJumpToggle(PlayerToggleFlightEvent e){
        Player p = e.getPlayer();

        if(p.getGameMode().equals(GameMode.ADVENTURE)){
            e.setCancelled(true);
            if(cooldown.contains(p))return;
            p.setVelocity(p.getLocation().getDirection().setY(1));
            p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 100, 100);
            cooldown.add(p);
            p.setAllowFlight(false);
        }else{
            cooldown.add(p);
        }
    }

    @EventHandler
    public void handleDoubleJumpMove(PlayerMoveEvent e){
        Player p = e.getPlayer();

        if(p.getGameMode().equals(GameMode.ADVENTURE)){
            if(!cooldown.contains(p))return;
            if(p.getLocation().getBlock().getType().equals(Material.AIR))return;
            if(p.getLocation().getBlock().getType() == null)return;
            cooldown.remove(p);
            p.setAllowFlight(true);
        }else{
            cooldown.add(p);
            p.setAllowFlight(false);
        }
    }

    @EventHandler
    public void handleDoubleJumpGamemode(PlayerGameModeChangeEvent e){
        Player p = e.getPlayer();

        if(p.getGameMode().equals(GameMode.ADVENTURE)){
            if(!cooldown.contains(p))return;
            cooldown.remove(p);
            p.setAllowFlight(true);
        }else{
            cooldown.add(p);
            p.setAllowFlight(false);
        }
    }

}

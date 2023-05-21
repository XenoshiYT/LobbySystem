package de.xenodev.unlimitedblocks.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        e.setQuitMessage("");
    }

}

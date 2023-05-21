package de.xenodev.unlimitedblocks.events;

import de.xenodev.unlimitedblocks.LobbySystem;
import de.xenodev.unlimitedblocks.utils.ScoreboardManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        if(LobbySystem.getInstance().getConfig().get("Locations.Spawn") == null){
            if(p.hasPermission("tmb.function.joinadmin")) {
                p.sendTitle("§cDer Spawn wurde nicht gesetzt!", "§eSetze in mit /locations", 20*1, 20*3, 20*1);
            }
        }else{
            p.teleport(LobbySystem.getInstance().getConfig().getLocation("Locations.Spawn"));
        }

        ScoreboardManager.setScoreboard(p);

        p.setGameMode(GameMode.ADVENTURE);
        p.setFoodLevel(20);
        p.setHealth(20);
    }
}

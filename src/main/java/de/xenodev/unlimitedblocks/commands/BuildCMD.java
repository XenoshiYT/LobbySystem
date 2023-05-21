package de.xenodev.unlimitedblocks.commands;

import de.xenodev.unlimitedblocks.LobbySystem;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player)sender;
            if(!p.hasPermission("tmb.command.build")){
                p.sendMessage(LobbySystem.getPrefix() + "§7Dir fehlt folgende Permission: §6" + "tmb.command.build");
                return true;
            }

            if(LobbySystem.getBuildArray().contains(p)){
                LobbySystem.getBuildArray().remove(p);
                p.sendMessage(LobbySystem.getPrefix() + "§7Du kannst nun nicht mehr in der Lobby bauen");
                p.setGameMode(GameMode.ADVENTURE);
                p.getInventory().clear();
            }else{
                LobbySystem.getBuildArray().add(p);
                p.sendMessage(LobbySystem.getPrefix() + "§7Du kannst nun in der Lobby bauen");
                p.setGameMode(GameMode.CREATIVE);
                p.getInventory().clear();
            }
        }

        return false;
    }
}

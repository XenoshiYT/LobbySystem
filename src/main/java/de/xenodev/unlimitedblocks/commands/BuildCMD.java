package de.xenodev.unlimitedblocks.commands;

import de.xenodev.unlimitedblocks.LobbySystem;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildCMD implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player)sender;
            if(!p.hasPermission("ub.command.build")){
                p.sendMessage(LobbySystem.getPrefix() + "§7Dir fehlt folgende Permission: §6" + "ub.command.build");
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
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();

        return arrayList;
    }

}

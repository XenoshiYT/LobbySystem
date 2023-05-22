package de.xenodev.unlimitedblocks.commands;

import de.xenodev.unlimitedblocks.LobbySystem;
import de.xenodev.unlimitedblocks.files.SaveLocations;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LocationsCMD implements CommandExecutor, TabCompleter {

    private ArrayList<String> locationNames = new ArrayList<>();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player)sender;
            if(!p.hasPermission("ub.command.locations")){
                p.sendMessage(LobbySystem.getPrefix() + "§7Dir fehlt folgende Permission: §6" + "ub.command.locations");
                return true;
            }

            setLocationNames();

            if(locationNames.contains(args[0])){
                SaveLocations.setLocation(args[0], p.getLocation());
                p.sendMessage(LobbySystem.getPrefix() + "§7Du hast die Location für §6" + args[0].toUpperCase() + " §7gesetzt");
            }else{
                p.sendMessage(LobbySystem.getPrefix() + "§cDiesen Locationnamen gibt es nicht!");
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length == 1) {
            setLocationNames();
        }

        return locationNames;
    }

    private void setLocationNames(){
        locationNames.add("spawn");
    }
}

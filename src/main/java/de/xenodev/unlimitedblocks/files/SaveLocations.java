package de.xenodev.unlimitedblocks.files;

import de.xenodev.unlimitedblocks.LobbySystem;
import org.bukkit.Location;

public class SaveLocations {

    public static void setLocation(String locationName, Location location){
        LobbySystem.getInstance().getConfig().set("Locations." + locationName, location);
        LobbySystem.getInstance().saveConfig();
    }

    public static Location getLocation(String locationName){
        return LobbySystem.getInstance().getConfig().getLocation("Locations." + locationName);
    }

}

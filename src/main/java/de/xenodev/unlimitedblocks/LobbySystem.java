package de.xenodev.unlimitedblocks;

import de.xenodev.unlimitedblocks.utils.ScoreboardManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class LobbySystem extends JavaPlugin {

    private static LobbySystem instance;
    private static String prefix = "§e§lUnlimitedBlocks §8| ";
    private static ArrayList<Player> buildArray = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;

        events(getServer().getPluginManager());
        commands();
        tabcomplets();

        ScoreboardManager.updateScoreboard();
    }

    public static ArrayList<Player> getBuildArray() {
        return buildArray;
    }

    @Override
    public void onDisable() {

    }

    private void commands(){

    }

    private void tabcomplets(){

    }

    private void events(PluginManager pluginManager){

    }

    public static LobbySystem getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }

}
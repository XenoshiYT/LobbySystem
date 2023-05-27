package de.xenodev.unlimitedblocks;

import de.xenodev.unlimitedblocks.commands.BuildCMD;
import de.xenodev.unlimitedblocks.commands.LocationsCMD;
import de.xenodev.unlimitedblocks.events.*;
import de.xenodev.unlimitedblocks.mysql.MySQL;
import de.xenodev.unlimitedblocks.utils.ScoreboardManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class LobbySystem extends JavaPlugin {

    private static LobbySystem instance;
    private static String prefix = "§e§lUnlimitedBlocks §8| ";
    private static ArrayList<Player> buildArray = new ArrayList<>();
    public static MySQL mysql;

    @Override
    public void onEnable() {
        instance = this;
        ConnectMySQL();

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
        mysql.close();
    }

    private void ConnectMySQL(){
        mysql = new MySQL("localhost", "unlimitedblocks", "unlimitedblocks", "[9j0pMyKvm5TLD_E]");
        mysql.update("CREATE TABLE IF NOT EXISTS Time(UUID VARCHAR(100),HOURS BIGINT,MINUTES INT,SECONDS INT)");
    }

    private void commands(){
        getCommand("build").setExecutor(new BuildCMD());
        getCommand("locations").setExecutor(new LocationsCMD());
    }

    private void tabcomplets(){
        getCommand("build").setTabCompleter(new BuildCMD());
        getCommand("locations").setTabCompleter(new LocationsCMD());
    }

    private void events(PluginManager pluginManager){
        pluginManager.registerEvents(new DefaultChangesEvent(), this);
        pluginManager.registerEvents(new DoubleJumpEvent(), this);
        pluginManager.registerEvents(new JoinEvent(), this);
        pluginManager.registerEvents(new NavigatorEvent(), this);
        pluginManager.registerEvents(new QuitEvent(), this);
    }

    public static LobbySystem getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }
}
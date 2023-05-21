package de.xenodev.unlimitedblocks.utils;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionGroup;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import de.xenodev.unlimitedblocks.LobbySystem;
import de.xenodev.unlimitedblocks.MySQL.TimeAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreboardManager {

    public static void setScoreboard(Player p){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("ubsb", Criteria.DUMMY, "§7§l» §e§lUnlimitedBlocks §7§l«");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        IPermissionUser permuser = CloudNetDriver.getInstance().getPermissionManagement().getUser(p.getUniqueId());
        IPermissionGroup permgroup = CloudNetDriver.getInstance().getPermissionManagement().getHighestPermissionGroup(permuser);
        TimeAPI timeAPI = new TimeAPI();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        objective.getScore(updateTeam(scoreboard, "time", "   §2§l" + simpleDateFormat.format(new Date()), "", ChatColor.AQUA)).setScore(8);
        objective.getScore("§7§o").setScore(7);
        objective.getScore("§8§l» §7Rank").setScore(6);
        objective.getScore(updateTeam(scoreboard, "rank",permgroup.getDisplay().replace("&", "§") + "§l" + permgroup.getName(), "", ChatColor.YELLOW)).setScore(5);
        objective.getScore("§1§o").setScore(4);
        objective.getScore("§8§l» §7Spielzeit").setScore(3);
        objective.getScore(updateTeam(scoreboard, "playtime", "§6" + timeAPI.getTime(p), "", ChatColor.RED)).setScore(2);
        objective.getScore("§3§o").setScore(1);

        p.setScoreboard(scoreboard);
    }

    public static void updateScoreboard() {
        new BukkitRunnable(){

            @Override
            public void run() {
                for(final Player players : Bukkit.getOnlinePlayers()){
                    Scoreboard scoreboard = players.getScoreboard();
                    Objective objective = scoreboard.getObjective("ubsb");

                    IPermissionUser permuser = CloudNetDriver.getInstance().getPermissionManagement().getUser(players.getUniqueId());
                    IPermissionGroup permgroup = CloudNetDriver.getInstance().getPermissionManagement().getHighestPermissionGroup(permuser);
                    TimeAPI timeAPI = new TimeAPI();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
                    objective.getScore(updateTeam(scoreboard, "time", "   §2§l" + simpleDateFormat.format(new Date()), "", ChatColor.AQUA)).setScore(8);
                    objective.getScore(updateTeam(scoreboard, "rank",permgroup.getDisplay().replace("&", "§") + "§l" + permgroup.getName(), "", ChatColor.YELLOW)).setScore(5);
                    objective.getScore(updateTeam(scoreboard, "playtime", "§6" + timeAPI.getTime(players), "", ChatColor.RED)).setScore(2);
                }
            }
        }.runTaskTimerAsynchronously(LobbySystem.getInstance(), 0, 20L*5);
    }

    public static Team getTeam(Scoreboard board, String Team, String prefix, String suffix) {
        Team team = board.getTeam(Team);
        if(team == null) {
            team = board.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.setAllowFriendlyFire(false);
        team.setCanSeeFriendlyInvisibles(true);

        return team;
    }

    public static String updateTeam(Scoreboard board, String Team, String prefix, String suffix, ChatColor entry) {
        Team team = board.getTeam(Team);
        if(team == null) {
            team = board.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(entry.toString());

        return entry.toString();
    }

}

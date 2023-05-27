package de.xenodev.unlimitedblocks.utils;

import de.xenodev.unlimitedblocks.LobbySystem;
import de.xenodev.unlimitedblocks.mysql.TimeAPI;
import eu.cloudnetservice.driver.inject.InjectionLayer;
import eu.cloudnetservice.driver.permission.PermissionGroup;
import eu.cloudnetservice.driver.permission.PermissionManagement;
import eu.cloudnetservice.driver.permission.PermissionUser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreboardManager {

    private static PermissionManagement permissionManagement = InjectionLayer.ext().instance(PermissionManagement.class);

    public static void setScoreboard(Player p){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("ubsb", Criteria.DUMMY, "§7§l» §e§lUnlimitedBlocks §7§l«");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        PermissionUser permissionUser = permissionManagement.user(p.getUniqueId());
        PermissionGroup permissionGroup = permissionManagement.highestPermissionGroup(permissionUser);
        TimeAPI timeAPI = new TimeAPI();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        objective.getScore(updateTeam(scoreboard, "time", "   §2§l" + simpleDateFormat.format(new Date()), "", ChatColor.AQUA)).setScore(8);
        objective.getScore("§7§o").setScore(7);
        objective.getScore("§8§l» §7Rank").setScore(6);
        objective.getScore(updateTeam(scoreboard, "rank",permissionGroup.display().replace("&", "§") + permissionGroup.name(), "", ChatColor.YELLOW)).setScore(5);
        objective.getScore("§1§o").setScore(4);
        objective.getScore("§8§l» §7Spielzeit").setScore(3);
        objective.getScore(updateTeam(scoreboard, "playtime", "§6" + timeAPI.changeTime(p.getUniqueId()), "", ChatColor.RED)).setScore(2);
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

                    PermissionUser permissionUser = permissionManagement.user(players.getUniqueId());
                    PermissionGroup permissionGroup = permissionManagement.highestPermissionGroup(permissionUser);
                    TimeAPI timeAPI = new TimeAPI();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
                    objective.getScore(updateTeam(scoreboard, "time", "   §2§l" + simpleDateFormat.format(new Date()), "", ChatColor.AQUA)).setScore(8);
                    objective.getScore(updateTeam(scoreboard, "rank",permissionGroup.display().replace("&", "§") + permissionGroup.name(), "", ChatColor.YELLOW)).setScore(5);
                    objective.getScore(updateTeam(scoreboard, "playtime", "§6" + timeAPI.changeTime(players.getUniqueId()), "", ChatColor.RED)).setScore(2);
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

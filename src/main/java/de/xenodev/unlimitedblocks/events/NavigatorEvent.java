package de.xenodev.unlimitedblocks.events;

import de.xenodev.unlimitedblocks.LobbySystem;
import de.xenodev.unlimitedblocks.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class NavigatorEvent implements Listener {

    private Integer navigatorID;

    @EventHandler
    public void handleNavigatorItemInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if(e.getItem().getType().equals(Material.COMPASS)){
            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §6Navigator §7«")){
                Inventory navigatorInventory = Bukkit.createInventory(p, 9*5, "§7» §6Navigator §7«");

                navigatorID = Bukkit.getScheduler().runTaskTimerAsynchronously(LobbySystem.getInstance(), new Runnable() {
                    private int navigatorTimer = 0;

                    @Override
                    public void run() {
                        if(navigatorTimer == 0){
                            for(int i = 0; i < 9; i++){
                                navigatorInventory.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build());
                            }
                            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 1f);
                        }else if(navigatorTimer == 5){
                            for (int i = 9; i < 18; i++) {
                                navigatorInventory.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build());
                            }
                            navigatorInventory.setItem(12, new ItemBuilder(Material.GRASS_BLOCK).setName("§7» §6CityBuild §7«").build());
                            navigatorInventory.setItem(14, new ItemBuilder(Material.MAGMA_CREAM).setName("§7» §6Spawn §7«").build());
                            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 1f);
                        }else if(navigatorTimer == 10){
                            for (int i = 18; i < 27; i++) {
                                navigatorInventory.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build());
                            }
                            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 1f);
                        }else if(navigatorTimer == 15){
                            for (int i = 27; i < 36; i++) {
                                navigatorInventory.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build());
                            }
                            navigatorInventory.setItem(28, new ItemBuilder(Material.NETHERITE_SWORD).setName("§7» §4§lXero Season 1 §7«").build());
                            navigatorInventory.setItem(30, new ItemBuilder(Material.ENDER_PEARL).setName("§7» §3SkyPvP §7«").build());
                            navigatorInventory.setItem(32, new ItemBuilder(Material.BARRIER).setName("§7» §cServer 4 §7«").build());
                            navigatorInventory.setItem(34, new ItemBuilder(Material.BARRIER).setName("§7» §cServer 5 §7«").build());
                            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 1f);
                        }else if(navigatorTimer == 20){
                            for (int i = 36; i < 45; i++) {
                                navigatorInventory.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build());
                            }
                            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100, 1f);
                            Bukkit.getScheduler().cancelTask(navigatorID);
                        }
                        navigatorTimer++;
                    }
                }, 0, 20L).getTaskId();

                p.openInventory(navigatorInventory);
            }
        }
    }

    @EventHandler
    public void handleNavigatorInventoryClose(InventoryCloseEvent e){
        if(e.getView().getTitle().equalsIgnoreCase("§7» §6Navigator §7«")){
            if(Bukkit.getScheduler().isCurrentlyRunning(navigatorID)){
                Bukkit.getScheduler().cancelTask(navigatorID);
            }
        }
    }

    @EventHandler
    public void handleNavigatorInventoryInteract(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equalsIgnoreCase("§7» §6Navigator §7«")){
            e.setCancelled(true);
            if(e.getCurrentItem().getType().equals(Material.BLACK_STAINED_GLASS_PANE)) return;
            if(e.getCurrentItem().getType().equals(Material.BARRIER)){
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 100, 1f);
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §6CityBuild §7«")){

            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §6Spawn §7«")){
                if(LobbySystem.getInstance().getConfig().get("Locations.Spawn") == null){
                    if(p.hasPermission("ub.join.admin")) {
                        p.sendTitle("§cDer Spawn wurde nicht gesetzt!", "§eSetze in mit /locations", 20*1, 20*3, 20*1);
                    }
                }else{
                    p.teleport(LobbySystem.getInstance().getConfig().getLocation("Locations.Spawn"));
                }
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §4§lXenoverse #1 §7«")){

            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §3SkyPvP §7«")){

            }
        }
    }



}

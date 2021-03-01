package de.falsedeveloping.falseparty.Listeners;

import de.falsedeveloping.falseparty.Main;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class PresentPickupSound implements Listener {

  private Main plugin;
  public PresentPickupSound(Main plugin) {
    this.plugin = plugin;
  }
  @EventHandler
  public void onPresentPickup(EntityPickupItemEvent e) {
    if (!(e.getEntity() instanceof Player)) return;
    Player p = (Player) e.getEntity();
    if (!(e.getItem().getItemStack().getItemMeta().getDisplayName().equals(plugin.getPresentItemStack().getName()))) return;
    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
  }

}

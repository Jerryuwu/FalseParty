package de.falsedeveloping.falseparty.Listeners;

import de.falsedeveloping.falseparty.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PresentUse implements Listener {

  private Main plugin;
  public PresentUse(Main plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onPresentUse(PlayerInteractEvent e) {
    Player p = e.getPlayer();
    if (e.getItem() == null) return;
    if (!(e.getAction().equals(Action.RIGHT_CLICK_AIR)
        || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;
    if (e.getItem().getType() != Material.PLAYER_HEAD) return;
    if (!(e.getItem().getItemMeta().getDisplayName().equals(plugin.getPresentItemStack().getName()))) return;
    e.setCancelled(true);
    ItemStack item = plugin.getPresentItemStack().getContent();
    p.getInventory().addItem(item);
    removeItem(p.getInventory().getItemInMainHand());
    p.sendMessage("§3Du hast §4" +item.getAmount() + " " + editText(item.getType().name()) + "§3 bekommen!");
    return;
  }
  public ItemStack removeItem(ItemStack i) {
    i.setAmount(i.getAmount() - 1);
    return i;
  }

  public String editText(String s) {
    s = s.toLowerCase();
    System.out.println(s);
    s = s.replace("_", " ");
    System.out.println(s);
    return s;
  }
}

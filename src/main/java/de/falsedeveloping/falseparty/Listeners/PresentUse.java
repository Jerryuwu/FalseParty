package de.falsedeveloping.falseparty.Listeners;

import de.falsedeveloping.falseparty.Main;
import org.bukkit.Material;
import org.bukkit.Sound;
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
    System.out.println("Hello2");
    System.out.println(e.getItem().getItemMeta().getDisplayName());
    if (!(e.getItem().getItemMeta().getDisplayName().equals("§fRotes Geschenk"))
        && !(e.getItem().getItemMeta().getDisplayName().equals("§fGrünes Geschenk"))) return;
    System.out.println("Hello3");
    e.setCancelled(true);
    ItemStack item = plugin.getPresentItemStack().getContent(e.getItem().getItemMeta().getDisplayName());
    p.getInventory().addItem(item);
    removeItem(p.getInventory().getItemInMainHand());
    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.1f, 1);
    p.sendMessage(
        "§3Du hast §4" + item.getAmount() + " " + editText(item.getType().name()) + "§3 bekommen!");
    return;
  }

  public ItemStack removeItem(ItemStack i) {
    i.setAmount(i.getAmount() - 1);
    return i;
  }

  public String editText(String s) {
    s = s.toLowerCase();
    s = s.replace("_", " ");
    return s;
  }
}

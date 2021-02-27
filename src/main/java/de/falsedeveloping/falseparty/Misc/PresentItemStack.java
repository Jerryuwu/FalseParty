package de.falsedeveloping.falseparty.Misc;

import de.falsedeveloping.falseparty.Main;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class PresentItemStack {
  private Main plugin;
  public PresentItemStack(Main plugin) {
    this.plugin = plugin;
  }

  public ItemStack getPresent() {
    //TODO:CHECK
    ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, 1);
    SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
    meta.setOwner("MHF_Present1");
    meta.setDisplayName(getName());
    List<String> lores = new ArrayList<>();
    lores.add("§fDrücke Rechtsklick, um das Geschenk zu öffnen!");
    meta.setLore(lores);
    itemStack.setItemMeta(meta);
    return itemStack;
  }
  public String getName() {
    return "§fGeschenk";
  }
}

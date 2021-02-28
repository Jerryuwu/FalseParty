package de.falsedeveloping.falseparty.Misc;

import de.falsedeveloping.falseparty.Main;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
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

  public ItemStack getContent() {
    //Get content of config file
    List<String> configContent = plugin.getConfiguration().getStringList("inhalt1");
    List<ItemStack> itemContentList = configContent.stream().map(content -> ItemStackBuilder.of(Material.valueOf(content.split("#")[0])).amount(Integer.parseInt(content.split("#")[1])).build()).collect(
        Collectors.toList());

    //Choose content of present by randomly picking item
    ItemStack itemContent = itemContentList.get(new Random().nextInt(itemContentList.size()));

    //Randomizing item amount
    int contentAmount = itemContent.getAmount();
    do {
      itemContent.setAmount(new Random().nextInt(contentAmount));
    } while (itemContent.getAmount() == 0);
    return itemContent;
  }
}

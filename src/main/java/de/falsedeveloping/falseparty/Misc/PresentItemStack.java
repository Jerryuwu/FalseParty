package de.falsedeveloping.falseparty.Misc;

import de.falsedeveloping.falseparty.Main;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PresentItemStack {
  private Main plugin;

  public PresentItemStack(Main plugin) {
    this.plugin = plugin;
  }

  public ItemStack getPresent() {
    ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, 1);
    SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
    String owner;
    String name;

    double chance = plugin.getConfiguration().getDouble("chance") / 100;
    float random = (float) Math.random();
    if (chance > random) {
      owner = "MHF_Present1";
      name = "§fGrünes Geschenk";
    } else {
      owner = "MHF_Present2";
      name = "§fRotes Geschenk";
    }

    meta.setOwner(owner);
    meta.setDisplayName(name);
    List<String> lores = new ArrayList<>();
    lores.add("§fDrücke Rechtsklick, um das Geschenk zu öffnen!");
    meta.setLore(lores);
    itemStack.setItemMeta(meta);
    return itemStack;
  }

  public ItemStack getContent(String name) {
    // Get content of config file
    String path;
    if (name.equals("§fGrünes Geschenk")) path = "inhalt1";
    else path = "inhalt2";
    List<String> configContent = plugin.getConfiguration().getStringList(path);
    List<ItemStack> itemContentList =
        configContent.stream()
            .map(
                content ->
                    ItemStackBuilder.of(Material.valueOf(content.split("#")[0]))
                        .amount(Integer.parseInt(content.split("#")[1]))
                        .build())
            .collect(Collectors.toList());

    // Choose content of present by randomly picking item
    ItemStack itemContent = itemContentList.get(new Random().nextInt(itemContentList.size()));

    // Randomizing item amount
    int contentAmount = itemContent.getAmount();
    itemContent.setAmount(new Random().nextInt(contentAmount) + 1);
    return itemContent;
  }
}

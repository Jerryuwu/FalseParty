package de.falsedeveloping.falseparty.Commands;

import de.falsedeveloping.falseparty.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Party implements CommandExecutor {

  private Main plugin;
  public Party(Main plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) return true;
    Player p = (Player) sender;
    p.getInventory().addItem(plugin.getPresentItemStack().getPresent());
    p.sendMessage("Added Item");
    if (!(p.hasPermission("party.party"))) {
      p.sendMessage("Du hast dazu keine Rechte");
      return true;
    }
    Bukkit.broadcastMessage("§4" + p.getName() + " hat eine Party gestartet!");
    return true;
  }
}

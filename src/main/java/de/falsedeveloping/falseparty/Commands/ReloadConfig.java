package de.falsedeveloping.falseparty.Commands;

import de.falsedeveloping.falseparty.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadConfig implements CommandExecutor {

  private Main plugin;

  public ReloadConfig(Main plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (!(sender instanceof Player)) return true;
    Player p = (Player) sender;

    if (!(p.hasPermission("party.reload"))) {
      p.sendMessage("§4Du hast dazu keine Rechte!");
      return true;
    }

    plugin.reloadConfig();
    p.sendMessage("§3§lKonfiguration wurde neu geladen");
    return true;
  }
}

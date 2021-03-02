package de.falsedeveloping.falseparty.Commands;

import de.falsedeveloping.falseparty.Main;
import de.falsedeveloping.falseparty.Misc.InstantFirework;
import de.falsedeveloping.falseparty.Misc.Radius;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
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

    if (!(sender instanceof Player)) {
      return true;
    }

    Player p = (Player) sender;
    if (!(p.hasPermission("party.party"))) {
      p.sendMessage("Du hast dazu keine Rechte");
      return true;
    }

    // Create location + amount of drops
    List<Location> dropLocations =
        Radius.getRandomLocations(
            p.getLocation(),
            p.getLocation().getY() - 50,
            (float) plugin.getConfiguration().getDouble("radius"),
            plugin.getConfiguration().getInt("geschenke"));

    // execute party on new thread
    new Thread(
            () -> {
              dropLocations.forEach(
                  location -> {
                    Bukkit.getScheduler()
                        .runTask(
                            plugin,
                            () -> {
                              p.getWorld()
                                  .dropItemNaturally(
                                      location, plugin.getPresentItemStack().getPresent());
                              new InstantFirework(getEffect(), location);
                              if (plugin.getConfiguration().getString("dropnachricht") != null)
                                Bukkit.broadcastMessage(
                                    plugin.getConfiguration().getString("dropnachricht"));
                            });
                    // break between drops
                    try {
                      if (plugin.getConfiguration().getInt("pause") != 0)
                        Thread.sleep(plugin.getConfiguration().getInt("pause") * 1000L);
                      else Thread.sleep(5000);
                    } catch (InterruptedException e) {
                      e.printStackTrace();
                    }
                  });
              // after loop
              Bukkit.broadcastMessage(p.getName() + "'s Party ist vorbei!");
            })
        .start();
    return true;
  }

  public FireworkEffect getEffect() {
    return FireworkEffect.builder()
        .flicker(false)
        .trail(true)
        .with(FireworkEffect.Type.BALL)
        .withColor(Color.ORANGE)
        .withFade(Color.RED)
        .build();
  }
}

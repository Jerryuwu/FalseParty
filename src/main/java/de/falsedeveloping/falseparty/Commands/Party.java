package de.falsedeveloping.falseparty.Commands;

import de.falsedeveloping.falseparty.Main;
import de.falsedeveloping.falseparty.Misc.Cooldown;
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
    cooldown = new Cooldown(0,plugin);
  }
  private Cooldown cooldown;

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (!(sender instanceof Player)) {
      return true;
    }
    //check if player has permission
    Player p = (Player) sender;
    if (!(p.hasPermission("party.party"))) {
      p.sendMessage("Du hast dazu keine Rechte");
      return true;
    }

    //check if cooldown has expired
    long timeBetweenCommand = System.currentTimeMillis() - cooldown.getCommandCooldown();
    if (timeBetweenCommand < cooldown.getDefaultCooldown()) {
      //minutes between cooldown
      long time = ((cooldown.mins * 60L) - timeBetweenCommand / 1000) / 60 + 1;
      p.sendMessage(
          "§cDu musst noch §4§l" + time + " Minute(n) §r§cwarten, bis du diesen Befehl ausführen kannst!");
      return true;
    }

    //reset cooldown
    cooldown.setCommandCooldown(System.currentTimeMillis());

    //executing party
    executeParty(p);

    return true;
  }

  public void executeParty(Player p) {
    //getting blocks in radius
    List<Location> dropLocations =
        Radius.getRandomLocations(
            p.getLocation(),
            p.getLocation().getY() - 50,
            (float) plugin.getConfiguration().getDouble("radius"),
            plugin.getConfiguration().getInt("geschenke"));
    Bukkit.broadcastMessage("§6" + p.getName() + " hat eine Party gestartet!");
    //executing party
    new Thread(
            () -> {
              dropLocations.forEach(
                  location -> {
                    Bukkit.getScheduler()
                        .runTask(
                            plugin,
                            () -> {
                              //dropping items with firework
                              p.getWorld()
                                  .dropItemNaturally(
                                      location, plugin.getPresentItemStack().getPresent());
                              new InstantFirework(getEffect(), location);
                              if (plugin.getConfiguration().getString("dropnachricht") != null)
                                Bukkit.broadcastMessage(
                                    plugin.getConfiguration().getString("dropnachricht"));
                            });
                    //pause between drops
                    try {
                      if (plugin.getConfiguration().getInt("pause") != 0)
                        Thread.sleep(plugin.getConfiguration().getInt("pause") * 1000L);
                      else Thread.sleep(5000);
                    } catch (InterruptedException e) {
                      e.printStackTrace();
                    }
                  });
              //after loop
              Bukkit.broadcastMessage("§6" + p.getName() + "'s Party ist vorbei!");
            })
        .start();
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

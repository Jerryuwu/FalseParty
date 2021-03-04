package de.falsedeveloping.falseparty.Misc;

import de.falsedeveloping.falseparty.Main;

public class Cooldown {

  private Main plugin;
  public Cooldown(Main plugin) {
    this.plugin = plugin;
  }

  private long cooldown = 0L;
  public int mins = 1;

  public Cooldown(long time, Main plugin) {
    this.cooldown = time;
    this.plugin = plugin;
  }

  public void setCommandCooldown(long ms) {
    cooldown = ms;
  }

  public long getCommandCooldown() {
    return cooldown;
  }

  public int getDefaultCooldown() {
    return plugin.getConfiguration().getInt("cooldown") * 60 * 1000;
  }
}

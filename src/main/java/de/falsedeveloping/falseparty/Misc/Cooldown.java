package de.falsedeveloping.falseparty.Misc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cooldown {

  private final Map<UUID, Integer> cooldowns = new HashMap<>();

  public static final int defaultCooldown = 15;

  public void setCooldown(UUID uuid, int time) {
    cooldowns.put(uuid, time);
  }

  public int getCooldown(UUID uuid) {
    return cooldowns.getOrDefault(uuid, 0);
  }
}

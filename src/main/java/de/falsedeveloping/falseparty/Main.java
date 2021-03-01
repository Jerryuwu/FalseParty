package de.falsedeveloping.falseparty;

import de.falsedeveloping.falseparty.Commands.Party;
import de.falsedeveloping.falseparty.Listeners.PresentPickupSound;
import de.falsedeveloping.falseparty.Listeners.PresentUse;
import de.falsedeveloping.falseparty.Misc.PresentItemStack;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  private PresentItemStack presentItemStack;
  private Party party;
  private FileConfiguration config;

  @Override
  public void onEnable() {
    PluginManager pm = Bukkit.getPluginManager();
    getCommand("partyy").setExecutor(new Party(this));

    pm.registerEvents(new PresentUse(this), this);
    pm.registerEvents(new PresentPickupSound(this), this);

    File configFile = new File(getDataFolder(), "config.yml");
    if (!configFile.exists()) {
      configFile.getParentFile().mkdirs();
      saveResource("config.yml", false);
    }
    presentItemStack = new PresentItemStack(this);
    config = YamlConfiguration.loadConfiguration(configFile);
  }

  public FileConfiguration getConfiguration() {
    return config;
  }

  public PresentItemStack getPresentItemStack() {
    return presentItemStack;
  }
}

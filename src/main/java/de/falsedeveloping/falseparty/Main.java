package de.falsedeveloping.falseparty;

import de.falsedeveloping.falseparty.Commands.Party;
import de.falsedeveloping.falseparty.Misc.PresentItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  private PresentItemStack presentItemStack;
  private Party party;

  @Override
  public void onEnable() {
      getCommand("partyy").setExecutor(new Party(this));

      presentItemStack = new PresentItemStack(this);
  }

  public PresentItemStack getPresentItemStack() {
    return presentItemStack;
  }
}

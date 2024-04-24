package de.spigotutils.delta203.spigot.commands;

import de.spigotutils.delta203.core.SpigotUtils;
import de.spigotutils.delta203.spigot.SpigotUtilsSpigot;
import de.spigotutils.delta203.spigot.utils.MapBuilder;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class TestCommand implements TabExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!(sender instanceof Player p)) {
      sender.sendMessage(SpigotUtils.prefix + "§cYou have to be a player!");
      return false;
    }
    p.sendMessage(SpigotUtils.prefix + "Test! :)");
    p.sendMessage(SpigotUtils.prefix + SpigotUtilsSpigot.config.getString("foo"));
    p.sendMessage(SpigotUtils.prefix + SpigotUtils.config.get("foo"));

    String url =
        "https://static.wikia.nocookie.net/minecraft_gamepedia/images/4/44/Diamond_Sword_JE3_BE3.png";
    if (args.length == 1) url = args[0];
    MapBuilder mapBuilder = new MapBuilder(p.getWorld(), url);
    p.getInventory().addItem(mapBuilder.getMapItem());

    return false;
  }

  @Override
  public List<String> onTabComplete(
      CommandSender sender, Command cmd, String label, String[] args) {
    if (args.length <= 1) {
      List<String> arguments = new ArrayList<>();
      arguments.add("foo");
      arguments.add("bar");
      arguments.add("alice");
      arguments.add("bob");
      return arguments;
    }
    return null;
  }
}

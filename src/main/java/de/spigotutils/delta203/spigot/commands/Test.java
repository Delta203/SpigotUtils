package de.spigotutils.delta203.spigot.commands;

import de.spigotutils.delta203.spigot.SpigotUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Test implements TabExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!(sender instanceof Player p)) {
      sender.sendMessage(SpigotUtils.prefix + "§cYou have to be a player!");
      return false;
    }
    p.sendMessage(SpigotUtils.prefix + "Test! :)");
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

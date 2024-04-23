package de.spigotutils.delta203.spigot;

import de.spigotutils.delta203.core.ServerType;
import de.spigotutils.delta203.core.SpigotUtils;
import de.spigotutils.delta203.core.mysql.MySQlManager;
import de.spigotutils.delta203.spigot.commands.TestCommand;
import de.spigotutils.delta203.spigot.utils.customitem.RubySword;
import de.spigotutils.delta203.spigot.files.FileManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotUtilsSpigot extends JavaPlugin {

  public static SpigotUtilsSpigot plugin;
  public static Configuration config;

  @Override
  public void onEnable() {
    plugin = this;
    SpigotUtils.serverType = ServerType.SPIGOT;
    SpigotUtils.prefix = "§f[§bSpigotUtils§f] §7";

    // load config file
    FileManager configYml = new FileManager("config.yml");
    configYml.create();
    configYml.load();
    config = configYml.get();
    // load global (core) config
    SpigotUtils.config = new HashMap<>();
    ArrayList<String> configKeys = new ArrayList<>();
    getConfigKeysRecursive(config, "", configKeys);
    for (String key : configKeys) {
      SpigotUtils.config.put(key, config.get(key));
    }
    Bukkit.getConsoleSender()
        .sendMessage(SpigotUtils.prefix + "§aConfig files successfully loaded.");

    // create mysql connection
    SpigotUtils.mysql = new MySQlManager("localhost", 3306, "test", "root", "");
    SpigotUtils.mysql.connect();
    if (SpigotUtils.mysql.isConnected()) {
      Bukkit.getConsoleSender().sendMessage(SpigotUtils.prefix + "§aMySQl successfully connected.");
      SpigotUtils.mysql.createTable();
      Bukkit.getConsoleSender()
          .sendMessage(SpigotUtils.prefix + "§aMySQl tables successfully registered.");
    }

    // register commands and listeners
    Objects.requireNonNull(getCommand("test")).setExecutor(new TestCommand());

    // register custom items
    new RubySword().register();

    Bukkit.getConsoleSender()
        .sendMessage(
            SpigotUtils.prefix
                + "§aPlugin successfully loaded. §7(§e"
                + SpigotUtils.serverType
                + "§7)");
  }

  @Override
  public void onDisable() {
    Bukkit.getConsoleSender().sendMessage(SpigotUtils.prefix + "§cPlugin successfully disabled.");
  }

  /**
   * Get all config keys recursively.
   *
   * @param config the configuration file
   * @param prefix the prefix key
   * @param keys the config keys
   */
  private void getConfigKeysRecursive(Configuration config, String prefix, ArrayList<String> keys) {
    for (String key : config.getKeys(true)) {
      Object value = config.get(key);
      if (value instanceof Configuration subConfig) {
        getConfigKeysRecursive(subConfig, prefix + key + ".", keys);
      } else {
        keys.add(prefix + key);
      }
    }
  }
}

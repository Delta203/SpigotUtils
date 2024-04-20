package de.spigotutils.delta203.bungee;

import de.spigotutils.delta203.bungee.commands.TestCommand;
import de.spigotutils.delta203.bungee.files.FileManager;
import de.spigotutils.delta203.core.ServerType;
import de.spigotutils.delta203.core.SpigotUtils;
import de.spigotutils.delta203.core.mysql.MySQlManager;
import java.util.ArrayList;
import java.util.HashMap;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

public class SpigotUtilsBungee extends Plugin {

  public static SpigotUtilsBungee plugin;
  public static Configuration config;

  @Override
  public void onEnable() {
    plugin = this;
    SpigotUtils.serverType = ServerType.BUNGEECORD;
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
    ProxyServer.getInstance()
        .getConsole()
        .sendMessage(new TextComponent(SpigotUtils.prefix + "§aConfig files successfully loaded."));

    // create mysql connection
    SpigotUtils.mysql = new MySQlManager("localhost", 3306, "test", "root", "");
    SpigotUtils.mysql.connect();
    if (SpigotUtils.mysql.isConnected()) {
      ProxyServer.getInstance()
          .getConsole()
          .sendMessage(new TextComponent(SpigotUtils.prefix + "§aMySQl successfully connected."));
      SpigotUtils.mysql.createTable();
      ProxyServer.getInstance()
          .getConsole()
          .sendMessage(
              new TextComponent(SpigotUtils.prefix + "§aMySQl tables successfully registered."));
    }

    // register commands and listeners
    ProxyServer.getInstance()
        .getPluginManager()
        .registerCommand(plugin, new TestCommand("test", "test.use"));

    ProxyServer.getInstance()
        .getConsole()
        .sendMessage(
            new TextComponent(
                SpigotUtils.prefix
                    + "§aPlugin successfully loaded. §7(§e"
                    + SpigotUtils.serverType
                    + "§7)"));
  }

  @Override
  public void onDisable() {
    ProxyServer.getInstance()
        .getConsole()
        .sendMessage(new TextComponent(SpigotUtils.prefix + "§cPlugin successfully disabled."));
  }

  /**
   * Get all config keys recursively.
   *
   * @param config the configuration file
   * @param prefix the prefix key
   * @param keys the config keys
   */
  private void getConfigKeysRecursive(Configuration config, String prefix, ArrayList<String> keys) {
    for (String key : config.getKeys()) {
      Object value = config.get(key);
      if (value instanceof Configuration subConfig) {
        getConfigKeysRecursive(subConfig, prefix + key + ".", keys);
      } else {
        keys.add(prefix + key);
      }
    }
  }
}

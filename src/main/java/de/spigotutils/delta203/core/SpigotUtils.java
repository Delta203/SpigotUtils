package de.spigotutils.delta203.core;

import de.spigotutils.delta203.core.mysql.MySQlManager;

import java.util.HashMap;

public class SpigotUtils {

  public static ServerType serverType;
  public static MySQlManager mysql;
  public static String prefix;

  /** Optional register global config. */
  public static HashMap<String, Object> config;
}

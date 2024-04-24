# Spigot Utils (Bungeecord & Spigot)
Useful classes for making a bungeecord & spigot plugin

## File Manager:
Register a `FileManager` to handle a configuration file:
```java
FileManager configYml = new FileManager("config.yml");
configYml.create();
configYml.load();
config = configYml.get();
```
Optional register global config:
```java
config = new HashMap<>();
ArrayList<String> configKeys = new ArrayList<>();
getConfigKeysRecursive(configYml.get(), "", configKeys);
for (String key : configKeys) {
    config.put(key, config.get(key));
}
```

## MySQl Manager:
Register a `MySQlManager` to create a MySQl connection and work with a MySQl database:
```java
mysql = new MySQlManager("localhost", 3306, "test", "root", "");
mysql.connect();
if (mysql.isConnected()) {
  mysql.createTable();
}
```

## Spigot Utils:
### Item Builder:
Register an `ItemBuilder` to modify `ItemStacks`:
```java
new ItemBuilder(new ItemStack(Material.DIAMOND_SWORD),"§aCool Item").getItem();
```

### Location Builder:
Register a `LocationBuilder` to load a location from the config file:
```java
new LocationBuilder(p.getLocation(), "test").create();
new LocationBuilder("test").getLocation();
```

### Map Builder
Register a `MapBuilder` to create custom map images:
```java
new MapBuilder(p.getWorld(), url).getMapItem();
```

### Particle Builder:
Register a `ParticleBuilder` to spawn particles in a more controlled way:
```java
new ParticleBuilder(Particle.FLAME, p.getLocation(), 0, 0, 0, 0, 1).showAll();
```

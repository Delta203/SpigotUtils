package de.spigotutils.delta203.spigot.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ParticleBuilder {

  private final Particle particle;
  private final Location location;
  private final float offSetX;
  private final float offSetY;
  private final float offSetZ;
  private final float speed;
  private final int amount;

  /**
   * Register a ParticleBuilder with specified parameters to spawn particles in a more controlled
   * way.
   *
   * @param particle the type of particle to display
   * @param location the location at which to display the particle
   * @param offSetX the X offset for the particle
   * @param offSetY the Y offset for the particle
   * @param offSetZ the Z offset for the particle
   * @param speed the speed of the particle
   * @param amount the number of particles to display
   */
  public ParticleBuilder(
      Particle particle,
      Location location,
      float offSetX,
      float offSetY,
      float offSetZ,
      float speed,
      int amount) {
    this.particle = particle;
    this.location = location;
    this.offSetX = offSetX;
    this.offSetY = offSetY;
    this.offSetZ = offSetZ;
    this.speed = speed;
    this.amount = amount;
  }

  /** Displays the particle at the specified location for all players in the world. */
  public void showAll() {
    Objects.requireNonNull(location.getWorld())
        .spawnParticle(
            particle,
            location.getX(),
            location.getY(),
            location.getZ(),
            amount,
            offSetX,
            offSetY,
            offSetZ,
            speed);
  }

  /**
   * Displays the particle at the specified location for a specific player.
   *
   * @param p the player to display the particle
   */
  public void show(Player p) {
    p.spawnParticle(
        particle,
        location.getX(),
        location.getY(),
        location.getZ(),
        amount,
        offSetX,
        offSetY,
        offSetZ,
        speed);
  }
}

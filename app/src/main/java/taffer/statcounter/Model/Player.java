package taffer.statcounter.Model;

import java.io.Serializable;

/**
 * Player class.
 */
public class Player implements Serializable {
    private int color;
    private String name;
    private int lifepoints = 20;
    private int poisonCounters;

    /**
     * Creates a Player
     * @param name, name of Player.
     * @param color, color of the Player.
     * @param points, the lifepoints of the Player.
     */
    public Player (String name, int color, int points){
        this.name = name;
        this.color = color;
        this.lifepoints = points;
    }

    /**
     * Adds LifePoints
     * @param points, points to be added.
     */
    public void addPoints(int points){
        this.lifepoints += points;
    }

    /**
     * Adds PoisonCounters
     * @param counters, counters to be added.
     */
    public void addPoisonCounters(int counters){
        this.poisonCounters += counters;
    }

    /**
     * Returns the color
     * @return int, color.
     */
    public int color(){
        return this.color;
    }

    /**
     * Returns the health
     * @return int, lifepoints.
     */
    public int health(){
        return this.lifepoints;
    }

    /**
     * Returns the poison
     * @return int, poison.
     */
    public int poison(){
        return this.poisonCounters;
    }
    /**
     * Returns the name
     * @return String, name.
     */
    public String name(){
        return this.name;
    }

    /**
     * Returns a String representation of the Player.
     * @return String.
     */
    @Override
    public String toString() {
        return "Name: " + this.name + "Life: " + this.lifepoints + "Poison counters: " + this.poisonCounters + "Color: " + color;
    }
}

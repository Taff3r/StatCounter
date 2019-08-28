package taffer.statcounter.Model;

import android.util.Log;
import java.io.Serializable;
import java.util.Map;

/**
 * Class for keeping count of the Players.
 */
public class Game implements Serializable {
    private Map<Integer, Player> players;
    private RandomHelper rand = new RandomHelper();
    private int defaultHP;

    /**
     * Game object.
     * @param defaultHP
     * @param players
     */
    public Game(int defaultHP, Map<Integer, Player> players){
        this.players = players;
        this.defaultHP = defaultHP;
    }

    /**
     * Returns the default HP of the Game.
     * @return
     */
    public int defaultHP(){
        return defaultHP;
    }

    /**
     * Resets the game.
     */
    public void resetGame(){
        for(Map.Entry<Integer, Player> e : players.entrySet()){
            Player p = e.getValue();
            p.addPoints(this.defaultHP - p.health());
            p.addPoisonCounters(-p.poison());
        }
    }

    /**
     * Adds LifePoints to Player.
     * @param playerNumber, the player number.
     * @param points, number of lifepoints.
     */
    public void addPoints(int playerNumber, int points){
        this.players.get(playerNumber).addPoints(points);
    }

    /**
     * Adds Poison to Player.
     * @param playerNumber, the player number.
     * @param counters, number of counters.
     */
    public void addPoisonCounters(int playerNumber, int counters){
        this.players.get(playerNumber).addPoisonCounters(counters);
    }

    /**
     * Returns the number of players.
     * @return int, number of players.
     */
    public int noOfPlayers(){
        return players.size();
    }

    /**
     * Returns the lifepoints of the player.
     * @param i, number of the player.
     * @return int, health of the player.
     */
    public int getPlayerHealth(int i){
        return players.get(i).health();
    }

    /**
     * Returns the poison of the player.
     * @param i, number of the player.
     * @return int, poison of the player.
     */
    public int getPlayerPoison(int i){
        return players.get(i).poison();
    }
    /**
     * Returns the color of the player.
     * @param i, number of the player.
     * @return int, color of the player.
     */
    public int getPlayerColor(int i){
        return players.get(i).color();
    }

    /**
     * Returns the name of the player.
     * @param i, number of the player.
     * @return String, name of the player.
     */
    public String getPlayerName(int i){
        return players.get(i).name();
    }

    /**
     * Rolls the die.
     * @return the result of the die.
     */
    public int rollDie(){
        return rand.rollDie();
    }

    /**
     * Flips the coin.
     * @return the result of the flip.
     */
    public boolean flipCoin(){
        return rand.flipCoin();
    }

}

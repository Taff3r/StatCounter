package taffer.statcounter.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for building a Game.
 */
public class GameBuilder implements Serializable {
    private int noOfPlayers;
    private int count = 0;
    private Map<Integer, Player> players = new HashMap<>();
    private String gameMode;

    /**
     * Sets the game mode.
     * @param gameMode, string game mode to set.
     * @return GameBuilder, this.
     */
    public GameBuilder setGameMode(String gameMode){
        this.gameMode = gameMode;
        return this;
    }

    /**
     * Returns the current number of players.
     * @return int
     */
    public int getNoOfPlayers(){
        return this.noOfPlayers;
    }

    /**
     * Sets the number of players.
     * @param noOfPlayers, the number of players.
     * @return GameBuilder, this.
     */
    public GameBuilder setNoOfPlayers(int noOfPlayers){
        this.noOfPlayers = noOfPlayers;
        return this;
    }

    /**
     * Adds a new Player
     * @param name name of the player.
     * @param color, the color for the player.
     * @return GameBuilder, this.
     */
    public GameBuilder addPlayer(String name, int color){
        this.count++;
        this.players.put(count, new Player(name, color, getLifePointsFromGameMode()));
        return this;
    }

    /**
     * Builds the Game.
     * @return Game, the built game.
     */
    public Game build(){
        return new Game(this.getLifePointsFromGameMode(), players);
    }

    private int getLifePointsFromGameMode(){
        switch (this.gameMode){
            case "Commander":
                return 40;
            case "Brawl":
                if(this.noOfPlayers >=3){
                    return 30;
                }
                return 25;
            case "Two-Headed Giant":
                return 30;
            default:
                return 20;
        }
    }

}

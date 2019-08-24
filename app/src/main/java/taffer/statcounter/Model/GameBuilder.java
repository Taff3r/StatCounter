package taffer.statcounter.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GameBuilder implements Serializable {
    private int noOfPlayers;
    private int count = 0;
    private Map<Integer, Player> players = new HashMap<>();
    private String gameMode;

    public GameBuilder setGameMode(String gameMode){
        this.gameMode = gameMode;
        return this;
    }

    public int getNoOfPlayers(){
        return this.noOfPlayers;
    }
    public GameBuilder setNoOfPlayers(int noOfPlayers){
        this.noOfPlayers = noOfPlayers;
        return this;
    }

    public GameBuilder addPlayer(String name, int color){
        this.count++;
        this.players.put(count, new Player(name, color, getLifePointsFromGameMode()));
        return this;
    }

    public Game build(){
        return new Game(players);
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

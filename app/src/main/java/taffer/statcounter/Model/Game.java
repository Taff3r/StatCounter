package taffer.statcounter.Model;

import java.io.Serializable;
import java.util.Map;

public class Game implements Serializable {
    private Map<String, Player> players;
    private RandomHelper rand = new RandomHelper();

    public Game(Map<String, Player> players){
        this.players = players;
    }

    public void addPoints(String playerNumber, int points){
        this.players.get(playerNumber).addPoints(points);
    }

    public void addPoisonCounters(String playerNumber, int counters){
        this.players.get(playerNumber).addPoisonCounters(counters);
    }


    /**
     * TODO: D20 or D6 ?
     * @return
     */
    public int rollDie(){
        return rand.rollDie();
    }

    public boolean flipCoin(){
        return rand.flipCoin();
    }

}

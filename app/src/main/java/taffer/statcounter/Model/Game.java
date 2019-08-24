package taffer.statcounter.Model;

import android.util.Log;
import java.io.Serializable;
import java.util.Map;

public class Game implements Serializable {
    private Map<Integer, Player> players;
    private RandomHelper rand = new RandomHelper();

    public Game(Map<Integer, Player> players){
        this.players = players;
    }

    public void addPoints(String playerNumber, int points){
        this.players.get(playerNumber).addPoints(points);
    }

    public void addPoisonCounters(String playerNumber, int counters){
        this.players.get(playerNumber).addPoisonCounters(counters);
    }

    public int noOfPlayers(){
        return players.size();
    }

    public int getPlayerHealth(int i){
        Log.e("PLAYER ", players.get(i).toString());

        return players.get(i).health();
    }
    public int getPlayerColor(int i){
        return players.get(i).color();
    }
    public String getPlayerName(int i){
        return players.get(i).name();
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

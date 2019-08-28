package taffer.statcounter.Model;

import android.util.Log;
import java.io.Serializable;
import java.util.Map;

public class Game implements Serializable {
    private Map<Integer, Player> players;
    private RandomHelper rand = new RandomHelper();
    private int defaultHP;

    public Game(int defaultHP, Map<Integer, Player> players){
        this.players = players;
        this.defaultHP = defaultHP;
    }

    public int defaultHP(){
        return defaultHP;
    }

    public void resetGame(){
        for(Map.Entry<Integer, Player> e : players.entrySet()){
            Player p = e.getValue();
            p.addPoints(this.defaultHP - p.health());
            p.addPoisonCounters(-p.poison());
        }
    }
    public void addPoints(int playerNumber, int points){
        this.players.get(playerNumber).addPoints(points);
    }

    public void addPoisonCounters(int playerNumber, int counters){
        this.players.get(playerNumber).addPoisonCounters(counters);
    }

    public int noOfPlayers(){
        return players.size();
    }

    public int getPlayerHealth(int i){
        return players.get(i).health();
    }

    public int getPlayerPoison(int i){
        return players.get(i).poison();
    }
    public int getPlayerColor(int i){
        return players.get(i).color();
    }

    public String getPlayerName(int i){
        return players.get(i).name();
    }

    public int rollDie(){
        return rand.rollDie();
    }

    public boolean flipCoin(){
        return rand.flipCoin();
    }

}

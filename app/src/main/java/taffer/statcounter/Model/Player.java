package taffer.statcounter.Model;

import android.util.Log;

import java.io.Serializable;

public class Player implements Serializable {
    private int color;
    private String name;
    private int lifepoints = 20;
    private int poisonCounters;

    public Player (String name, int color, int points){
        this.name = name;
        this.color = color;
        this.lifepoints = points;
        Log.e("RUN", color + "");
    }

    public void addPoints(int points){
        this.lifepoints += points;
    }

    public void addPoisonCounters(int counters){
        this.poisonCounters += counters;
    }

    public int color(){
        return this.color;
    }

    public int health(){
        return this.lifepoints;
    }

    public String name(){
        return this.name;
    }


    @Override
    public String toString() {
        return "Name: " + this.name + "Life: " + this.lifepoints + "Poison counters: " + this.poisonCounters + "Color: " + color;
    }
}

package taffer.statcounter.Model;

public class Player {
    private String color;
    private String name;
    private int lifepoints = 20;
    private int poisonCounters;

    public Player (String name, String color, int points){
        this.name = name;
        this.color = color;
        this.lifepoints = points;
    }

    public void addPoints(int points){
        this.lifepoints += points;
    }

    public void addPoisonCounters(int counters){
        this.poisonCounters += counters;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "Life: " + this.lifepoints + "Poison counters: " + this.poisonCounters + "Color: " + color;
    }
}

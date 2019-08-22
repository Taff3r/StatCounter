package taffer.statcounter.Model;

import java.util.Random;

public class RandomHelper {
    private Random rand = new Random();

    public int rollDie(){
        return rand.nextInt(6) + 1;
    }

    public boolean flipCoin(){
        return rand.nextInt(1) == 0;
    }
}

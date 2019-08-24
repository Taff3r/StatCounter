package taffer.statcounter.Model;

import java.io.Serializable;
import java.util.Random;

public class RandomHelper implements Serializable {
    private Random rand = new Random();

    public int rollDie(){
        return rand.nextInt(20) + 1;
    }

    public boolean flipCoin(){
        return rand.nextBoolean();
    }
}

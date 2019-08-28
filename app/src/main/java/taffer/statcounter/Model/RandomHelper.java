package taffer.statcounter.Model;

import java.io.Serializable;
import java.util.Random;

/**
 * Helper class for handling random events.
 */
public class RandomHelper implements Serializable {
    private Random rand = new Random();

    /**
     * Rolls a D20 die.
     * @returns the result of the roll.
     */
    public int rollDie(){
        return rand.nextInt(20) + 1;
    }

    /**
     * Flips a coin.
     * @return boolean, the result of the flip.
     */
    public boolean flipCoin(){
        return rand.nextBoolean();
    }
}

package taffer.statcounter.Fragments;

/**
 * Interface for all StatCounter activities/Fragments.
 */
public interface StatCounter {

    void setHP(int playerNo, int hp);
    void setDieValue(int val);
    void setCoinValue(boolean val);
    void resetGame();
    void setPoison(int playerNo, int poison);
}

package taffer.statcounter.Fragments;

public interface StatCounter {

    void setHP(int playerNo, int hp);
    void setDieValue(int val);
    void setCoinValue(boolean val);
    void resetGame();
    void setPoison(int playerNo, int poison);
}

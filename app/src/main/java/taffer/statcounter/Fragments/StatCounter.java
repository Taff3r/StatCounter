package taffer.statcounter.Fragments;

public interface StatCounter { // TODO: Convert to Abstract

    void setHP(int playerNo, int hp);
    void setDieValue(int val);
    void setCoinValue(boolean val);
    void resetGame();
}

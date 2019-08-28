package taffer.statcounter.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import taffer.statcounter.R;

/**
 * Fragment for 2 Player game.
 */
public class Game2Fragment extends Fragment implements StatCounter {
    private String defaultHp;
    private String p1Name;
    private String p2Name;
    private String p1Hp;
    private String p2Hp;
    private int p1Color;
    private int p2Color;
    private View v;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.v = inflater.inflate(R.layout.fragment_game2, container, false);
        this.setViews();

        if(savedInstanceState != null){
            this.setHP(1, Integer.parseInt(savedInstanceState.getString("HP1")));
            this.setHP(2, Integer.parseInt(savedInstanceState.getString("HP2")));
        }
        return v;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("HP1", ((TextView) v.findViewById(R.id.tvHP1)).getText().toString());
        outState.putString("HP2", ((TextView) v.findViewById(R.id.tvHP2)).getText().toString());
        super.onSaveInstanceState(outState);
    }

    /**
     * Sets the arguments for the View.
     * @param args, Bundle of arguments.
     */
    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        this.p1Name = args.getString("P1NAME");
        this.p2Name = args.getString("P2NAME");
        this.p1Color = args.getInt("P1COLOR");
        this.p2Color = args.getInt("P2COLOR");
        this.defaultHp = args.getString("HP1");
        this.p1Hp = args.getString("HP1");
        this.p2Hp = args.getString("HP2");

    }

    private void setViews(){
        this.v.findViewById(R.id.tvHP1).setZ(4);
        this.v.findViewById(R.id.tvHP2).setZ(4);
        this.v.findViewById(R.id.tvDieValue).setZ(4);
        ((TextView) this.v.findViewById(R.id.tvHP1)).setTextColor(this.p1Color);
        ((TextView) this.v.findViewById(R.id.tvHP2)).setTextColor(this.p2Color);
        ((TextView) this.v.findViewById(R.id.tvHP1)).setText(this.p1Hp);
        ((TextView) this.v.findViewById(R.id.tvHP2)).setText(this.p2Hp);
        ((TextView) this.v.findViewById(R.id.tvName1)).setText(this.p1Name);
        ((TextView) this.v.findViewById(R.id.tvName2)).setText(this.p2Name);
        this.v.findViewById(R.id.clPlayer1).setBackgroundColor(this.p1Color);
        this.v.findViewById(R.id.clPlayer2).setBackgroundColor(this.p2Color);
        this.v.findViewById(R.id.tvDieValue).setVisibility(View.INVISIBLE);
    }

    /**
     * Changes the layout to represent the Players health.
     * @param player, player number.
     * @param hp, the number to display.
     */
    public void setHP(int player, int hp){
        if(player == 1){
            ((TextView) v.findViewById(R.id.tvHP1)).setText(hp + "");
        }else{
            ((TextView) v.findViewById(R.id.tvHP2)).setText(hp + "");
        }
    }

    /**
     * Changes the layout to represent the Players poison.
     * @param player, player number.
     * @param poison, the number to display.
     */
    @Override
    public void setPoison(int player, int poison) {
        // Not implemented
    }

    /**
     * Changes the layout to represent the result of the roll.
     * @param i, the number to display.
     */
    public void setDieValue(int i){
        this.v.findViewById(R.id.tvDieValue).setVisibility(View.VISIBLE);
        ((TextView) this.v.findViewById(R.id.tvDieValue)).setText(i + "");
    }

    /**
     * Sets the coin value.
     * @param res, the value to represent.
     */
    public void setCoinValue(boolean res){
        this.v.findViewById(R.id.tvDieValue).setVisibility(View.VISIBLE);
        if(res){
            ((TextView) this.v.findViewById(R.id.tvDieValue)).setText("T");
        }else{
            ((TextView) this.v.findViewById(R.id.tvDieValue)).setText("H");
        }
    }

    /**
     * Resets the game.
     */
    @Override
    public void resetGame() {
        this.setViews();
    }

}

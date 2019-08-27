package taffer.statcounter.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import taffer.statcounter.R;

public class Game2Fragment extends Fragment implements StatCounter {
    private String defaultHp;
    private String p1Name;
    private String p2Name;
    private int p1Color;
    private int p2Color;
    private View v;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.v = inflater.inflate(R.layout.fragment_game2, container, false);
        this.setViews();
        return v;
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        this.p1Name = args.getString("P1NAME");
        this.p2Name = args.getString("P2NAME");
        this.p1Color = args.getInt("P1COLOR");
        this.p2Color = args.getInt("P2COLOR");
        this.defaultHp = args.getString("HP");
    }

    private void setViews(){
        this.v.findViewById(R.id.tvHP1).setZ(4);
        this.v.findViewById(R.id.tvHP2).setZ(4);
        this.v.findViewById(R.id.tvDieValue).setZ(4);
        ((TextView) this.v.findViewById(R.id.tvHP1)).setTextColor(this.p1Color);
        ((TextView) this.v.findViewById(R.id.tvHP2)).setTextColor(this.p2Color);
        ((TextView) this.v.findViewById(R.id.tvHP1)).setText(this.defaultHp);
        ((TextView) this.v.findViewById(R.id.tvHP2)).setText(this.defaultHp);
        ((TextView) this.v.findViewById(R.id.tvName1)).setText(this.p1Name);
        ((TextView) this.v.findViewById(R.id.tvName2)).setText(this.p2Name);
        this.v.findViewById(R.id.clPlayer1).setBackgroundColor(this.p1Color);
        this.v.findViewById(R.id.clPlayer2).setBackgroundColor(this.p2Color);
        this.v.findViewById(R.id.tvDieValue).setVisibility(View.INVISIBLE);
    }

    public void setHP(int player, int hp){
        if(player == 1){
            ((TextView) v.findViewById(R.id.tvHP1)).setText(hp + "");
        }else{
            ((TextView) v.findViewById(R.id.tvHP2)).setText(hp + "");
        }

    }


    public void setDieValue(int i){
        this.v.findViewById(R.id.tvDieValue).setVisibility(View.VISIBLE);
        ((TextView) this.v.findViewById(R.id.tvDieValue)).setText(i + "");
    }

    public void setCoinValue(boolean res){
        this.v.findViewById(R.id.tvDieValue).setVisibility(View.VISIBLE);
        if(res){
            ((TextView) this.v.findViewById(R.id.tvDieValue)).setText("T");
        }else{
            ((TextView) this.v.findViewById(R.id.tvDieValue)).setText("H");
        }
    }

    public void playDiceSound() {
        // TODO: Play sound
    }

    public void playCoinSound() {
        // TODO: Play sound
    }
}

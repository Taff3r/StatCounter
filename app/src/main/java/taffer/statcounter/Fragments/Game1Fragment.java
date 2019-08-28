package taffer.statcounter.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import taffer.statcounter.R;

public class Game1Fragment extends Fragment implements StatCounter{
    private String defaultHp;
    private String playerName;
    private int color;
    private View v;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.v = inflater.inflate(R.layout.fragment_game1, container, false);
        this.setViews();
        return v;
    }

    private void setViews(){
        ((TextView)v.findViewById(R.id.tvHP)).setText(this.defaultHp);
        ((TextView)v.findViewById(R.id.tvHP)).setTextColor(this.color);
        ((TextView)v.findViewById(R.id.tvName)).setText(this.playerName);
        v.findViewById(R.id.tvHP).setZ(4);
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        this.playerName = args.getString("NAME");
        this.defaultHp = args.getString("HP");
        this.color = args.getInt("COLOR");
    }

    public void setHP(int player, int hp){
        ((TextView) v.findViewById(R.id.tvHP)).setText(hp + "");
    }

    public void setPoison(int player, int poison){
        ((TextView) this.v.findViewById(R.id.tvPoison)).setText(poison + "");
    }

    public void setDieValue(int i){
        this.v.findViewById(R.id.tvDieValue).setVisibility(View.VISIBLE);
        ((TextView) this.v.findViewById(R.id.tvDieValue)).setText(i + "");
    }

    public void setCoinValue(boolean res){
        this.v.findViewById(R.id.tvDieValue).setVisibility(View.VISIBLE);
        this.v.findViewById(R.id.tvDieValue).setZ(4);
        if(res){
            ((TextView) this.v.findViewById(R.id.tvDieValue)).setText("TAILS");
        }else{
            ((TextView) this.v.findViewById(R.id.tvDieValue)).setText("HEADS");
        }
    }

    @Override
    public void resetGame() {
        this.setViews();
    }
}

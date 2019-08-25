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

public class Game2Fragment extends Fragment implements StatCounter {
    private String defaultHp;
    private String playerName;
    private int color;
    private View v;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.v = inflater.inflate(R.layout.fragment_game2, container, false);
        v.findViewById(R.id.tvHP).setZ(4);
        return v;
    }


    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
    }

    public void setHP(int player, int hp){
        ((TextView) v.findViewById(R.id.tvHP)).setText(hp + "");
    }


    public void setDieValue(int i){
        this.v.findViewById(R.id.tvDieValue).setVisibility(View.VISIBLE);
        ((TextView) this.v.findViewById(R.id.tvDieValue)).setText(i + "");
    }

    public void setCoinValue(boolean res){
        this.v.findViewById(R.id.tvDieValue).setVisibility(View.VISIBLE);
        this.v.findViewById(R.id.tvDieValue).setZ(4);
        if(res){
            ((TextView) this.v.findViewById(R.id.tvDieValue)).setText("T");
        }else{
            ((TextView) this.v.findViewById(R.id.tvDieValue)).setText("H");
        }
    }
}

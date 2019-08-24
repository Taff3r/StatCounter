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

public class Game1Fragment extends Fragment {

    private String defaultHp;
    private String playerName;
    private int color;
    private View v;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.v = inflater.inflate(R.layout.fragment_game1, container, false);
        ((TextView)v.findViewById(R.id.tvHP)).setText(this.defaultHp);
        ((TextView)v.findViewById(R.id.tvHP)).setTextColor(this.color);
        //((TextView)v.findViewById(R.id.tvName)).setText(this.playerName); // TODO: One player doesnt need name?
        v.findViewById(R.id.tvHP).setZ(4);
        return v;
    }


    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        this.playerName = args.getString("NAME");
        this.defaultHp = args.getString("HP");
        this.color = args.getInt("COLOR");
    }

    public void setHP(int hp){
        ((TextView) v.findViewById(R.id.tvHP)).setText(hp + "");
    }

    public void setDieValue(int i){
        // TODO: Hide coin if exists
        this.v.findViewById(R.id.ivDie).setVisibility(View.VISIBLE);
        this.v.findViewById(R.id.ivDie).setZ(2);
        this.v.findViewById(R.id.tvDieValue).setVisibility(View.VISIBLE);
        this.v.findViewById(R.id.tvDieValue).setZ(4);
        ((TextView) this.v.findViewById(R.id.tvDieValue)).setText(i + "");
        ((TextView) this.v.findViewById(R.id.tvDieValue)).setTextColor(Color.WHITE);
    }

    public void setCoinValue(int i){
        // TODO: Hide die if exists
        this.v.findViewById(R.id.ivDie).setVisibility(View.VISIBLE);
        this.v.findViewById(R.id.tvDieValue).setVisibility(View.VISIBLE);
        this.v.findViewById(R.id.tvDieValue).setVisibility(View.VISIBLE);
    }
}

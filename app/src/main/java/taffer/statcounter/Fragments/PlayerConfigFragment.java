package taffer.statcounter.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import taffer.statcounter.R;

public class PlayerConfigFragment extends Fragment {
    private int player = 1;

    /**
     * Sets the arguments
     * @param args
     */
    @Override
    public void setArguments(@Nullable Bundle args) {
        if(args != null){
            this.player = args.getInt("PLAYER");
        }
        super.setArguments(args);
    }

    /**
     * Creates the Fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return View, the view.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(this.player == 1){
            return inflater.inflate(R.layout.fragment_1player_layout, container, false);
        }else{
            return inflater.inflate(R.layout.fragment_2player_layout, container, false);
        }

    }
}

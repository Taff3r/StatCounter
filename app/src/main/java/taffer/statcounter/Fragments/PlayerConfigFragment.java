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
    private int noOfPlayers = 1;
    @Override
    public void setArguments(@Nullable Bundle args) {
        if(args != null){
            this.noOfPlayers= args.getInt("#PLAYERS");
        }
        super.setArguments(args);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("#PLAYERS", this.noOfPlayers + "");
        if(this.noOfPlayers == 1){
            return inflater.inflate(R.layout.fragment_1player_layout, container, false);
        }else{
            return inflater.inflate(R.layout.fragment_1player_layout, container, false); // TODO: Change to 2player
        }

    }
}

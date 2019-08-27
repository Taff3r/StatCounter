package taffer.statcounter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import taffer.statcounter.Fragments.GamemodeFragment;
import taffer.statcounter.Fragments.PlayerConfigFragment;
import taffer.statcounter.Fragments.PlayerFragment;
import taffer.statcounter.Model.GameBuilder;

public class BuilderActivity extends AppCompatActivity {
    private GameBuilder gb;
    private int step = 0;
    private Toast toast;

    // TODO: Make all Activities replace instead of stack.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build);
        Toolbar toolbar = findViewById(R.id.bar);
        setSupportActionBar(toolbar);
        if(savedInstanceState != null){
            this.gb = (GameBuilder) savedInstanceState.get("BUILDER");
            this.step = savedInstanceState.getInt("STEP");
        }else{
            this.gb = new GameBuilder();
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_frame, new PlayerFragment()).commit();

        // TODO: ADD COOL TRANSITION !!
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("BUILDER", this.gb);
        outState.putInt("STEP", this.step);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);
        return true;
    }

    public void nextStage(View v){
        int initialStep = this.step;
        switch(this.step){
            case 0:
                this.step += this.chosePlayerCount();
                break;
            case 1:
                this.step += this.setGameMode();
                break;
            case 2:
                this.step += this.addPlayer();
                break;
            case 3:
                this.step += this.addPlayer();
                break;
            case 4:
                this.step += this.confirm();
                break;
        }

        if(this.step != initialStep){
            this.changeStep();
        }
    }

    private int chosePlayerCount(){
        RadioGroup rGroup = findViewById(R.id.rGroupPlayers);
        int id = rGroup.getCheckedRadioButtonId();

        if(id == -1){
            displayToast("Please select one of the options.");
            return 0;
        } else {
            if(id == R.id.rBtnOne) {
                this.gb = gb.setNoOfPlayers(1);
            }else{
                this.gb = gb.setNoOfPlayers(2);
            }
            return 1;
        }
    }

    private int confirm(){
        return 0;
    }

    private int setGameMode(){
        Spinner spinner = findViewById(R.id.spinner);
        String selected = spinner.getSelectedItem().toString();
        this.gb.setGameMode(selected);
        return 1;
    }

    private int addPlayer(){
        ImageView selected = findViewById(R.id.ivSelected);
        EditText et = findViewById(R.id.etName);
        String name = "";
        if(et.getText() == null) {
            if(this.step == 2){
                name = "Player 1";
            }else{
                name = "Player 2";
            }
        }else{
            name = et.getText().toString();
        }

        if(selected.getVisibility() == View.INVISIBLE){
            displayToast("Please pick a color");
            return 0;
        }

        if(this.gb.getNoOfPlayers() == 2){
            this.gb.addPlayer(name, selected.getImageTintList().getDefaultColor());
            return 1;
        }else{
            this.gb.addPlayer(name, selected.getImageTintList().getDefaultColor());
            return 2;
        }

    }

    private void changeStep(){
        FragmentTransaction ft;
        switch (this.step){
            case 0:
                ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.fragment_frame, new PlayerFragment()).commit();
                break;
            case 1:
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_frame, new GamemodeFragment()).commit();
                break;
            case 2:
                ft = getSupportFragmentManager().beginTransaction();
                Bundle b = new Bundle();
                b.putInt("PLAYER", 1);
                Fragment f = new PlayerConfigFragment();
                f.setArguments(b);
                ft.replace(R.id.fragment_frame, f).commit();
                break;
            case 3:
               ft = getSupportFragmentManager().beginTransaction();
               Bundle bun = new Bundle();
               Fragment fragment = new PlayerConfigFragment();
               fragment.setArguments(bun);
               ft.replace(R.id.fragment_frame, fragment).commit();
               break;
            case 4:
                // TODO: Add confirmation Fragment
                Intent i = new Intent(this, GameActivity.class);
                i.putExtra("GAME", this.gb.build());
                startActivity(i);
                break;
        }
    }

    private void displayToast(String s){
            if(this.toast != null) {
                this.toast.cancel();
            }
            this.toast = Toast.makeText(this, s, Toast.LENGTH_LONG);
            this.toast.show();
    }

    public void onColorClick(View v){
        ImageView clicked = (ImageView) v;
        ImageView display = findViewById(R.id.ivSelected);
        display.setVisibility(View.VISIBLE);
        display.setImageTintList(clicked.getImageTintList());
    }

    @Override
    public void onBackPressed() {
        if(this.step == 0){
            return; // TODO: Replace this activity with start menu
        }else if(this.step==4 && this.gb.getNoOfPlayers() == 1){
           this.step = 2;
           changeStep();
           return;
        }
        this.step--;
        changeStep();
    }
}

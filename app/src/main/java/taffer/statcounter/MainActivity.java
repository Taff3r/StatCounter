package taffer.statcounter;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import taffer.statcounter.Fragments.GamemodeFragment;
import taffer.statcounter.Fragments.PlayerFragment;
import taffer.statcounter.Model.GameBuilder;

public class MainActivity extends AppCompatActivity {
    private GameBuilder gb;
    private int step = 0;
    private ActionBar actionBar;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            this.gb = (GameBuilder) savedInstanceState.get("BUILDER");
        }else{
            this.gb = new GameBuilder();
        }
        this.actionBar = getSupportActionBar();
        this.actionBar.hide();
        //this.actionBar.setShowHideAnimationEnabled(false);

        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_frame, new PlayerFragment()).commit();
        //this.disableShowHideAnimation(this.actionBar);
        // TODO: ADD COOL TRANSITION !!
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("BUILDER", this.gb);
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
                this.step = this.chosePlayerCount();
                break;
        }

        Log.d("Ini, curr", initialStep + " | " + this.step);
        if(this.step != initialStep){
            this.changeStep();
        }
    }

    private int chosePlayerCount(){
        RadioGroup rGroup = findViewById(R.id.rGroupPlayers);
        int id = rGroup.getCheckedRadioButtonId();

        Log.e("ID", id + "");
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
    private void changeStep(){
        FragmentTransaction ft;
        switch (this.step){
            case 0:
                 ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.fragment_frame, new PlayerFragment()).commit();
                break;
            case 1:
                ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.fragment_frame, new GamemodeFragment()).commit();
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btnHide) {
            getSupportActionBar().hide();
            findViewById(R.id.floatingActionButton2).setVisibility(View.VISIBLE);

            //Handle the onClick with Toast over here.
            //IF you have multiple options then replace if with a switch() statement
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void showBar(View v){
        findViewById(R.id.floatingActionButton2).setVisibility(View.INVISIBLE);
        this.actionBar.show();
    }

    @Override
    public void onBackPressed() {
        if(this.step == 0){
            return;
        }
        this.step--;
        changeStep();
    }
}

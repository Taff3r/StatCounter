package taffer.statcounter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import taffer.statcounter.Fragments.Game1Fragment;
import taffer.statcounter.Model.Detector;
import taffer.statcounter.Model.Game;
import taffer.statcounter.Model.OrientationDetector;
import taffer.statcounter.Model.ShakeDetector;

public class GameActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SensorEventListener {
    private DrawerLayout drawer;
    private Game game;
    private int players;
    private Game1Fragment fGame; // TODO: MAKE A GAMEFRAGMENT INTERFACE
    private Detector shakeDetector;
    private Detector orientationDetector;
    private SensorManager sMan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        try{
            Bundle b = getIntent().getExtras();
            this.game = (Game) b.get("GAME");
        } catch(Exception e){
            // Ignore Exception
            Log.e("SHIT", "FUCKY WUCKY");
        }
        Toolbar toolbar = findViewById(R.id.bar);
        toolbar.setBackgroundColor(this.game.getPlayerColor(1));
        toolbar.setTitle("");
        //((LinearLayout) findViewById(R.id.nav_view).getLa).setBackgroundColor(this.game.getPlayerColor(1)); // TODO: Maybe change color of nav_header
        setSupportActionBar(toolbar);
        this.players = this.game.noOfPlayers();
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(this.players == 1){
            FrameLayout layout = findViewById(R.id.game_container);
            layout.setBackgroundColor(this.game.getPlayerColor(1));
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Game1Fragment f = new Game1Fragment();
            this.fGame = f;
            Bundle b = new Bundle();
            b.putString("HP",this.game.getPlayerHealth(1) + "");
            b.putString("NAME",this.game.getPlayerName(1) + "");
            b.putInt("COLOR",this.game.getPlayerColor(1));
            f.setArguments(b);
            ft.replace(R.id.game_container,f).commit();

        }else{
            // TODO: 2 players
        }
        registerSensors();

    }


    private void registerSensors(){
        this.sMan = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        this.sMan.registerListener(this, sMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        this.shakeDetector = new ShakeDetector();
        this.orientationDetector = new OrientationDetector(OrientationDetector.UPSIDEDOWN, 1);
    }



    public void changeHP(View v){
        if(game.noOfPlayers() == 1){
            switch (v.getId()){
                case R.id.fabMinus1:
                    this.game.addPoints(1, -1);
                    break;
                case R.id.fabMinus5:
                    this.game.addPoints(1, -5);
                    break;
                case R.id.fabPlus1:
                    this.game.addPoints(1, 1);
                    break;

                    case R.id.fabPlus5:
                    this.game.addPoints(1, 5);
                    break;
            }
        }else{
            // TODO: Two players
        }

        this.fGame.setHP(this.game.getPlayerHealth(1));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // TODO: CHANGE TOOLTIP TO A RANDOM
        ((TextView) findViewById(R.id.toolTip)).setText(getResources().getStringArray(R.array.toolTips)[0]);
        ActionBar actionBar = getSupportActionBar();
        switch (item.getItemId()) {
            // INSERT ITEMS HERE
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

   @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.e("EVENT", sensorEvent.toString());
        if(orientationDetector.detectEvent(sensorEvent) == Detector.SUCCESS){
            Log.e("FLIPPED", "FLIPPIN FLIP!");
            // TODO: Flip coin!
            boolean res = this.game.flipCoin();
            this.fGame.setCoinValue(res);
        }else if(shakeDetector.detectEvent(sensorEvent) == Detector.SUCCESS){
            // TODO: Throw dice!
            int res = this.game.rollDie();
            this.fGame.setDieValue(res);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // hush....
    }
}

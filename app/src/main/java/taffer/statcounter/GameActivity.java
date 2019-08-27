package taffer.statcounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
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
import taffer.statcounter.Fragments.Game2Fragment;
import taffer.statcounter.Fragments.StatCounter;
import taffer.statcounter.Model.Detector;
import taffer.statcounter.Model.Game;
import taffer.statcounter.Model.GameBuilder;
import taffer.statcounter.Model.OrientationDetector;
import taffer.statcounter.Model.ShakeDetector;

public class GameActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SensorEventListener, DrawerLayout.DrawerListener {
    private DrawerLayout drawer;
    private Game game;
    private int players;
    private StatCounter fGame;
    private Detector shakeDetector;
    private Detector orientationDetector;
    private SensorManager sMan;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        try{
            Bundle b = getIntent().getExtras();
            this.game = (Game) b.get("GAME");
        } catch(Exception e){
            // Ignore Exception
            Log.e("THIS ", "SHOULDN'T HAPPEN");
        }
        Toolbar toolbar = findViewById(R.id.bar);
        this.players = this.game.noOfPlayers();
        if(this.players == 1){
            toolbar.setBackgroundColor(this.game.getPlayerColor(1));
        }else{
            toolbar.setBackgroundColor(this.game.getPlayerColor(2));
        }
        toolbar.setTitle("");
        //((LinearLayout) findViewById(R.id.nav_view).getLa).setBackgroundColor(this.game.getPlayerColor(1)); // TODO: Maybe change color of nav_header
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer.addDrawerListener(this);
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
            FrameLayout layout = findViewById(R.id.game_container);
            layout.setBackgroundColor(Color.BLACK);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Game2Fragment f = new Game2Fragment();
            this.fGame = f;
            Bundle b = new Bundle();
            b.putString("HP",this.game.getPlayerHealth(1) + "");
            b.putString("P1NAME",this.game.getPlayerName(1) + "");
            b.putString("P2NAME",this.game.getPlayerName(2) + "");
            b.putInt("P1COLOR",this.game.getPlayerColor(1));
            b.putInt("P2COLOR",this.game.getPlayerColor(2));
            f.setArguments(b);
            ft.replace(R.id.game_container,f).commit();
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
            this.fGame.setHP(1, this.game.getPlayerHealth(1));
        }else{
            switch (v.getId()) {
                case R.id.fabMinus1P1:
                    this.game.addPoints(1, -1);
                    break;
                case R.id.fabMinus5P1:
                    this.game.addPoints(1, -5);
                    break;
                case R.id.fabPlus1P1:
                    this.game.addPoints(1, 1);
                    break;
                case R.id.fabPlus5P1:
                    this.game.addPoints(1, 5);
                    break;
                case R.id.fabPlus5P2:
                    this.game.addPoints(2, 5);
                    break;
                case R.id.fabPlus1P2:
                    this.game.addPoints(2, 1);
                    break;
                case R.id.fabMinus1P2:
                    this.game.addPoints(2, -1);
                    break;
                case R.id.fabMinus5P2:
                    this.game.addPoints(2, -5);
                    break;
            }
            this.fGame.setHP(1, this.game.getPlayerHealth(1));
            this.fGame.setHP(2, this.game.getPlayerHealth(2));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        ActionBar actionBar = getSupportActionBar();
        switch (item.getItemId()) {
            case R.id.nav_reset:
                this.resetGame();
                break;
            case R.id.nav_newGame:
                Intent i = new Intent(this, BuilderActivity.class);
                startActivity(i);
                this.finish();
                break;

            case R.id.nav_quick:
                SharedPreferences sp = getApplicationContext().getSharedPreferences(getString(R.string.prefKey), Context.MODE_PRIVATE);
                int p1Color = sp.getInt("def_p1Color", Color.WHITE);
                String mode = sp.getString("def_mode", "Constructed");
                String p1Name = sp.getString("def_p1Name", "");

                Game g = new GameBuilder().setNoOfPlayers(1).setGameMode(mode).addPlayer(p1Name, p1Color).build();
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("GAME", g);
                startActivity(intent);
                this.finish();
                break;
            case R.id.nav_quick2:
                SharedPreferences sp2 = getApplicationContext().getSharedPreferences(getString(R.string.prefKey), Context.MODE_PRIVATE);
                int p1Col = sp2.getInt("def_p1Color", Color.WHITE);
                int p2Col = sp2.getInt("def_p2Color", Color.GRAY);
                String m = sp2.getString("def_mode", "Constructed");
                String p1N = sp2.getString("def_p1Name", "");
                String p2N = sp2.getString("def_p2Name", "");

                Game game = new GameBuilder().setNoOfPlayers(2).setGameMode(m).addPlayer(p1N, p1Col).addPlayer(p2N, p2Col).build();
                Intent init = new Intent(this, GameActivity.class);
                init.putExtra("GAME", game);
                startActivity(init);
                this.finish();
                break;
            case R.id.nav_settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                this.finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void resetGame(){
        this.game.resetGame();
        if(this.players == 2){
            this.fGame.setHP(2, this.game.defaultHP());
        }
        this.fGame.setHP(1, this.game.defaultHP());
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
        if(orientationDetector.detectEvent(sensorEvent) == Detector.SUCCESS){
            boolean res = this.game.flipCoin();
            this.fGame.setCoinValue(res);
            this.playCoinSound();
        }else if(shakeDetector.detectEvent(sensorEvent) == Detector.SUCCESS){
            int res = this.game.rollDie();
            this.fGame.setDieValue(res);
            this.playDiceSound();
        }
    }

    private void playDiceSound(){
        if(this.mp == null){
            this.mp = MediaPlayer.create(this, R.raw.dice);
            mp.start();
        }else{
            this.mp.stop();
            this.mp = MediaPlayer.create(this, R.raw.dice);
            mp.start();
        }
    }

    private void playCoinSound(){
        if(this.mp == null){
            this.mp = MediaPlayer.create(this, R.raw.coin);
            mp.start();
        }else{
            this.mp.stop();
            this.mp = MediaPlayer.create(this, R.raw.coin);
            mp.start();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // hush....
    }


    @Override
    public void onDrawerSlide(@NonNull View view, float v) {
        // Do nothing
    }

    @Override
    public void onDrawerOpened(@NonNull View view) {
        String[] tooltips = getResources().getStringArray(R.array.toolTips);
        TextView tv = findViewById(R.id.toolTip);

        if(tv.getText().toString().equals(tooltips[0])){
            tv.setText(tooltips[1]);
        }else{
            tv.setText(tooltips[0]);
        }
    }

    @Override
    public void onDrawerClosed(@NonNull View view) {
        // Do nothing
    }

    @Override
    public void onDrawerStateChanged(int i) {
        // Do nothing
    }
}

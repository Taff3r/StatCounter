package taffer.statcounter;

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
import android.widget.FrameLayout;
import android.widget.TextView;

import taffer.statcounter.Fragments.Game1Fragment;
import taffer.statcounter.Model.Game;

public class GameActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Game game;
    private int players;
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
            Fragment f = new Game1Fragment();
            Bundle b = new Bundle();
            b.putString("HP",this.game.getPlayerHealth(1) + "");
            b.putString("HP",this.game.getPlayerName(1) + "");
            f.setArguments(b);
            ft.replace(R.id.game_container,f).commit();

        }else{
            // TODO: 2 players
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            super.onBackPressed();
        }
    }
}

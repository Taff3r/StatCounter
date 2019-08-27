package taffer.statcounter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;

import taffer.statcounter.Model.Game;
import taffer.statcounter.Model.GameBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = (ImageView) findViewById(R.id.ivLogo);

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(
                iv,
                PropertyValuesHolder.ofFloat("scaleX", 1.1f),
                PropertyValuesHolder.ofFloat("scaleY", 1.1f));
        animator.setDuration(500);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);

        ObjectAnimator imageViewObjectAnimator = ObjectAnimator.ofFloat(iv ,
                "rotation", 0f, 360f);
        imageViewObjectAnimator.setDuration(5000);
        imageViewObjectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        imageViewObjectAnimator.setRepeatMode(ObjectAnimator.RESTART);
        // Starts animations
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator,imageViewObjectAnimator);

        animatorSet.start();

    }

    public void newGame(View v){
        Intent i = new Intent(this, BuilderActivity.class);
        startActivity(i);
    }

    public void quickGame(View v){
        // TODO: ADD DEFAULT 1 AND 2 PLAYER NAMES AND COLORS FOR QUICK GAME
        SharedPreferences sp = getApplicationContext().getSharedPreferences(getString(R.string.prefKey), Context.MODE_PRIVATE);
        int p1Color = sp.getInt("def_p1Color", Color.WHITE);
        String mode = sp.getString("def_mode", "Constructed");
        String p1Name = sp.getString("def_p1Name", "");

        Game g = new GameBuilder().setNoOfPlayers(1).setGameMode(mode).addPlayer(p1Name, p1Color).build();
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("GAME", g);
        startActivity(i);
        this.finish();
    }

    public void quick2PlayerGame(View v){
        // TODO: ADD DEFAULT SETTINGS FOR QUICK GAME
        SharedPreferences sp = getApplicationContext().getSharedPreferences(getString(R.string.prefKey), Context.MODE_PRIVATE);
        int p1Color = sp.getInt("def_p1Color", Color.WHITE);
        int p2Color = sp.getInt("def_p2Color", Color.GRAY);
        String mode = sp.getString("def_mode", "Constructed");
        String p1Name = sp.getString("def_p1Name", "");
        String p2Name = sp.getString("def_p2Name", "");

        Game g = new GameBuilder().setNoOfPlayers(2).setGameMode(mode).addPlayer(p1Name, p1Color).addPlayer(p2Name, p2Color).build();
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("GAME", g);
        startActivity(i);
        this.finish();
    }

    public void settings(View v){
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
        this.finish();
    }
}

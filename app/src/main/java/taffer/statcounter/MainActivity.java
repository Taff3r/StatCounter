package taffer.statcounter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
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
        Game g = new GameBuilder().setNoOfPlayers(1).setGameMode("Constructed").addPlayer("Player 1", "someColor").build();
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("GAME", g);
        startActivity(i);
    }

    public void quick2PlayerGame(View v){
        // TODO: ADD DEFAULT SETTINGS FOR QUICK GAME
        Game g = new GameBuilder().setNoOfPlayers(2).setGameMode("Constructed").addPlayer("Player 1", "someColor").addPlayer("Player 2", "anotherColor").build();
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("GAME", g);
        startActivity(i);
    }
}

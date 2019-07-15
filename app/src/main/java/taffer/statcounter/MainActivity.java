package taffer.statcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private GameBuilder gb;
    private int step = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            this.gb = (GameBuilder) savedInstanceState.get("BUILDER");
        }else{
            this.gb = new GameBuilder();
        }
        setContentView(R.layout.activity_main);
        // TODO: ADD COOL TRANSITION !! setContentTransitionManager();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("BUILDER", this.gb);
        super.onSaveInstanceState(outState);
    }

    public void nextStage(View v){
        this.step++;
        setLayout();
    }

    private void setLayout(){
        switch (this.step){
            case 1:
                this.setContentView(R.layout.builder_layout_1);
                break;
            case 2:
                this.setContentView(R.layout.builder_layout_2);
                break;
            case 3:
                this.setContentView(R.layout.builder_layout_3);
                break;
                default:
                    this.setContentView(R.layout.activity_main);
                    break;
        }
    }


}

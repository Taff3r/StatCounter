package taffer.statcounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.sp = getApplicationContext().getSharedPreferences(getString(R.string.prefKey), Context.MODE_PRIVATE);
        this.editor = sp.edit();
    }

    public void setDefaultMode(View v){
        String text = ""; // TODO
        this.editor.putString("SETTINGS_MODE", text);
    }
}

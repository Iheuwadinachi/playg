package nl.saxion.playground.template;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import nl.saxion.playground.template.platformer.Activity;

public class SettingActivity extends AppCompatActivity {
    private TextView settingsTextView, soundTextView, musicTextView;

    private Switch soundSwitch,musicSwitch, themeSwitch;
    private Button btnQuitGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);

        soundSwitch = findViewById(R.id.soundSwitch);

        musicSwitch = findViewById(R.id.musicSwitch);

        themeSwitch = findViewById(R.id.themeSwitch);

        btnQuitGame = findViewById(R.id.quitButton);


        btnQuitGame.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                finishActivity(12);
                System.exit(115);


            }
        });
    }
}

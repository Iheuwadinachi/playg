package nl.saxion.playground.template;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.lib.GameModel;
import nl.saxion.playground.template.spaceshooter.Game;
import nl.saxion.playground.template.spaceshooter.Activity;
import nl.saxion.playground.template.spaceshooter.SettingsMenu;

public class MainActivity extends AppCompatActivity {

    GameView spaceShooterCanvas, platformerCanvas,lumberjackCanvas;
    GameModel spaceShooter, platformer, lumberjack;
    Button settingBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spaceShooterCanvas = findViewById(R.id.spaceShooter);
        spaceShooter = new Game();

        settingBtn= findViewById(R.id.Settingbutton);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(MainActivity.this,SettingActivity.class);
             startActivity(intent);
            }
        });


        findViewById(R.id.spaceShooterText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Activity.class));
            }
        });

        lumberjackCanvas = findViewById(R.id.platformer);
        lumberjack = new nl.saxion.playground.template.lumberjack_simulator.Game();


        startActivity(new Intent(MainActivity.this, nl.saxion.playground.template.lumberjack_simulator.Activity.class));

        platformer = new nl.saxion.playground.template.platformer.Game();


    }

    @Override
    protected void onResume() {
        super.onResume();
        spaceShooterCanvas.setGame(spaceShooter);
        lumberjackCanvas.setGame(lumberjack);
    }

    @Override
    protected void onPause() {
        super.onPause();
        spaceShooterCanvas.setGame(null);
        lumberjackCanvas.setGame(null);
    }

}

package nl.saxion.playground.template;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.lib.GameModel;
import nl.saxion.playground.template.lumberjack_simulator.Activity;
import nl.saxion.playground.template.lumberjack_simulator.Game;

public class MainActivity extends AppCompatActivity {
    // this is a test

    GameView lumberjackSimulatorCanvas;
    GameModel lumberjackSimulator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lumberjackSimulatorCanvas = findViewById(R.id.lumberjackSimulator);
        lumberjackSimulator = new Game();

        findViewById(R.id.lumberjackText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Activity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        lumberjackSimulatorCanvas.setGame(lumberjackSimulator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lumberjackSimulatorCanvas.setGame(null);
    }
}

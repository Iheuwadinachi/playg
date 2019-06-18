package nl.saxion.playground.template;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.lib.GameModel;
import nl.saxion.playground.template.lumberjack_simulator.Activity;
import nl.saxion.playground.template.lumberjack_simulator.sound_lib.MusicPlayer;


// Teacher: This class has to be removed from master branch.
// Michael: Rewrote this class so that it now just runs our app
public class MainActivity extends AppCompatActivity {

  GameView lumberjackCanvas;
  GameModel lumberjack;
  MusicPlayer musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lumberjackCanvas = findViewById(R.id.platformer);
        lumberjack = new nl.saxion.playground.template.lumberjack_simulator.Game();
        musicPlayer = new MusicPlayer(MainActivity.this, R.raw.piano1);
        musicPlayer.playMusic(this, R.raw.piano1);


        startActivity(new Intent(MainActivity.this, Activity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        lumberjackCanvas.setGame(lumberjack);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lumberjackCanvas.setGame(null);
    }
}

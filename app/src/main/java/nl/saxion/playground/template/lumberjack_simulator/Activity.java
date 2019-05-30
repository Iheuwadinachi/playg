package nl.saxion.playground.template.lumberjack_simulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.GameView;

public class Activity extends AppCompatActivity {

    Game game;
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lumberjack);
        gameView = findViewById(R.id.gameViewLumberjack);

        if (savedInstanceState != null && savedInstanceState.containsKey("game")){
            game = (Game) savedInstanceState.getSerializable("game");
        }else{
            game = new Game();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("game", game);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.setGame(game);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.setGame(null);
    }
}

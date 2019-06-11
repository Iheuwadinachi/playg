package nl.saxion.playground.template.lumberjack_simulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.GameView;

public class Activity extends AppCompatActivity {

    private Game game;
    private GameView gameView;
    private TextView coinIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // In this example, we don't require a Layout or any other Android Views than
        // are custom GameCanvas.
        setContentView(R.layout.activity_);

        gameView = findViewById(R.id.lumberView);

//        gameView = findViewById(R.id.lumberjack);
//
        coinIndicator = findViewById(R.id.coins);



        // If a running game has been serialized (because it has been paused for
        // a long time, or because of an orientation change), recreate the Game
        // object from the serialized bundle.
        if (savedInstanceState!=null && savedInstanceState.containsKey("game")) {
            game = (nl.saxion.playground.template.lumberjack_simulator.Game)savedInstanceState.getSerializable("game");
        } else {
            game = new Game();
        }

        //coinIndicator.setText("Coins: " + game.coinsEarned);
        game.setGameActivity(this);
    }

    void setTextIndicator(int coins){
        coinIndicator.setText("Coins: " + coins);
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

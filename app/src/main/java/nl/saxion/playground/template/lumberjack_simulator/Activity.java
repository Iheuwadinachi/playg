package nl.saxion.playground.template.lumberjack_simulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.lumberjack_simulator.data_storage.Constants;
import nl.saxion.playground.template.lumberjack_simulator.data_storage.DataWrapper;
import nl.saxion.playground.template.lumberjack_simulator.data_storage.JsonHandler;
import nl.saxion.playground.template.lumberjack_simulator.data_storage.Save;
import nl.saxion.playground.template.lumberjack_simulator.store.BuyView;

public class Activity extends AppCompatActivity {
//teacher:
    private Game game;
    private GameView gameView;
    private TextView coinIndicator;
    BuyView buyView;
    private JsonHandler jsonHandler;

    private static int REMOVE_ME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jsonHandler = new JsonHandler(getApplicationContext());
        // In this example, we don't require a Layout or any other Android Views than
        // are custom GameCanvas.
        setContentView(R.layout.activity_);



        gameView = findViewById(R.id.lumberView33);

        buyView = findViewById(R.id.buy_view);

        coinIndicator = findViewById(R.id.coins);

        // If a running game has been serialized (because it has been paused for
        // a long time, or because of an orientation change), recreate the Game
        // object from the serialized bundle.
//        if (savedInstanceState!=null && savedInstanceState.containsKey("game")) {
//            game = (nl.saxion.playground.template.lumberjack_simulator.Game)savedInstanceState.
//                    getSerializable("game");
//            game.setGameActivity(this);
//        } else {
//            game = new Game(this);
//        }

        game = new Game(this);

        DataWrapper dataWrapper = new DataWrapper();
        dataWrapper = dataWrapper.getInstance();

        int coins = dataWrapper.getCoins();
        game.setCoinsEarned(coins);


        buyView.transparent("invisible");
        buyView.setGame(game);

        game.setGameActivity(this);
        gameView.setGame(game);
    }

    void setPrices(){
        buyView.setPrices(Constants.prices);
    }

    void setTextIndicator(int coins){
        coinIndicator.setText("Coins: " + coins);
    }

    public void onClick(View v) {
        if(REMOVE_ME % 2 == 0){
            buyView.transparent("visible");
        } else {
            buyView.transparent("gone");
        }
        REMOVE_ME++;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.setGame(game);
    }

    @Override
    protected void onPause() {
        save();
        gameView.setGame(null);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        save();
        gameView.setGame(null);
        finish();
    }

    private void save(){
        Save save = new Save();
        save = save.getInstance();
        save.setPrices(buyView.getPrices());
        jsonHandler.saveConstants();
    }
}

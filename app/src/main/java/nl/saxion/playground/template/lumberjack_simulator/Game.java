package nl.saxion.playground.template.lumberjack_simulator;

import android.util.Log;

import nl.saxion.playground.template.lib.GameModel;

public class Game extends GameModel {

    boolean[] treeChopped;
    int coinsEarned;

    Activity thatActivity;

    @Override
    public void start() {

        treeChopped = new boolean[2];

        addEntity(new Background(this));
        addEntity(new TreeGenerator(this));
        addEntity(new MoneyGenerator(this));
        addEntity(new Lumberjack(this));
        Log.i("extra","game Width: " + getWidth() + "f, game Height: " + getHeight() + "f.");
    }

    void updateTextView(){
        coinsEarned++;
        if(thatActivity != null) {
            thatActivity.coinIndicator.setText("Coins: " + coinsEarned);
        }
    }

    @Override
    public float getWidth() {
        // Virtual screen should be at least 100 wide and 100 high.
        return 100f * actualWidth / Math.min(actualWidth,actualHeight);
    }

    @Override
    public float getHeight() {
        // Virtual screen should be at least 100 wide and 100 high.
        return 100f * actualHeight / Math.min(actualWidth,actualHeight);
    }

}

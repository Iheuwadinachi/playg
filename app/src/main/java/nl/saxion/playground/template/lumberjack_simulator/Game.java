package nl.saxion.playground.template.lumberjack_simulator;

import android.util.Log;

import nl.saxion.playground.template.lib.GameModel;

public class Game extends GameModel {

    private boolean[] treeChopped;
    private int coinsEarned;

    private Activity gameActivity;

    @Override
    public void start() {

        treeChopped = new boolean[2];

        addEntity(new Background(this));
        addEntity(new TreeGenerator(this));
        addEntity(new CoinGenerator(this));
        addEntity(new Lumberjack(this));
        Log.d("extra","game Width: " + getWidth() + "f, game Height: " + getHeight() + "f.");
    }

    void setTreeChopped(boolean chopped){
        for (int i = 0; i < treeChopped.length; i++) {
            treeChopped[i] = chopped;
        }
    }

    void setTreeChopped(boolean chopped, Object o){
        if(o instanceof Lumberjack){
            treeChopped[0] = chopped;
        } else if(o instanceof CoinGenerator){
            treeChopped[1] = chopped;
        }
    }

    void setGameActivity(Activity activity){
        gameActivity = activity;
    }

    boolean ifTreeChopped(Object o){
        if(o instanceof Lumberjack){
            return treeChopped[0];
        } else if(o instanceof CoinGenerator){
            return treeChopped[1];
        } else return false;
    }

    void updateTextView(){
        coinsEarned++;
        if(gameActivity != null) {
            gameActivity.setTextIndicator(coinsEarned);
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

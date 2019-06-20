package nl.saxion.playground.template.lumberjack_simulator;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameModel;
import nl.saxion.playground.template.lumberjack_simulator.data_storage.Constants;

// teacher: This class has only default constructor which means game object can be created anywhere
// in the app which is dangerous. gameActvitiy might remain null in this case leading
// to nullpointer exception.
public class Game extends GameModel {

    private int coinsEarned;

    private Activity gameActivity;

    private Background background;
    private TreeGenerator treeGenerator;
    private CoinGenerator coinGenerator;
    private Lumberjack lumberjack;


    private Map<Entity,Boolean> treeChopped;

    public Game(Activity gameActivity){
        this.gameActivity = gameActivity;
    }

    @Override
    public void start() {
        treeChopped = new HashMap<>();

        background = new Background(this);
        addEntity(background);

        treeGenerator = new TreeGenerator(this);
        addEntity(treeGenerator);

        coinGenerator = new CoinGenerator(this);
        addEntity(coinGenerator);

        lumberjack = new Lumberjack(this);
        addEntity(lumberjack);

        treeChopped.put(treeGenerator,false);
        treeChopped.put(coinGenerator,false);
        treeChopped.put(lumberjack,false);

        gameActivity.setPrices();

        Log.d("extra","game Width: " + getWidth() + "f, game Height: " + getHeight() + "f.");
    }

    void setTreeChopped(boolean chopped){
        for (Entity entity : treeChopped.keySet()){
            treeChopped.put(entity,chopped);
        }
    }

    void setTreeChopped(boolean chopped, Entity entity){
        treeChopped.put(entity,chopped);
    }

    void setGameActivity(Activity activity){
        gameActivity = activity;
    }

    public void setCoinsEarned(int coinsEarned) {
        this.coinsEarned = coinsEarned - 1;

        updateTextView();
    }

// Teacher: using object class and type checking is not the best wy to do it. Secondly, never use
//  0 as object name since it is hard to distinguish it from o.

    //RESOLVED

    boolean ifTreeChopped(Entity entity){
        try {
            return treeChopped.get(entity);
        } catch (NullPointerException e){
            Log.d("extra_info","Entity non in HashMap");
            e.printStackTrace();
            return false;
        }
    }

    void updateTextView(){
        coinsEarned++;

        if(gameActivity != null) {
            gameActivity.setTextIndicator(coinsEarned);
        }
    }

    public int getCoinsEarned(){
        return coinsEarned;
    }

    void addCoinToSpawn(){
        coinGenerator.setNUMBER_OF_COINS(coinGenerator.getNUMBER_OF_COINS()+1);
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

    public Activity getGameActivity(){
        return  gameActivity;
    }



}

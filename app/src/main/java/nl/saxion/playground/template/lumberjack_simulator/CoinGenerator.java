package nl.saxion.playground.template.lumberjack_simulator;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nl.saxion.playground.template.lumberjack_simulator.global.GlobalApplication;
import nl.saxion.playground.template.lumberjack_simulator.local_lib.Vector;

import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameModel;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.lumberjack_simulator.sound_lib.SoundEffects;

public class CoinGenerator extends Entity {

    private Game game;
    private SoundEffects soundEffects;

    private int NUMBER_OF_COINS = 1;

    private int frameForCoin = 4;

    private int tickRate;
    private int lastTimeTouched;

    private int PERIOD_BETWEEN_COLLECTING = 30;

    private List<CoinElement> coins;

    public CoinGenerator(Game game) {
        this.game = game;
        coins = new ArrayList<>();
        context = GlobalApplication.getAppContext();//game.getGameActivity().getBaseContext();
        soundEffects = new SoundEffects(context);
    }

    @Override
    public void tick() {
        if (game.ifTreeChopped(this)) {
            for (int i = 0; i < NUMBER_OF_COINS; i++) {
                CoinElement element = new CoinElement(game);
                element.setPosition(TreeGenerator.TREE_X_AXIS,100f);

                float directionX = generateNumber(-3, element.MIN_X_SPEED);
                float directionY = generateNumber(-7f,4f);

                element.setDirection(new Vector(directionX,directionY));

                coins.add(element);
            }
            game.setTreeChopped(false,this);
        }

        if(tickRate % 30 == 0) {
            frameForCoin++;
            if(frameForCoin > 7){
                frameForCoin = 0;
            }
        }


        if (tickRate < 3000) tickRate++;
        else {
            tickRate = 0;
            lastTimeTouched = PERIOD_BETWEEN_COLLECTING - 2;
            lastTimeTouched *= -1;
        }

        for (CoinElement element : coins){
            element.tick();
        }

    }

    @Override
    public void handleTouch(GameModel.Touch touch, MotionEvent event) {
        // teacher: One line if statmenets are not really recommended.
        if (tickRate - lastTimeTouched < PERIOD_BETWEEN_COLLECTING) return;

        lastTimeTouched = tickRate;

        Log.i("extra_info", "Touched! X: " + touch.x + ", Y: " + touch.y);

        for (int i = coins.size()-1 ; i > -1 ; i--) {
            // teacher: Can you simplify the conditions inside if.
            if (touch.x > coins.get(i).getPosition().x && touch.x < coins.get(i).getPosition().x + CoinElement.WIDTH
                    && touch.y > coins.get(i).getPosition().y && touch.y < coins.get(i).getPosition().y + CoinElement.HEIGHT) {
                Log.d("extra_info", "Silver removed");
                soundEffects.playCoinSound();
                coins.remove(i);
                game.updateTextView();
                return;
            }
        }
    }

    @Override
    public void draw(GameView gv) {
        // Teacher: Looping for on draw can be slower. I do not know exactly what you are trying to do here.
        for (CoinElement element : coins) {
            element.draw(gv,frameForCoin);
        }
    }

    private float generateNumber(float from, float till){
        float difference = Math.abs(till - from);
        return (float) (Math.random() * difference) + from;
    }

    public void setNUMBER_OF_COINS(int NUMBER_OF_COINS) {
        this.NUMBER_OF_COINS = NUMBER_OF_COINS;
    }

    public int getNUMBER_OF_COINS() {
        return NUMBER_OF_COINS;
    }
}

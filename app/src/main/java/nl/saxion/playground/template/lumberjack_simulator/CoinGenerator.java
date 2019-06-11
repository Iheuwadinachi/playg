package nl.saxion.playground.template.lumberjack_simulator;

import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameModel;
import nl.saxion.playground.template.lib.GameView;

public class CoinGenerator extends Entity {

    private Game game;

    private int NUMBER_OF_COINS = 2;

    private int frameForCoin = 4;

    private int tickRate;
    private int lastTimeTouched;

    private int PERIOD_BETWEEN_COLLECTING = 30;

    private List<CoinElement> coins;

    public CoinGenerator(Game game) {
        this.game = game;
        coins = new ArrayList<>();
    }

    @Override
    public void tick() {
        if (game.ifTreeChopped(this)) {
            for (int i = 0; i < NUMBER_OF_COINS; i++) {
                CoinElement element = new CoinElement(game);
                element.setPosition(40f,85f);
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
        if (tickRate - lastTimeTouched < PERIOD_BETWEEN_COLLECTING) return;

        lastTimeTouched = tickRate;

        Log.i("extra_info", "Touched! X: " + touch.x + ", Y: " + touch.y);

        for (int i = coins.size()-1 ; i > -1 ; i--) {
            if (touch.x > coins.get(i).getPosition().x && touch.x < coins.get(i).getPosition().x + CoinElement.WIDTH
            && touch.y > coins.get(i).getPosition().y && touch.y < coins.get(i).getPosition().y + CoinElement.HEIGHT) {
                Log.d("extra_info", "Silver removed");
                coins.remove(i);
                game.updateTextView();
                return;
            }
        }
    }

    @Override
    public void draw(GameView gv) {
        for (CoinElement element : coins) {
            element.draw(gv,frameForCoin);
        }
    }
}
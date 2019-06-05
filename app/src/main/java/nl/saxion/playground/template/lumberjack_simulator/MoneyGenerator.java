package nl.saxion.playground.template.lumberjack_simulator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameModel;
import nl.saxion.playground.template.lib.GameView;

// TODO: Rename with CoinGenerator
public class MoneyGenerator extends Entity {

    private Game game;

    private int NUMBER_OF_COINS = 2;

    private int frameForCoin = 4;

    private int tickRate;
    private int lastTimeTouched;

    private int PERIOD_BETWEEN_COLLECTING = 30;

    private List<MoneyElement> money;

    public MoneyGenerator(Game game) {
        this.game = game;
        money = new ArrayList<>();
    }

    @Override
    public void tick() {
        if (game.treeChopped[1]) {
            for (int i = 0; i < NUMBER_OF_COINS; i++) {
                MoneyElement element = new MoneyElement(game);
                element.position.y = 120f;
                element.position.x = (float) (Math.random() * 30);
                money.add(element);
            }
            game.treeChopped[1] = false;
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
            lastTimeTouched = PERIOD_BETWEEN_COLLECTING - 1;
            lastTimeTouched *= -1;
        }
    }

    @Override
    public void handleTouch(GameModel.Touch touch, MotionEvent event) {
        if (tickRate - lastTimeTouched < PERIOD_BETWEEN_COLLECTING) return;

        lastTimeTouched = tickRate;

        Log.i("extra_info", "Touched! X: " + touch.x + ", Y: " + touch.y);

        for (int i = money.size()-1 ; i > -1 ; i--) {
            if (touch.x > money.get(i).position.x && touch.x < money.get(i).position.x + MoneyElement.WIDTH
            && touch.y > money.get(i).position.y && touch.y < money.get(i).position.y + MoneyElement.HEIGHT) {
                Log.i("extra_info", "Silver removed");
                money.remove(i);
                game.updateTextView();
                return;
            }
        }
    }

    @Override
    public void draw(GameView gv) {
        for (MoneyElement element : money) {
            element.draw(gv,frameForCoin);
        }
    }
}

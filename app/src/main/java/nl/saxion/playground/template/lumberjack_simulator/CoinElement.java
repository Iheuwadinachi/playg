package nl.saxion.playground.template.lumberjack_simulator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.lumberjack_simulator.local_lib.Vector;

public class CoinElement extends Entity {

    private Bitmap[] bitmap;

    public static final float WIDTH = 8f;
    public static final float HEIGHT = 13f;

    private final float MIN_HEIGHT = 120f;
    private final float MAX_WIDTH = 35f;


    //public int frame;

    private Vector position;

    private Vector movement;

    private Game game;

    public CoinElement(Game game) {
        position = new Vector();
        movement = new Vector(-0.5f,-0.6f);
        this.game = game;
        bitmap = new Bitmap[8];
    }

    @Override
    public void tick(){
        position.x -= movement.x;
        if(movement.y != 0){
            position.y *= movement.y;
        }

        if(position.x > MAX_WIDTH) {
            position.x = MAX_WIDTH;
            if (movement.x < 0.1f & movement.x > -0.1) {
                movement.x = 0; //We don`t allow bouncing if the power left a little
            } else movement.x = movement.x * -(float) (Math.random() * 0.5f) + 0.5f;
        }
        if(position.x < 0){
            position.x = 0;
            if (movement.x < 0.1f & movement.x > -0.1) {
                movement.x = 0; //We don`t allow bouncing if the power left a little
            } else movement.x = movement.x * -(float) (Math.random() * 0.5f) + 0.5f;
        }

        if(position.y > MIN_HEIGHT){ //because MIN_HEIGHT is a positive number
            position.y = MIN_HEIGHT;
            if(movement.y < 0.1 & movement.y > -0.1){
                movement.y = 0;
            } else{
                movement.y = -(float) (Math.random() * (movement.y-movement.y/2) + movement.y/2);
            }
        }

        if(movement.y > 0) movement.y -= 0.09f;

    }

    public void draw(GameView gv, int frame) {

        if (bitmap[0] == null) {
            int[] resources = {R.drawable.silver_coin1, R.drawable.silver_coin2, R.drawable.silver_coin3,
                    R.drawable.silver_coin4, R.drawable.silver_coin5, R.drawable.silver_coin6,
                    R.drawable.silver_coin7, R.drawable.silver_coin8};
            for (int i = 0; i < bitmap.length; i++) {
                if (bitmap[i] == null)
                    bitmap[i] = BitmapFactory.decodeResource(gv.getResources(), resources[i]);
            }
        }

        gv.drawBitmap(bitmap[frame], position.x, position.y, WIDTH, HEIGHT);
    }

    public void setPosition(float x,float y){
        position.x = x;
        position.y = y;
    }

    public Vector getPosition() {
        return position;
    }
}

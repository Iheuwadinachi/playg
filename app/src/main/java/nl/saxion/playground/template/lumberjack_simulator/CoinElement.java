package nl.saxion.playground.template.lumberjack_simulator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.lumberjack_simulator.local_lib.Vector;

public class CoinElement extends Entity {

    private Bitmap[] bitmap;

    public static final float WIDTH = 8f;
    public static final float HEIGHT = 13f;

    private static final int FRAMES_PER_SECOND = 15;

    public final float MIN_X_SPEED = 0.4f;

    private final float MIN_HEIGHT = 120f;
    private final float MAX_WIDTH = TreeGenerator.TREE_X_AXIS - 5f;

    private boolean doAnimation = true;

    private int tickRate = 0;

    private float gravity = 1;
    private static float MULTIPLY = 1.20f;
    private static float DELETER = 0.7f;
    private float bouncer = -2.5f;

    private float deBouncer = 0.5f;
    private float airFriction = 0.02f;

    private boolean inversion = false;

    private boolean goRight = false;
    private boolean goLeft = true;

    private Vector position;
    private Vector direction;

    private Game game;

    public CoinElement(Game game) {
        position = new Vector();

        this.game = game;
        bitmap = new Bitmap[8];
    }
// teacher: The function is too big try making it simple.
    @Override
    public void tick() {
        if (doAnimation) {
            if (tickRate % FRAMES_PER_SECOND == 0) {
                position.y += direction.y;
                position.x += direction.x;

                if (inversion) { //If coin bounced from ground, it jumps upwards
                    direction.y = gravity * DELETER;
                    gravity = gravity * DELETER;
                } else {
                    direction.y = gravity * MULTIPLY;
                    gravity = gravity * MULTIPLY;
                }

                if(goRight){
                    direction.x -= airFriction;
                    if(direction.x < MIN_X_SPEED) {
                        direction.x = MIN_X_SPEED;
                        Log.d("speed_info","Right direction ---> min speed was assigned");
                    }
                } else if (goLeft){
                    direction.x -= airFriction;
                    if(direction.x > (MIN_X_SPEED * -1f)){
                        direction.x = (MIN_X_SPEED * -1f);
                        Log.d("speed_info","Left direction <--- min speed was assigned");
                    }
                }

                if(position.x < 0){
                    direction.x = direction.x * -1; //Bouncing from wall
                    position.x = 0;
                    direction.x = Math.abs(direction.x);
                    goRight = true;
                    goLeft = false;
                    Log.d("speed_info","Coin bounced from starting point");
                } else if (position.x > MAX_WIDTH){
                    direction.x = direction.x * -1; //Bouncing from wall
                    position.x = MAX_WIDTH;
                    direction.x = Math.abs(direction.x);
                    direction.x = direction.x * -1;
                    direction.x += deBouncer;
                    goLeft = true;
                    goRight = false;
                    Log.d("speed_info","Coin bounced from tree");
                }

                if (position.y < 0) {
                    position.y = 0;
                    gravity = 1f;
                    inversion = false;
                } else if (position.y > MIN_HEIGHT) {
                    position.y = MIN_HEIGHT;
                    gravity = bouncer;
                    bouncer += 0.6f;
                    inversion = true;
                }

                if (direction.y < 0.2f && direction.y > -0.2f) {
                    inversion = false;
                    gravity = 0.9f;
                }

                if (bouncer > -1f) {
                    doAnimation = false;
                    position.y = MIN_HEIGHT;
                }

                Log.d("speed_info", "  Coin has position X: " + position.x + " , y: " + position.y  + " direction.x : " + direction.x);

            }
        }
        tickRate++;
    }

//    {
//                position.y += direction.y;
//                position.x += direction.x;
//
//                if (inversion) {
//                    direction.y = gravity * DELETER;
//                    gravity = gravity * DELETER;
//                } else {
//                    direction.y = gravity * MULTIPLY;
//                    gravity = gravity * MULTIPLY;
//                }
//
//                if(goRight){
//                    direction.x = positionx;
//                    positionx -= airFriction;
//                    if(positionx < MIN_X_SPEED) {
//                        positionx = MIN_X_SPEED;
//                        Log.d("speed_info","Right direction ---> min speed was assigned");
//                    }
//                } else if (goLeft){
//                    direction.x = positionx;
//                    positionx -= airFriction;
//                    if(positionx > (MIN_X_SPEED * -1f)){
//                        positionx = (MIN_X_SPEED * -1f);
//
//                        Log.d("speed_info","Left direction <--- min speed was assigned");
//                    }
//                }
//
//                if(position.x < 0){
//                    direction.x = direction.x * -1; //Bouncing from wall
//                    position.x = 0;
//                    positionx = Math.abs(positionx);
//                    goRight = true;
//                    goLeft = false;
//                    Log.d("speed_info","Coin bounced from starting point");
//                } else if (position.x > TreeGenerator.TREE_X_AXIS - 5f){
//                    direction.x = direction.x * -1; //Bouncing from wall
//                    position.x = TreeGenerator.TREE_X_AXIS - 5f;
//                    positionx = Math.abs(positionx);
//                    positionx = positionx * -1;
//                    positionx += deBouncer;
//                    goLeft = true;
//                    goRight = false;
//                    Log.d("speed_info","Coin bounced from tree");
//                }
//
//                if (position.y < 0) {
//                    position.y = 0;
//                    gravity = 1f;
//                    inversion = false;
//                } else if (position.y > MIN_HEIGHT) {
//                    position.y = MIN_HEIGHT;
//                    gravity = bouncer;
//                    bouncer += 0.6f;
//                    inversion = true;
//                }
//
//                if (direction.y < 0.2f && direction.y > -0.2f) {
//                    inversion = false;
//                    gravity = 0.9f;
//                }
//
//                if (bouncer > 2.9f) {
//                    doAnimation = false;
//                    position.y = MIN_HEIGHT;
//                }
//
//                Log.d("speed_info", "* Coin has position X: " + position.x + " f Y: " + position.y + " .Friction: " + positionx + " direction.x : " + direction.x);
//
//            }


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

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public Vector getPosition() {
        return position;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
        setUpVarriables();
    }

    private void setUpVarriables(){
        if(direction.y < 1){
            inversion = true;
        }
        gravity = direction.y;
    }
}

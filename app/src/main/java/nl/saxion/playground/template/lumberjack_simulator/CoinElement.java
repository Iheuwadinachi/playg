package nl.saxion.playground.template.lumberjack_simulator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.Printer;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.lumberjack_simulator.local_lib.Vector;

public class CoinElement extends Entity {

    private Bitmap[] bitmap;

    public static final float WIDTH = 8f;
    public static final float HEIGHT = 13f;

    private static final int FRAMES_PER_SECOND = 10;

    private final float MIN_HEIGHT = 120f;
    private final float MAX_WIDTH = 35f;

    private boolean doAnimation = true;

    private int tickRate = 0;

    private float GRAVITY = 1;
    private final float MULTIPLY = 1.05f;
    private final float DELETER = 0.8f;
    private float BOUNCER = -3f;

    private boolean inversion = false;

    //public int frame;

    private Vector position;
    private Vector direction;

    private Game game;

    public CoinElement(Game game) {
        position = new Vector();
        direction = new Vector(0, 1f);
        this.game = game;
        bitmap = new Bitmap[8];
    }

    @Override
    public void tick() {

        if (doAnimation) {
            if (tickRate % FRAMES_PER_SECOND == 0) {
                position.y += direction.y;

                if (inversion) {
                    direction.y = GRAVITY * DELETER;
                    GRAVITY = GRAVITY * DELETER;
                } else {
                    direction.y = GRAVITY * MULTIPLY;
                    GRAVITY = GRAVITY * MULTIPLY;
                }

                if (position.y < 0) {
                    position.y = 0;
                    GRAVITY = 1f;
                    inversion = false;
                } else if (position.y > MIN_HEIGHT) {
                    position.y = MIN_HEIGHT;
                    GRAVITY = BOUNCER;
                    BOUNCER += 0.6f;
                    inversion = true;
                }

                if (direction.y < 0.2f && direction.y > -0.2f) {
                    inversion = false;
                    GRAVITY = 0.9f;
                }

                if (BOUNCER > 10) {
                    doAnimation = false;
                    position.y = MIN_HEIGHT;
                }

                Log.d("speed_info", "          *Coin has position: " + position.y + " f. BOUNCER : " + BOUNCER + "           ");

            }

        }
        tickRate++;
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

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public Vector getPosition() {
        return position;
    }
}

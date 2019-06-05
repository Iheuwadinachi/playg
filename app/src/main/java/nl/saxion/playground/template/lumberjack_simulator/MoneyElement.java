package nl.saxion.playground.template.lumberjack_simulator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.lumberjack_simulator.local_lib.Vector;

public class MoneyElement extends Entity {

    private Bitmap[] bitmap;

    public static final float WIDTH = 8f;
    public static final float HEIGHT = 13f;

    //public int frame;

    Vector position;

    private Game game;

    public MoneyElement(Game game) {
        position = new Vector();
        this.game = game;
        bitmap = new Bitmap[8];
    }

    public void draw(GameView gv, int frame) {

        if (bitmap[0] == null) {
            int[] resources = {R.drawable.silver_coin1, R.drawable.silver_coin2, R.drawable.silver_coin3, R.drawable.silver_coin4, R.drawable.silver_coin5, R.drawable.silver_coin6, R.drawable.silver_coin7, R.drawable.silver_coin8};
            for (int i = 0; i < bitmap.length; i++) {
                if (bitmap[i] == null)
                    bitmap[i] = BitmapFactory.decodeResource(gv.getResources(), resources[i]);
            }
        }

        gv.drawBitmap(bitmap[frame], position.x, position.y, WIDTH, HEIGHT);
    }
}

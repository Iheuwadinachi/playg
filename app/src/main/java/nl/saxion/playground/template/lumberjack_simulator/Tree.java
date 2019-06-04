package nl.saxion.playground.template.lumberjack_simulator;

import android.graphics.Bitmap;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.lumberjack_simulator.Game;

public class Tree extends Entity {

    private final float objectWidth = 20f;
    private final float objectHeight = 115f;

    private static  Bitmap bitmap;

    private Game game;

    public Tree(Game game) {
        this.game = game;
    }

    @Override
    public void draw(GameView gv) {
        if (bitmap==null) {
            bitmap = gv.getBitmapFromResource(R.drawable.tree);
        }

        float width = game.getWidth();

        float halfWidth = width/2;

        halfWidth = halfWidth - objectWidth / 2;

        halfWidth -= 10f;

        gv.drawBitmap(bitmap,halfWidth, 5f, objectWidth, objectHeight);

    }
}

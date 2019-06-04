package nl.saxion.playground.template.lumberjack_simulator;

import android.graphics.Bitmap;
import android.graphics.Color;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;

public class Background extends Entity {

    private static Bitmap bitmap;

    private Game game;

    Background(Game game) {
        this.game = game;
    }

    @Override
    public void draw(GameView gv) {
        if (bitmap==null) bitmap = gv.getBitmapFromResource(R.drawable.landscape1);

        gv.getCanvas().drawColor(Color.rgb(219,228,238));

    }
}
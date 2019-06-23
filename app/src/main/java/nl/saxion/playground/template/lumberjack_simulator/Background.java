package nl.saxion.playground.template.lumberjack_simulator;

import android.graphics.Bitmap;
import android.graphics.Color;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;

/**
 * Class for the background entity the code is lifted from one of the templates
 * @author Michael Cornelisse
 */

public class Background extends Entity {

    private static Bitmap bitmap;
    private Game game;
    private boolean dayMode = true;

    Background(Game game) {
        this.game = game;
    }

    public void draw(GameView gv) {

        if (dayMode) {
            if (bitmap == null) {
                bitmap = gv.getBitmapFromResource(R.drawable.landscape1);
            }
            float bgWidth = (float) bitmap.getWidth() / (float) bitmap.getHeight() * game.getHeight();

            for (int i = 0; i <= Math.ceil(game.getWidth() / bgWidth); i++) {
                gv.drawBitmap(bitmap, (float) i * bgWidth, 0, bgWidth, game.getHeight());
            }
        }
    }
        /*
          else if (!dayMode){
            if (bitmap==null){
//                bitmap = gv.getBitmapFromResource(R.drawable.landscape2);
            }
            float bgWidth = (float)bitmap.getWidth() / (float)bitmap.getHeight() * game.getHeight();

            for (int i = 0; i <= Math.ceil(game.getWidth()/bgWidth) ; i++) {
                gv.drawBitmap(bitmap, (float) i * bgWidth, 0, bgWidth, game.getHeight());
            }
        }
    }
         */


        public void setDayMode ( boolean dayMode){
            this.dayMode = dayMode;
        }
    }
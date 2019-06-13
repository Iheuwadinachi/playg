package nl.saxion.playground.template.lumberjack_simulator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.lumberjack_simulator.local_lib.Vector;

public class TreeElement extends Entity {
// Teacher: Public access must be avoided instead try to use accessor methods.
    public Bitmap bitmap;

    public Vector position;
// Teacher: game object have never been used.
    private Game game;

    private float width;
    private float height;

    public TreeElement(Game game){
        this.game = game;
        position = new Vector();
        width = 20f;
        height = 20f;
    }
// Teacher: Empty method.
    @Override
    public void tick() {

    }

    @Override
    public void draw(GameView gv) {
        if(bitmap==null){
            bitmap = BitmapFactory.decodeResource(gv.getResources(), R.drawable.wood_block_medium);
        }

        gv.drawBitmap(bitmap,position.x,position.y,width,height);
    }
}

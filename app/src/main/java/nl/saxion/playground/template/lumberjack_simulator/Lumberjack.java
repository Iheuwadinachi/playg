package nl.saxion.playground.template.lumberjack_simulator;



import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;

import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.GameModel;
import nl.saxion.playground.template.lumberjack_simulator.sound_lib.SoundEffects;

public class Lumberjack extends Entity {


    private Context context;
    private SoundEffects soundEffects;
    private final float width = 20f;
    private final float height = 35f;

    private static final double TIME_TO_DO_ALL_ANIMATION = 60;

    private int tickRate;
    private int lastTouched;

    private boolean touched;

    private int touchesToDestroy;
    private int leftTouchesToDestroy;

    private Bitmap[] bitmap;

    private Game game;

    private int frame;

    public Lumberjack(Game game) {
        this.game = game;
        bitmap = new Bitmap[4];
        touchesToDestroy = 5;
        leftTouchesToDestroy = touchesToDestroy;
        //context = game.getGameActivity().getApplicationContext();
        context = game.getGameActivity().getBaseContext();
        soundEffects = new SoundEffects(context);
    }

    @Override
    public void tick() {
        if(touched) {
            int chooser = tickRate - lastTouched;
            if(chooser < TIME_TO_DO_ALL_ANIMATION * 0.25){
                frame = 0;
            } else if(chooser < TIME_TO_DO_ALL_ANIMATION * 0.5){
                frame = 1;
            } else if(chooser < TIME_TO_DO_ALL_ANIMATION * 0.75){
                frame = 2;
            } else if(chooser < TIME_TO_DO_ALL_ANIMATION){
                frame = 3;
                soundEffects.playChopSound();
            } else {
                leftTouchesToDestroy --;
                if(leftTouchesToDestroy < 0) { //remake it
                    game.setTreeChopped(true);
                    leftTouchesToDestroy = touchesToDestroy; //assign number of chops back(10)
                }
                frame = 0;
                touched = false;
            }
        }

        if(tickRate < 2000) tickRate ++; else {
            tickRate = 0;
            lastTouched = (int) TIME_TO_DO_ALL_ANIMATION;
            lastTouched = lastTouched * -1;
        }
    }

    @Override
    public void handleTouch(GameModel.Touch touch, MotionEvent event) {

        if(touch.lastAction == MotionEvent.ACTION_DOWN) {

            if (tickRate - lastTouched > TIME_TO_DO_ALL_ANIMATION) {
                if(!touched) {
                    lastTouched = tickRate;
                    touched = true;
                    Log.i("events", tickRate + ") Touched! Button works");
                } else {
                    Log.i("events","Button hasn`t finished everything to do (boolean touched=false).");
                }
            } else {
                Log.i("events", tickRate + ") Button is banned and was not procedured");
            }
        }

    }

    @Override
    public void draw(GameView gv) {
        if (bitmap[0]==null) {
            bitmap[0] = gv.getBitmapFromResource(R.drawable.timberman);
        }
        if (bitmap[1]==null) {
            bitmap[1] = gv.getBitmapFromResource(R.drawable.timberman1);
        }
        if (bitmap[2]==null) {
            bitmap[2] = gv.getBitmapFromResource(R.drawable.timberman2);
        }
        if (bitmap[3]==null) {
            bitmap[3] = gv.getBitmapFromResource(R.drawable.timberman3);
        }

        float left = 0.6f * game.getWidth();
        float top = TreeGenerator.TREE_Y_AXIS_FINISH + 5f;//previously it was 0.5, then 0.53f

        float extraWidth = width + 35f;

        float extraLeft = left - 18f;

        switch (frame){
            case 0:
                gv.drawBitmap(bitmap[0], left, top, width, height, 0);
                break;
            case 1:
                gv.drawBitmap(bitmap[1], extraLeft-1f, top , extraWidth, height);
                break;
            case 2:
                gv.drawBitmap(bitmap[2], extraLeft-2f, top, extraWidth, height);
                break;
            case 3:
                gv.drawBitmap(bitmap[3], extraLeft-3f, top, extraWidth, height);
                break;
        }


    }
}


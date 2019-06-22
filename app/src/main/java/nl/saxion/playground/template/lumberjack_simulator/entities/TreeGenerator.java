package nl.saxion.playground.template.lumberjack_simulator.entities;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.lumberjack_simulator.Game;

public class TreeGenerator extends Entity {
// Teacher: unused fields.

    // Teacher: log in constructor, element in tick() and bottomBlock in draw() represents the same object.
//  Since game object have not been changed in this class.  You can make it a field.
    private List<TreeElement> logs;

    private boolean addNewLog;

    static float TREE_Y_AXIS_FINISH;

    static float TREE_X_AXIS;

    private static final byte LOGS_AMOUNT = 6;

    private Game game;


    public TreeGenerator(Game game) {
        this.game = game;
        logs = new ArrayList<>();

        TREE_X_AXIS = game.getWidth() * 0.35f;
        TREE_Y_AXIS_FINISH = game.getHeight() * 0.52f;

        Log.d("extra_info","Tree x axis is: " + TREE_X_AXIS + ", Y axis is: " + TREE_Y_AXIS_FINISH);

        float y = TREE_Y_AXIS_FINISH;
        // Teacher: better to have a separate method that generate tree elements.
        for (int i = 0; i <= LOGS_AMOUNT; i++) {
            TreeElement log = new TreeElement();

            float logSize = 20f;

            log.position.x = TREE_X_AXIS;
            log.position.y = y;

            logs.add(log);

            Log.i("extra_info", "Coordinates " + i + ") X:" + TREE_X_AXIS + ", Y: " + y);
            y -= logSize;
        }

    }

    @Override
    public void tick() {
        if (game.ifTreeChopped(this)) {
            logs.remove(0);
            game.setTreeChopped(false, this);
            addNewLog = true;
        }

        if (addNewLog) {
            TreeThread thread = new TreeThread();
            addNewLog = false;
            thread.start();
            debugTreeCoordinates();
            Log.d("extra_info","My thread works");
        }

    }

    private void debugTreeCoordinates(){
        int counter = 0;
        for (TreeElement element : logs){
            Log.d("extra_info","Tree element # " + counter + " has X: " + element.position.x + " and Y: " + element.position.y);
        }
    }

//    private void moveLogDown(int index) {
//        int counter = 0;
//        for (TreeElement element : logs) {
//            if (index == counter) {
//                element.position.y += 20f;
//                return;
//            }
//            counter++;
//        }
//    }

    @Override
    public void draw(GameView gv) {

        for (TreeElement element : logs) {
            element.draw(gv);
        }

        TreeElement bottomBlock = new TreeElement();
        bottomBlock.position.x = TREE_X_AXIS;
        bottomBlock.position.y = TREE_Y_AXIS_FINISH + 20f;
        bottomBlock.draw(gv);
    }

    //TODO: Check if it can be private
    class TreeThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < LOGS_AMOUNT; i++) {
                for (int j = 0; j < 5; j++) {
                    logs.get(i).position.y += 4f;
                    customsleep(30);
                }
                customsleep(70);
            }

            TreeElement element = new TreeElement();
            element.position.x = TREE_X_AXIS;
            element.position.y = logs.get(logs.size()-1).position.y-20f;

            logs.add(element);
        }

        private void customsleep(int millis){
            try {
                sleep(millis);
            } catch (InterruptedException e) {
                Log.d("extra_info","Exception with sleep in thread");
            }
        }
    }
}
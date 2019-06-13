package nl.saxion.playground.template.lumberjack_simulator;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Collection;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Vector;

import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;

public class TreeGenerator extends Entity {

    private Queue<TreeElement> logs;
    
    private static int ANIMATION_TIME = 50;
    private static int ANIMATION_LENGTH = 10;

    public static float TREE_X_AXIS = 35f;

    private int logIndex;

    private int tickRate;

    private boolean addNewTreeElement;

    private Game game;

    public TreeGenerator(Game game){
        this.game = game;
        logs = new PriorityQueue<>();
        tickRate = 0;
        logIndex = 0;

        float x = 35f;
        float y = 90f;

        for (int i = 0; i < 5; i++) {
            TreeElement log = new TreeElement(game);

            float logSize = 20f;

            log.position.x = x;
            log.position.y = y;

            logs.add(log);

            Log.i("extra_info","Coordinates " + i + ") X:" + x + ", Y: " + y);
            y -= logSize;
        }

    }

    @Override
    public void tick() {
        if(game.ifTreeChopped(this)){
            logs.remove();
            game.setTreeChopped(false,this);

            int counter = 0;
            for (TreeElement element : logs) {
                Log.d("extra_info", counter + ") X: " + element.position.x + ", Y:" + element.position.y);
                moveLogDown(counter);
                counter++;
            }
            TreeElement element = new TreeElement(game);
            element.position.x = 35f;
            element.position.y = 10f;
            logs.add(element);
        }

        tickRate++;
    }

    private void moveLogDown(int index){
        int counter = 0;
        for (TreeElement element : logs){
            if(index == counter){
                element.position.y += 20f;
                return;
            }
            counter ++;
        }
    }
    
    @Override
    public void draw(GameView gv) {

        for(TreeElement element : logs){
            element.draw(gv);
        }

        TreeElement bottomBlock = new TreeElement(game);
        bottomBlock.position.x = 35f;
        bottomBlock.position.y = 110f;
        bottomBlock.draw(gv);
    }
}
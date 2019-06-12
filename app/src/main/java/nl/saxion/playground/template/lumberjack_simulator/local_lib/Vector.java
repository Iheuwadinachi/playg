package nl.saxion.playground.template.lumberjack_simulator.local_lib;

public class Vector {

    public float x;
    public float y;

    public Vector(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector(){
        x = 0f;
        y = 0f;
    }

    public boolean ifNumbersAreZero(){
        if(x > 0.1f & x < -0.1f && y > 0.1f & y < -0.1f){
            return false;
        } else return true;
    }
}

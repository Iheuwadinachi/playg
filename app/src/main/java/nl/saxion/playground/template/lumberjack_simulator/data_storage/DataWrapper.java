package nl.saxion.playground.template.lumberjack_simulator.data_storage;

public class DataWrapper {
    public static int coins = 0;

    public DataWrapper(){

    }

    public static DataWrapper instance;

    public DataWrapper getInstance() {
        if(instance == null) instance = new DataWrapper();
        return instance;
    }

    public void setInstance(DataWrapper instance) {
        this.instance = instance;
    }

    public  int getCoins() {
        return coins;
    }

    public  void setCoins(int coins) {
        DataWrapper.coins = coins;
    }
}

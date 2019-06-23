package nl.saxion.playground.template.lumberjack_simulator.data_storage;

import nl.saxion.playground.template.lumberjack_simulator.local_lib.Price;

/**
 * @author Mark Kravchuk
 */
public class DataWrapper {
    public static int coins = 0;
    public static Price[] prices;

    public DataWrapper() {

    }

    private static DataWrapper instance;

    public DataWrapper getInstance() {
        if (instance == null) {
            instance = new DataWrapper();
        }
        return instance;
    }

    public void setInstance(DataWrapper instance) {
        DataWrapper.instance = instance;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        DataWrapper.coins = coins;
    }

    public Price[] getPrices() {
        return prices;
    }

    public void setPrices(Price[] prices) {
        DataWrapper.prices = prices;
    }
}

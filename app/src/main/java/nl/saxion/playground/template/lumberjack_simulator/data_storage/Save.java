package nl.saxion.playground.template.lumberjack_simulator.data_storage;

import nl.saxion.playground.template.lumberjack_simulator.store.Price;

/**
 * @author Adomas Aleksandravicius, Michael Cornelisse
 */

public class Save {
    private int coins;

    private Price[] prices;

    private static Save save;

    static{
        save = new Save();
    }

    public Save() {
        this.coins = Constants.coins;
    }

    public int getCoins() {
        return coins;
    }

    public static void setSave(Save save) {
        Save.save = save;
    }

    public Save getInstance(){
        return save;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setPrices(Price[] prices) {
        this.prices = prices;
    }

    Price[] getPrices() {
        return prices;
    }
}

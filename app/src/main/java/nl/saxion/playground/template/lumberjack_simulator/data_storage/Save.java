package nl.saxion.playground.template.lumberjack_simulator.data_storage;

/**
 * @author Adomas Aleksandravicius, Michael Cornelisse
 */
public class Save {
    private int coins;

    public Save() {
        this.coins = Constants.coins;
    }

    public int getCoins() {
        return coins;
    }
}

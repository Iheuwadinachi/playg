package nl.saxion.playground.template.lumberjack_simulator.local_lib;

import java.util.ArrayList;
import java.util.List;

public class Price {

    private int maxTimesUpgrade;
    private int startPrice;

    private String currentPrice;

    private List<Integer> risingPrices;

    private  double multiply = 5;

    private int priceCounter = -1;

    public Price(int startPrice, int maxTimeUpgrade){
        this.startPrice = startPrice;
        this.maxTimesUpgrade = maxTimeUpgrade;
        risingPrices = new ArrayList<>();
        init();
    }

    private void init(){
        for (int i = 0; i < maxTimesUpgrade; i++) {
            if(multiply < 2) {
                multiply = 1.9;
            }
            double decrease_multiply = 0.4;
            risingPrices.add((int) (startPrice * (multiply - decrease_multiply)));
            multiply -= decrease_multiply;
            startPrice = (int) (startPrice * (multiply - decrease_multiply));
        }


    }

    public String getNewPrice(){
        priceCounter++;
        if(priceCounter >= risingPrices.size()) return "-";
        currentPrice = Integer.toString(risingPrices.get(priceCounter));
        return currentPrice;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public int getPriceCounter() {
        return priceCounter + 1;
    }
}
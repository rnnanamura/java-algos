package com.seir.algos.banking;

import java.util.List;

public class Trading {

    public static double maxTradingProfit(List<Double> prices) {
        double maxProfit = 0.0;
        if (prices == null || prices.isEmpty()) {
            return maxProfit;
        }
        double minPrice = prices.get(0);
        for (double price: prices) {
            if (price < minPrice) {
                minPrice = price;
                maxProfit = 0.0;
                continue;
            }
            double currentProfit = price - minPrice;
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
            }
        }
        return maxProfit;
    }
}

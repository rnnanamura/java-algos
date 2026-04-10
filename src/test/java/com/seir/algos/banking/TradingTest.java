package com.seir.algos.banking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradingTest {

    @Test
    public void testMaxTradingProfit() {
        double expected = 0.0;
        double profit = Trading.maxTradingProfit(new ArrayList<>());
        assertEquals(expected, profit);

        List<Double> prices = List.of(1., 3. , 2., 5.);
        expected = 4.;
        profit = Trading.maxTradingProfit(prices);
        assertEquals(expected, profit);

        prices = List.of(100., 113., 110., 85., 105., 102., 86., 63., 81., 101., 94., 106., 101., 79., 94., 90., 97.);
        expected = 43.;
        profit = Trading.maxTradingProfit(prices);
        assertEquals(expected, profit);

    }
}

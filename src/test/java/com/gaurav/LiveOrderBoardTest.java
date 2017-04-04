package com.gaurav;

import org.junit.*;

import java.util.List;
import static org.junit.Assert.*;

public class LiveOrderBoardTest{

    private LiveOrderBoard orderBoard;

    @Before
    public void setup() {
        orderBoard = new LiveOrderBoard();
    }

    //Needs implementing edge cases and test for checking null/invalid values in the order


    @Test
    // Initially started with just shouldGetSingleOrder but later refactored so can use
    // summary for verification and retain encapsulation of DB. Ideally I would like a
    // test to only test one thing
    public void shouldRegisterSingleBuyOrder() {
        orderBoard.registerOrder(new Order("TestUserId", 2.3, 10000, OrderType.BUY));
        List<String> summary = orderBoard.getBuySummary();
        assertEquals("2.3 kg for £100", summary.get(0));
    }

    @Test
    public void shouldRegisterTwoBuyOrderWithDifferentPriceAndShowCorrectSummary() {
        orderBoard.registerOrder(new Order("TestUserId", 2.3, 10000, OrderType.BUY));
        orderBoard.registerOrder(new Order("TestUserId2", 3.5, 20000, OrderType.BUY));
        List<String> summary = orderBoard.getBuySummary();
        assertEquals("3.5 kg for £200", summary.get(0));
        assertEquals("2.3 kg for £100", summary.get(1));
    }

    @Test
    public void shouldRegisterMultipleBuyOrderWithDifferentPriceAndShowCorrectSummary() {
        orderBoard.registerOrder(new Order("TestUserId", 2.3, 10000, OrderType.BUY));
        orderBoard.registerOrder(new Order("TestUserId2", 3.5, 20000, OrderType.BUY));
        orderBoard.registerOrder(new Order("TestUserId2", 1.3, 30000, OrderType.BUY));
        orderBoard.registerOrder(new Order("TestUserId2", 4.3, 40000, OrderType.BUY));
        List<String> summary = orderBoard.getBuySummary();
        assertEquals("4.3 kg for £400", summary.get(0));
        assertEquals("1.3 kg for £300", summary.get(1));
        assertEquals("3.5 kg for £200", summary.get(2));
        assertEquals("2.3 kg for £100", summary.get(3));
    }

    @Test
    public void shouldRegisterMultipleBuyOrderWithRecurringPriceAndShowCorrectSummary() {
        orderBoard.registerOrder(new Order("TestUserId", 2.3, 10000, OrderType.BUY));
        orderBoard.registerOrder(new Order("TestUserId", 1.4, 10000, OrderType.BUY));
        orderBoard.registerOrder(new Order("TestUserId2", 3.5, 20000, OrderType.BUY));
        orderBoard.registerOrder(new Order("TestUserId2", 1.3, 30000, OrderType.BUY));
        orderBoard.registerOrder(new Order("TestUserId2", 4.3, 40000, OrderType.BUY));
        orderBoard.registerOrder(new Order("TestUserId2", 3.4, 40000, OrderType.BUY));
        List<String> summary = orderBoard.getBuySummary();
        assertEquals("7.7 kg for £400", summary.get(0));
        assertEquals("1.3 kg for £300", summary.get(1));
        assertEquals("3.5 kg for £200", summary.get(2));
        assertEquals("3.7 kg for £100", summary.get(3));
    }

    @Test
    public void shouldRegisterSingleSellOrder() {
        orderBoard.registerOrder(new Order("TestUserId", 2.3, 10000, OrderType.SELL));
        List<String> summary = orderBoard.getSellSummary();
        assertEquals("2.3 kg for £100", summary.get(0));
    }

    @Test
    public void shouldRegisterTwoSELLOrderWithDifferentPriceAndShowCorrectSummary() {
        orderBoard.registerOrder(new Order("TestUserId", 2.3, 10000, OrderType.SELL));
        orderBoard.registerOrder(new Order("TestUserId2", 3.5, 20000, OrderType.SELL));
        List<String> summary = orderBoard.getSellSummary();
        assertEquals("2.3 kg for £100", summary.get(0));
        assertEquals("3.5 kg for £200", summary.get(1));

    }

    @Test
    public void shouldRegisterMultipleSELLOrderWithDifferentPriceAndShowCorrectSummary() {
        orderBoard.registerOrder(new Order("TestUserId", 2.3, 10000, OrderType.SELL));
        orderBoard.registerOrder(new Order("TestUserId2", 3.5, 20000, OrderType.SELL));
        orderBoard.registerOrder(new Order("TestUserId2", 1.3, 30000, OrderType.SELL));
        orderBoard.registerOrder(new Order("TestUserId2", 4.3, 40000, OrderType.SELL));
        List<String> summary = orderBoard.getSellSummary();
        assertEquals("2.3 kg for £100", summary.get(0));
        assertEquals("3.5 kg for £200", summary.get(1));
        assertEquals("1.3 kg for £300", summary.get(2));
        assertEquals("4.3 kg for £400", summary.get(3));
    }

    @Test
    public void shouldRegisterMultipleSELLOrderWithRecurringPriceAndShowCorrectSummary() {
        orderBoard.registerOrder(new Order("TestUserId", 2.3, 10000, OrderType.SELL));
        orderBoard.registerOrder(new Order("TestUserId2", 3.5, 20000, OrderType.SELL));
        orderBoard.registerOrder(new Order("TestUserId2", 1.1, 20000, OrderType.SELL));
        orderBoard.registerOrder(new Order("TestUserId2", 2.2, 20000, OrderType.SELL));
        orderBoard.registerOrder(new Order("TestUserId2", 1.3, 30000, OrderType.SELL));
        orderBoard.registerOrder(new Order("TestUserId2", 4.3, 40000, OrderType.SELL));
        List<String> summary = orderBoard.getSellSummary();
        assertEquals("2.3 kg for £100", summary.get(0));
        assertEquals("6.8 kg for £200", summary.get(1));
        assertEquals("1.3 kg for £300", summary.get(2));
        assertEquals("4.3 kg for £400", summary.get(3));
    }

    @Test
    public void shouldRegisterMultipleOrdersWithMixOfBuyAndSellWithRecurringPriceAndShowCorrectSummary() {
        orderBoard.registerOrder(new Order("TestUserId", 2.3, 10000, OrderType.BUY));
        orderBoard.registerOrder(new Order("TestUserId", 1.4, 10000, OrderType.BUY));
        orderBoard.registerOrder(new Order("TestUserId2", 3.5, 20000, OrderType.BUY));
        orderBoard.registerOrder(new Order("TestUserId2", 1.3, 30000, OrderType.BUY));
        orderBoard.registerOrder(new Order("TestUserId2", 4.3, 40000, OrderType.BUY));
        orderBoard.registerOrder(new Order("TestUserId2", 3.4, 40000, OrderType.BUY));
        orderBoard.registerOrder(new Order("TestUserId", 2.3, 10000, OrderType.SELL));
        orderBoard.registerOrder(new Order("TestUserId2", 3.5, 20000, OrderType.SELL));
        orderBoard.registerOrder(new Order("TestUserId2", 1.1, 20000, OrderType.SELL));
        orderBoard.registerOrder(new Order("TestUserId2", 2.2, 20000, OrderType.SELL));
        orderBoard.registerOrder(new Order("TestUserId2", 1.3, 30000, OrderType.SELL));
        orderBoard.registerOrder(new Order("TestUserId2", 4.3, 40000, OrderType.SELL));
        List<String> summary = orderBoard.getBuySummary();
        assertEquals("7.7 kg for £400", summary.get(0));
        assertEquals("1.3 kg for £300", summary.get(1));
        assertEquals("3.5 kg for £200", summary.get(2));
        assertEquals("3.7 kg for £100", summary.get(3));
        List<String> sellSummary = orderBoard.getSellSummary();
        assertEquals("2.3 kg for £100", sellSummary.get(0));
        assertEquals("6.8 kg for £200", sellSummary.get(1));
        assertEquals("1.3 kg for £300", sellSummary.get(2));
        assertEquals("4.3 kg for £400", sellSummary.get(3));
    }
}

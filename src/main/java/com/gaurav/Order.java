package com.gaurav;

public class Order implements Comparable {
    // We dont need a getter for userid for now
    public int getPriceInPence() {
        return priceInPence;
    }

    public double getQuantity() {
        return quantity;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    private final String userId;
    private final double quantity;

    //User Integer to represent price in pence so there is no precision loss
    private final int priceInPence;
    private final OrderType orderType;

    public Order(String userId, double quantity, int priceInPence, OrderType type) {
        this.userId = userId;
        this.quantity = quantity;
        this.priceInPence = priceInPence;
        this.orderType = type;
    }


    @Override
    public int compareTo(Object o) {
        if(this.getOrderType().equals(OrderType.SELL)) {
            return this.priceInPence - ((Order) o).getPriceInPence();
        }else {
            return ((Order) o).getPriceInPence() - this.priceInPence;
        }
    }
}

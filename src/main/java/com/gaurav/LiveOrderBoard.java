package com.gaurav;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LiveOrderBoard {
    private static final DecimalFormat formatter = new DecimalFormat("#.##");
    private final List<Order> buyOrders;
    private final List<Order> sellOrders;
    public LiveOrderBoard() {
        buyOrders = new ArrayList<>();
        sellOrders = new ArrayList<>();
    }

    public void registerOrder(Order order) {
        if(order.getOrderType().equals(OrderType.BUY))
            buyOrders.add(order);
        else
            sellOrders.add(order);
    }

    public List<String> getBuySummary() {
        // Can get rid of this by using TreeMap
        Collections.sort(buyOrders);
        return createSummaryList(reduceToPriceOrderMap(buyOrders));
    }

    public List<String> getSellSummary() {
        // Can get rid of this by using TreeMap
        Collections.sort(sellOrders);
        return createSummaryList(reduceToPriceOrderMap(sellOrders));
    }
    private List<String> createSummaryList(Map<Integer, List<Order>> priceOrderMap) {
        return priceOrderMap.keySet().stream().map(price -> getOrderSummary(priceOrderMap.get(price), price)).collect(Collectors.toList());
    }

    private Map<Integer, List<Order>> reduceToPriceOrderMap(List<Order> orders) {
        Map<Integer, List<Order>> priceOrderMap = new HashMap<>();
        orders.stream().map(Order::getPriceInPence).distinct().forEach(price -> priceOrderMap.put(price,
                orders.stream().filter(order -> price.equals(order.getPriceInPence())).collect(Collectors.toList())));
        return priceOrderMap;
    }

    private String getOrderSummary(List<Order> orders, int price) {
        double totalQty = orders.stream().mapToDouble(Order::getQuantity).sum();
        return formatter.format(totalQty) + " kg for £" + formatter.format(price/100);

    }

}

package com.fatshaw.learning.refactoring.chapter6.domain;

public class Inventory {

    private double quantity;
    private double itemPrice;

    public double getPrice() {

        double basePrice = quantity * itemPrice;
        double discountFactor;
        if (basePrice > 1000) {
            discountFactor = 0.95;
        } else {
            discountFactor = 0.98;
        }
        return basePrice * discountFactor;
    }

    public double getPriceIncludingShip() {
        return quantity * itemPrice
            - Math.max(0, quantity - 500) * itemPrice * 0.05
            + Math.min(quantity * itemPrice * 0.1, 100.0);
    }

}

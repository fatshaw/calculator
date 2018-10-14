package com.fatshaw.learning.refactoring.chapter6.domain;

public class InventoryWithShipping extends Inventory {

    private double quantity;
    private double itemPrice;

    @Override
    public double getPriceIncludingShip() {
        return getBasePrice() - getDiscount() + getShipping();
    }

    private double getShipping() {
        return Math.min(quantity * itemPrice * 0.1, 100.0);
    }

    private double getDiscount() {
        return Math.max(0, quantity - 500) * itemPrice * 0.05;
    }

    private double getBasePrice() {
        return quantity * itemPrice;
    }

}

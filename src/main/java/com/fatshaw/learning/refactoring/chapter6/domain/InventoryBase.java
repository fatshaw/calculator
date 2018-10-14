package com.fatshaw.learning.refactoring.chapter6.domain;

public class InventoryBase extends Inventory {

    private double quantity;
    private double itemPrice;

    @Override
    public double getPrice() {
        return getBasePrice() * getDiscountFactor();
    }

    private double getDiscountFactor() {
        return getBasePrice() > 1000 ? 0.95 : 0.98;
    }

    private double getBasePrice() {
        return quantity * itemPrice;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}

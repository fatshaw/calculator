package com.fatshaw.learning.refactoring.chapter1.domain.version5;

import java.util.ArrayList;
import java.util.List;


class Customer {

    private String name;
    private List<Rental> rentalList = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        this.rentalList.add(rental);
    }

    public String getName() {
        return name;
    }


    public String statement() {

        // remove local variable
        final StringBuilder result = new StringBuilder().append("Rental Record for ").append(getName()).append("\n");
        rentalList.stream().forEach(rental -> result.append("\t").append(rental.getMovie().getTitle()).append("\t")
            .append(String.valueOf(rental.getCharge())).append("\n"));

        result.append("Amount owned is ").append(String.valueOf(getTotalAmount())).append("\n");
        result.append("You earned ").append(String.valueOf(getFrequentRenterPoints())).append(" frequent renter points");

        return result.toString();

    }

    public double getTotalAmount() {
        return rentalList.stream().map(i -> i.getCharge()).reduce(0.0, (sum, i) -> sum + i);
    }

    public double getFrequentRenterPoints() {
        return rentalList.stream().map(i -> i.getFrequentRenterPoints()).reduce(0.0, (sum, i) -> sum + i);
    }


}

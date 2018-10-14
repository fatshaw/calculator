package com.fatshaw.learning.refactoring.chapter1.domain.version2;

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
        double totalAmout = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        for (Rental rental : rentalList) {

            /**
                 1. extract method
                 2. move method
                 3. inline local variable
             */

            frequentRenterPoints++;
            if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            result += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rental.getCharge()) + "\n";

            totalAmout += rental.getCharge();
        }

        result += "Amount owned is " + String.valueOf(totalAmout) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;

    }


}

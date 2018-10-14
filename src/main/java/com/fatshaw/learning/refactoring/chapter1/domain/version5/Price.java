package com.fatshaw.learning.refactoring.chapter1.domain.version5;

interface Price {

    double getCharge(int dayRented);

    default double getFrequentRenterPoints(int daysRented) {
        return 1;
    }

}

package com.fatshaw.learning.refactoring.chapter1.domain.version5;

class NewsReleasePrice implements Price {

    @Override
    public double getCharge(int dayRented) {
        return dayRented * 3;
    }

    @Override
    public double getFrequentRenterPoints(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }
}

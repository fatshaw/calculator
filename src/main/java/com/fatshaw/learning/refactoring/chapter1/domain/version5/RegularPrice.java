package com.fatshaw.learning.refactoring.chapter1.domain.version5;

class RegularPrice implements Price {


    @Override
    public double getCharge(int dayRented) {
        double result = 2;
        if (dayRented > 2) {
            result += (dayRented - 2) * 1.5;
        }
        return result;
    }
}

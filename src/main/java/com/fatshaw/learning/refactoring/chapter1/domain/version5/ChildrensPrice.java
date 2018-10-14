package com.fatshaw.learning.refactoring.chapter1.domain.version5;

class ChildrensPrice implements Price {

    @Override
    public double getCharge(int dayRented) {

        double result = 1.5;
        if (dayRented > 3) {
            result += (dayRented - 3) * 1.5;
        }
        return result;

    }

}

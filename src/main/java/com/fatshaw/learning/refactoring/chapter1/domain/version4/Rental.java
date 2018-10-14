package com.fatshaw.learning.refactoring.chapter1.domain.version4;

class Rental {

    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public double getCharge() {
        // move method
        // 不要在一个对象中switch另一个对象
        // 封装计算逻辑到movie内部
        return movie.getCharge(daysRented);
    }

    public double getFrequentRenterPoints() {
        // move method
        // 封装计算逻辑到movie内部
        return movie.getFrequentRenterPoints(daysRented);
    }
}

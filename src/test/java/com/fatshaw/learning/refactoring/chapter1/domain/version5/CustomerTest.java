package com.fatshaw.learning.refactoring.chapter1.domain.version5;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CustomerTest {

    public static final int DAYS_RENTED = 3;
    public static final int FOUR_DAYS_RENTED = 4;

    public static final String REGULAR_MOVIE_TITLE = "regular_movie_title";
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String NEW_RELEASE_TITLE = "new_release_title";
    public static final String CHILDREN_TITLE = "children_title";

    @Test
    public void one_regular_movie_for_three_days_statement() {
        Customer customer = new Customer(CUSTOMER_NAME);
        customer.addRental(new Rental(new Movie(REGULAR_MOVIE_TITLE, Movie.REGULAR), DAYS_RENTED));
        assertThat(customer.statement(), is("Rental Record for customer_name\n"
            + "\tregular_movie_title\t3.5\n"
            + "Amount owned is 3.5\n"
            + "You earned 1.0 frequent renter points"));
    }

    @Test
    public void one_regular_movie_with_new_movie_for_three_days_statement() {
        Customer customer = new Customer(CUSTOMER_NAME);
        customer.addRental(new Rental(new Movie(REGULAR_MOVIE_TITLE, Movie.REGULAR), DAYS_RENTED));
        customer.addRental(new Rental(new Movie(NEW_RELEASE_TITLE, Movie.NEW_RELEASE), DAYS_RENTED));
        assertThat(customer.statement(), is("Rental Record for customer_name\n"
            + "\tregular_movie_title\t3.5\n"
            + "\tnew_release_title\t9.0\n"
            + "Amount owned is 12.5\n"
            + "You earned 3.0 frequent renter points"));
    }

    @Test
    public void one_children_movie_for_three_day_statement() {
        Customer customer = new Customer(CUSTOMER_NAME);
        customer.addRental(new Rental(new Movie(CHILDREN_TITLE, Movie.CHILDRENS), DAYS_RENTED));
        assertThat(customer.statement(), is("Rental Record for customer_name\n"
            + "\tchildren_title\t1.5\n"
            + "Amount owned is 1.5\n"
            + "You earned 1.0 frequent renter points"));
    }

    @Test
    public void one_children_movie_for_four_day_statement() {
        Customer customer = new Customer(CUSTOMER_NAME);
        customer.addRental(new Rental(new Movie(CHILDREN_TITLE, Movie.CHILDRENS), FOUR_DAYS_RENTED));
        assertThat(customer.statement(), is("Rental Record for customer_name\n"
            + "\tchildren_title\t3.0\n"
            + "Amount owned is 3.0\n"
            + "You earned 1.0 frequent renter points"));
    }

}
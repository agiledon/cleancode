package zhangyi.refactoring.mfbook.view;

import zhangyi.refactoring.mfbook.model.Customer;
import zhangyi.refactoring.mfbook.model.Movie;
import zhangyi.refactoring.mfbook.model.Rental;

public class CustomerFixture {
    public static final String REGULAR_MOVIE_NAME = "Brave Heart";
    public static final String NEW_RELEASE_MOVIE_NAME = "Iron Man";
    public static final String CHILDREN_MOVIE = "Kongfu Panda";
    public static final String CUSTOMER_NAME = "zhangyi";

    public static Movie createClidrenMovie() {
        return new Movie(CHILDREN_MOVIE, 2);
    }

    public static Movie createNewReleaseMovie() {
        return new Movie(NEW_RELEASE_MOVIE_NAME, 1);
    }

    public static Movie createRegularMovie() {
        return new Movie(REGULAR_MOVIE_NAME, 0);
    }

    public static Customer createCustomerWithThreeRentals() {
        Customer customer = new Customer(CUSTOMER_NAME);
        Rental rentalForRegularMovie = new Rental(createRegularMovie(), 3);
        Rental rentalForNewReleaseMovie = new Rental(createNewReleaseMovie(), 3);
        Rental rentalForChildrenMovie = new Rental(createClidrenMovie(), 3);

        customer.addRental(rentalForRegularMovie);
        customer.addRental(rentalForNewReleaseMovie);
        customer.addRental(rentalForChildrenMovie);
        return customer;
    }
}
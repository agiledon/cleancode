package zhangyi.refactoring.mfbook;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
    private Customer customer;
    private Movie regularMovie;
    private Movie newReleaseMovie;
    private Movie childrenMovie;

    @Before
    public void setUp() {
        customer = new Customer("zhangyi");
        regularMovie = new Movie("Brave Heart", Movie.REGULAR);
        newReleaseMovie = new Movie("Iron Man", Movie.NEW_RELEASE);
        childrenMovie = new Movie("Kongfu Panda", Movie.CHILDREN);
    }

    @Test
    public void test_get_total_charge_no_rentals() {
        assertEquals(0.0, customer.getTotalCharge(), 0.01);
    }

    @Test
    public void test_get_total_charge_regular_movie_days_rented_less_than_or_equal_to_2() {
        Rental rental = new Rental(regularMovie, 2);
        customer.addRental(rental);
        assertEquals(2.0, customer.getTotalCharge(), 0.01);
    }

    @Test
    public void test_get_total_charge_regular_movie_days_rented_greater_than_2() {
        Rental rental = new Rental(regularMovie, 3);
        customer.addRental(rental);
        assertEquals(3.5, customer.getTotalCharge(), 0.01);
    }

    @Test
    public void test_get_total_charge_new_release_movie_days_rented_less_than_or_equal_to_1() {
        Rental rental = new Rental(newReleaseMovie, 1);
        customer.addRental(rental);
        assertEquals(3.0, customer.getTotalCharge(), 0.01);
    }

    @Test
    public void test_get_total_charge_new_release_movie_days_rented_greater_than_1() {
        Rental rental = new Rental(newReleaseMovie, 3);
        customer.addRental(rental);
        assertEquals(9.0, customer.getTotalCharge(), 0.01);
    }

    @Test
    public void test_get_total_charge_children_movie_days_rented_less_than_or_equal_to_3() {
        Rental rental = new Rental(childrenMovie, 3);
        customer.addRental(rental);
        assertEquals(1.5, customer.getTotalCharge(), 0.01);
    }

    @Test
    public void test_get_total_charge_children_movie_days_rented_greater_than_3() {
        Rental rental = new Rental(childrenMovie, 4);
        customer.addRental(rental);
        assertEquals(3.0, customer.getTotalCharge(), 0.01);
    }

    @Test
    public void test_get_total_charge_multiple_rentals() {
        Rental rentalForRegularMovie = new Rental(regularMovie, 3);
        Rental rentalForNewReleaseMovie = new Rental(newReleaseMovie, 3);
        Rental rentalForChildrenMovie = new Rental(childrenMovie, 3);
        customer.addRental(rentalForRegularMovie);
        customer.addRental(rentalForNewReleaseMovie);
        customer.addRental(rentalForChildrenMovie);
        assertEquals(14.0, customer.getTotalCharge(), 0.01);
    }

    @Test
    public void test_get_total_frequent_renter_points_no_rentals() {
        assertEquals(0, customer.getTotalFrequentRenterPoints());
    }

    @Test
    public void test_get_total_frequent_renter_points_regular_movie() {
        Rental rental = new Rental(regularMovie, 3);
        customer.addRental(rental);
        assertEquals(1, customer.getTotalFrequentRenterPoints());
    }

    @Test
    public void test_get_total_frequent_renter_points_new_release_movie_days_rented_less_than_or_equal_to_1() {
        Rental rental = new Rental(newReleaseMovie, 1);
        customer.addRental(rental);
        assertEquals(1, customer.getTotalFrequentRenterPoints());
    }

    @Test
    public void test_get_total_frequent_renter_points_new_release_movie_days_rented_greater_than_1() {
        Rental rental = new Rental(newReleaseMovie, 3);
        customer.addRental(rental);
        assertEquals(2, customer.getTotalFrequentRenterPoints());
    }

    @Test
    public void test_get_total_frequent_renter_points_children_movie() {
        Rental rental = new Rental(childrenMovie, 4);
        customer.addRental(rental);
        assertEquals(1, customer.getTotalFrequentRenterPoints());
    }

    @Test
    public void test_get_total_frequent_renter_points_multiple_rentals() {
        Rental rentalForRegularMovie = new Rental(regularMovie, 3);
        Rental rentalForNewReleaseMovie = new Rental(newReleaseMovie, 3);
        Rental rentalForChildrenMovie = new Rental(childrenMovie, 3);
        customer.addRental(rentalForRegularMovie);
        customer.addRental(rentalForNewReleaseMovie);
        customer.addRental(rentalForChildrenMovie);
        assertEquals(4, customer.getTotalFrequentRenterPoints());
    }

    @Test
    public void test_get_name() {
        assertEquals("zhangyi", customer.getName());
    }

    @Test
    public void test_get_rentals_no_rentals() {
        assertEquals(0, customer.getRentals().size());
    }

    @Test
    public void test_get_rentals_with_rentals() {
        Rental rental = new Rental(regularMovie, 3);
        customer.addRental(rental);
        assertEquals(1, customer.getRentals().size());
        assertEquals(rental, customer.getRentals().get(0));
    }
}

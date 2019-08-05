package zhangyi.refactoring.mfbook;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CustomerTest {
    public static final String REGULAR_MOVIE_NAME = "Brave Heart";
    public static final String NEW_RELEASE_MOVIE_NAME = "Iron Man";
    public static final String CUSOMTER_NAME = "zhangyi";
    public static final String CHILDREN_MOVIE = "Kongfu Panda";
    private Movie regularMovie;
    private Customer customer;
    private Movie newReleaseMovie;
    private Movie childrenMovie;

    @Before
    public void setUp() throws Exception {
        regularMovie = new Movie(REGULAR_MOVIE_NAME, 0);
        customer = new Customer(CUSOMTER_NAME);
        newReleaseMovie = new Movie(NEW_RELEASE_MOVIE_NAME, 1);
        childrenMovie = new Movie(CHILDREN_MOVIE, 2);
    }

    @Test
    public void should_statement_for_regular_movie_and_rental_days_less_than_or_equal_to_2() {
        Rental rental = new Rental(regularMovie, 2);
        Customer customer = new Customer(CUSOMTER_NAME);
        customer.addRental(rental);

        double expectedTotalAmount = 2.0;
        int expectedFrequentRenterPoints = 1;
        assertThat(Customer.statement(customer), is(result(CUSOMTER_NAME, REGULAR_MOVIE_NAME, expectedTotalAmount, expectedFrequentRenterPoints)));
    }

    @Test
    public void should_statement_for_regular_movie_and_rental_days_greater_than_2() {
        Rental rental = new Rental(regularMovie, 3);
        customer.addRental(rental);

        double expectedTotalAmount = 3.5;
        int expectedFrequentRenterPoints = 1;
        assertThat(Customer.statement(customer), is(result(CUSOMTER_NAME, REGULAR_MOVIE_NAME, expectedTotalAmount, expectedFrequentRenterPoints)));
    }

    @Test
    public void should_statement_for_new_release_movie_and_rental_days_less_than_or_equal_to_1() {
        Rental rental = new Rental(newReleaseMovie, 1);
        customer.addRental(rental);

        double expectedTotalAmount = 3.0;
        int expectedFrequentRenterPoints = 1;
        assertThat(Customer.statement(customer), is(result(CUSOMTER_NAME, NEW_RELEASE_MOVIE_NAME, expectedTotalAmount, expectedFrequentRenterPoints)));
    }

    @Test
    public void should_statement_for_new_release_movie_and_rental_days_greater_than_1() {
        Rental rental = new Rental(newReleaseMovie, 3);
        customer.addRental(rental);

        double expectedTotalAmount = 9.0;
        int expectedFrequentRenterPoints = 2;
        assertThat(Customer.statement(customer), is(result(CUSOMTER_NAME, NEW_RELEASE_MOVIE_NAME, expectedTotalAmount, expectedFrequentRenterPoints)));
    }

    @Test
    public void should_statement_for_children_movie_and_rental_days_less_than_or_equal_to_3() {
        Rental rental = new Rental(childrenMovie, 3);
        customer.addRental(rental);

        double expectedTotalAmount = 1.5;
        int expectedFrequentRenterPoints = 1;
        assertThat(Customer.statement(customer), is(result(CUSOMTER_NAME, CHILDREN_MOVIE, expectedTotalAmount, expectedFrequentRenterPoints)));
    }

    @Test
    public void should_statement_for_children_movie_and_rental_days_greater_than_3() {
        Rental rental = new Rental(childrenMovie, 4);
        customer.addRental(rental);

        double expectedTotalAmount = 3.0;
        int expectedFrequentRenterPoints = 1;
        assertThat(Customer.statement(customer), is(result(CUSOMTER_NAME, CHILDREN_MOVIE, expectedTotalAmount, expectedFrequentRenterPoints)));
    }

    @Test
    public void should_statement_for_all_kinds_of_movie() {
        Rental rentalForRegularMovie = new Rental(regularMovie, 3);
        Rental rentalForNewReleaseMovie = new Rental(newReleaseMovie, 3);
        Rental rentalForChildrenMovie = new Rental(childrenMovie, 3);
        customer.addRental(rentalForRegularMovie);
        customer.addRental(rentalForNewReleaseMovie);
        customer.addRental(rentalForChildrenMovie);

        assertThat(Customer.statement(customer), is("Rental Record for zhangyi\n\t" +
                "Brave Heart\t3.5\n" +
                "\tIron Man\t9.0\n" +
                "\tKongfu Panda\t1.5\n" +
                "Amount owed is 14.0\n" +
                "You earned 4 frequent renter points"));

    }

    private String result(String customerName, String movieName, double totalAmout, int frequentRenterPoints) {
        return String.format("Rental Record for %s\n\t" +
                "%s\t%s\nAmount owed is %s\n" +
                "You earned %d frequent renter points", customerName, movieName, totalAmout, totalAmout, frequentRenterPoints);
    }
}

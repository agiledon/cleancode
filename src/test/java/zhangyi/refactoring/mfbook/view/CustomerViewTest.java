package zhangyi.refactoring.mfbook.view;

import org.junit.Before;
import org.junit.Test;
import zhangyi.refactoring.mfbook.model.Customer;
import zhangyi.refactoring.mfbook.model.Movie;
import zhangyi.refactoring.mfbook.model.Rental;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static zhangyi.refactoring.mfbook.view.CustomerFixture.*;

public class CustomerViewTest {
    private Movie regularMovie;
    private Customer customer;

    private CustomerView customerView;
    private Movie newReleaseMovie;
    private Movie childrenMovie;

    @Before
    public void setUp() {
        regularMovie = createRegularMovie();
        newReleaseMovie = createNewReleaseMovie();
        childrenMovie = createClidrenMovie();
        customer = new Customer(CUSTOMER_NAME);
        customerView = new CustomerPlainTextView(customer);
    }

    @Test
    public void should_statement_for_regular_movie_and_rental_days_less_than_or_equal_to_2() {
        Rental rental = new Rental(regularMovie, 2);
        customer.addRental(rental);

        double expectedTotalAmount = 2.0;
        int expectedFrequentRenterPoints = 1;
        assertThat(customerView.statement(), is(result(CUSTOMER_NAME, REGULAR_MOVIE_NAME, expectedTotalAmount, expectedFrequentRenterPoints)));
    }

    @Test
    public void should_statement_for_regular_movie_and_rental_days_greater_than_2() {
        Rental rental = new Rental(regularMovie, 3);
        customer.addRental(rental);

        double expectedTotalAmount = 3.5;
        int expectedFrequentRenterPoints = 1;
        assertThat(customerView.statement(), is(result(CUSTOMER_NAME, REGULAR_MOVIE_NAME, expectedTotalAmount, expectedFrequentRenterPoints)));
    }

    @Test
    public void should_statement_for_new_release_movie_and_rental_days_less_than_or_equal_to_1() {
        Rental rental = new Rental(newReleaseMovie, 1);
        customer.addRental(rental);

        double expectedTotalAmount = 3.0;
        int expectedFrequentRenterPoints = 1;
        assertThat(customerView.statement(), is(result(CUSTOMER_NAME, NEW_RELEASE_MOVIE_NAME, expectedTotalAmount, expectedFrequentRenterPoints)));
    }

    @Test
    public void should_statement_for_new_release_movie_and_rental_days_greater_than_1() {
        Rental rental = new Rental(newReleaseMovie, 3);
        customer.addRental(rental);

        double expectedTotalAmount = 9.0;
        int expectedFrequentRenterPoints = 2;
        assertThat(customerView.statement(), is(result(CUSTOMER_NAME, NEW_RELEASE_MOVIE_NAME, expectedTotalAmount, expectedFrequentRenterPoints)));
    }

    @Test
    public void should_statement_for_children_movie_and_rental_days_less_than_or_equal_to_3() {
        Rental rental = new Rental(childrenMovie, 3);
        customer.addRental(rental);

        double expectedTotalAmount = 1.5;
        int expectedFrequentRenterPoints = 1;
        assertThat(customerView.statement(), is(result(CUSTOMER_NAME, CHILDREN_MOVIE, expectedTotalAmount, expectedFrequentRenterPoints)));
    }

    @Test
    public void should_statement_for_children_movie_and_rental_days_greater_than_3() {
        Rental rental = new Rental(childrenMovie, 4);
        customer.addRental(rental);

        double expectedTotalAmount = 3.0;
        int expectedFrequentRenterPoints = 1;
        assertThat(customerView.statement(), is(result(CUSTOMER_NAME, CHILDREN_MOVIE, expectedTotalAmount, expectedFrequentRenterPoints)));
    }

    @Test
    public void should_statement_for_all_kinds_of_movie() {
        customer = createCustomerWithThreeRentals();
        customerView = new CustomerPlainTextView(customer);

        assertThat(customerView.statement(), is("Rental Record for zhangyi\n\t" +
                "Brave Heart\t3.5\n" +
                "\tIron Man\t9.0\n" +
                "\tKongfu Panda\t1.5\n" +
                "Amount owed is 14.0\n" +
                "You earned 4 frequent renter points"));

    }

    private String result(String customerName, String movieName, double totalAmount, int frequentRenterPoints) {
        return String.format("Rental Record for %s\n\t" +
                "%s\t%s\nAmount owed is %s\n" +
                "You earned %d frequent renter points", customerName, movieName, totalAmount, totalAmount, frequentRenterPoints);
    }
}

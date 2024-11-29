package zhangyi.refactoring.mfbook;

class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getFrequentRenterPoints() {
        // add frequent renter points
        int frequentRenterPoints = 1;
        // add bonus for a two days new release rental
        if ((getMovie().getPriceCode() == Movie.NEW_RELEASE)
                &&
                getDaysRented() > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

    public double getCharge() {
        double thisAmount = 0;
        //determine amounts for rental line
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (getDaysRented() > 2)
                    thisAmount += (getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += getDaysRented() * 3;
                break;
            case Movie.CHILDREN:
                thisAmount += 1.5;
                if (getDaysRented() > 3)
                    thisAmount += (getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }
}

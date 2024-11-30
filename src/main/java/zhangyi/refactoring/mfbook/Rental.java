package zhangyi.refactoring.mfbook;

class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public String getMovieTitle() {
        return movie.getTitle();
    }

    public int getFrequentRenterPoints() {
        // add frequent renter points
        int frequentRenterPoints = 1;
        // add bonus for a two days new release rental
        if ((movie.getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1) frequentRenterPoints++;
        return frequentRenterPoints;
    }

    public double getCharge() {
        double thisAmount = 0;
        //determine amounts for rental line
        switch (movie.getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (daysRented > 2)
                    thisAmount += (daysRented - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += daysRented * 3;
                break;
            case Movie.CHILDREN:
                thisAmount += 1.5;
                if (daysRented > 3)
                    thisAmount += (daysRented - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

}

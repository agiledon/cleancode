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
        return movie.getFrequentRenterPoints(daysRented);
    }

    public double getCharge() {
        return movie.getCharge(daysRented);
    }

}

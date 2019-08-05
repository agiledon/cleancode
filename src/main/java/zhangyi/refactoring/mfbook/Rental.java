package zhangyi.refactoring.mfbook;

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

    public int pointsFor() {
        return movie.pointsFor(this.daysRented);
    }

    public double amountFor() {
        return movie.amountFor(daysRented);
    }

}

package zhangyi.refactoring.mfbook;

public enum MovieType {
    REGULAR(new RegularPriceStrategy()),
    NEW_RELEASE(new NewReleasePriceStrategy()),
    CHILDREN(new ChildrenPriceStrategy());

    private final PriceStrategy priceStrategy;

    MovieType(PriceStrategy priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    public PriceStrategy getPriceStrategy() {
        return priceStrategy;
    }
}
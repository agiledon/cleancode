package zhangyi.cleancode.slap.entity;

public class Course {
    public static final int ENTERPRISE = 0;
    public static final int COMMUNITY = 1;
    public static final int COMMONWEAL = 2;

    private String title;
    private int priceCode;

    public Course(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int arg) {
        priceCode = arg;
    }

    public String getTitle() {
        return title;
    }
}

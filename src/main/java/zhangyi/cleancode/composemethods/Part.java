package zhangyi.cleancode.composemethods;

public class Part {
    private String name;
    private String brand;
    private double retailPrice;

    public Part() {
    }

    public Part(String name, String brand, double retailPrice) {
        this.name = name;
        this.brand = brand;
        this.retailPrice = retailPrice;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double totalPrice() {
        return getRetailPrice() * 0.8;
    }
}

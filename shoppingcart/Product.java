package shoppingcart;

public class Product {
    private String name, category, brand, description;
    private double price;
    private int stock;

    public Product(String name, String category, String brand, String description, double price, int stock) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getBrand() { return brand; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void reduceStock() { stock--; }
    public void increaseStock() { stock++; }
}

import java.time.LocalDate;

public class ProductExpirableShippable extends Product implements Shippable , Expirable  {

     private LocalDate expiryDate;
    private double weight;

    public ProductExpirableShippable(String name, double price, int stock, LocalDate expiryDate, double weight) {
        super(name, price, stock);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return getProductName();
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
    
}

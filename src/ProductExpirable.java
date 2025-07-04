import java.time.LocalDate;

public class ProductExpirable extends Product implements Expirable {

    private LocalDate expiryDate;

    public ProductExpirable(String name, double price, int stock, LocalDate expiryDate) {
        super(name, price, stock);
        this.expiryDate = expiryDate;
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
    
}

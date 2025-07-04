public class ProductShippable extends Product implements Shippable {

     private double weight;

    public ProductShippable(String name, double price, int stock, double weight) {
        super(name, price, stock);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return getProductName();
    }
    
}

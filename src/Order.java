import java.util.List;
import java.util.ArrayList;


public class Order {
    private Customer customer;
    private String area;
    private double totalPrice;
    private OrderStatus status;
    private List<Product> products;


    public Order(Customer customer, String area, List<Product> products) {
        this.customer = customer;
        this.area = area;
        totalPrice=0;
        for(Product product:products){
            totalPrice+=product.getProductPrice();
        }
        this.status = OrderStatus.PENDING; 
        this.products = new ArrayList<>(products); 

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);  
    }

    public void setProducts(List<Product> products) {
        this.products = new ArrayList<>(products); 
    }

    public void setOrderStatus(OrderStatus status) {
        this.status = status;
    }

}



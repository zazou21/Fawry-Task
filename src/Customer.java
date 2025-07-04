
import java.util.ArrayList;
import java.util.List;



public class Customer {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$"; // For email validation / not the most accurate but for demonstration
    private String name;
    private String email;
    private String phoneNumber;
    private Cart cart;
    private List<Order> orders;
    private double balance;
    

    private boolean isValidEmail(String email){
        boolean validEmail=email.matches(EMAIL_REGEX);
        return validEmail;
    }

    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        if(!isValidEmail(email)){
            throw new IllegalArgumentException("Invalid email : " + email);
        }
        this.phoneNumber = phoneNumber;
        orders=new ArrayList<>();

      
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Cart getCart() {
        if(cart == null){
            cart=new Cart();
        }
        return cart;
    }
    public void addToCart(CartItem item){
        if (this.cart == null) {
            this.cart = new Cart();
        }

        cart.addCartItem(item.getItemProduct(),item.getQuantity());

    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance=balance;
    }

    public List<Order> getOrders(){
        return orders;
    }

    public void addOrder(Order order){
        orders.add(order);
    }
    
}





import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems;
    private double totalPrice;

    public Cart(){
        this.cartItems = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public Cart(Cart cart){
        this.cartItems = new ArrayList<>(cart.cartItems);
        this.totalPrice = cart.totalPrice;
    }

    public void clearCart(){
        cartItems.clear();
        totalPrice=0.0;
    }


    public void addCartItem(Product product,int quantity) {
        if(product==null){
            System.out.println("can't add a null product");
            return;
        }   
        CartItem item=new CartItem(product,quantity);
        int stock=product.getStock();


        if(quantity<stock){
        cartItems.add(item);
        totalPrice += item.getItemPrice(); 
        product.setStock(stock-quantity);
        }


        else{
            System.out.println("Quantity exceeds product stock");
            
            return;
        }

    }
    public void removeProduct(CartItem item) {
        if (cartItems.remove(item)) {
            totalPrice -= item.getItemPrice();
        }
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public List<CartItem> getCartItems(){
        return new ArrayList<>(cartItems);

    }

    
}

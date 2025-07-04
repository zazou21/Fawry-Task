import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        // Create sample products
        ProductShippable tv = new ProductShippable("TV", 5000, 10, 15.0);
        ProductExpirable biscuits = new ProductExpirable("Biscuits", 30, 50, LocalDate.now().plusDays(10));
        ProductExpirableShippable cheese = new ProductExpirableShippable("Cheese", 100, 20, LocalDate.now().plusDays(5), 1.2);

        // Create Customer
        Customer customer = new Customer("Alice", "alice@example.com", "0100000000");
        customer.setBalance(10000);  // Enough balance

        // Add Products to Cart
        Cart cart = customer.getCart();
        cart.addCartItem(tv, 1);         // Shippable only
        cart.addCartItem(biscuits, 2);   // Expirable only
        cart.addCartItem(cheese, 3);     // Shippable + Expirable

        // Checkout
        // this run should be successful
        Checkout checkout = new Checkout(customer);
        checkout.process("Heliopolis", new ShippingService());


        
        ProductExpirable biscuits_expired = new ProductExpirable("Biscuits_Expired", 30, 50, LocalDate.now().minusDays(10));
        Cart cart_with_expired = customer.getCart(); 

        cart_with_expired.addCartItem(biscuits_expired, 2);     

        Checkout checkout_expired = new Checkout(customer);
        checkout_expired.process("Heliopolis", new ShippingService());

        cart.clearCart();

        // This is the case if the customer tries to add to cart a product that already is out of stock


        ProductShippable out_of_stock_tv = new ProductShippable("OutOfStockTV", 5000, 0, 15.0);
        Cart cart_out_of_stock = customer.getCart();
        cart_out_of_stock.addCartItem(out_of_stock_tv, 1); // Out of stock product
        Checkout checkout_out_of_stock = new Checkout(customer);
        checkout_out_of_stock.process("Heliopolis", new ShippingService());


        // This is the case where in between the customer adds the product and proceeds to checkout someone else finished the stock so the checkout should actually show empty cart

        ProductShippable out_of_stock_cheese = new ProductShippable("OutOfStockCheese", 30, 10, 1.0);
        cart_out_of_stock.addCartItem(out_of_stock_cheese,5);
        out_of_stock_cheese.setStock(0);
        Checkout new_checkout_out_of_stock= new Checkout(customer);
        new_checkout_out_of_stock.process("Heliopolis",new ShippingService());

        // customer doesn't have enough balance

        ProductShippable insufficient_balance_tv = new ProductShippable("InsufficientBalanceTV", 20000, 5, 20.0);
        Cart cart_insufficient_balance = customer.getCart();
        cart_insufficient_balance.clearCart();
        cart_insufficient_balance.addCartItem(insufficient_balance_tv, 1); // Price exceeds balance
        Checkout checkout_insufficient_balance = new Checkout(customer);
        checkout_insufficient_balance.process("Heliopolis", new ShippingService());


        // not shippable to area

        ProductShippable invalid_area_tv = new ProductShippable("invalidAreaTV",50,2,20.0);
        cart.clearCart();
        cart.addCartItem(invalid_area_tv,1);
        Checkout checkout_invalid_area = new Checkout(customer);
        checkout_invalid_area.process("invalid",new ShippingService());
    }

     


}

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class Checkout {

    private Customer customer;
    private Cart cart;
private static final Map<String, Double> SHIPPING_FEES = new HashMap<>();
static {
    SHIPPING_FEES.put("Heliopolis", 20.0);
    SHIPPING_FEES.put("Madinet Nasr", 35.0);
    SHIPPING_FEES.put("6 October", 50.0);
}


public void printReceipt(double shippingFee) {
    System.out.println("** Checkout receipt **");
    double subtotal = 0.0;
    for (CartItem item : cart.getCartItems()) {
        System.out.printf("%dx %s\t%.0f%n", item.getQuantity(), item.getItemProduct().getProductName(), item.getItemPrice());
        subtotal += item.getItemPrice();
    }
    double total = subtotal + shippingFee;
    System.out.println("-----------------------");
    System.out.printf("Subtotal\t%.0f%n", subtotal);
    System.out.printf("Shipping\t%.0f%n", shippingFee);
    System.out.printf("Amount\t\t%.0f%n", total);
    System.out.println("-----------------------");
}


    public Checkout(Customer customer) {
        this.customer = customer;
        this.cart = customer.getCart();
    }

    public boolean hasEnoughBalance(double total) {
        return customer.getBalance() >= total;
    }

    public boolean areItemsValid() {
        List<CartItem> items = cart.getCartItems();

        for (CartItem item : items) {
            Product product = item.getItemProduct();

           // compare stock with quantity
            if (item.getQuantity() > product.getStock()) {
                System.out.println("Error: Product '" + product.getProductName() + "' is out of stock.");
                System.out.println("----------------------------");
                return false;
            }

            // Check expiry if applicable
            if (product instanceof Expirable) {
                Expirable expirable = (Expirable) product;
                if (expirable.getExpiryDate().isBefore(java.time.LocalDate.now())) {
                    System.out.println("Error: Product '" + product.getProductName() + "' has expired.");
                    System.out.println("---------------------");
                    return false;
                }
            }
        }
        return true;
    }

    public double subtotal() {
        double subtotal = 0.0;
        for (CartItem item : cart.getCartItems()) {
            subtotal += item.getItemPrice();
        }
        return subtotal;
    }

    public double shippingFee(String area) {
        if (area == null || area.isEmpty()) {
            System.out.println("Error: Shipping area is not specified.");
            return 0.0;
        }
        if (!SHIPPING_FEES.containsKey(area)) {
            System.out.println("Error: Invalid shipping area specified.");
            return 0.0;
        }
        return SHIPPING_FEES.getOrDefault(area, 0.0);
    }

    public void process(String area, ShippingService shippingService) {
        if (cart == null || cart.getCartItems().isEmpty()) {
            System.out.println("Error: Cart is empty.");
            System.out.println("-----------------");
            return;
        }

        if (!areItemsValid()) {
            return;
        }

        double subtotal = subtotal();
        double shippingFee = shippingFee(area);
        if (shippingFee == 0.0) {
            System.out.println("Error: Invalid shipping area.");
            return;
        }
        double total = subtotal + shippingFee;

        if (!hasEnoughBalance(total)) {
            System.out.println("Error: Insufficient balance.");
            System.out.println("-----------------");
            return;
        }

        // Deduct payment and reduce stock
        customer.setBalance(customer.getBalance() - total);
        for (CartItem item : cart.getCartItems()) {
            item.getItemProduct().reduceStock(item.getQuantity());
        }

        // Create order
        List<Product> orderedProducts = new ArrayList<>();
        for (CartItem item : cart.getCartItems()) {
            orderedProducts.add(item.getItemProduct());
        }
        Order order = new Order(customer, area, orderedProducts);
        order.setTotalPrice(subtotal);
        customer.addOrder(order);

        
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : cart.getCartItems()) {
            Product product = item.getItemProduct();
            if (product instanceof Shippable) {
                shippableItems.add((Shippable) product);
            }
        }
        if (!shippableItems.isEmpty()) {
            shippingService.ship(shippableItems);
        }

        printReceipt(shippingFee);

        // Clear cart
        cart.clearCart();
    }
}

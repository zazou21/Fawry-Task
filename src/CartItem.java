public class CartItem {

    private Product product;
    private int quantity;
    private double cartItemPrice;

    public CartItem(Product product, int quantity){
        this.product=product;
        this.quantity=quantity;
        cartItemPrice=product.getProductPrice() * quantity;
    }

    public double getItemPrice(){
        return cartItemPrice;
    }

    public Product getItemProduct(){
        return product;
    }
    public int getQuantity(){
        return quantity;
    }


}

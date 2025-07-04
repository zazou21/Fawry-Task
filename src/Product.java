public class Product {
    String product_name;
    double product_price;
    int stock;

    

public Product(String product_name, double product_price,int stock) {
    this.product_name = product_name;
    this.product_price = product_price;
    this.stock=stock;
}

public String getProductName() {
    return product_name;
}

public void setProductName(String product_name) {
    this.product_name = product_name;
}

public double getProductPrice() {
    return product_price;

}


public void setProductPrice(double product_price) {
    this.product_price = product_price;

}

public int getStock(){
    return stock;
}

public void setStock(int newStock){
    stock=newStock;
}

public void reduceStock(int quantity) {
    if (quantity <= stock) {
        stock -= quantity;
    } else {
        System.out.println("Insufficient stock for " + product_name);
    }

}

}



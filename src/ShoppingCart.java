import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    public List <Product> products;

    public ShoppingCart (){
        this.products = new ArrayList<>();
    }
    // Method to add a product to the shopping cart
    public void addProduct(Product product){
        this.products.add(product);
    }
    // Method to remove a product from the shopping cart
    public void removeProduct (Product product){
        this.products.remove(product);
    }
    // Method to calculate the total price of products in the shopping cart
    public double calculateTotal (double Price){
        double Total = 0;
        for (Product product : products){
            Total += product.getPrice();
        }
        return Total;

    }

    public List<Product> getProducts() {
        return products;
    }
}

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> products;



    public ShoppingCart (){
        this.products = new ArrayList<>();
    }

    public ShoppingCart(WestminsterShoppingManager shoppingManager, java.awt.List productList) {
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
     public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        System.out.println("Total Price: " + total);
        return total;
    }

    public List<Product> getProducts() {
        return products;
    }



    public void addObserver(GUI gui) {
    }

    public void removeObserver(GUI gui) {
    }
}

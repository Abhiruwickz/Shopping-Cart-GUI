import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    public ArrayList <Product> products;
    public ShoppingCart(){
        this.products = new ArrayList<>();
    }
    public void addProduct (Product product){
        products.add(product);
        System.out.println(product.getProductName() + "Product Added to the shopping cart!");
    }

    public void removeProduct (String productName){
        for (Product product : products){
            if (product.getProductName().equals(productName)){
                products.remove(product);
                System.out.println(productName + "Removed from shopping cart!");
                return;
            }
        }
        System.out.println(productName + "is not in the shopping cart!");
    }
    public List <Product>getProducts(){
        return products;
    }
    public double total(){
        double totalAmount = 0;
        for (Product product : products){
            totalAmount +=product.getPrice();

        }
        return totalAmount;
    }
    private boolean isFirstPurchase = true;

    public double calculateFirstPurchaseDiscount(double total){
        if (isFirstPurchase){
            isFirstPurchase = false;
            return total * 0.1;
        }
        return 0;
    }
}
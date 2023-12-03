import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WestminsterShoppingManager {

    private final List <Product> productList;

    public WestminsterShoppingManager() {
        this.productList = new ArrayList<>();
    }
    public void addProduct(Product product){
        if (productList.size() < 50) {
            productList.add(product);
            System.out.println("Product added : " + product.getProductName());
            //saveProductsToFile();
        }
        else {
            System.out.println("Cannot add more products. Maximum is reached");
        }
    }
    public void removeProduct (String productId){
        Product removedProduct = null;
        for (Product product : productList){
            if (product.getProductID().equals(productId)){
                removedProduct = product;
                break;
            }
        }
        if (removedProduct != null){
            productList.remove(removedProduct);
            System.out.println("Product deleted : " + removedProduct.getProductID());
            //saveProductsToFile();
        }
    }



    public double calculateTotal() {
        double total = 0;
        for (Product product : productList) {
            total += product.getPrice();
        }
        System.out.println("Total Price: " + total);
        return total;
    }

    public void displayProducts() {
        Collections.sort(productList, Comparator.comparing(Product::getProductID));
        System.out.println("List of products in the system");
        for (Product product : productList) {
            if (product instanceof Electronics) {
                System.out.println("Product Type : Electronics");
            } else if (product instanceof Clothing) {
                System.out.println("Product Type : Clothing");
            }

        }
    }


}

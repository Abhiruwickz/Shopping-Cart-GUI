import java.io.*;
import java.util.*;

public class WestminsterShoppingManager {

    private final List <Product> productList;

    public WestminsterShoppingManager() {
        this.productList = new ArrayList<>();
    }
    public void addProduct(Product product){
        if (productList.size() < 50) {
            productList.add(product);
            System.out.println("Product added ! ");
            //saveProductsToFile();
        }
        else {
            System.out.println("Cannot add more products. Maximum is reached");
        }
    }
    public boolean removeProduct (String productId){
        Product removedProduct = null;
        for (Product product : productList){
            if (Objects.equals(product.getProductID(), productId)){
                removedProduct = product;
                break;
            }
        }
        if (removedProduct != null) {
            productList.remove(removedProduct);
            System.out.println("Product deleted: " + removedProduct.getProductID());
            return true; // Product found and removed successfully
        } else {
            System.out.println("Product with ID " + productId + " not found!");
            return false; // Product not found
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
            System.out.println(
                    "Product ID : " + product.getProductID() + " " +
                    "Items : " + product.getItems() + " " +
                    "Price : " + product.getPrice());

        }
        SavedToFile();

    }
    public void SavedToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("saveProducts.txt"))) {
            for (Product product : productList) {
                writer.write(product.getProductID() + "-");
                writer.write(product.getProductName() + ",");
                writer.write(product.getPrice() + ",");
                writer.write(product.getItems() + ",");

                if (product instanceof Electronics) {
                    writer.write("Electronics," + ((Electronics) product) .getWarranty());

                } else if (product instanceof Clothing) {
                    writer.write("Clothing," + ((Clothing ) product) .getSize());

                }
                writer.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public List<Product>loadFromFile(){
        List<Product> loadFromFile = null;
        File file = new File("saveProducts.txt");

        if (file.exists()){
            try (BufferedReader reader = new BufferedReader(new FileReader("saveProducts.txt"))){
                loadFromFile = readProductsFromBufferedReader(reader);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return loadFromFile != null ? loadFromFile : new ArrayList<>();
    }
    private List<Product> readProductsFromBufferedReader(BufferedReader reader) throws IOException {
        List<Product> products = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 4) {
                String productId = parts[0];
                String productName = parts[1];

                try {
                    int availableItems = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);

                    if (parts.length >= 5) {
                        String additionalAttribute = parts[4];

                        if (additionalAttribute.equals("Electronics") && parts.length >= 6) {
                            int warrantyPeriod = Integer.parseInt(parts[5]);
                            products.add(new Electronics(productId, productName, availableItems, price, warrantyPeriod));
                        } else if (additionalAttribute.equals("Clothing") && parts.length >= 6) {
                            String size = parts[5];
                            products.add(new Clothing(productId, productName, availableItems, price, size));
                        } else {
                            // Handle unknown product type
                        }
                    } else {
                        // Handle case when additional attributes are missing
                    }
                } catch (NumberFormatException e) {
                    // Handle the case when parsing of availableItems or price fails
                    System.out.println("Error parsing availableItems or price: " + e.getMessage());
                }
            }
        }

        return products;
    }


}






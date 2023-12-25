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
        productList.sort(Comparator.comparing(Product::getProductID));
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
                String ProductId = parts[0];
                String productName = parts[1];

                try {
                    int Items = Integer.parseInt(parts[2].trim());
                    double price = Double.parseDouble(parts[3].trim());

                    if (parts.length >= 5) {
                        String additionalAttribute = parts[4].trim();

                        if ("Electronics".equals(additionalAttribute) && parts.length >= 6) {
                            try {
                                String Brand = parts[5];
                                int warrantyPeriod = Integer.parseInt(parts[6].trim());
                                products.add(new Electronics(Brand, warrantyPeriod, ProductId, productName,Items, price));
                            } catch (NumberFormatException e) {
                                System.out.println("Error parsing warranty period in line: " + line + ". " + e.getMessage());
                            }
                        } else if ("Clothing".equals(additionalAttribute) && parts.length >= 6) {
                            String color = parts[4].trim();
                            String Size = parts[5].trim();
                            products.add(new Clothing(Size,color,ProductId ,productName,Items, price));
                        } else {
                            // Handle unknown product type or missing attributes
                            System.out.println("Unknown product type or missing attributes in line: " + line);
                        }
                    } else {
                        // Handle case when additional attributes are missing
                        System.out.println("Additional attributes missing for product ID: " + ProductId);
                    }
                } catch (NumberFormatException e) {
                    // Handle the case when parsing of availableItems or price fails
                    System.out.println("Error parsing availableItems or price in line: " + line + ". " + e.getMessage());
                }
            } else {
                // Handle case when basic product information is missing
                System.out.println("Basic product information missing in line: " + line);
            }
        }

        return products;
    }


}






import java.io.*;
import java.util.*;
 public class WestminsterShoppingManager {
     private final ArrayList<Product> ProductList;

     public WestminsterShoppingManager(){
         this.ProductList = new ArrayList<>();
     }
// add product
     public void addProduct (Product product){
         if (ProductList.size()<50){
             ProductList.add(product);
         }
         else {
             System.out.println("Exceeding product limit!");
         }
     }
     public void deleteProduct(String productID){
         Product newProduct = null;
         for (Product product:ProductList){
             if (product.getProductID().equals(productID)){
                 newProduct = product;
                 break;
             }
         }
         if (newProduct != null){
             ProductList.remove(newProduct);
             String type;
             if (newProduct.isElectronics()){
                 type = "Electronics";
                 System.out.println("Deleted: "+ newProduct.getProductName() + "(Type: " + type + ")");
             }
             else {
                 type = "Clothing";
                 System.out.println("Deleted: "+ newProduct.getProductName() + "(Type: " + type + ")");
             }
         }
         else {
             System.out.println("Product with ID" + newProduct + "not found.");
         }
         System.out.println("Total number of products left: " + ProductList.size());
     }

     public void displayProducts(){
         if (ProductList.isEmpty()){
             System.out.println("No products are available in the system.");
             return;
         }
         ProductList.sort(Comparator.comparing(Product::getProductID));
         for (Product product : ProductList){
             product.displayInfo();
         }
     }

     public void saveToFile (String fileName, List<Product>ProductList){
         try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
             for (Product product : ProductList){
                 writer.write(product.getClass().getSimpleName() + "," + product.getProductID() + "," + product.getProductName() + "," + product.getNumItems() + "," + product.getPrice());

                 if (product instanceof Electronics){
                     Electronics electronicProduct = (Electronics) product;
                     writer.write("," + electronicProduct.getBrand() + "," + electronicProduct.getWarrantyPeriod());

                 }
                 else if (product instanceof Clothing){
                     Clothing clothingProduct = (Clothing) product;
                     writer.write("," + clothingProduct.getSize() + "," + clothingProduct.getColor());
                 }
                 writer.newLine();
             }
             System.out.println("Products save to file" + fileName);
         }
         catch (IOException e) {
             System.out.println("Error! saving products to file: " + e.getMessage());
         }
     }
     public List<Product> readFromFile(String fileName) {
         List<Product> ProductList = new ArrayList<>();
         try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
             String line;
             while ((line = reader.readLine()) != null) {
                 String[] data = line.split(",");
                 Product product;
                 if (data[0].equals("Electronics")) {
                     product = new Electronics(data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]),
                             data[5], Integer.parseInt(data[6]));
                 } else {
                     product = new Clothing(data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]),
                             data[5], data[6]);
                 }
                 ProductList.add(product);
             }
             System.out.println("Products read from file: " + fileName);
         } catch (IOException e) {
             System.out.println("Error reading products from file: " + e.getMessage());
         }
         return ProductList;
     }

     public List<Product> getProductList(){
         return ProductList;
     }


}





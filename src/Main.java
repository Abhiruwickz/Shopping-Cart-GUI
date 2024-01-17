import java.util.*;

public class Main {
    public static void main(String[] args){

        ShoppingCart shoppingCart = new ShoppingCart();
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        GUI gui = new GUI();

        List<Product>ProductList = new ArrayList<>();

        Scanner Input = new Scanner(System.in);

        while (true){
            System.out.println("\nMain Menu:");
            System.out.println("1. Add a new product");
            System.out.println("2. Delete a product");
            System.out.println("3. Display Products");
            System.out.println("4. Save to File");
            System.out.println("5. Load from the File");
            System.out.println("6. Display GUI");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = Input.nextInt();
            Input.nextLine();

            switch (choice){

                case 1:
                    System.out.println("Enter the product type (Select 1 for Electronics  Select 2 for Clothes): ");
                    int productType = Input.nextInt();
                    Input.nextLine(); // Consume newline

                    System.out.println("Enter the product ID: ");
                    String productId = Input.nextLine();

                    System.out.println("Enter the product name: ");
                    String productName = Input.nextLine();

                    System.out.println("Enter the number of available items: ");
                    int availableItems = Input.nextInt();

                    System.out.println("Enter the price: ");
                    double price = Input.nextDouble();
                    Input.nextLine(); // Consume newline

                    // Create product based on user inputs
                    if (productType == 1) {
                        System.out.println("Enter the brand: ");
                        String brand = Input.nextLine();

                        System.out.println("Enter the warranty period (in years): ");
                        int warrantyPeriod = Input.nextInt();

                        Electronics electronicProduct = new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod);
                        shoppingManager.addProduct(electronicProduct);
                        ProductList.add(electronicProduct);
                        shoppingCart.addProduct(electronicProduct);
//
                       gui.updateElectronicsTable(productId, productName, price, brand, warrantyPeriod);
                    } else if (productType == 2) {
                        System.out.println("Enter the size:");
                        String size = Input.nextLine();

                        System.out.println("Enter the color:");
                        String color = Input.nextLine();

                        Clothing clothingProduct = new Clothing(productId, productName, availableItems, price, size, color);
                        shoppingManager.addProduct(clothingProduct);
                        ProductList.add(clothingProduct);
                        shoppingCart.addProduct(clothingProduct);

                       gui.updateClothingTable(productId, productName, price, size, color);
                    } else {
                        System.out.println("Invalid product type.");
                    }
                    break;
                case 2:
                    System.out.println("Enter the product ID to delete:");
                    String productIdToDelete = Input.nextLine();
                    shoppingManager.deleteProduct(productIdToDelete);
                    break;
                case 3:
                    shoppingManager.displayProducts();
                    break;
                case 4:
                    shoppingManager.saveToFile("Saved_Products.txt", ProductList);
                    System.out.println("Products saved to file.");
                    break;
                case 5:
                    List<Product> savedProducts = shoppingManager.readFromFile("Saved_Products.txt");
                    if (savedProducts != null) {
                        ProductList.addAll(savedProducts);
                        System.out.println("Products read from file.");
                    } else {
                        System.out.println("Unable to read products from file.");
                    }
                    break;
                case 6:
                case 7:
                    System.out.println("Exiting the application");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }
}
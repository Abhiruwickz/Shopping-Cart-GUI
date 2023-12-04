import java.nio.file.WatchEvent;
import java.util.*;

public class Main {
    public static void main (String[] args){
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Scanner Input = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Display Products");
            System.out.println("4. Saved to File");
            System.out.println("5. Load From the File");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = Input.nextInt();
            Input.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addProduct(shoppingManager, Input);
                    break;
                case 2:
                    shoppingManager.displayProducts();
                    break;
                case 3:
                    System.out.println("Remove Products");
                case 4:
                    System.out.println("Products Saved to the file !");
                    shoppingManager.SavedToFile();
                case 5:
                    System.out.println("Load Products FRom the File !");
                    shoppingManager.loadFromFile();
                case 6:
                    System.out.println("Exiting the application.");
                    System.exit(0);
                case 7:
                    System.out.println("Display Gui");
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void addProduct(WestminsterShoppingManager shoppingManager, Scanner scanner) {
        System.out.println("Enter product details:");
        System.out.print("Product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Product Name: ");
        String productName = scanner.nextLine();
        System.out.print("Available Items: ");
        int availableItems = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        System.out.println("Choose product type:");
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        System.out.print("Enter your choice: ");
        int productTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (productTypeChoice == 1) {
            System.out.print("Enter warranty period: ");
            int warrantyPeriod = scanner.nextInt();
            shoppingManager.addProduct(new Electronics(productId, productName, availableItems, price, warrantyPeriod));
        } else if (productTypeChoice == 2) {
            System.out.print("Enter clothing size: ");
            String size = scanner.nextLine();
            shoppingManager.addProduct(new Clothing(productId, productName, availableItems, price, size));
        } else {
            System.out.println("Invalid product type choice.");

        }
        System.out.println("Total of the products : " + shoppingManager.calculateTotal());

    }
}







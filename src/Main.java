import java.nio.file.WatchEvent;
import java.util.*;

public class Main {
    public static void main (String[] args){
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Display Products");
            System.out.println("4. Saved to File");
            System.out.println("5. Load From the File");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addProduct(shoppingManager, scanner);
                    break;
                case 2:
                    removeProduct(shoppingManager,scanner);
                    break;
                case 3:
                    shoppingManager.displayProducts();
                    break;
                case 4:
                    shoppingManager.SavedToFile();
                    System.out.println("Products Saved to the file !");
                    break;
                case 5:
                    shoppingManager.loadFromFile();
                    System.out.println("Load Products From the File !");
                    break;
                case 6:
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;
                case 7:
                    System.out.println("Display Gui");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private static void addProduct(WestminsterShoppingManager shoppingManager, Scanner scanner) {
        System.out.println("Enter product details:");
        System.out.print("Product ID: ");
        String ProductId = scanner.nextLine();
        System.out.print("Product Name: ");
        String productName = scanner.nextLine();
        System.out.print("Available Items: ");
        int Items = scanner.nextInt();
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
            shoppingManager.addProduct(new Electronics(ProductId, productName, Items, price, warrantyPeriod));
        } else if (productTypeChoice == 2) {
            System.out.print("Enter clothing size: ");
            String Size = scanner.nextLine();
            shoppingManager.addProduct(new Clothing(ProductId, productName, Items, price, Size));
        } else {
            System.out.println("Invalid product type choice.");

        }
        double totalPrice = shoppingManager.calculateTotal();
        System.out.println("Total of the products : " + totalPrice);

    }
    private static void removeProduct (WestminsterShoppingManager shoppingManager, Scanner scanner){
        System.out.print("Enter the Product ID to remove: ");
        String productIdRemove = scanner.nextLine();
        boolean removed = shoppingManager.removeProduct(productIdRemove);

        if (removed){
            System.out.println("Product with ID : " + " " + productIdRemove + " " + "Removed Successfully !");
        }

        }
}







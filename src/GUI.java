import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends ShoppingCart {
    private JPanel detailsPanel;
    private JTextArea detailsTextArea;
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private ShoppingCart shoppingCart;

    public GUI() {
        shoppingCart = new ShoppingCart();
        frame = new JFrame();
        frame.setSize(500, 550);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Westminster Shopping Cart", SwingConstants.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);


        String[] categories = {"All", "Electronics", "Clothing"};
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);

        JLabel categoryLabel = new JLabel("Select Product Category:");
        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        categoryPanel.add(categoryLabel);
        categoryPanel.add(categoryComboBox);

        //      Shopping cart button
        JButton shoppingCartButton = new JButton("Shopping Cart");
        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayShoppingCart(); // Show shopping cart UI
            }
        });
        categoryPanel.add(shoppingCartButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(categoryPanel, BorderLayout.NORTH);

        // ... (other components setup)

        // Initialize Gui product table with default data
        String column[] = {"Product ID", "Name", "Category", "Price", "Info"};
        String data[][] = {

        };
        model = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(topPanel, BorderLayout.CENTER);

        // Displaying user selections and details
        detailsPanel = new JPanel(new BorderLayout());
        detailsTextArea = new JTextArea(10, 30);
        detailsTextArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(detailsTextArea);
        detailsPanel.add(detailsScrollPane, BorderLayout.CENTER);

        // Add to cart button
        JButton addToCartButton = new JButton("Add to Shopping Cart");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String category = (String) table.getValueAt(selectedRow, 2);
                    String productName = (String) table.getValueAt(selectedRow, 1);
                    double productPrice = Double.parseDouble(table.getValueAt(selectedRow, 3).toString());

                    if (category.equals("Electronics")) {
                        Electronics elecproduct = new Electronics("ID", productName, 1, productPrice, "Brand", 12);
                        shoppingCart.addProduct(elecproduct);
                    } else if (category.equals("Clothing")) {
                        Clothing clothproduct = new Clothing("ID", productName, 1, productPrice, "Medium", "Blue");
                        shoppingCart.addProduct(clothproduct);
                    } else {
                        System.out.println("Invalid category.");
                        return;
                    }
                } else {
                    System.out.println("No row selected.");
                }
                // Prompt for choice after displaying the product added message
                System.out.print("Enter your choice: ");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(addToCartButton);
        buttonPanel.add(Box.createHorizontalGlue());

        detailsPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add listeners for raw selection in the table
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        String[] labels = {"Product ID:", "Name:", "Category:", "Price:", "Info:"};
                        detailsTextArea.setText(""); // Clear previous details
                        for (int i = 0; i < labels.length; i++) {
                            detailsTextArea.append(labels[i] + " " + table.getValueAt(selectedRow, i) + "\n");
                        }
                    }
                }
            }
        });

        // Add ActionListener for category selection
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryComboBox.getSelectedItem();
                filterTableByCategory(selectedCategory);
            }
        });

        frame.add(detailsPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void displayShoppingCart() {
        JFrame cartFrame = new JFrame("Shopping Cart");
        cartFrame.setSize(600, 400);

        String[] cartColumns = {"Product", "Quantity", "Price"};
        DefaultTableModel cartModel = new DefaultTableModel(cartColumns, 0);

        double total = 0;

        // Count the purchase for each category
        int electronicsCount = 0;
        int clothingCount = 0;

        // Get products from ShoppingCart and updating the table
        for (Product product : shoppingCart.getProducts()) {
            int quantity = product.getNumItems();
            double price = product.getPrice();
            double subtotal = price * quantity;
            total += subtotal;

            if (product instanceof Electronics) {
                electronicsCount += quantity;
            } else if (product instanceof Clothing) {
                clothingCount += quantity;
            }

            cartModel.addRow(new Object[]{product.getProductName(), quantity, price});
        }

        // Total discount calculation based on purchases
        double firstPurchaseDiscount = shoppingCart.calculateFirstPurchaseDiscount(total);
        double threeItemsDiscount = 0;

        // Calculate the 20% discount
        if (electronicsCount >= 3 || clothingCount >= 3) {
            threeItemsDiscount = total * 0.2;
        }

        // After applying discounts updated the table
        double finalTotal = total - firstPurchaseDiscount - threeItemsDiscount;

        JLabel firstPurchaseDiscountLabel = new JLabel("First Purchase Discount (10%): " + firstPurchaseDiscount, SwingConstants.CENTER);
        JLabel threeItemsDiscountLabel = new JLabel("Three Items in same Category Discount (20%): " + threeItemsDiscount, SwingConstants.CENTER);
        JLabel finalTotalLabel = new JLabel("Final amount: " + finalTotal, SwingConstants.CENTER);

        JPanel discountPanel = new JPanel(new GridLayout(3, 1));
        discountPanel.add(firstPurchaseDiscountLabel);
        discountPanel.add(threeItemsDiscountLabel);
        discountPanel.add(finalTotalLabel);

        JScrollPane cartScrollPane = new JScrollPane(new JTable(cartModel));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(cartScrollPane, BorderLayout.CENTER);
        mainPanel.add(discountPanel, BorderLayout.SOUTH);

        cartFrame.add(mainPanel);
        cartFrame.setVisible(true);
    }

    // Helper method to get the quantity of a specific product in the cart
    private int getQuantityInCart(Product product) {
        int quantityInCart = 0;
        for (Product p : shoppingCart.getProducts()) {
            if (p.equals(product)) {
                quantityInCart++;
            }
        }
        return quantityInCart;
    }

    // Method to update table for Electronics products
    public void updateElectronicsTable(String productId, String productName, double price, String brand, int warrantyPeriod) {
        model.addRow(new Object[]{productId, productName, "Electronics", price, brand + ", " + warrantyPeriod + " years warranty"});
    }

    // Method to update table for Clothing products
    public void updateClothingTable(String productId, String productName, double price, String size, String color) {
        model.addRow(new Object[]{productId, productName, "Clothing", price, size + ", " + color});
    }

    // Method to filter the table by category
    private void filterTableByCategory(String category) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        if (!category.equals("All")) {
            RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                @Override
                public boolean include(Entry<?, ?> entry) {
                    String entryCategory = entry.getValue(2).toString();
                    return entryCategory.equals(category);
                }
            };
            sorter.setRowFilter(filter);
        } else {
            sorter.setRowFilter(null);
        }
    }
}

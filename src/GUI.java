import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends ShoppingCart  {
    private JPanel detailsPanel;
    private JTextArea detailsTextArea;
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private ShoppingCart shoppingCart;

    // Constructor accepting a ShoppingCart instance
    public GUI(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
        intializeGUI();
    }
        private void intializeGUI (){
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Westminster Shopping Cart", SwingConstants.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);

        String[] categories = {"All", "Electronics", "Clothing"};
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);

        JLabel categoryLabel = new JLabel("Select Product Category:");
        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        categoryPanel.add(categoryLabel);
        categoryPanel.add(categoryComboBox);

        //Cart Button

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

        // Initialize table with default data
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

        detailsPanel = new JPanel(new BorderLayout());
        detailsTextArea = new JTextArea(10, 30);
        detailsTextArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(detailsTextArea);
        detailsPanel.add(detailsScrollPane, BorderLayout.CENTER);

        //Add to cart button
        JButton addToCartButton = new JButton("Add to Shopping Cart");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String category = (String) table.getValueAt(selectedRow, 2);
                    String productName = (String) table.getValueAt(selectedRow, 1);
                    double productPrice = Double.parseDouble(table.getValueAt(selectedRow, 3).toString());
                    if (category.equals("Electronics")){
                        Electronics electronic = new Electronics("Brand", 1,"ID",productName,12,productPrice);
                        shoppingCart.addProduct(electronic);
                    } else if (category.equals("Clothing")) {
                        Clothing clothingP = new Clothing("Large","White","ID",productName,10,productPrice);
                        shoppingCart.addProduct(clothingP);
                    }
                    else {
                        System.out.println("Invalid Category");
                        return;
                    }
                    // Use the ShoppingCart instance
                    // For example:
                    // shoppingCart.addProduct(new Product(productName, productPrice));
                    // Update the shopping cart and UI accordingly
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

        // Add listeners for table row selection
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

    }
    public void showGUI(){
            frame.setVisible(true);
        }


    public void displayShoppingCart() {
        JFrame cartFrame = new JFrame("Shopping Cart");
        cartFrame.setSize(600,400);

        String [] cartColumn = {"Product" , "Quantity" , "Price"};
        DefaultTableModel cartModel = new DefaultTableModel(cartColumn,0);

        double Total = 0;

        // count purchases by category
        int electronicCount = 0;
        int ClothingPCount = 0;

        // Get product from shopping cart and table populate
        for (Product product : shoppingCart.getProducts()){
            int NumItems = product.getItems();
            double price = product.getPrice();
            double subTotal = price * NumItems;
            Total += subTotal;

            if (product instanceof Electronics){
                electronicCount += NumItems;
            }
            else if (product instanceof Clothing){
                ClothingPCount += NumItems;
            }
            cartModel.addRow(new Object[]{product.getProductName(),NumItems,price});
        }
        cartFrame.setVisible(true);

    }
     public void updateTable(){
         model.setRowCount(0);  // Clear the existing table data

         // Update the table with products from the shopping cart
         for (Product product : shoppingCart.getProducts()) {
             int NumItems = product.getItems();
             double price = product.getPrice();
             String category = product instanceof Electronics ? "Electronics" : "Clothing";

             // Highlight items with reduced availability in red
             Object[] rowData = new Object[]{product.getProductID(), product.getProductName(), category, price, NumItems};
             model.addRow(rowData);

             // Customize cell rendering for the "Quantity" column
             int quantityColumn = 4;  // Assuming the "Quantity" column is the fifth column (index 4)
             TableCellRenderer renderer = new DefaultTableCellRenderer() {
                 @Override
                 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                     Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                     // Highlight items with reduced availability in red
                     if (column == quantityColumn && NumItems < 3) {
                         component.setForeground(Color.RED);
                     } else {
                         component.setForeground(table.getForeground());
                     }

                     return component;
                 }
             };

             table.getColumnModel().getColumn(quantityColumn).setCellRenderer(renderer);
         }
     }
    private void filterTableByCategory(String category) {
        TableRowSorter <TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        if (!category.equals("All")){
            RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                @Override
                public boolean include(Entry<?, ? extends Object> entry) {
                    String entryCategory = entry.getValue(2).toString();
                    return entryCategory.equals(category);
                }
            };
            sorter.setRowFilter(filter);
        }else {
            sorter.setRowFilter(null);
        }

    }

    // Helper method to update table for Electronics products
    public void updateElectronicsTable(String productId, String productName, double price, String brand, int warrantyPeriod) {
        model.addRow(new Object[]{productId, productName, "Electronics", price, brand + ", " + warrantyPeriod + " years warranty"});
    }

    // Helper method to update table for Clothing products
    public void updateClothingTable(String productId, String productName, double price, String size, String color) {
        model.addRow(new Object[]{productId, productName, "Clothing", price, size + ", " + color});
    }
}




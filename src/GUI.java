import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI extends JFrame {
    private DefaultTableModel productTableModel;
    private JTable productTable;
    private JTextArea productDetailsTextArea;
    private JButton addToCartButton;
    private JButton viewShoppingCartButton;
    private List<Product> productList;  // Assuming you have a List<Product> in your WestminsterShoppingManager

    public GUI(List<Product> productList) {
        this.productList = productList;

        setTitle("Shopping App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Product Table
        String[] columnNames = {"Product ID", "Product Name", "Available Items", "Price", "Type", "Additional Attribute"};
        productTableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(productTableModel);
        JScrollPane tableScrollPane = new JScrollPane(productTable);

        // Set up cell renderer to highlight items with reduced availability in red
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                int availableItemsColumnIndex = 2;  // Assuming available items are in the third column
                int availableItems = Integer.parseInt(table.getValueAt(row, availableItemsColumnIndex).toString());

                if (availableItems < 3) {
                    component.setForeground(Color.RED);
                } else {
                    component.setForeground(table.getForeground());
                }
                return component;
            }
        };
        productTable.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);

        // Product Details Panel
        productDetailsTextArea = new JTextArea();
        productDetailsTextArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(productDetailsTextArea);

        // Add-to-Cart Button
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Product selectedProduct = productList.get(selectedRow);
                    // Add the selected product to the shopping cart (implement your shopping cart logic)
                }
            }
        });

        // View Shopping Cart Button
        viewShoppingCartButton = new JButton("Shopping Cart");
        viewShoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the shopping cart (implement your shopping cart display logic)
            }
        });

        // Panel for UI components
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Select Product Type: "));
        panel.add(viewShoppingCartButton);
        panel.add(tableScrollPane);
        panel.add(addToCartButton);

        add(panel, BorderLayout.CENTER);
        add(detailsScrollPane, BorderLayout.SOUTH);

        // Initialize the product table with existing products
        updateProductTable(productList);
    }

    public void updateProductTable(List<Product> productList) {
        // Clear existing rows
        productTableModel.setRowCount(0);

        // Add new rows based on productList
        for (Product product : productList) {
            if (product instanceof Electronics) {
                Electronics electronicsProduct = (Electronics) product;
                productTableModel.addRow(new Object[]{
                        electronicsProduct.getProductID(),
                        electronicsProduct.getProductName(),
                        electronicsProduct.getItems(),
                        electronicsProduct.getPrice(),
                        "Electronics",
                        electronicsProduct.getBrand(),
                        electronicsProduct.getWarranty()
                });
            } else if (product instanceof Clothing) {
                Clothing clothingProduct = (Clothing) product;
                productTableModel.addRow(new Object[]{
                        clothingProduct.getProductID(),
                        clothingProduct.getProductName(),
                        clothingProduct.getItems(),
                        clothingProduct.getPrice(),
                        "Clothing",
                        clothingProduct.getColor(),
                        clothingProduct.getSize()
                });
            }
        }
    }

    public void updateProductTable() {
    }
}


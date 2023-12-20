import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {
    private List<Product> productList;  // Assuming Product is your data model
    private JComboBox<String> productTypeComboBox;
    private DefaultListModel<String> productListModel;
    private JList<String> productListJList;
    private JButton addToCartButton;

    public GUI() {
        productList = new ArrayList<>();  // Initialize or load products

        setTitle("Shopping App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Product Type ComboBox
        productTypeComboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothes"});
        productTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterProducts();
                updateProductList();
            }
        });

        // Product List JList
        productListModel = new DefaultListModel<>();
        productListJList = new JList<>(productListModel);

        // Add-to-Cart Button
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle adding to the shopping cart
                int selectedIndex = productListJList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Product selectedProduct = productList.get(selectedIndex);
                    // Add your logic to handle adding the product to the shopping cart
                    System.out.println("Product added to cart: " + selectedProduct.getProductName());
                }
            }
        });

        // Panel for UI components
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Select Product Type: "));
        panel.add(productTypeComboBox);
        panel.add(new JScrollPane(productListJList));
        panel.add(addToCartButton);

        add(panel, BorderLayout.CENTER);

        filterProducts();
        updateProductList();
    }

    private void filterProducts() {
        String selectedProductType = (String) productTypeComboBox.getSelectedItem();
        if (selectedProductType.equals("All")) {
            // Show all products
            productListModel.clear();
            for (Product product : productList) {
                productListModel.addElement(product.getProductName());
            }
        } else {
            // Show products of the selected type
            productListModel.clear();
            for (Product product : productList) {
                if (product.getProductName().equals(selectedProductType)) {
                    productListModel.addElement(product.getProductName());
                }
            }
        }
    }

    private void updateProductList() {
        productListJList.setModel(productListModel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI shoppingApp = new GUI();
                shoppingApp.setVisible(true);
            }
        });
    }
}

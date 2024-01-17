public abstract class Product{
    protected String productID;
    protected String productName;
    protected int numItems;
    protected double price;

    public Product (String Id, String Name, int Items, double price){
        productID = Id;
        productName = Name;
        numItems = Items;
        this.price = price;
    }
// Getters
    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getNumItems() {
        return numItems;
    }

    public double getPrice() {
        return price;
    }
// Setters
    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public abstract void displayInfo();

    public boolean isElectronics() {
        return false;
    }
}
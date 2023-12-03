public abstract class Product {
    protected String ProductID;
    protected String ProductName;
    protected int Items;
    protected double price;

  public Product(String ProductId , String ProductName, int Items, double price ) {
      this.ProductID = ProductId;
      this.ProductName = ProductName;
      this.Items= Items;
      this.price = price;

  }

    public Product() {

    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getItems() {
        return Items;
    }

    public void setItems(int items) {
        Items = items;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ProductID='" + ProductID + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", Items=" + Items +
                ", price=" + price +
                '}';
    }
}

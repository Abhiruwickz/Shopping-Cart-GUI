public class Electronics extends Product {
    public int warranty;
    public String Brand;

    public Electronics (String Brand,int warranty,String ProductId , String ProductName, int Items, double price ){
        super(ProductId, ProductName, Items, price);
        this.Brand = Brand;
        this.warranty = warranty;
        this.ProductID = ProductId;
        this.Items = Items;
        this.ProductName = ProductName;

    }


    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    @Override
    public String toString() {
        return "Electronics{" +
                "warranty=" + warranty +
                ", Brand='" + Brand + '\'' +
                '}';
    }
}

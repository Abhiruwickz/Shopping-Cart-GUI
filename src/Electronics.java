public class Electronics extends Product{

    public String brand;
    public int warrantyPeriod;

    public Electronics (String Id, String Name, int Items, double price, String brand, int warranty){
        super(Id,Name,Items,price);
        this.brand = brand;
        warrantyPeriod = warranty;
    }
// Getters
    public String getBrand() {
        return brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
// Setters
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public void displayInfo() {
        System.out.println("Electronics - Product ID" + getProductID() +
                "Name:" + getProductName() +
                "Available Items: " + getNumItems() +
                "Price: $" + getPrice() +
                "Brand: "+ getBrand() +
                "Warranty: "+ getWarrantyPeriod() + "years ");



    }

    @Override
    public boolean isElectronics(){
        return true;
    }
}
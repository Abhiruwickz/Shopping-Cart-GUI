public class Clothing extends Product{

    public String size;
    public String color;

    public Clothing(String Id, String Name,int Items, double price, String size, String color){
        super(Id, Name, Items, price);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }
    public String getColor(){
        return color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void displayInfo() {
        System.out.println("Clothing - Product ID: " + getProductID() +
                "Name: " + getProductName() +
                "Available Items: " + getNumItems() +
                "Price: " + getPrice() +
                "Size: " + getSize() +
                "Color: " + getColor());
    }
}
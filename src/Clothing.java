public class Clothing extends Product {
    public String Size;
    public String Color;

    public Clothing (String Size,String color,String ProductId , String ProductName, int Items, double price ){
        super(ProductId, ProductName, Items, price);
        this.Size = Size;
        this.Color= color;
    }

    public Clothing(String productId, String ProductName, int Items, double price, String Size) {
        this.ProductID = productId;
        this.ProductName = ProductName;
        this.price = price;
        this.Size = Size;
        this.Items = Items;
    }

    public String getSize() {
        return Size;
    }
    public void setSize(String size) {
        Size = size;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    @Override
    public String toString() {
        return "Clothing{" +
                "Size='" + Size + '\'' +
                ", Color='" + Color + '\'' +
                '}';
    }
}

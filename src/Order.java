import java.util.ArrayList;

public class Order {
    String itemType;
    double price = 0;

    public Order(String itemType){
        this.itemType = itemType;
    }
    public Order(){
    }

    public double getPrice(){
        return price;
    }
}

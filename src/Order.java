import java.util.ArrayList;
public class Order {
    String itemType;
    String nameType;
    boolean canBeChanged = false;
    double price = 0;

    public Order(String itemType , String nameType){
        this.nameType = nameType;
        this.itemType = itemType;
    }

    public Order(){
    }

    public double getPrice(){
        return price;
    }

}

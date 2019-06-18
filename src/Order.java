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

    public Order(String itemType){
        this.itemType = itemType;
    }

    public Order(){
    }

    public double getPrice(){
        return price;
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}

import java.util.ArrayList;

public class Side extends Order {
    String nameType;
    String size;

    ArrayList<String> contain = new ArrayList<String>();

    public Side(String nameType, String size, String itemType){
        super(itemType);
        this.nameType = nameType;
        this.size = size;
        sideList();
    }

    public Side(String nameType, String itemType){
        super(itemType);
        this.nameType = nameType;
        sideList();
    }

    public void sideList(){
        if(nameType.equals("French Fries")) {
            if (size.equals("Small")) {
                price = 1;
            } else if (size.equals("Medium")) {
                price = 0.1;
            }else if(size.equals("Large")){
                price = 0.1;
            }
        }

        if(nameType.equals("Poutine")) {
            contain.add("Gravy");
            contain.add("Cheese Curds");
            price = 0.1;
        }

        if(nameType.equals("Bacon Poutine")) {
            contain.add("Gravy");
            contain.add("Cheese Curds");
            contain.add("Ripped Bacon");
            price = 0.1;
        }

        if(nameType.equals("Chilli Cheese Fries")) {
            contain.add("Chilli");
            contain.add("Cheese Sauce");
            contain.add("Shredded Cheese");
            price = 0.1;
        }

        if(nameType.equals("Chilli Cheese Nachos")) {
            contain.add("Chilli");
            contain.add("Cheese Sauce");
            contain.add("Nachos");
            price = 0.1;
        }

        if(nameType.equals("Chilli")) {
            if (size.equals("Small")) {
                price = 0.1;
            }else{
                price = 0.1;
            }
        }

        if(nameType.equals("Caesar Side Salad")){
            contain.add("Crumbled Asiago Cheese");
            contain.add("Bacon bits");
            contain.add("Lettue Blend");
            contain.add("Croutons");
            price = 0.1;
        }

        if(nameType.equals("Garden Side Salad")){
            contain.add("Chopped Tomatos");
            contain.add("Shredded Chedder Cheese");
            contain.add("Salad Blend");
            contain.add("Croutons");
            price = 0.1;
        }

        if(nameType.equals("Plain Potato")){
            contain.add("Plain Potato");
            price = 0.1;
        }

        if(nameType.equals("Sour Cream & Chives Potato")){
            contain.add("Chives");
            contain.add("Sour Cream");
            price = 0.1;
        }

        if(nameType.equals("Bacon Cheese Potato")){
            contain.add("Bacon Bits");
            contain.add("Cheese Sauces");
            contain.add("Shredded Cheese");
            price = 0.1;
        }

        if(nameType.equals("Chilli Cheese Potato")){
            contain.add("Chilli");
            contain.add("Cheese Sauces");
            contain.add("Shredded Cheese");
            price = 0.1;
        }
    }
        public String toString(){
            if(nameType.contentEquals("French Fries") || nameType.contentEquals("Chilli")){
                return size + " " + nameType;
            }else{
                return nameType;
            }
        }
}

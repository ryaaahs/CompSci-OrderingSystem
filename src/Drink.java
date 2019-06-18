import java.util.ArrayList;

public class Drink extends Order {
    String drinkType;
    String name;
    String size;
    boolean ice;

    ArrayList<String> item = new ArrayList<String>();

    public Drink(String drinkType, String size, boolean ice, String nameType, String itemType){
        super(itemType, nameType);
        this.drinkType = drinkType;
        this.size = size;
        this.ice = ice;
        typeOfDrink();
    }

    public Drink(String drinkType, String nameType, String itemType){ // For packaged Drinks/Hot Drinks
        super(itemType, nameType);
        this.drinkType = drinkType;
        typeOfDrink();
    }

    public void typeOfDrink(){
        if(drinkType.equals("Soda")){
            if(size.equals("Kids")){
                price = 0.80;
            }else if(size.equals("Value")){ // Also considered a senior drink
                price = 1;
            }else if(size.equals("Small")){
                price = 1.89;
            }else if(size.equals("Medium")){
                price = 2.79;
            }else if(size.equals("Large")){
                price = 3.50;
            }
            canBeChanged = true;
        }

        if(drinkType.equals("Water")){
            price = 1.89;
        }

        if(drinkType.equals("Hot Drink")){
            price = 1.89; //Only comes in one size
            canBeChanged = true;
        }

        if(drinkType.equals("Milk")){
            price = 1.89; // One Size
        }
        //^^ Also Packaged, but holds a different price, look into revamp this later on
        if(drinkType.equals("Packaged Drink")){
            price = 1.89; // One Size
        }

        if(drinkType.equals("Lemonade")){
            if(size.equals("Kids")){
                price = 0.80;
            }else if(size.equals("Value")){ // Also considered a senior drink
                price = 1;
            }else if(size.equals("Small")){
                price = 1.89;
            }else if(size.equals("Medium")){
                price = 2.79;
            }else if(size.equals("Large")){
                price = 3.50;
            }
            canBeChanged = true;
        }
    }
    public String toString() {
        if (size == null) {
            return nameType;
        } else if(drinkType.contentEquals("Hot Drink")){
            return nameType + itemLoop();
        }else{
            return size + " " + nameType;
        }

    }

    public String itemLoop(){
        String list = "";
        for(int i = 0; i < item.size(); i++){
             list = list + "\n" + "    " + item.get(i);
        }
        return list;
    }
}

import java.util.ArrayList;

public class Burger extends Order {
    boolean canCombo;
    String bunType;

    //Use if there a Sandwitch related with the order
    ArrayList<String> crown = new ArrayList<String>();
    ArrayList<String> base = new ArrayList<String>();

    public Burger(String nameType, String itemType) {
        super(itemType, nameType); // Causes an error with y codebased when swapped
        burgerList();
    }

    public void burgerList(){
        //List of all the burgers to order from
        if(nameType.equals("Single Cheese")){
            bunType = "Premium";
            canCombo = true;
            price = 4.19;
            //Crown
            crown.add("Mayo");
            crown.add("Ketchup");
            crown.add("Pickles");
            crown.add("Onion");
            crown.add("Tomato");
            crown.add("Iceberg Lettue");
            //Base
            base.add("Cheese");
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            canBeChanged = true;
        }

        if(nameType.equals("Double Cheese")){
            bunType = "Premium";
            canCombo = true;
            price = 5.19;
            //Crown
            crown.add("Mayo");
            crown.add("Ketchup");
            crown.add("Pickles");
            crown.add("Onion");
            crown.add("Tomato");
            crown.add("Iceberg Lettue");
            //Base
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            canBeChanged = true;
        }

        if(nameType.equals("Triple Cheese")){
            bunType = "Premium";
            canCombo = true;
            price = 6.09;
            //Crown
            crown.add("Mayo");
            crown.add("Ketchup");
            crown.add("Pickles");
            crown.add("Onion");
            crown.add("Tomato");
            crown.add("Iceberg Lettue");
            //Base
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            canBeChanged = true;
        }

        if(nameType.equals("Single Bacon Deluxe")){
            bunType = "Premium";
            canCombo = true;
            price = 5.19;
            //Crown
            crown.add("Mayo");
            crown.add("Ketchup");
            crown.add("Pickles");
            crown.add("Onion");
            crown.add("Tomato");
            crown.add("Iceberg Lettue");
            //Base
            base.add("Cheese");
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("3 Pieces of Bacon");
            canBeChanged = true;
        }

        if(nameType.equals("Double Bacon Deluxe")){
            bunType = "Premium";
            canCombo = true;
            price = 6.19;
            //Crown
            crown.add("Mayo");
            crown.add("Ketchup");
            crown.add("Pickles");
            crown.add("Onion");
            crown.add("Tomato");
            crown.add("Iceberg Lettue");
            //Base
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("3 Pieces of Bacon");
            canBeChanged = true;
        }

        if(nameType.equals("Triple Bacon Deluxe")){
            bunType = "Premium";
            canCombo = true;
            price = 7.09;
            //Crown
            crown.add("Mayo");
            crown.add("Ketchup");
            crown.add("Pickles");
            crown.add("Onion");
            crown.add("Tomato");
            crown.add("Iceberg Lettue");
            //Base
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("3 Pieces of Bacon");
            canBeChanged = true;
        }

        if(nameType.equals("Single Baconator")){
            bunType = "Premium";
            canCombo = true;
            price = 6.19;
            //Crown
            crown.add("Mayo");
            crown.add("Ketchup");
            //Base
            base.add("Cheese");
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("3 Pieces of Bacon");
            canBeChanged = true;
        }

        if(nameType.equals("Double Baconator")){
            bunType = "Premium";
            canCombo = true;
            price = 7.19;
            //Crown
            crown.add("Mayo");
            crown.add("Ketchup");
            //Base
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("3 Pieces of Bacon");
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("3 Pieces of Bacon");
            canBeChanged = true;
        }

        if(nameType.equals("Triple Baconator")){
            bunType = "Premium";
            canCombo = true;
            price = 8.09;
            //Crown
            crown.add("Mayo");
            crown.add("Ketchup");
            //Base
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("3 Pieces of Bacon");
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("3 Pieces of Bacon");
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("3 Pieces of Bacon");
            canBeChanged = true;
        }

        if(nameType.equals("Son of the Baconator")){
            bunType = "Premium";
            canCombo = true;
            price = 4.69;
            //Crown
            crown.add("Mayo");
            crown.add("Ketchup");
            //Base
            base.add("2OZ Meat Patty");
            base.add("Cheese");
            base.add("2 Pieces of Bacon");
            base.add("2OZ Meat Patty");
            base.add("Cheese");
            base.add("2 Pieces of Bacon");
            canBeChanged = true;
        }

        if(nameType.equals("Jr. Hamburger Deluxe")){
            bunType = "Value";
            canCombo = false;
            price = 1.89;
            //Crown
            crown.add("Mayo");
            crown.add("Ketchup");
            crown.add("Pickles");
            crown.add("Onion");
            crown.add("Tomato");
            crown.add("Iceberg Lettue");
            //Base
            base.add("2OZ Meat Patty");
            canBeChanged = true;

        }

        if(nameType.equals("Jr. Cheeseburger Deluxe")){
            bunType = "Value";
            canCombo = false;
            price = 1.89;
            //Crown
            crown.add("Mayo");
            crown.add("Ketchup");
            crown.add("Pickles");
            crown.add("Onion");
            crown.add("Tomato");
            crown.add("Iceberg Lettue");
            //Base
            base.add("2OZ Meat Patty");
            base.add("Cheese");
            canBeChanged = true;
        }

        if(nameType.equals("Jr. Bacon Cheeseburger")){
            bunType = "Value";
            canCombo = false;
            price = 2.18;
            //Crown
            crown.add("Mayo");
            crown.add("Tomato");
            crown.add("Iceberg Lettue");
            //Base
            base.add("2OZ Meat Patty");
            base.add("Cheese");
            base.add("2 Pieces of Bacon");
            canBeChanged = true;
        }

        if(nameType.equals("Cheesey Cheeseburger")){
            bunType = "Value";
            canCombo = false;
            price = 1.89;
            //Crown
            crown.add("Cheese Sauce");
            //Base
            base.add("2OZ Meat Patty");
            base.add("Cheese");
            canBeChanged = true;
        }
    }
    public String toString(){
        return nameType;
    }


}

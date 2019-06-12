import java.util.ArrayList;

public class Burger extends Order {
    boolean canCombo;
    String bunType;

    //Use if there a Sandwitch related with the order
    ArrayList<String> crown = new ArrayList<String>();
    ArrayList<String> base = new ArrayList<String>();

    public Burger(String nameType, String itemType) {
        super(nameType, itemType);
        burgerList();
    }

    public void burgerList(){
        //List of all the burgers to order from
        if(nameType.equals("Single Cheese")){
            bunType = "Premium";
            canCombo = true;
            price = 1;
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
        }

        if(nameType.equals("Double Cheese")){
            bunType = "Premium";
            canCombo = true;
            price = 0.1;
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
        }

        if(nameType.equals("Triple Cheese")){
            bunType = "Premium";
            canCombo = true;
            price = 0.1;
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
        }

        if(nameType.equals("Single Bacon Deluxe")){
            bunType = "Premium";
            canCombo = true;
            price = 0.1;
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
        }

        if(nameType.equals("Double Bacon Deluxe")){
            bunType = "Premium";
            canCombo = true;
            price = 0.1;
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
        }

        if(nameType.equals("Triple Bacon Deluxe")){
            bunType = "Premium";
            canCombo = true;
            price = 0.1;
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
        }

        if(nameType.equals("Single Baconator")){
            bunType = "Premium";
            canCombo = true;
            price = 0.1;
            //Crown
            crown.add("Mayo");
            crown.add("Ketchup");
            //Base
            base.add("Cheese");
            base.add("4OZ Meat Patty");
            base.add("Cheese");
            base.add("3 Pieces of Bacon");
        }

        if(nameType.equals("Double Baconator")){
            bunType = "Premium";
            canCombo = true;
            price = 0.1;
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
        }

        if(nameType.equals("Triple Baconator")){
            bunType = "Premium";
            canCombo = true;
            price = 0.1;
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
        }

        if(nameType.equals("Son of the Baconator")){
            bunType = "Premium";
            canCombo = true;
            price = 0.1;
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
        }

        if(nameType.equals("Jr. Hamburger Deluxe")){
            bunType = "Value";
            canCombo = false;
            price = 0.1;
            //Crown
            crown.add("Mayo");
            crown.add("Ketchup");
            crown.add("Pickles");
            crown.add("Onion");
            crown.add("Tomato");
            crown.add("Iceberg Lettue");
            //Base
            base.add("2OZ Meat Patty");

        }

        if(nameType.equals("Jr. Cheeseburger Deluxe")){
            bunType = "Value";
            canCombo = false;
            price = 0.1;
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
        }

        if(nameType.equals("Jr. Bacon Cheeseburger")){
            bunType = "Value";
            canCombo = false;
            price = 0.1;
            //Crown
            crown.add("Mayo");;
            crown.add("Tomato");
            crown.add("Iceberg Lettue");
            //Base
            base.add("2OZ Meat Patty");
            base.add("Cheese");
            base.add("2 Pieces of Bacon");
        }

        if(nameType.equals("Cheesey Cheeseburger")){
            bunType = "Value";
            canCombo = false;
            price = 0.1;
            //Crown
            crown.add("Cheese Sauce");
            //Base
            base.add("2OZ Meat Patty");
            base.add("Cheese");
        }
    }
    public String toString(){
        return nameType;
    }


}

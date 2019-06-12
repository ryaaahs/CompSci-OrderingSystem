import java.util.ArrayList;

public class Chicken extends Order {
    //Chicken label
    boolean canCombo;
    String bunType;

    //Use if there a Sandwitch related with the order
    ArrayList<String> crown = new ArrayList<String>();
    ArrayList<String> base = new ArrayList<String>();
    ArrayList<String> item = new ArrayList<String>();
    //ArrayList<String> sauce = new ArrayList<String>();

    public Chicken(String nameType, String itemType) {
        super(itemType, nameType);
        this.nameType = nameType;
        chickenList();
    }


    public void chickenList(){
        if(nameType.equals("Spicy Chicken")){
            bunType = "Premium";
            canCombo = true;
            price = 0.1;
            //Crown
            crown.add("Mayo");
            crown.add("Tomato");
            crown.add("Iceberg Lettue");
            //Base
            base.add("Spicy Chicken Breast");

        }

        if(nameType.equals("Homestyle Chicken")){
            bunType = "Premium";
            canCombo = true;
            price = 0.1;
            //Crown
            crown.add("Mayo");
            crown.add("Tomato");
            crown.add("Iceberg Lettue");
            //Base
            base.add("Homestyle Chicken Breast");

        }

        if(nameType.equals("Grilled Chicken")){
            bunType = "Premium";
            canCombo = true;
            price = 0.1;
            //Crown
            crown.add("Honey Mustard");
            crown.add("Tomato");
            crown.add("Spring Mix Lettue");
            //Base
            base.add("Grilled Chicken Breast");

        }

        if(nameType.equals("Spicy Asiago Ranch Club")){
            bunType = "Premium";
            canCombo = true;
            price = 0.1;
            //Crown
            crown.add("Mayo");
            crown.add("Tomato");
            crown.add("Iceberg Lettue");
            //Base
            base.add("Spicy Chicken Breast");
            base.add("Asiago Cheese");
            base.add("3 Pieces of bacon");

        }

        if(nameType.equals("Homestyle Asiago Ranch Club")){
            bunType = "Premium";
            canCombo = true;
            price = 0.1;
            //Crown
            crown.add("Ranch");
            crown.add("Tomato");
            crown.add("Iceberg Lettue");
            //Base
            base.add("Homestyle Chicken Breast");
            base.add("Asiago Cheese");
            base.add("3 Pieces of bacon");

        }

        if(nameType.equals("Grilled Asiago Ranch Club")){
            bunType = "Premium";
            canCombo = true;
            price = 0.1;
            //Crown
            crown.add("Honey Mustard");
            crown.add("Tomato");
            crown.add("Spring Mix Lettue");
            //Base
            base.add("Grilled Chicken Breast");
            base.add("Asiago Cheese");
            base.add("3 Pieces of bacon");

        }

        if(nameType.equals("5 Piece Chicken Nugget")){
            bunType = "Null";
            canCombo = false;
            price = 0.1;
            //Add Suace to the order
        }

        if(nameType.equals("10 Piece Chicken Nugget")){
            bunType = "Null";
            canCombo = true;
            price = 0.1;
            //Item

            //Add Suace to the order
        }

        if(nameType.equals("Homestyle Chicken Strips")){
            bunType = "Null";
            canCombo = true;
            price = 0.1;
            //Item
            //Add Suace to the order
        }

        if(nameType.equals("Spicy Chicken Strips")){
            bunType = "Null";
            canCombo = true;
            price = 0.1;
            //Item
            //Add Suace to the order
        }

        if(nameType.equals("Grilled Chicken Wrap")){
            bunType = "Wrap";
            canCombo = false;
            price = 0.1;
            //Base
            base.add("Honey Mustard");
            base.add("Shredded Cheese");
            base.add("Chopped Iceburg Lettue");
            base.add("Half a Grilled Chicken Breast");
        }

        if(nameType.equals("Spicy Chicken Wrap")){
            bunType = "Wrap";
            canCombo = false;
            price = 0.1;
            //Base
            base.add("Ranch Sauce");
            base.add("Shredded Cheese");
            base.add("Chopped Iceburg Lettue");
            base.add("Half a Spicy Chicken Breast");
        }

        if(nameType.equals("Homestyle Chicken Wrap")){
            bunType = "Wrap";
            canCombo = false;
            price = 0.1;
            //Base
            base.add("Ranch Sauce");
            base.add("Shredded Cheese");
            base.add("Chopped Iceburg Lettue");
            base.add("Half a Homestyle Chicken Breast");
        }
    }

    public String toString(){
        if(nameType.contentEquals("5 Piece Chicken Nugget") || nameType.contentEquals("10 Piece Chicken Nugget")){
            return nameType + "\n" + "    " + item.get(0) + "\n" + "    " + item.get(1);
        }else if(nameType.contentEquals("Homestyle Chicken Strips") || nameType.contentEquals("Spicy Chicken Strips")){
            return nameType + "\n" + "    " + item.get(0) + "\n" + "    " + item.get(1);
        }else{
            return nameType;
        }

    }
}

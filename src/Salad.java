import java.util.ArrayList;
//*******Add a no chicken option
public class Salad extends Order {
    //String type = "Salad";
    String size;
    boolean canCombo;
    ArrayList<String> contain = new ArrayList<String>();

    public Salad(String nameType, String size, String itemType){
        super(itemType, nameType);
        this.size = size;
        canBeChanged = true;
        saladList();
    }

    public Salad(String nameType, String itemType){
        super(itemType, nameType);
        canBeChanged = true;
        saladList();
    }

    public void saladList(){
        if(nameType.equals("Southwest Avocado Chicken Salad")){
            if(size.equals("Half")){
                contain.add("Scoop of Avocado");
                contain.add("Strip of Bacon");
                contain.add("Half Grill Chicken");
                contain.add("Pepper Jack Cheese");
                contain.add("Southwest Dressing");
                contain.add("Salad Blend");
                price = 0.1;
            }else if(size.equals("Full")){
                contain.add("Scoop of Avocado");
                contain.add("Scoop of Avocado");
                contain.add("Pepper Jack Cheese");
                contain.add("Pepper Jack Cheese");
                contain.add("Strip of Bacon");
                contain.add("Strip of Bacon");
                contain.add("Full Grill Chicken");
                contain.add("Southwest Dressing");
                contain.add("Southwest Dressing");
                contain.add("Salad Blend");
                price = 0.1;
            }
        }

        if(nameType.equals("Taco Salad")){
            if(size.equals("Half")){
                contain.add("Tortilla Chip Bag");
                contain.add("Salsa");
                contain.add("Sour Cream");
                contain.add("Half Chilly");
                contain.add("Dice Tomato");
                contain.add("Shredded Cheese");
                contain.add("Lettue Blend");
                price = 0.1;
            }else if(size.equals("Full")){
                contain.add("Tortilla Chip Bag");
                contain.add("Salsa");
                contain.add("Sour Cream");
                contain.add("Small Chilly");
                contain.add("Dice Tomato");
                contain.add("Dice Tomato");
                contain.add("Shredded Cheese");
                contain.add("Shredded Cheese");
                contain.add("Lettue Blend");
                price = 0.1;
            }
        }

        if(nameType.equals("Apple Pecan Chicken Salad")){
            if(size.equals("Half")){
                contain.add("Roasted Pecans");
                contain.add("Crumbled Blue Cheese");
                contain.add("Cranberries");
                contain.add("Apple Chunks");
                contain.add("Pomegranate Vinaigrette");
                contain.add("Half Grill Chicken");
                contain.add("Salad Blend");
                price = 0.1;
            }else if(size.equals("Full")){
                contain.add("Roasted Pecans");
                contain.add("Crumbled Blue Cheese");
                contain.add("Crumbled Blue Cheese");
                contain.add("Cranberries");
                contain.add("Cranberries");
                contain.add("Apple Chunks");
                contain.add("Apple Chunks");
                contain.add("Pomegranate Vinaigrette");
                contain.add("Pomegranate Vinaigrette");
                contain.add("Full Grill Chicken");
                contain.add("Salad Blend");
                price = 0.1;
            }
        }

        if(nameType.equals("Grilled Chicken Casar Salad")){
            if(size.equals("Half")){
                contain.add("Croutons");
                contain.add("Bacon bits");
                contain.add("Crumbled Asiago Cheese");
                contain.add("Caesar Dressing");
                contain.add("Half Grill Chicken");
                contain.add("Lettue Blend");
                price = 0.1;
            }else if(size.equals("Full")){
                contain.add("Croutons");
                contain.add("Bacon bits");
                contain.add("Bacon bits");
                contain.add("Crumbled Asiago Cheese");
                contain.add("Crumbled Asiago Cheese");
                contain.add("Caesar Dressing");
                contain.add("Caesar Dressing");
                contain.add("Full Grill Chicken");
                contain.add("Lettue Blend");
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
    }

    public String toString(){

        //Check to see if size is null, if not; do the other option
        if(size == null){
            return nameType;
        }else{
            return nameType + " " + size;
        }

    }
}

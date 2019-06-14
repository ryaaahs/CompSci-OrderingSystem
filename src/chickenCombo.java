public class chickenCombo extends Order {
    Chicken mainItem;
    Side sideItem;
    Drink drinkItem;
    String size;

    public chickenCombo(Chicken mainItem, Side sideItem, Drink drinkItem, String size){
        this.mainItem = mainItem;
        this.sideItem = sideItem;
        this.drinkItem = drinkItem;
        this.size = size;
        canBeChanged = true;
        price = drinkItem.getPrice() + mainItem.getPrice() + sideItem.getPrice();
    }

    public String toString(){
        if(mainItem.nameType.contentEquals("5 Piece Chicken Nugget") || mainItem.nameType.contentEquals("10 Piece Chicken Nugget")){
            return mainItem.nameType + " Combo \n" + "    " + mainItem.item.get(0) + "\n" + "    " + mainItem.item.get(1) + "\n" + "        " + drinkItem.size + " " + drinkItem.name + "\n" + "        " + size + " " + sideItem.nameType;
        }else if(mainItem.nameType.contentEquals("Homestyle Chicken Strips") || mainItem.nameType.contentEquals("Spicy Chicken Strips")){
            return mainItem.nameType + " Combo \n" + "    " + mainItem.item.get(0) + "\n" + "    " + mainItem.item.get(1) + "\n" + "        " + drinkItem.size + " " + drinkItem.name + "\n" + "        " + size + " " + sideItem.nameType;
        }else{
            return mainItem.nameType + " Combo \n" + "    " + drinkItem.size + " " + drinkItem.name + "\n" + "    " + size + " " + sideItem.nameType;
        }
    }

}
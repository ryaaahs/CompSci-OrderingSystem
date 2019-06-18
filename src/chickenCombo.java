public class chickenCombo extends Order {
    Chicken mainItem;
    Side sideItem;
    Drink drinkItem;
    String size;

    public chickenCombo(Chicken mainItem, Side sideItem, Drink drinkItem, String size, String itemType){
        super(itemType);
        this.mainItem = mainItem;
        this.sideItem = sideItem;
        this.drinkItem = drinkItem;
        this.size = size;
        canBeChanged = true;
        price = drinkItem.getPrice() + mainItem.getPrice() + sideItem.getPrice();
        price = round(price, 2);
        canBeChanged = true;
    }

    public String toString(){
        if(mainItem.nameType.contentEquals("10 Piece Chicken Nugget") || mainItem.nameType.contentEquals("Homestyle Chicken Strips") || mainItem.nameType.contentEquals("Spicy Chicken Strips")){
            if(sideItem.itemType.equals("Chilli")){
                return mainItem.nameType + " Combo" + itemLoop() + "\n" + "        " + drinkItem.size + " " + drinkItem.nameType + "\n" + "        " + sideItem.size + " " + sideItem.nameType;
            }else if (sideItem.itemType.equals("Side") || sideItem.itemType.equals("Side Salad") || sideItem.itemType.equals("Potato")){
                return mainItem.nameType + " Combo" + itemLoop() + "\n" + "        " + drinkItem.size + " " + drinkItem.nameType + "\n" + "        " + sideItem.nameType;
            }else{
                return mainItem.nameType + " Combo" + itemLoop() + "\n" + "        " + drinkItem.size + " " + drinkItem.nameType + "\n" + "        " + size + " " + sideItem.nameType;
            }

        }else{
            return mainItem.nameType + " Combo \n" + "    " + drinkItem.size + " " + drinkItem.nameType + "\n" + "    " + size + " " + sideItem.nameType;
        }
    }

    public String itemLoop(){
        String list = "";
        for(int i = 0; i < mainItem.item.size(); i++){
             list = list + "\n" + "    " + mainItem.item.get(i);
        }
        return list;
    }

}
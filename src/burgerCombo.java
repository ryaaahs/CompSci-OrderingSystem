public class burgerCombo extends Order{
    Burger mainItem;
    Side sideItem;
    Drink drinkItem;
    String size;

    public burgerCombo(Burger mainItem, Side sideItem, Drink drinkItem, String size, String itemType){
        super(itemType);
        this.mainItem = mainItem;
        this.sideItem = sideItem;
        this.drinkItem = drinkItem;
        this.size = size;
        price = drinkItem.getPrice() + mainItem.getPrice() + sideItem.getPrice();
        price = round(price, 2);
        canBeChanged = true;
    }

    public String toString(){
        if(sideItem.itemType.equals("Chilli")){
            return mainItem.nameType + " Combo \n" + "    " + drinkItem.size + " " + drinkItem.nameType + "\n" + "    " + sideItem.size + " " + sideItem.nameType;
        }else if (sideItem.itemType.equals("Side") || sideItem.itemType.equals("Side Salad") || sideItem.itemType.equals("Potato")){
            return mainItem.nameType + " Combo \n" + "    " + drinkItem.size + " " + drinkItem.nameType + "\n" + "    " + sideItem.nameType;
        }else{
            return mainItem.nameType + " Combo \n" + "    " + drinkItem.size + " " + drinkItem.nameType + "\n" + "    " + size + " " + sideItem.nameType;
        }

    }
}

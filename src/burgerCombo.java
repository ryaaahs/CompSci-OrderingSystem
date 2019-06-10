public class burgerCombo extends Order{
    Burger mainItem;
    Side sideItem;
    Drink drinkItem;
    String size;

    public burgerCombo(Burger mainItem, Side sideItem, Drink drinkItem, String size){
        this.mainItem = mainItem;
        this.sideItem = sideItem;
        this.drinkItem = drinkItem;
        this.size = size;

        price = drinkItem.getPrice() + mainItem.getPrice() + sideItem.getPrice();
    }

    public String toString(){
        return mainItem.nameType + " Combo \n" + "    " + drinkItem.size + " " + drinkItem.name + "\n" + "    " + size + " " + sideItem.nameType;
    }
}

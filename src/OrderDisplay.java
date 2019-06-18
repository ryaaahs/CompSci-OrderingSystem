import java.util.ArrayList;
import java.util.Scanner;

public class OrderDisplay {
    public static void main(String[] args) {
        //Order Display data
        boolean order = true;
        boolean startingOrder = true;
        Scanner scrChoice = new Scanner(System.in);
        Scanner scrChoiceStr = new Scanner(System.in);
        String list = "";
        double total = 0;
        double totalTax = 0;
        ArrayList<Order> orderList = new ArrayList<>();
        String lastBurger = "0";
        String lastChicken = "0";
        String lastSalad = "0";
        String lastSide = "0";
        String lastDrink = "0";
        int addPick = 0;
        int pickAVal = 0;

        //Change Holders
        Burger changeBurger = null;
        Burger coBurger = null;

        Chicken changeChickenWrap = null;
        Chicken coChickenWrap = null;
        Chicken changeChicken = null;
        Chicken coChicken = null;
        Chicken changeChickenSide = null;
        Chicken coChickenSide = null;

        burgerCombo coComboBurger = null;
        chickenCombo coComboChicken = null;

        Salad coSalad = null;
        double hst = 0;
        double fed = 0;
        double prov = 0;
        Salad changeSalad = null;

        Drink changeDrink = null;
        Drink coDrink = null;

        Side changeSide = null;
        Side coSide = null;

        int aRPick = 0; // 1 for Add, 2 for Remove, 0 for null
        int addOrRemove = 0;
        int burgerPick = 0;
        int chickenPick = 0;
        String burgerChangeChoice = "";
        String chickenChangeChoice = "";


        while (order) {
            if (startingOrder) {
                System.out.println("Welcome to Wendy's, what can I get for you today?");
                nl();
                displayList();
                list = "Choice";
                int choice = scrChoice.nextInt();

                //Choose from the current choices offered
                if (choice == 1) {
                    //Display Hamburger list
                    displayHamburger();
                    list = "Hamburger";
                    startingOrder = false;

                } else if (choice == 2) {
                    //Display Chicken list
                    displayChicken();
                    list = "Chicken";
                    startingOrder = false;
                } else if (choice == 3) {
                    displaySalad();
                    list = "Salad";
                    startingOrder = false;
                }else if(choice == 4){
                    //Display Side list
                    displaySide();
                    list = "Side";
                    startingOrder = false;
                }else if(choice == 5){
                    //Display the Drink list
                    displaySideDrink();
                    list = "Drink";
                    startingOrder = false;
                }else if(choice == 6){
                    //Display ETC list
                    //displaySalad();
                    list = "ETC";
                    startingOrder = false;
                }else{
                    //Display an error if a nonvalid choice is picked
                    print("Error: Invaild Choice");
                    nl();
                    printStar();
                    nl();
                }

            }else{

                if(list.contentEquals("Order End")){
                    nl();
                    printStar();
                    nl();

                    print("Thanks for ordering at Wendy's!");
                    print("Your order is: ");
                    displayOrder(orderList);
                    total = getOrderPrice(orderList);
                    totalTax = getOrderPrice(orderList);
                    fed = totalTax * 0.05;
                    prov = totalTax * 0.08;
                    fed = round(fed, 2);
                    prov = round(prov, 2);
                    hst = fed + prov;

                    hst = round(hst, 2);
                    totalTax += hst;
                    total = round(total, 2);
                    totalTax = round(totalTax, 2);

                    System.out.println("Without tax: $" + total);
                    System.out.println("PROV: $" + prov);
                    System.out.println("FED: $" + fed);
                    System.out.println("HST: $" + hst);
                    System.out.println("Your total is: $" + totalTax);
                    print("Come back again!!");
                    order = false;
                }

                if(list.contentEquals("Remove from Order")){
                    //REMOVE
                    nl();
                    printStar();
                    nl();
                    print("What would you like to remove?");

                    for (int i = 0; i < orderList.size(); i++) {
                        System.out.println("(" + (i + 1) + ") " + orderList.get(i));
                    }

                    int removeIndex = scrChoice.nextInt();
                    removeIndex -= 1;

                    if (!(removeIndex > orderList.size())) {
                        print("You removed: " + orderList.get(removeIndex));
                        orderList.remove(removeIndex);

                        nl();
                        printStar();
                        nl();
                        print("The Order now contains: ");

                        for (int i = 0; i < orderList.size(); i++) {
                            System.out.println(orderList.get(i));
                        }

                        displayRemoveOrder();
                        int removePick = scrChoice.nextInt();

                        if (removePick == 1) {
                            //Allow them to remove the current item again
                            list = "Remove from Order";
                        } else if (removePick == 2) {
                            //return to the main menu
                            displayListOther();
                            list = listChoiceOther();
                        } else {
                            //show an error
                            list = displayError(orderList);
                        }
                    }else {
                        //Show an error and reset
                        list = displayError(orderList);
                    }

                }

                if(list.contentEquals("Change Order")){
                    //Loop through the current order list
                    int changePick = 1;
                    if(aRPick == 0) {
                        nl();
                        printStar();
                        nl();
                        print("Which one would you like to change?");
                        for (int i = 0; i < orderList.size(); i++) {
                            if (orderList.get(i).itemType.contentEquals("Chicken Side")) {
                                System.out.println("(" + (i + 1) + ") " + orderList.get(i).nameType);   // + " " + ((Salad) orderList.get(i)).size
                            }else{
                                System.out.println("(" + (i + 1) + ") " + orderList.get(i));   // + " " + ((Salad) orderList.get(i)).size
                            }
                        }

                        changePick = scrChoice.nextInt();

                        if(orderList.get(changePick - 1).canBeChanged == false){
                            list = displayError(orderList);
                            aRPick = 0;
                        }

                        nl();
                        printStar();
                        nl();

                        if (orderList.get(changePick - 1).itemType.contentEquals("Chicken Side")) {
                            System.out.println("You are changing the " + orderList.get(changePick - 1).nameType);

                        }else if(orderList.get(changePick - 1).itemType.contentEquals("Burger Combo") || orderList.get(changePick - 1).itemType.contentEquals("Burger Combo")){
                            nl();
                            printStar();
                            nl();
                            print("Error: Cannot change combos; the functionality is not implemented. Please remove the item");
                            displayListOther();
                            listChoiceOther();

                        }else{
                            System.out.println("You are changing the " + orderList.get(changePick - 1));
                        }

                    }

                    //Side Salad Change
                    if (orderList.get(changePick - 1).itemType.contentEquals("Side Salad") || (coSide != null && coComboBurger.sideItem.itemType.contentEquals("Side Salad"))) {

                        if(aRPick == 0) {
                            coSide = (Side) orderList.get(changePick - 1);

                            nl();
                            printStar();
                            nl();
                            print("The Salad contains: ");

                            for (int i = 0; i < coSide.contain.size(); i++) {
                                System.out.println(coSide.contain.get(i));
                            }
                            nl();
                            printStar();
                            nl();
                            print("What would you like to Add or Remove from the " + coSide + " (1)[Add] (2)[Remove]");
                            addOrRemove = scrChoice.nextInt();
                        }

                        if (addOrRemove == 1 || aRPick == 1) {
                            //ADD
                            nl();
                            printStar();
                            nl();
                            print("Here are a list you can add to the Salad. (Pick)");
                            displaySaladOptions();
                            addPick = scrChoice.nextInt();

                            if(addPick == 1){

                                coSide.contain.add("N Shredded Cheese");


                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Shredded Cheese");

                                nl();
                                printStar();
                                nl();
                                print("The Salad now contains: ");
                                for (int i = 0; i < coSide.contain.size(); i++) {
                                    System.out.println(coSide.contain.get(i));
                                }
                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 2){

                                coSide.contain.add("N Diced Tomatos");


                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Diced Tomatos");

                                nl();
                                printStar();
                                nl();
                                print("The Salad now contains: ");

                                for (int i = 0; i < coSide.contain.size(); i++) {
                                    System.out.println(coSide.contain.get(i));
                                }


                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 3){

                                coSide.contain.add("N Asiago Cheese");

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Asiago Cheese");

                                nl();
                                printStar();
                                nl();
                                print("The Salad now contains: ");

                                for (int i = 0; i < coSide.contain.size(); i++) {
                                    System.out.println(coSide.contain.get(i));
                                }


                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 4){
                                coSide.contain.add("N 3 Pieces of Bacon");


                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: 3 Pieces of Bacon");

                                nl();
                                printStar();
                                nl();
                                print("The Salad now contains: ");
                                for (int i = 0; i < coSide.contain.size(); i++) {
                                    System.out.println(coSide.contain.get(i));
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 5){

                                coSide.contain.add("N Bacon Bits");


                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Bacon Bits");

                                nl();
                                printStar();
                                nl();
                                print("The Salad now contains: ");

                                for (int i = 0; i < coSide.contain.size(); i++) {
                                    System.out.println(coSide.contain.get(i));
                                }


                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else{
                                //Show an error and reset
                                list = displayError(orderList);
                                coSide = null;
                                aRPick = 0;
                            }

                        } else if (addOrRemove == 2 || aRPick == 2){
                            //REMOVE
                            print("What would you like to remove?");

                            for (int i = 0; i < coSide.contain.size(); i++) {
                                System.out.println("(" + (i + 1) + ") " + coSide.contain.get(i));
                            }

                            int removeIndex = scrChoice.nextInt();
                            removeIndex -= 1;

                            if (!(removeIndex > coSide.contain.size()) && aRPick == 0) {
                                print("You removed: " + coSide.contain.get(removeIndex));
                                coSide.contain.remove(removeIndex);

                                nl();
                                printStar();
                                nl();
                                print("The Salad now contains: ");

                                for (int i = 0; i < coSide.contain.size(); i++) {
                                    System.out.println(coSide.contain.get(i));
                                }

                                displayChoiceRemove();
                                int removePick = scrChoice.nextInt();

                                if (removePick == 1) {
                                    //Allow them to remove the current item again
                                    aRPick = 2;
                                } else if (removePick == 2) {
                                    //Bring them to the Add menu for the item
                                    aRPick = 1;
                                } else if (removePick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //show an error
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }
                            }else {
                                //Show an error and reset
                                list = displayError(orderList);
                                coSide = null;
                                aRPick = 0;
                            }
                        }
                        addOrRemove = 0;
                    }

                    //Potato Change
                    if (orderList.get(changePick - 1).itemType.contentEquals("Potato") || (coSide != null && coComboBurger.sideItem.itemType.contentEquals("Potato"))) {

                        if(aRPick == 0) {
                            coSide = (Side) orderList.get(changePick - 1);

                            nl();
                            printStar();
                            nl();
                            print("The Potato contains: ");

                            for (int i = 0; i < coSide.contain.size(); i++) {
                                System.out.println(coSide.contain.get(i));
                            }
                            nl();
                            printStar();
                            nl();
                            print("What would you like to Add or Remove from the " + coSide + " (1)[Add] (2)[Remove]");
                            addOrRemove = scrChoice.nextInt();
                        }

                        if (addOrRemove == 1 || aRPick == 1) {
                            //ADD
                            nl();
                            printStar();
                            nl();
                            print("Here are a list you can add to the Potato. (Pick)");
                            displaySideOptions();
                            addPick = scrChoice.nextInt();

                            if(addPick == 1){

                                coSide.contain.add("N Chilli");

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Chilli");

                                nl();
                                printStar();
                                nl();
                                print("The Potato now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 2){

                                coSide.contain.add("N Shredded Cheese");

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Shredded Cheese");

                                nl();
                                printStar();
                                nl();
                                print("The Potato now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                    addOrRemove = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                    addOrRemove = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 3){

                                coSide.contain.add("N Cheese Sauce");

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Cheese Sauce");

                                nl();
                                printStar();
                                nl();
                                print("The Potato now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 4){

                                coSide.contain.add("N Bacon bits");


                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Bacon bits");

                                nl();
                                printStar();
                                nl();
                                print("The Potato now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 5){

                                coSide.contain.add("N 3 Pieces of Bacon");


                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: 3 Pieces of Bacon");

                                nl();
                                printStar();
                                nl();
                                print("The Potato now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else{
                                //Show an error and reset
                                list = displayError(orderList);
                                coSide = null;
                                aRPick = 0;
                            }

                        } else if (addOrRemove == 2 || aRPick == 2){
                            //REMOVE
                            print("What would you like to remove?");
                            for (int i = 0; i < coSide.contain.size(); i++) {
                                System.out.println("(" + (i + 1) + ") " + coSide.contain.get(i));
                            }

                            int removeIndex = scrChoice.nextInt();
                            removeIndex -= 1;

                            if (!(removeIndex > coSide.contain.size())) {
                                print("You removed: " + coSide.contain.get(removeIndex));
                                coSide.contain.remove(removeIndex);

                                nl();
                                printStar();
                                nl();
                                print("The Potato now contains: ");

                                for (int i = 0; i < coSide.contain.size(); i++) {
                                    System.out.println(coSide.contain.get(i));
                                }

                                displayChoiceRemove();
                                int removePick = scrChoice.nextInt();

                                if (removePick == 1) {
                                    //Allow them to remove the current item again
                                    aRPick = 2;
                                } else if (removePick == 2) {
                                    //Bring them to the Add menu for the item
                                    aRPick = 1;
                                } else if (removePick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //show an error
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }
                            }else {
                                //Show an error and reset
                                list = displayError(orderList);
                                coSide = null;
                                aRPick = 0;
                            }
                        }
                        addOrRemove = 0;
                    }

                    //Chilli Change
                    if (orderList.get(changePick - 1).itemType.contentEquals("Chilli") || (coSide != null && orderList.get(changePick - 1).itemType.contentEquals("Chilli"))) {

                        if(aRPick == 0) {
                            coSide = (Side) orderList.get(changePick - 1);

                            nl();
                            printStar();
                            nl();
                            print("The Chilli contains: ");

                            for (int i = 0; i < coSide.contain.size(); i++) {
                                System.out.println(coSide.contain.get(i));
                            }
                            nl();
                            printStar();
                            nl();
                            print("What would you like to Add or Remove from the " + coSide + " (1)[Add] (2)[Remove]");
                            addOrRemove = scrChoice.nextInt();
                        }

                        if (addOrRemove == 1 || aRPick == 1) {
                            //ADD
                            nl();
                            printStar();
                            nl();
                            print("Here are a list you can add to the Side. (Pick)");
                            print("(1) Crackers");
                            print("(2) Hot Sauce");
                            print("(3) Shredded Cheese");
                            addPick = scrChoice.nextInt();

                            if(addPick == 1){

                                coSide.contain.add("N Crackers");

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Crackers");

                                nl();
                                printStar();
                                nl();
                                print("The Side now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 2){

                                coSide.contain.add("N Hot Sauce");

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Hot Sauce");

                                nl();
                                printStar();
                                nl();
                                print("The Side now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                    addOrRemove = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                    addOrRemove = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 3){

                                coSide.contain.add("N Shredded Cheese");

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Shredded Cheese");

                                nl();
                                printStar();
                                nl();
                                print("The Side now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                    addOrRemove = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                    addOrRemove = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else{
                                //Show an error and reset
                                list = displayError(orderList);
                                coSide = null;
                                aRPick = 0;
                            }

                        } else if (addOrRemove == 2 || aRPick == 2){
                            //REMOVE
                            print("What would you like to remove?");
                            for (int i = 0; i < coSide.contain.size(); i++) {
                                System.out.println("(" + (i + 1) + ") " + coSide.contain.get(i));
                            }

                            int removeIndex = scrChoice.nextInt();
                            removeIndex -= 1;

                            if (!(removeIndex > coSide.contain.size())) {
                                print("You removed: " + coSide.contain.get(removeIndex));
                                coSide.contain.remove(removeIndex);

                                nl();
                                printStar();
                                nl();
                                print("The Side now contains: ");

                                for (int i = 0; i < coSide.contain.size(); i++) {
                                    System.out.println(coSide.contain.get(i));
                                }

                                displayChoiceRemove();
                                int removePick = scrChoice.nextInt();

                                if (removePick == 1) {
                                    //Allow them to remove the current item again
                                    aRPick = 2;
                                } else if (removePick == 2) {
                                    //Bring them to the Add menu for the item
                                    aRPick = 1;
                                } else if (removePick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //show an error
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }
                            }else {
                                //Show an error and reset
                                list = displayError(orderList);
                                coSide = null;
                                aRPick = 0;
                            }
                        }
                        addOrRemove = 0;
                    }

                    //Side Change
                    if (orderList.get(changePick - 1).itemType.contentEquals("Side") || (coSide != null && coComboBurger.sideItem.itemType.contentEquals("Side"))) {

                        if(aRPick == 0) {
                            if(coSide == null) {
                                coSide = (Side) orderList.get(changePick - 1);
                            }

                            nl();
                            printStar();
                            nl();
                            print("The Side contains: ");

                            for (int i = 0; i < coSide.contain.size(); i++) {
                                System.out.println(coSide.contain.get(i));
                            }
                            nl();
                            printStar();
                            nl();
                            print("What would you like to Add or Remove from the " + coSide + " (1)[Add] (2)[Remove]");
                            addOrRemove = scrChoice.nextInt();
                        }

                        if (addOrRemove == 1 || aRPick == 1) {
                            //ADD
                            nl();
                            printStar();
                            nl();
                            print("Here are a list you can add to the Side. (Pick)");
                            displaySideOptions();
                            addPick = scrChoice.nextInt();

                            if(addPick == 1){

                                coSide.contain.add("N Chilli");

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Chilli");

                                nl();
                                printStar();
                                nl();
                                print("The Side now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 2){

                                coSide.contain.add("N Shredded Cheese");

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Shredded Cheese");

                                nl();
                                printStar();
                                nl();
                                print("The Side now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                    addOrRemove = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                    addOrRemove = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 3){

                                coSide.contain.add("N Cheese Sauce");

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Cheese Sauce");

                                nl();
                                printStar();
                                nl();
                                print("The Side now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 4){

                                coSide.contain.add("N Bacon bits");


                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Bacon bits");

                                nl();
                                printStar();
                                nl();
                                print("The Side now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 5){

                                coSide.contain.add("N 3 Pieces of Bacon");


                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: 3 Pieces of Bacon");

                                nl();
                                printStar();
                                nl();
                                print("The Side now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSide.contain.size(); i++) {
                                        System.out.println(coSide.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }

                            }else{
                                //Show an error and reset
                                list = displayError(orderList);
                                coSide = null;
                                aRPick = 0;
                            }

                        } else if (addOrRemove == 2 || aRPick == 2){
                            //REMOVE
                            print("What would you like to remove?");
                            for (int i = 0; i < coSide.contain.size(); i++) {
                                System.out.println("(" + (i + 1) + ") " + coSide.contain.get(i));
                            }

                            int removeIndex = scrChoice.nextInt();
                            removeIndex -= 1;

                            if (!(removeIndex > coSide.contain.size())) {
                                print("You removed: " + coSide.contain.get(removeIndex));
                                coSide.contain.remove(removeIndex);

                                nl();
                                printStar();
                                nl();
                                print("The Side now contains: ");

                                for (int i = 0; i < coSide.contain.size(); i++) {
                                    System.out.println(coSide.contain.get(i));
                                }

                                displayChoiceRemove();
                                int removePick = scrChoice.nextInt();

                                if (removePick == 1) {
                                    //Allow them to remove the current item again
                                    aRPick = 2;
                                } else if (removePick == 2) {
                                    //Bring them to the Add menu for the item
                                    aRPick = 1;
                                } else if (removePick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    coSide = null;
                                    aRPick = 0;
                                } else {
                                    //show an error
                                    list = displayError(orderList);
                                    coSide = null;
                                    aRPick = 0;
                                }
                            }else {
                                //Show an error and reset
                                list = displayError(orderList);
                                coSide = null;
                                aRPick = 0;
                            }
                        }
                        addOrRemove = 0;
                    }

                    //Fries
                    if (orderList.get(changePick - 1).itemType.contentEquals("Fries") || (coSide != null && coComboBurger.sideItem.itemType.contentEquals("Fries"))) {
                        aRPick = 1;
                        if(aRPick == 1) {
                            if(coSide == null){
                                coSide = (Side) orderList.get(changePick - 1);
                            }


                            coSide.size = frySizeChoiceChange();

                            nl();
                            printStar();
                            nl();
                            //Display the new drink
                            System.out.println("Your new side is: " + coSide.size + " " + coSide.nameType);

                            displayHotDrinkAdd();
                            addPick = scrChoice.nextInt();
                            if (addPick == 1) {
                                //Allow them to add the current item again
                                aRPick = 1;
                            }else if (addPick == 2) {
                                //return to the main menu
                                displayListOther();
                                list = listChoiceOther();
                                coSide = null;
                                aRPick = 0;
                            } else {
                                //Show an error and reset
                                list = displayError(orderList);
                                coSide = null;
                                aRPick = 0;
                            }


                        }else {
                            //Show an error and reset
                            list = displayError(orderList);
                            coSide = null;
                            aRPick = 0;
                        }
                        addOrRemove = 0;
                    }

                    //Hot Drink
                    if (orderList.get(changePick - 1).itemType.contentEquals("Hot Drink") || (coDrink != null && coComboBurger.drinkItem.itemType.contentEquals("Hot Drink"))) {
                        aRPick = 1;
                        if(aRPick == 1) {
                            coDrink = (Drink) orderList.get(changePick - 1);

                            nl();
                            printStar();
                            nl();
                            print("Our Coffes come in one size..");
                            print("What would you like to add to the drink?");
                            print("(1) Sugar");
                            print("(2) Milk");
                            print("(3) Sweetner");

                            addPick = scrChoice.nextInt();
                            if (addPick == 1) {
                                coDrink.item.add("Sugar");
                            } else if (addPick == 2) {
                                coDrink.item.add("Milk");
                            } else if (addPick == 3) {
                                coDrink.item.add("Sweetner");
                            }else{
                                //Show an error and reset
                                list = displayError(orderList);
                                coDrink = null;
                            }


                            nl();
                            printStar();
                            nl();
                            print("The Coffee now contains: ");
                            for (int i = 0; i < coDrink.item.size(); i++) {
                                System.out.println(coDrink.item.get(i));
                            }


                            displayHotDrinkAdd();
                            addPick = scrChoice.nextInt();
                            if (addPick == 1) {
                                //Allow them to add the current item again
                                aRPick = 1;
                            }else if (addPick == 2) {
                                //return to the main menu
                                displayListOther();
                                list = listChoiceOther();
                                coDrink = null;
                                aRPick = 0;
                            } else {
                                //Show an error and reset
                                list = displayError(orderList);
                                coDrink = null;
                                aRPick = 0;
                            }


                        }else {
                            //Show an error and reset
                            list = displayError(orderList);
                            coDrink = null;
                            aRPick = 0;
                        }
                        addOrRemove = 0;
                    }

                    //Lemonade
                    if (orderList.get(changePick - 1).itemType.contentEquals("Lemonade") || (coDrink != null && coComboBurger.drinkItem.itemType.contentEquals("Lemonade"))) {

                        if(aRPick == 0) {
                            coDrink = (Drink) orderList.get(changePick - 1);

                            nl();
                            printStar();
                            nl();
                            print("You have a " + coDrink.size + " " + coDrink.nameType);

                            nl();
                            printStar();
                            nl();
                            print("What would you like to do to the drink? (1)[Size] (2)[Flavor]");
                            addOrRemove = scrChoice.nextInt();

                        }

                        if (addOrRemove == 1 || aRPick == 1) {
                            //Change Size
                            coDrink.size = drinkSizeChoiceChange();

                            nl();
                            printStar();
                            nl();
                            //Display the new drink
                            System.out.println("Your new drink is: " + coDrink.size + " " + coDrink.nameType);

                            displayDrinkAdd();
                            addPick = scrChoice.nextInt();
                            if (addPick == 1) {
                                //Allow them to add the current item again
                                aRPick = 1;
                            } else if (addPick == 2) {
                                //Bring them to the Remove menu for the item
                                aRPick = 2;
                            } else if (addPick == 3) {
                                //return to the main menu
                                displayListOther();
                                list = listChoiceOther();
                                coDrink = null;
                                aRPick = 0;
                            } else {
                                //Show an error and reset
                                list = displayError(orderList);
                                coDrink = null;
                                aRPick = 0;
                            }


                        }else if (addOrRemove == 2 || aRPick == 2){
                            //Change Flavor
                            nl();
                            printStar();
                            nl();
                            print("What Flavor would you like to change to?");
                            print("(1) Lemonade");
                            print("(2) Strawberry Lemonade");

                            addPick = scrChoice.nextInt();
                            if (addPick == 1) {
                                coDrink.nameType = "Lemonade";
                            } else if (addPick == 2) {
                                coDrink.nameType = "Strawberry Lemonade";
                            }else{
                                //Show an error and reset
                                list = displayError(orderList);
                                changeDrink = null;
                                aRPick = 0;
                            }

                            nl();
                            printStar();
                            nl();
                            //Display the new drink


                            System.out.println("Your new drink is: " + coDrink.size + " " + coDrink.nameType);


                            displayDrinkAdd();
                            int nextPick = scrChoice.nextInt();
                            if (nextPick == 1) {
                                //Allow them to add the current item again
                                aRPick = 1;
                            } else if (nextPick == 2) {
                                //Bring them to the Remove menu for the item
                                aRPick = 2;
                            } else if (nextPick == 3) {
                                //return to the main menu
                                displayListOther();
                                list = listChoiceOther();
                                coDrink = null;
                                aRPick = 0;
                            } else {
                                //Show an error and reset
                                list = displayError(orderList);
                                coDrink = null;
                                aRPick = 0;
                            }

                        }else {
                            //Show an error and reset
                            list = displayError(orderList);
                            coDrink = null;
                            aRPick = 0;
                        }
                        addOrRemove = 0;
                    }

                    //Sodas
                    if (orderList.get(changePick - 1).itemType.contentEquals("Soda") || (coDrink != null && coComboBurger.drinkItem.itemType.contentEquals("Soda"))) {

                        if(aRPick == 0) {
                            if(coDrink == null){
                               coDrink = (Drink) orderList.get(changePick - 1);
                            }


                            nl();
                            printStar();
                            nl();
                            print("You have a " + coDrink.size + " " + coDrink.nameType);

                            nl();
                            printStar();
                            nl();
                            print("What would you like to do to the drink? (1)[Size] (2)[Flavor]");
                            addOrRemove = scrChoice.nextInt();

                        }

                        if (addOrRemove == 1 || aRPick == 1) {
                            //Change Size
                            coDrink.size = drinkSizeChoiceChange();

                            nl();
                            printStar();
                            nl();
                            //Display the new drink
                            System.out.println("Your new drink is: " + coDrink.size + " " + coDrink.nameType);

                            displayDrinkAdd();
                            addPick = scrChoice.nextInt();
                            if (addPick == 1) {
                                //Allow them to add the current item again
                                aRPick = 1;
                            } else if (addPick == 2) {
                                //Bring them to the Remove menu for the item
                                aRPick = 2;
                            } else if (addPick == 3) {
                                //return to the main menu
                                displayListOther();
                                list = listChoiceOther();
                                coDrink = null;
                                aRPick = 0;
                            } else {
                                //Show an error and reset
                                list = displayError(orderList);
                                coDrink = null;
                                aRPick = 0;
                            }


                        } else if (addOrRemove == 2 || aRPick == 2){
                            //Change Flavor
                            nl();
                            printStar();
                            nl();
                            print("What Flavor would you like to change to?");
                            displayDrinkOptions();

                            addPick = scrChoice.nextInt();
                            if (addPick == 1) {
                                coDrink.nameType = "Coca-cola";
                            } else if (addPick == 2) {
                                coDrink.nameType = "Coca-cola Zero";
                            } else if (addPick == 3) {
                                coDrink.nameType = "Diet Coke";
                            } else if (addPick == 4){
                                coDrink.nameType = "Sprite";
                            }else if (addPick == 5){
                                coDrink.nameType = "Barq's Root Beer";
                            }else if (addPick == 6){
                                coDrink.nameType = "Fanta Orange";
                            }else if (addPick == 7){
                                coDrink.nameType = "Nestea";
                            }else if (addPick == 8){
                                coDrink.nameType = "Fruit Passion Fruitopia";
                            }else{
                                //Show an error and reset
                                list = displayError(orderList);
                                changeDrink = null;
                                aRPick = 0;
                            }

                            nl();
                            printStar();
                            nl();
                            //Display the new drink


                            System.out.println("Your new drink is: " + coDrink.size + " " + coDrink.nameType);


                            displayDrinkAdd();
                            int nextPick = scrChoice.nextInt();
                            if (nextPick == 1) {
                                //Allow them to add the current item again
                                aRPick = 1;
                            } else if (nextPick == 2) {
                                //Bring them to the Remove menu for the item
                                aRPick = 2;
                            } else if (nextPick == 3) {
                                //return to the main menu
                                displayListOther();
                                list = listChoiceOther();
                                coDrink = null;
                                aRPick = 0;
                            } else {
                                //Show an error and reset
                                list = displayError(orderList);
                                coDrink = null;
                                aRPick = 0;
                            }

                        }else {
                            //Show an error and reset
                            list = displayError(orderList);
                            coDrink = null;
                            aRPick = 0;
                        }
                        addOrRemove = 0;
                    }


                    //Chicken Wrap
                    if (orderList.get(changePick - 1).itemType.contentEquals("Chicken Wrap") || changeChickenWrap != null) {

                        if(aRPick == 0) {
                            coChickenWrap = (Chicken) orderList.get(changePick - 1);

                            nl();
                            printStar();
                            nl();
                            print("The Chicken Wrap contains: ");

                            for (int i = 0; i < coChickenWrap.base.size(); i++) {
                                System.out.println(coChickenWrap.base.get(i));
                            }
                            nl();
                            printStar();
                            nl();
                            print("What would you like to Add or Remove from the Chicken Wrap? (1)[Add] (2)[Remove]");
                            addOrRemove = scrChoice.nextInt();
                        }

                        if (addOrRemove == 1 || aRPick == 1) {

                            //ADD
                            nl();
                            printStar();
                            nl();
                            print("Here are a list you can add to the Chicken Wrap. (Pick)");

                            displayWrapOptions();
                            addPick = scrChoice.nextInt();

                            if(addPick == 1){
                                if(aRPick != 0){
                                    changeChickenWrap.base.add("N Ranch");
                                }else{
                                    coChickenWrap.base.add("N Ranch");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Ranch");

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Wrap now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeChickenWrap.base.size(); i++) {
                                        System.out.println(changeChickenWrap.base.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coChickenWrap.base.size(); i++) {
                                        System.out.println(coChickenWrap.base.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 2){
                                if(aRPick != 0){
                                    changeChickenWrap.base.add("N Honey Mustard");
                                }else{
                                    coChickenWrap.base.add("N Honey Mustard");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Honey Mustard");

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Wrap now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeChickenWrap.base.size(); i++) {
                                        System.out.println(changeChickenWrap.base.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coChickenWrap.base.size(); i++) {
                                        System.out.println(coChickenWrap.base.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 3){
                                if(aRPick != 0){
                                    changeChickenWrap.base.add("N Shredded Cheese");
                                }else{
                                    coChickenWrap.base.add("N Shredded Cheese");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Shredded Cheese");

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Wrap now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeChickenWrap.base.size(); i++) {
                                        System.out.println(changeChickenWrap.base.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coChickenWrap.base.size(); i++) {
                                        System.out.println(coChickenWrap.base.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 4){
                                if(aRPick != 0){
                                    changeChickenWrap.base.add("N Half a Grilled Chicken");
                                }else{
                                    coChickenWrap.base.add("N Half a Grilled Chicken");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Half a Grilled Chicken");

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Wrap now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeChickenWrap.base.size(); i++) {
                                        System.out.println(changeChickenWrap.base.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coChickenWrap.base.size(); i++) {
                                        System.out.println(coChickenWrap.base.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 5){
                                if(aRPick != 0){
                                    changeChickenWrap.base.add("N Half a Spicy Chicken");
                                }else{
                                    coChickenWrap.base.add("N Half a Spicy Chicken");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Half a Spicy Chicken");

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Wrap now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeChickenWrap.base.size(); i++) {
                                        System.out.println(changeChickenWrap.base.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coChickenWrap.base.size(); i++) {
                                        System.out.println(coChickenWrap.base.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 6){
                                if(aRPick != 0){
                                    changeChickenWrap.base.add("N Half a Homestyle Chicken");
                                }else{
                                    coChickenWrap.base.add("N Half a Homestyle Chicken");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Half a Homestyle Chicken");

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Wrap now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeChickenWrap.base.size(); i++) {
                                        System.out.println(changeChickenWrap.base.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coChickenWrap.base.size(); i++) {
                                        System.out.println(coChickenWrap.base.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                }

                            }else{
                                //Show an error and reset
                                list = displayError(orderList);
                                changeChickenWrap = null;
                                aRPick = 0;
                            }

                        } else if (addOrRemove == 2 || aRPick == 2){
                            //REMOVE
                            nl();
                            printStar();
                            nl();
                            print("What would you like to remove?");
                            if(aRPick == 0) {
                                for (int i = 0; i < coChickenWrap.base.size(); i++) {
                                    System.out.println("(" + (i + 1) + ") " + coChickenWrap.base.get(i));
                                }
                            }else{
                                for (int i = 0; i < changeChickenWrap.base.size(); i++) {
                                    System.out.println("(" + (i + 1) + ") " + changeChickenWrap.base.get(i));
                                }
                            }

                            int removeIndex = scrChoice.nextInt();
                            removeIndex -= 1;

                            if (!(removeIndex > coChickenWrap.base.size()) && aRPick == 0) {
                                print("You removed: " + coChickenWrap.base.get(removeIndex));
                                coChickenWrap.base.remove(removeIndex);

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Wrap now contains: ");

                                for (int i = 0; i < coChickenWrap.base.size(); i++) {
                                    System.out.println(coChickenWrap.base.get(i));
                                }

                                displayChoiceRemove();
                                int removePick = scrChoice.nextInt();

                                if (removePick == 1) {
                                    //Allow them to remove the current item again
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 2;
                                } else if (removePick == 2) {
                                    //Bring them to the Add menu for the item
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 1;
                                } else if (removePick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                } else {
                                    //show an error
                                    list = displayError(orderList);
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                }
                            }else if(!(removeIndex > changeChickenWrap.base.size()) && aRPick > 0){
                                print("You removed: " + changeChickenWrap.base.get(removeIndex));
                                changeChickenWrap.base.remove(removeIndex);

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Wrap now contains: ");

                                for (int i = 0; i < changeChickenWrap.base.size(); i++) {
                                    System.out.println(changeChickenWrap.base.get(i));
                                }

                                displayChoiceRemove();
                                int removePick = scrChoice.nextInt();

                                if (removePick == 1) {
                                    //Allow them to remove the current item again
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 2;
                                } else if (removePick == 2) {
                                    //Bring them to the Add menu for the item
                                    changeChickenWrap = coChickenWrap;
                                    aRPick = 1;
                                } else if (removePick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                } else {
                                    //show an error
                                    list = displayError(orderList);
                                    changeChickenWrap = null;
                                    aRPick = 0;
                                }
                            }else {
                                //Show an error and reset
                                list = displayError(orderList);
                                changeChickenWrap = null;
                                aRPick = 0;
                            }
                        }
                        addOrRemove = 0;
                    }

                    //Chicken Side
                    if (orderList.get(changePick - 1).itemType.contentEquals("Chicken Side") || (coChicken != null && coComboChicken.mainItem.itemType.contentEquals("Chicken Side"))) {

                        if(aRPick == 0) {
                            coChickenSide = (Chicken) orderList.get(changePick - 1);

                            nl();
                            printStar();
                            nl();
                            print("The Chicken Side contains: ");

                            for (int i = 0; i < coChickenSide.item.size(); i++) {
                                System.out.println(coChickenSide.item.get(i));
                            }
                            nl();
                            printStar();
                            nl();
                            print("What would you like to Add or Remove from the Chicken Side? (1)[Add] (2)[Remove]");
                            addOrRemove = scrChoice.nextInt();
                        }

                        if (addOrRemove == 1 || aRPick == 1) {
                            //ADD
                            nl();
                            printStar();
                            nl();
                            print("Here are a list you can add to the Chicken Side. (Pick)");
                            displaySauceOptions();
                            addPick = scrChoice.nextInt();

                            if(addPick == 1){
                                if(aRPick != 0){
                                    changeChickenSide.item.add("N Sweet and Sour");
                                }else{
                                    coChickenSide.item.add("N Sweet and Sour");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Sweet and Sour");

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Side now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeChickenSide.item.size(); i++) {
                                        System.out.println(changeChickenSide.item.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coChickenSide.item.size(); i++) {
                                        System.out.println(coChickenSide.item.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeChickenSide = coChickenSide;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeChickenSide = coChickenSide;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChickenSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 2){
                                if(aRPick != 0){
                                    changeChickenSide.item.add("N BBQ");
                                }else{
                                    coChickenSide.item.add("N BBQ");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: BBQ");

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Side now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeChickenSide.item.size(); i++) {
                                        System.out.println(changeChickenSide.item.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coChickenSide.item.size(); i++) {
                                        System.out.println(coChickenSide.item.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeChickenSide = coChickenSide;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeChickenSide = coChickenSide;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChickenSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 3){
                                if(aRPick != 0){
                                    changeChickenSide.item.add("N Buttermilk Ranch");
                                }else{
                                    coChickenSide.item.add("N Buttermilk Ranch");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Buttermilk Ranch");

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Side now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeChickenSide.item.size(); i++) {
                                        System.out.println(changeChickenSide.item.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coChickenSide.item.size(); i++) {
                                        System.out.println(coChickenSide.item.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeChickenSide = coChickenSide;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeChickenSide = coChickenSide;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChickenSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 4){
                                if(aRPick != 0){
                                    changeChickenSide.item.add("N Honey Mustard");
                                }else{
                                    coChickenSide.item.add("N Honey Mustard");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: 3 Pieces of Bacon");

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Side now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeChickenSide.item.size(); i++) {
                                        System.out.println(changeChickenSide.item.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coChickenSide.item.size(); i++) {
                                        System.out.println(coChickenSide.item.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeChickenSide = coChickenSide;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeChickenSide = coChickenSide;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChickenSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 5){
                                if(aRPick != 0){
                                    changeChickenSide.item.add("N Creamy Sriracha");
                                }else{
                                    coChickenSide.item.add("N Creamy Sriracha");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Creamy Sriracha");

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Side now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeChickenSide.item.size(); i++) {
                                        System.out.println(changeChickenSide.item.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coChickenSide.item.size(); i++) {
                                        System.out.println(coChickenSide.item.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeChickenSide = coChickenSide;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeChickenSide = coChickenSide;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChickenSide = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 6){
                                if(aRPick != 0){
                                    changeChickenSide.item.add("N Ketchup");
                                }else{
                                    coChickenSide.item.add("N Ketchup");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Ketchup");

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Side now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeChickenSide.item.size(); i++) {
                                        System.out.println(changeChickenSide.item.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coChickenSide.item.size(); i++) {
                                        System.out.println(coChickenSide.item.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeChickenSide = coChickenSide;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeChickenSide = coChickenSide;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenSide = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChickenSide = null;
                                    aRPick = 0;
                                }

                            }else{
                                //Show an error and reset
                                list = displayError(orderList);
                                changeSalad = null;
                                aRPick = 0;
                            }

                        } else if (addOrRemove == 2 || aRPick == 2){
                            //REMOVE
                            print("What would you like to remove?");
                            if(aRPick == 0) {
                                for (int i = 0; i < coChickenSide.item.size(); i++) {
                                    System.out.println("(" + (i + 1) + ") " + coChickenSide.item.get(i));
                                }
                            }else{
                                for (int i = 0; i < changeChickenSide.item.size(); i++) {
                                    System.out.println("(" + (i + 1) + ") " + changeChickenSide.item.get(i));
                                }
                            }

                            int removeIndex = scrChoice.nextInt();
                            removeIndex -= 1;

                            if (!(removeIndex > coChickenSide.item.size()) && aRPick == 0) {
                                print("You removed: " + coChickenSide.item.get(removeIndex));
                                coSalad.contain.remove(removeIndex);

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Side now contains: ");

                                for (int i = 0; i < coChickenSide.item.size(); i++) {
                                    System.out.println(coChickenSide.item.get(i));
                                }

                                displayChoiceRemove();
                                int removePick = scrChoice.nextInt();

                                if (removePick == 1) {
                                    //Allow them to remove the current item again
                                    changeChickenSide = coChickenSide;
                                    aRPick = 2;
                                } else if (removePick == 2) {
                                    //Bring them to the Add menu for the item
                                    changeChickenSide = coChickenSide;
                                    aRPick = 1;
                                } else if (removePick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenSide = null;
                                    aRPick = 0;
                                } else {
                                    //show an error
                                    list = displayError(orderList);
                                    changeChickenSide = null;
                                    aRPick = 0;
                                }
                            }else if(!(removeIndex > changeChickenSide.item.size()) && aRPick > 0){
                                print("You removed: " + changeChickenSide.item.get(removeIndex));
                                changeChickenSide.item.remove(removeIndex);

                                nl();
                                printStar();
                                nl();
                                print("The Chicken Side now contains: ");

                                for (int i = 0; i < changeChickenSide.item.size(); i++) {
                                    System.out.println(changeChickenSide.item.get(i));
                                }

                                displayChoiceRemove();
                                int removePick = scrChoice.nextInt();

                                if (removePick == 1) {
                                    //Allow them to remove the current item again
                                    changeChickenSide = coChickenSide;
                                    aRPick = 2;
                                } else if (removePick == 2) {
                                    //Bring them to the Add menu for the item
                                    changeChickenSide = coChickenSide;
                                    aRPick = 1;
                                } else if (removePick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeChickenSide = null;
                                    aRPick = 0;
                                } else {
                                    //show an error
                                    list = displayError(orderList);
                                    changeChickenSide = null;
                                    aRPick = 0;
                                }
                            }else {
                                //Show an error and reset
                                list = displayError(orderList);
                                changeChickenSide = null;
                                aRPick = 0;
                            }
                        }
                        addOrRemove = 0;
                    }

                    //Chicken Change
                    if (orderList.get(changePick - 1).itemType.contentEquals("Chicken") || (coChicken != null && coComboChicken.mainItem.itemType.contentEquals("Chicken"))) {

                        if(aRPick == 0) {
                            coChicken = (Chicken) orderList.get(changePick - 1);
                            nl();
                            printStar();
                            nl();
                            print("Which do you want to change..." + " (1)[Crown] (2)[Base]");
                            chickenPick = scrChoice.nextInt();

                            if(chickenPick == 1){
                                chickenChangeChoice = "Crown";

                                nl();
                                printStar();
                                nl();
                                print("You are changing the crown for: " + coChicken);


                                nl();
                                printStar();
                                nl();
                                print("What would you like to Add or Remove from the " + coChicken + " crown... (1)[Add] (2)[Remove]");

                                addOrRemove = scrChoice.nextInt();
                            }else if(chickenPick == 2){
                                chickenChangeChoice = "Base";

                                nl();
                                printStar();
                                nl();
                                print("You are changing the base for: " + coChicken);

                                nl();
                                printStar();
                                nl();
                                print("What would you like to Add or Remove from the " + coChicken + " base... (1)[Add] (2)[Remove]");

                                addOrRemove = scrChoice.nextInt();
                            }else{
                                //show error
                            }
                        }

                        if(chickenChangeChoice.contentEquals("Crown")){

                            if (addOrRemove == 1 || aRPick == 1) {
                                //ADD
                                nl();
                                printStar();
                                nl();
                                print("Here are a list you can add to the Burger crown (Pick)");
                                displayCrownOptions();
                                addPick = scrChoice.nextInt();

                                if(addPick == 1){
                                    if(aRPick != 0){
                                        changeChicken.crown.add("N Ranch");
                                    }else{
                                        coChicken.crown.add("N Ranch");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: Ranch");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Burger crown now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeChicken.crown.size(); i++) {
                                            System.out.println(changeChicken.crown.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coChicken.crown.size(); i++) {
                                            System.out.println(coChicken.crown.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeChicken = coChicken;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeChicken = coChicken;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeChicken = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeChicken = null;
                                        aRPick = 0;
                                    }

                                }else if(addPick == 2){
                                    if(aRPick != 0){
                                        changeBurger.crown.add("N Honey Mustard");
                                    }else{
                                        coBurger.crown.add("N Honey Mustard");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: Honey Mustard");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Chicken crown now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeChicken.crown.size(); i++) {
                                            System.out.println(changeChicken.crown.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coChicken.crown.size(); i++) {
                                            System.out.println(coChicken.crown.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeChicken = coChicken;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeChicken = coChicken;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeChicken = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeChicken = null;
                                        aRPick = 0;
                                    }

                                }else if(addPick == 3){
                                    if(aRPick != 0){
                                        changeChicken.crown.add("N Mayo");
                                    }else{
                                        coChicken.crown.add("N Mayo");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: Mayo");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Chicken crown now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeChicken.crown.size(); i++) {
                                            System.out.println(changeChicken.crown.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coChicken.crown.size(); i++) {
                                            System.out.println(coChicken.crown.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeChicken = coChicken;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeChicken = coChicken;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeChicken = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeChicken = null;
                                        aRPick = 0;
                                    }

                                }else if(addPick == 4){
                                    if(aRPick != 0){
                                        changeChicken.crown.add("N Mustard");
                                    }else{
                                        coChicken.crown.add("N Mustard");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: Mustard");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Chicken crown now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeChicken.crown.size(); i++) {
                                            System.out.println(changeChicken.crown.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coChicken.crown.size(); i++) {
                                            System.out.println(coChicken.crown.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeChicken = coChicken;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeChicken = coChicken;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeChicken = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeChicken = null;
                                        aRPick = 0;
                                    }

                                }else if(addPick == 5){
                                    if(aRPick != 0){
                                        changeChicken.crown.add("N Ketchup");
                                    }else{
                                        coChicken.crown.add("N Ketchup");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: Ketchup");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Chicken crown now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeChicken.crown.size(); i++) {
                                            System.out.println(changeChicken.crown.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coChicken.crown.size(); i++) {
                                            System.out.println(coChicken.crown.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeChicken = coChicken;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeChicken = coChicken;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeChicken = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeChicken = null;
                                        aRPick = 0;
                                    }

                                }else{
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChicken = null;
                                    aRPick = 0;
                                }

                            }else if (addOrRemove == 2 || aRPick == 2){
                                //REMOVE
                                nl();
                                printStar();
                                nl();
                                print("What would you like to remove from the crown? (Pick)");

                                if (aRPick == 0) {
                                    for (int i = 0; i < coChicken.crown.size(); i++) {
                                        System.out.println("(" + (i + 1) + ") " + coChicken.crown.get(i));
                                    }
                                } else {
                                    for (int i = 0; i < changeChicken.crown.size(); i++) {
                                        System.out.println("(" + (i + 1) + ") " + changeChicken.crown.get(i));
                                    }
                                }

                                int removeIndex = scrChoice.nextInt();
                                removeIndex -= 1;

                                if (!(removeIndex > coChicken.crown.size()) && aRPick == 0) {
                                    nl();
                                    printStar();
                                    nl();
                                    print("You removed: " + coChicken.crown.get(removeIndex));
                                    coBurger.crown.remove(removeIndex);

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Burger crown now contains: ");

                                    for (int i = 0; i < coChicken.crown.size(); i++) {
                                        System.out.println(coChicken.crown.get(i));
                                    }

                                    displayChoiceRemove();
                                    int removePick = scrChoice.nextInt();

                                    if (removePick == 1) {
                                        //Allow them to remove the current item again
                                        changeChicken = coChicken;
                                        aRPick = 2;
                                    } else if (removePick == 2) {
                                        //Bring them to the add menu for the item
                                        changeChicken = coChicken;
                                        aRPick = 1;
                                    } else if (removePick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeChicken = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeChicken = null;
                                        aRPick = 0;
                                    }

                                } else if (!(removeIndex > changeChicken.crown.size()) && aRPick == 2) {
                                    nl();
                                    printStar();
                                    nl();
                                    print("You removed: " + changeChicken.crown.get(removeIndex));
                                    changeChicken.crown.remove(removeIndex);

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Chicken crown now contains: ");

                                    for (int i = 0; i < changeChicken.crown.size(); i++) {
                                        System.out.println(changeChicken.crown.get(i));
                                    }

                                    displayChoiceRemove();
                                    int removePick = scrChoice.nextInt();

                                    if (removePick == 1) {
                                        //Allow them to remove the current item again
                                        changeChicken = coChicken;
                                        aRPick = 2;
                                    } else if (removePick == 2) {
                                        //Bring them to the add menu for the item
                                        changeChicken = coChicken;
                                        aRPick = 1;
                                    } else if (removePick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeChicken = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeChicken = null;
                                        aRPick = 0;
                                    }
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChicken = null;
                                    aRPick = 0;
                                }
                            }
                            addOrRemove = 0;
                        }else if(chickenChangeChoice.contentEquals("Base")){

                            if (addOrRemove == 1 || aRPick == 1) {
                                //ADD
                                nl();
                                printStar();
                                nl();
                                print("Here are a list you can add to the Chicken base (Pick)");
                                displayBaseOptions();
                                addPick = scrChoice.nextInt();

                                if(addPick == 1){
                                    if(aRPick != 0){
                                        changeChicken.base.add("N 2oz Patty");
                                    }else{
                                        coChicken.base.add("N 2oz Patty");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: 2oz Patty");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Chicken base now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeChicken.base.size(); i++) {
                                            System.out.println(changeChicken.base.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coChicken.base.size(); i++) {
                                            System.out.println(coChicken.base.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeChicken = coChicken;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeChicken = coChicken;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeChicken = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeChicken = null;
                                        aRPick = 0;
                                    }

                                }else if(addPick == 2){
                                    if(aRPick != 0){
                                        changeChicken.base.add("N 4oz Patty");
                                    }else{
                                        coChicken.base.add("N 4oz Patty");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: 4oz Patty");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Chicken base now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeChicken.base.size(); i++) {
                                            System.out.println(changeChicken.base.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coChicken.base.size(); i++) {
                                            System.out.println(coChicken.base.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeChicken = coChicken;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeChicken = coChicken;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeChicken = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeChicken = null;
                                        aRPick = 0;
                                    }

                                }else if(addPick == 3){
                                    if(aRPick != 0){
                                        changeChicken.base.add("N Grilled Chicken");
                                    }else{
                                        coChicken.base.add("N Grilled Chicken");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: Grilled Chicken");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Chicken now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeChicken.base.size(); i++) {
                                            System.out.println(changeChicken.base.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coChicken.base.size(); i++) {
                                            System.out.println(coChicken.base.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeChicken = coChicken;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeChicken = coChicken;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        list = "Choice";
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeChicken = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeChicken = null;
                                        aRPick = 0;
                                    }

                                }else if(addPick == 4){

                                    if(aRPick != 0){
                                        changeChicken.base.add("N Homestyle Chicken");
                                    }else{
                                        coChicken.base.add("N Homestyle Chicken");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: Homestyle Chicken");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Chicken base now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeChicken.base.size(); i++) {
                                            System.out.println(changeChicken.base.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coChicken.base.size(); i++) {
                                            System.out.println(coChicken.base.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeChicken = coChicken;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeChicken = coChicken;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeChicken = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeChicken = null;
                                        aRPick = 0;
                                    }

                                }else if(addPick == 5){

                                    if(aRPick != 0){
                                        changeChicken.base.add("N Spicy Chicken");
                                    }else{
                                        coChicken.base.add("N Spicy Chicken");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: Spicy Chicken");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Chicken base now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeChicken.base.size(); i++) {
                                            System.out.println(changeChicken.base.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coChicken.base.size(); i++) {
                                            System.out.println(coChicken.base.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeChicken = coChicken;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeChicken = coChicken;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeChicken = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeChicken = null;
                                        aRPick = 0;
                                    }

                                }else{
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChicken = null;
                                    aRPick = 0;
                                }

                            }else if (addOrRemove == 2 || aRPick == 2){
                                //REMOVE
                                nl();
                                printStar();
                                nl();
                                print("What would you like to remove from the base? (Pick)");

                                if (aRPick == 0) {
                                    for (int i = 0; i < coChicken.base.size(); i++) {
                                        System.out.println("(" + (i + 1) + ") " + coChicken.base.get(i));
                                    }
                                } else {
                                    for (int i = 0; i < changeChicken.base.size(); i++) {
                                        System.out.println("(" + (i + 1) + ") " + changeChicken.base.get(i));
                                    }
                                }

                                int removeIndex = scrChoice.nextInt();
                                removeIndex -= 1;

                                if (!(removeIndex > coChicken.base.size()) && aRPick == 0) {
                                    nl();
                                    printStar();
                                    nl();
                                    print("You removed: " + coChicken.base.get(removeIndex));
                                    coChicken.base.remove(removeIndex);

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Chicken base now contains: ");

                                    for (int i = 0; i < coChicken.base.size(); i++) {
                                        System.out.println(coChicken.base.get(i));
                                    }

                                    displayChoiceRemove();
                                    int removePick = scrChoice.nextInt();

                                    if (removePick == 1) {
                                        //Allow them to remove the current item again
                                        changeChicken = coChicken;
                                        aRPick = 2;
                                    } else if (removePick == 2) {
                                        //Bring them to the Add menu for the item
                                        changeChicken = coChicken;
                                        aRPick = 1;
                                    } else if (removePick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeChicken = null;
                                        aRPick = 0;
                                    } else {
                                        //show an error
                                        list = displayError(orderList);
                                        changeChicken = null;
                                        aRPick = 0;
                                    }

                                }else if (!(removeIndex > changeChicken.base.size()) && aRPick == 2) {
                                    nl();
                                    printStar();
                                    nl();
                                    print("You removed: " + changeChicken.base.get(removeIndex));
                                    changeChicken.base.remove(removeIndex);

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Chicken base now contains: ");

                                    for (int i = 0; i < changeChicken.base.size(); i++) {
                                        System.out.println(changeChicken.base.get(i));
                                    }

                                    displayChoiceRemove();
                                    int removePick = scrChoice.nextInt();

                                    if (removePick == 1) {
                                        //Allow them to remove the current item again
                                        changeChicken = coChicken;
                                        aRPick = 2;
                                    } else if (removePick == 2) {
                                        //Bring them to the Add menu for the item
                                        changeChicken = coChicken;
                                        aRPick = 1;
                                    } else if (removePick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeChicken = null;
                                        aRPick = 0;
                                    } else {
                                        //show an error
                                        list = displayError(orderList);
                                        changeChicken = null;
                                        aRPick = 0;
                                    }
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeChicken = null;
                                    aRPick = 0;
                                }
                            }
                            addOrRemove = 0;
                        }
                    }

                    //Burger Chanage
                    if (orderList.get(changePick - 1).itemType.contentEquals("Burger") || (coBurger != null && coComboBurger.mainItem.itemType.contentEquals("Burger"))) {

                        if(aRPick == 0) {
                            if(coBurger == null){
                                coBurger = (Burger) orderList.get(changePick - 1);
                            }

                            nl();
                            printStar();
                            nl();
                            print("Which do you want to change..." + " (1)[Crown] (2)[Base]");
                            burgerPick = scrChoice.nextInt();

                            if(burgerPick == 1){
                                burgerChangeChoice = "Crown";

                                nl();
                                printStar();
                                nl();
                                print("You are changing the crown for: " + coBurger);


                                nl();
                                printStar();
                                nl();
                                print("What would you like to Add or Remove from the " + coBurger + " crown... (1)[Add] (2)[Remove]");

                                addOrRemove = scrChoice.nextInt();
                            }else if(burgerPick == 2){
                                burgerChangeChoice = "Base";

                                nl();
                                printStar();
                                nl();
                                print("You are changing the base for: " + coBurger);

                                nl();
                                printStar();
                                nl();
                                print("What would you like to Add or Remove from the " + coBurger + " base... (1)[Add] (2)[Remove]");

                                addOrRemove = scrChoice.nextInt();
                            }else{
                                //show error
                            }
                        }

                        if(burgerChangeChoice.contentEquals("Crown")){

                            if (addOrRemove == 1 || aRPick == 1) {
                                //ADD
                                nl();
                                printStar();
                                nl();
                                print("Here are a list you can add to the Burger crown (Pick)");
                                displayCrownOptions();
                                addPick = scrChoice.nextInt();

                                if(addPick == 1){
                                    if(aRPick != 0){
                                        changeBurger.crown.add("N Ranch");
                                    }else{
                                        coBurger.crown.add("N Ranch");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: Ranch");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Burger crown now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeBurger.crown.size(); i++) {
                                            System.out.println(changeBurger.crown.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coBurger.crown.size(); i++) {
                                            System.out.println(coBurger.crown.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeBurger = coBurger;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeBurger = coBurger;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeBurger = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeBurger = null;
                                        aRPick = 0;
                                    }

                                }else if(addPick == 2){
                                    if(aRPick != 0){
                                        changeBurger.crown.add("N Honey Mustard");
                                    }else{
                                        coBurger.crown.add("N Honey Mustard");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: Honey Mustard");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Burger crown now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeBurger.crown.size(); i++) {
                                            System.out.println(changeBurger.crown.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coBurger.crown.size(); i++) {
                                            System.out.println(coBurger.crown.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeBurger = coBurger;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeBurger = coBurger;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeBurger = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeBurger = null;
                                        aRPick = 0;
                                    }

                                }else if(addPick == 3){
                                    if(aRPick != 0){
                                        changeBurger.crown.add("N Mayo");
                                    }else{
                                        coBurger.crown.add("N Mayo");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: Mayo");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Burger crown now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeBurger.crown.size(); i++) {
                                            System.out.println(changeBurger.crown.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coBurger.crown.size(); i++) {
                                            System.out.println(coBurger.crown.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeBurger = coBurger;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeBurger = coBurger;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeBurger = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeBurger = null;
                                        aRPick = 0;
                                    }

                                }else if(addPick == 4){
                                    if(aRPick != 0){
                                        changeBurger.crown.add("N Mustard");
                                    }else{
                                        coBurger.crown.add("N Mustard");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: Mustard");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Burger crown now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeBurger.crown.size(); i++) {
                                            System.out.println(changeBurger.crown.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coBurger.crown.size(); i++) {
                                            System.out.println(coBurger.crown.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeBurger = coBurger;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeBurger = coBurger;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeBurger = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeBurger = null;
                                        aRPick = 0;
                                    }

                                }else if(addPick == 5){
                                    if(aRPick != 0){
                                        changeBurger.crown.add("N Ketchup");
                                    }else{
                                        coBurger.crown.add("N Ketchup");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: Ketchup");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Burger crown now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeBurger.crown.size(); i++) {
                                            System.out.println(changeBurger.crown.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coBurger.crown.size(); i++) {
                                            System.out.println(coBurger.crown.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeBurger = coBurger;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeBurger = coBurger;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeBurger = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeBurger = null;
                                        aRPick = 0;
                                    }

                                }else{
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeBurger = null;
                                    aRPick = 0;
                                }

                            }else if (addOrRemove == 2 || aRPick == 2){
                                //REMOVE
                                nl();
                                printStar();
                                nl();
                                print("What would you like to remove from the crown? (Pick)");

                                if (aRPick == 0) {
                                    for (int i = 0; i < coBurger.crown.size(); i++) {
                                        System.out.println("(" + (i + 1) + ") " + coBurger.crown.get(i));
                                    }
                                } else {
                                    for (int i = 0; i < changeBurger.crown.size(); i++) {
                                        System.out.println("(" + (i + 1) + ") " + changeBurger.crown.get(i));
                                    }
                                }

                                int removeIndex = scrChoice.nextInt();
                                removeIndex -= 1;

                                if (!(removeIndex > coBurger.crown.size()) && aRPick == 0) {
                                    nl();
                                    printStar();
                                    nl();
                                    print("You removed: " + coBurger.crown.get(removeIndex));
                                    coBurger.crown.remove(removeIndex);

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Burger crown now contains: ");

                                    for (int i = 0; i < coBurger.crown.size(); i++) {
                                        System.out.println(coBurger.crown.get(i));
                                    }

                                    displayChoiceRemove();
                                    int removePick = scrChoice.nextInt();

                                    if (removePick == 1) {
                                        //Allow them to remove the current item again
                                        changeBurger = coBurger;
                                        aRPick = 2;
                                    } else if (removePick == 2) {
                                        //Bring them to the Add menu for the item
                                        changeBurger = coBurger;
                                        aRPick = 1;
                                    } else if (removePick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeBurger = null;
                                        aRPick = 0;
                                    } else {
                                        //show an error
                                        list = displayError(orderList);
                                        changeBurger = null;
                                        aRPick = 0;
                                    }

                                } else if (!(removeIndex > changeBurger.crown.size()) && aRPick == 2) {
                                    nl();
                                    printStar();
                                    nl();
                                    print("You removed: " + changeBurger.crown.get(removeIndex));
                                    changeBurger.crown.remove(removeIndex);

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Burger crown now contains: ");

                                    for (int i = 0; i < changeBurger.crown.size(); i++) {
                                        System.out.println(changeBurger.crown.get(i));
                                    }

                                    displayChoiceRemove();
                                    int removePick = scrChoice.nextInt();

                                    if (removePick == 1) {
                                        //Allow them to remove the current item again
                                        changeBurger = coBurger;
                                        aRPick = 2;
                                    } else if (removePick == 2) {
                                        //Bring them to the Add menu for the item
                                        changeBurger = coBurger;
                                        aRPick = 1;
                                    } else if (removePick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeBurger = null;
                                        aRPick = 0;
                                    } else {
                                        //show an error
                                        list = displayError(orderList);
                                        changeBurger = null;
                                        aRPick = 0;
                                    }
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeBurger = null;
                                    aRPick = 0;
                                }
                            }
                            addOrRemove = 0;
                        }else if(burgerChangeChoice.contentEquals("Base")){

                            if (addOrRemove == 1 || aRPick == 1) {
                                //ADD
                                nl();
                                printStar();
                                nl();
                                print("Here are a list you can add to the Burger base (Pick)");
                                displayBaseOptions();
                                addPick = scrChoice.nextInt();

                                if(addPick == 1){
                                    if(aRPick != 0){
                                        changeBurger.base.add("N 2oz Patty");
                                    }else{
                                        coBurger.base.add("N 2oz Patty");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: 2oz Patty");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Burger base now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeBurger.base.size(); i++) {
                                            System.out.println(changeBurger.base.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coBurger.base.size(); i++) {
                                            System.out.println(coBurger.base.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeBurger = coBurger;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeBurger = coBurger;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeBurger = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeBurger = null;
                                        aRPick = 0;
                                    }

                                }else if(addPick == 2){
                                    if(aRPick != 0){
                                        changeBurger.base.add("N 4oz Patty");
                                    }else{
                                        coBurger.base.add("N 4oz Patty");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: 4oz Patty");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Burger base now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeBurger.base.size(); i++) {
                                            System.out.println(changeBurger.base.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coBurger.base.size(); i++) {
                                            System.out.println(coBurger.base.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeBurger = coBurger;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeBurger = coBurger;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeBurger = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeBurger = null;
                                        aRPick = 0;
                                    }

                                }else if(addPick == 3){
                                if(aRPick != 0){
                                    changeBurger.base.add("N Grilled Chicken");
                                }else{
                                    coBurger.base.add("N Grilled Chicken");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Grilled Chicken");

                                nl();
                                printStar();
                                nl();
                                print("The Burger now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeBurger.base.size(); i++) {
                                        System.out.println(changeBurger.base.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coBurger.base.size(); i++) {
                                        System.out.println(coBurger.base.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeSalad = coSalad;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeSalad = coSalad;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeSalad = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeSalad = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 4){
                                    if(aRPick != 0){
                                        changeBurger.base.add("N Homestyle Chicken");
                                    }else{
                                        coBurger.base.add("N Homestyle Chicken");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: Homestyle Chicken");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Burger base now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeBurger.base.size(); i++) {
                                            System.out.println(changeBurger.base.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coBurger.base.size(); i++) {
                                            System.out.println(coBurger.base.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeBurger = coBurger;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeBurger = coBurger;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeBurger = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeBurger = null;
                                        aRPick = 0;
                                    }

                                }else if(addPick == 5){

                                    if(aRPick != 0){
                                        changeBurger.base.add("N Spicy Chicken");
                                    }else{
                                        coBurger.base.add("N Spicy Chicken");
                                    }

                                    nl();
                                    printStar();
                                    nl();
                                    System.out.println("You added: Spicy Chicken");

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Burger base now contains: ");
                                    if(aRPick != 0){
                                        for (int i = 0; i < changeBurger.base.size(); i++) {
                                            System.out.println(changeBurger.base.get(i));
                                        }
                                    }else{
                                        for (int i = 0; i < coBurger.base.size(); i++) {
                                            System.out.println(coBurger.base.get(i));
                                        }
                                    }

                                    displayChoiceAdd();
                                    addPick = scrChoice.nextInt();

                                    if (addPick == 1) {
                                        //Allow them to add the current item again
                                        changeBurger = coBurger;
                                        aRPick = 1;
                                    } else if (addPick == 2) {
                                        //Bring them to the Remove menu for the item
                                        changeBurger = coBurger;
                                        aRPick = 2;
                                    } else if (addPick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeBurger = null;
                                        aRPick = 0;
                                    } else {
                                        //Show an error and reset
                                        list = displayError(orderList);
                                        changeBurger = null;
                                        aRPick = 0;
                                    }

                                }else{
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeBurger = null;
                                    aRPick = 0;
                                }

                            }else if (addOrRemove == 2 || aRPick == 2){
                                //REMOVE
                                nl();
                                printStar();
                                nl();
                                print("What would you like to remove from the base? (Pick)");

                                if (aRPick == 0) {
                                    for (int i = 0; i < coBurger.base.size(); i++) {
                                        System.out.println("(" + (i + 1) + ") " + coBurger.base.get(i));
                                    }
                                } else {
                                    for (int i = 0; i < changeBurger.base.size(); i++) {
                                        System.out.println("(" + (i + 1) + ") " + changeBurger.base.get(i));
                                    }
                                }

                                int removeIndex = scrChoice.nextInt();
                                removeIndex -= 1;

                                if (!(removeIndex > coBurger.base.size()) && aRPick == 0) {
                                    nl();
                                    printStar();
                                    nl();
                                    print("You removed: " + coBurger.base.get(removeIndex));
                                    coBurger.base.remove(removeIndex);

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Burger base now contains: ");

                                    for (int i = 0; i < coBurger.base.size(); i++) {
                                        System.out.println(coBurger.base.get(i));
                                    }

                                    displayChoiceRemove();
                                    int removePick = scrChoice.nextInt();

                                    if (removePick == 1) {
                                        //Allow them to remove the current item again
                                        changeBurger = coBurger;
                                        aRPick = 2;
                                    } else if (removePick == 2) {
                                        //Bring them to the Add menu for the item
                                        changeBurger = coBurger;
                                        aRPick = 1;
                                    } else if (removePick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeBurger = null;
                                        aRPick = 0;
                                    } else {
                                        //show an error
                                        list = displayError(orderList);
                                        changeBurger = null;
                                        aRPick = 0;
                                    }

                                }else if (!(removeIndex > changeBurger.base.size()) && aRPick == 2) {
                                    nl();
                                    printStar();
                                    nl();
                                    print("You removed: " + changeBurger.base.get(removeIndex));
                                    changeBurger.base.remove(removeIndex);

                                    nl();
                                    printStar();
                                    nl();
                                    print("The Burger base now contains: ");

                                    for (int i = 0; i < changeBurger.base.size(); i++) {
                                        System.out.println(changeBurger.base.get(i));
                                    }

                                    displayChoiceRemove();
                                    int removePick = scrChoice.nextInt();

                                    if (removePick == 1) {
                                        //Allow them to remove the current item again
                                        changeBurger = coBurger;
                                        aRPick = 2;
                                    } else if (removePick == 2) {
                                        //Bring them to the Add menu for the item
                                        changeBurger = coBurger;
                                        aRPick = 1;
                                    } else if (removePick == 3) {
                                        //return to the main menu
                                        displayListOther();
                                        list = listChoiceOther();
                                        changeBurger = null;
                                        aRPick = 0;
                                    } else {
                                        //show an error
                                        list = displayError(orderList);
                                        changeBurger = null;
                                        aRPick = 0;
                                    }
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeBurger = null;
                                    aRPick = 0;
                                }
                            }
                            addOrRemove = 0;
                        }
                    }

                    //Salad Change
                    if (orderList.get(changePick - 1).itemType.contentEquals("Salad") || changeSalad != null) {

                        if(aRPick == 0) {
                            coSalad = (Salad) orderList.get(changePick - 1);

                            nl();
                            printStar();
                            nl();
                            print("The Salad contains: ");

                            for (int i = 0; i < coSalad.contain.size(); i++) {
                                System.out.println(coSalad.contain.get(i));
                            }
                            nl();
                            printStar();
                            nl();
                            print("What would you like to Add or Remove from the " + coSalad + " (1)[Add] (2)[Remove]");
                            addOrRemove = scrChoice.nextInt();
                        }

                        if (addOrRemove == 1 || aRPick == 1) {
                            //ADD
                            nl();
                            printStar();
                            nl();
                            print("Here are a list you can add to the Salad. (Pick)");
                            displaySaladOptions();
                            addPick = scrChoice.nextInt();

                            if(addPick == 1){
                                if(aRPick != 0){
                                    changeSalad.contain.add("N Shredded Cheese");
                                }else{
                                    coSalad.contain.add("N Shredded Cheese");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Shredded Cheese");

                                nl();
                                printStar();
                                nl();
                                print("The Salad now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeSalad.contain.size(); i++) {
                                        System.out.println(changeSalad.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSalad.contain.size(); i++) {
                                        System.out.println(coSalad.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeSalad = coSalad;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeSalad = coSalad;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeSalad = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeSalad = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 2){
                                if(aRPick != 0){
                                    changeSalad.contain.add("N Diced Tomatos");
                                }else{
                                    coSalad.contain.add("N Diced Tomatos");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Diced Tomatos");

                                nl();
                                printStar();
                                nl();
                                print("The Salad now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeSalad.contain.size(); i++) {
                                        System.out.println(changeSalad.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSalad.contain.size(); i++) {
                                        System.out.println(coSalad.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeSalad = coSalad;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeSalad = coSalad;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeSalad = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeSalad = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 3){
                                if(aRPick != 0){
                                    changeSalad.contain.add("N Asiago Cheese");
                                }else{
                                    coSalad.contain.add("N Asiago Cheese");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Asiago Cheese");

                                nl();
                                printStar();
                                nl();
                                print("The Salad now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeSalad.contain.size(); i++) {
                                        System.out.println(changeSalad.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSalad.contain.size(); i++) {
                                        System.out.println(coSalad.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeSalad = coSalad;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeSalad = coSalad;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeSalad = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeSalad = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 4){
                                if(aRPick != 0){
                                    changeSalad.contain.add("N 3 Pieces of Bacon");
                                }else{
                                    coSalad.contain.add("N 3 Pieces of Bacon");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: 3 Pieces of Bacon");

                                nl();
                                printStar();
                                nl();
                                print("The Salad now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeSalad.contain.size(); i++) {
                                        System.out.println(changeSalad.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSalad.contain.size(); i++) {
                                        System.out.println(coSalad.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeSalad = coSalad;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeSalad = coSalad;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeSalad = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeSalad = null;
                                    aRPick = 0;
                                }

                            }else if(addPick == 5){
                                if(aRPick != 0){
                                    changeSalad.contain.add("N Bacon Bits");
                                }else{
                                    coSalad.contain.add("N Bacon Bits");
                                }

                                nl();
                                printStar();
                                nl();
                                System.out.println("You added: Bacon Bits");

                                nl();
                                printStar();
                                nl();
                                print("The Salad now contains: ");
                                if(aRPick != 0){
                                    for (int i = 0; i < changeSalad.contain.size(); i++) {
                                        System.out.println(changeSalad.contain.get(i));
                                    }
                                }else{
                                    for (int i = 0; i < coSalad.contain.size(); i++) {
                                        System.out.println(coSalad.contain.get(i));
                                    }
                                }

                                displayChoiceAdd();
                                addPick = scrChoice.nextInt();

                                if (addPick == 1) {
                                    //Allow them to add the current item again
                                    changeSalad = coSalad;
                                    aRPick = 1;
                                } else if (addPick == 2) {
                                    //Bring them to the Remove menu for the item
                                    changeSalad = coSalad;
                                    aRPick = 2;
                                } else if (addPick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeSalad = null;
                                    aRPick = 0;
                                } else {
                                    //Show an error and reset
                                    list = displayError(orderList);
                                    changeSalad = null;
                                    aRPick = 0;
                                }

                            }else{
                                //Show an error and reset
                                list = displayError(orderList);
                                changeSalad = null;
                                aRPick = 0;
                            }

                        } else if (addOrRemove == 2 || aRPick == 2){
                            //REMOVE
                            print("What would you like to remove?");
                            if(aRPick == 0) {
                                for (int i = 0; i < coSalad.contain.size(); i++) {
                                    System.out.println("(" + (i + 1) + ") " + coSalad.contain.get(i));
                                }
                            }else{
                                for (int i = 0; i < changeSalad.contain.size(); i++) {
                                    System.out.println("(" + (i + 1) + ") " + changeSalad.contain.get(i));
                                }
                            }

                            int removeIndex = scrChoice.nextInt();
                            removeIndex -= 1;

                            if (!(removeIndex > coSalad.contain.size()) && aRPick == 0) {
                                print("You removed: " + coSalad.contain.get(removeIndex));
                                coSalad.contain.remove(removeIndex);

                                nl();
                                printStar();
                                nl();
                                print("The Salad now contains: ");

                                for (int i = 0; i < coSalad.contain.size(); i++) {
                                    System.out.println(coSalad.contain.get(i));
                                }

                                displayChoiceRemove();
                                int removePick = scrChoice.nextInt();

                                if (removePick == 1) {
                                    //Allow them to remove the current item again
                                    changeSalad = coSalad;
                                    aRPick = 2;
                                } else if (removePick == 2) {
                                    //Bring them to the Add menu for the item
                                    changeSalad = coSalad;
                                    aRPick = 1;
                                } else if (removePick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeSalad = null;
                                    aRPick = 0;
                                } else {
                                    //show an error
                                    list = displayError(orderList);
                                    changeSalad = null;
                                    aRPick = 0;
                                }
                            }else if(!(removeIndex > changeSalad.contain.size()) && aRPick > 0){
                                print("You removed: " + changeSalad.contain.get(removeIndex));
                                coSalad.contain.remove(removeIndex);

                                nl();
                                printStar();
                                nl();
                                print("The Salad now contains: ");

                                for (int i = 0; i < changeSalad.contain.size(); i++) {
                                    System.out.println(changeSalad.contain.get(i));
                                }

                                displayChoiceRemove();
                                int removePick = scrChoice.nextInt();

                                if (removePick == 1) {
                                    //Allow them to remove the current item again
                                    changeSalad = coSalad;
                                    aRPick = 2;
                                } else if (removePick == 2) {
                                    //Bring them to the Add menu for the item
                                    changeSalad = coSalad;
                                    aRPick = 1;
                                } else if (removePick == 3) {
                                    //return to the main menu
                                    list = "Choice";
                                    displayListOther();
                                    list = listChoiceOther();
                                    changeSalad = null;
                                    aRPick = 0;
                                } else {
                                    //show an error
                                    list = displayError(orderList);
                                    changeSalad = null;
                                    aRPick = 0;
                                }
                            }else {
                                //Show an error and reset
                                list = displayError(orderList);
                                changeSalad = null;
                                aRPick = 0;
                            }
                        }
                        addOrRemove = 0;
                    }
                }

                    //Checken Order list
                    if (list.contentEquals("Chicken") || list.contentEquals("ChickenSkip")) {
                        //When the user picks one of themn
                        String chickenChoice;
                        if(!list.contentEquals("ChickenSkip")){
                             chickenChoice = scrChoiceStr.nextLine();
                        }else{
                             chickenChoice = lastChicken;
                        }

                        switch (chickenChoice) {
                            case "1":
                                Chicken c1 = new Chicken("Spicy Chicken", "Chicken");
                                if (wantToComboChicken(c1)) {
                                    //Call the Burger Combo Function
                                    chickenCombo CC1 = currentChickenCombo(c1);
                                    if(CC1 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(CC1);
                                    displayOrder(orderList);
                                    anotherChicken(c1);
                                    lastChicken = "1";
                                    list = afterDisplay("ChickenSkip", orderList);


                                } else {
                                    //Call the non Burger Function
                                    dontWantToComboChicken(c1);
                                    orderList.add(c1);
                                    displayOrder(orderList);
                                    anotherChicken(c1);
                                    lastChicken = "1";
                                    list = afterDisplay("ChickenSkip",orderList);

                                }
                                break;
                            case "2":
                                Chicken c2 = new Chicken("Homestyle Chicken", "Chicken");
                                if (wantToComboChicken(c2)) {
                                    //Call the Burger Combo Function
                                    chickenCombo CC2 = currentChickenCombo(c2);
                                    if(CC2 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(CC2);
                                    displayOrder(orderList);
                                    anotherChicken(c2);
                                    lastChicken = "2";
                                    list = afterDisplay("ChickenSkip", orderList);

                                } else {
                                    //Call the non Burger Function
                                    dontWantToComboChicken(c2);
                                    orderList.add(c2);
                                    displayOrder(orderList);
                                    anotherChicken(c2);
                                    lastChicken = "2";
                                    list = afterDisplay("ChickenSkip", orderList);
                                }
                                break;
                            case "3":
                                Chicken c3 = new Chicken("Grilled Chicken", "Chicken");
                                if (wantToComboChicken(c3)) {
                                    //Call the Burger Combo Function
                                    chickenCombo CC3 = currentChickenCombo(c3);
                                    if(CC3 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(CC3);
                                    displayOrder(orderList);
                                    anotherChicken(c3);
                                    lastChicken = "3";
                                    list = afterDisplay("ChickenSkip", orderList);

                                } else {
                                    //Call the non Burger Function
                                    dontWantToComboChicken(c3);
                                    orderList.add(c3);
                                    displayOrder(orderList);
                                    anotherChicken(c3);
                                    lastChicken = "3";
                                    list = afterDisplay("ChickenSkip", orderList);
                                }
                                break;
                            case "4":
                                Chicken c4 = new Chicken("Spicy Asiago Ranch Club", "Chicken");
                                if (wantToComboChicken(c4)) {
                                    //Call the Burger Combo Function
                                    chickenCombo CC4 = currentChickenCombo(c4);
                                    if(CC4 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(CC4);
                                    displayOrder(orderList);
                                    anotherChicken(c4);
                                    lastChicken = "4";
                                    list = afterDisplay("ChickenSkip", orderList);

                                } else {
                                    //Call the non Burger Function
                                    dontWantToComboChicken(c4);
                                    orderList.add(c4);
                                    displayOrder(orderList);
                                    anotherChicken(c4);
                                    lastChicken = "4";
                                    list = afterDisplay("ChickenSkip", orderList);
                                }
                                break;
                            case "5":
                                Chicken c5 = new Chicken("Homestyle Asiago Ranch Club", "Chicken");
                                if (wantToComboChicken(c5)) {
                                    //Call the Burger Combo Function
                                    chickenCombo CC5 = currentChickenCombo(c5);
                                    if(CC5 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(CC5);
                                    displayOrder(orderList);
                                    anotherChicken(c5);
                                    lastChicken = "5";
                                    list = afterDisplay("ChickenSkip", orderList);

                                } else {
                                    //Call the non Burger Function
                                    dontWantToComboChicken(c5);
                                    orderList.add(c5);
                                    displayOrder(orderList);
                                    anotherChicken(c5);
                                    lastChicken = "5";
                                    list = afterDisplay("ChickenSkip", orderList);
                                }
                                break;
                            case "6":
                                Chicken c6 = new Chicken("Grilled Asiago Ranch Club", "Chicken");
                                if (wantToComboChicken(c6)) {
                                    //Call the Burger Combo Function
                                    chickenCombo CC6 = currentChickenCombo(c6);
                                    if(CC6 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(CC6);
                                    displayOrder(orderList);
                                    anotherChicken(c6);
                                    lastChicken = "6";
                                    list = afterDisplay("ChickenSkip", orderList);

                                } else {
                                    //Call the non Burger Function
                                    dontWantToComboChicken(c6);
                                    orderList.add(c6);
                                    displayOrder(orderList);
                                    anotherChicken(c6);
                                    lastChicken = "6";
                                    list = afterDisplay("ChickenSkip", orderList);
                                }
                                break;
                            case "7":
                                Chicken c7 = new Chicken("5 Piece Chicken Nugget", "Chicken Side");
                                if (wantToComboChicken(c7)) {
                                    //Call the Burger Combo Function
                                    whatSauce(c7);
                                    whatSauce(c7);
                                    chickenCombo CC7 = currentChickenCombo(c7);
                                    if(CC7 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(CC7);
                                    displayOrder(orderList);
                                    anotherChicken(c7);
                                    lastChicken = "7";
                                    list = afterDisplay("ChickenSkip", orderList);

                                } else {
                                    //Call the non Burger Function
                                    whatSauce(c7);
                                    whatSauce(c7);
                                    dontWantToComboChicken(c7);
                                    orderList.add(c7);
                                    displayOrder(orderList);
                                    anotherChicken(c7);
                                    lastChicken = "7";
                                    list = afterDisplay("ChickenSkip", orderList);
                                }
                                break;
                            case "8":
                                Chicken c8 = new Chicken("10 Piece Chicken Nugget", "Chicken Side");
                                if (wantToComboChicken(c8)) {
                                    //Call the Burger Combo Function
                                    whatSauce(c8);
                                    whatSauce(c8);
                                    chickenCombo CC8 = currentChickenCombo(c8);
                                    if(CC8 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(CC8);
                                    displayOrder(orderList);
                                    anotherChicken(c8);
                                    lastChicken = "8";
                                    list = afterDisplay("ChickenSkip", orderList);

                                } else {
                                    //Call the non Burger Function
                                    whatSauce(c8);
                                    whatSauce(c8);
                                    dontWantToComboChicken(c8);
                                    orderList.add(c8);
                                    displayOrder(orderList);
                                    anotherChicken(c8);
                                    lastChicken = "8";
                                    list = afterDisplay("ChickenSkip", orderList);
                                }
                                break;
                            case "9":
                                Chicken c9 = new Chicken("Homestyle Chicken Strips", "Chicken Side");
                                if (wantToComboChicken(c9)) {
                                    //Call the Burger Combo Function
                                    chickenCombo CC9 = currentChickenCombo(c9);
                                    if(CC9 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(CC9);
                                    displayOrder(orderList);
                                    anotherChicken(c9);
                                    lastChicken = "9";
                                    list = afterDisplay("ChickenSkip", orderList);

                                } else {
                                    //Call the non Burger Function
                                    dontWantToComboChicken(c9);
                                    orderList.add(c9);
                                    displayOrder(orderList);
                                    anotherChicken(c9);
                                    lastChicken = "9";
                                    list = afterDisplay("ChickenSkip", orderList);
                                }
                                break;
                            case "10":
                                Chicken c10 = new Chicken("Spicy Chicken Strips", "Chicken Side");
                                if (wantToComboChicken(c10)) {
                                    //Call the Burger Combo Function
                                    whatSauce(c10);
                                    whatSauce(c10);
                                    chickenCombo CC10 = currentChickenCombo(c10);
                                    if(CC10 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(CC10);
                                    displayOrder(orderList);
                                    anotherChicken(c10);
                                    lastChicken = "10";
                                    list = afterDisplay("ChickenSkip", orderList);

                                } else {
                                    //Call the non Burger Function
                                    dontWantToComboChicken(c10);
                                    orderList.add(c10);
                                    displayOrder(orderList);
                                    anotherChicken(c10);
                                    lastChicken = "10";
                                    list = afterDisplay("ChickenSkip", orderList);
                                }
                                break;

                            case "11":
                                Chicken c11 = new Chicken("Spicy Chicken Wrap", "Chicken Wrap");
                                if (wantToComboChicken(c11)){
                                    //Call the Burger Combo Function
                                    whatSauce(c11);
                                    whatSauce(c11);
                                    chickenCombo CC11 = currentChickenCombo(c11);
                                    if(CC11 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(CC11);
                                    displayOrder(orderList);
                                    anotherChicken(c11);
                                    lastChicken = "11";
                                    list = afterDisplay("ChickenSkip", orderList);

                                } else {
                                    //Call the non Burger Function
                                    dontWantToComboChicken(c11);
                                    orderList.add(c11);
                                    displayOrder(orderList);
                                    anotherChicken(c11);
                                    lastChicken = "11";
                                    list = afterDisplay("ChickenSkip", orderList);
                                }
                                break;
                            case "12":
                                Chicken c12 = new Chicken("Homestyle Chicken Wrap", "Chicken Wrap");
                                if (wantToComboChicken(c12)) {
                                    //Call the Burger Combo Function
                                    chickenCombo CC12 = currentChickenCombo(c12);
                                    if(CC12 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(CC12);
                                    displayOrder(orderList);
                                    anotherChicken(c12);
                                    lastChicken = "12";
                                    list = afterDisplay("ChickenSkip", orderList);

                                } else {
                                    //Call the non Burger Function
                                    dontWantToComboChicken(c12);
                                    orderList.add(c12);
                                    displayOrder(orderList);
                                    anotherChicken(c12);
                                    lastChicken = "12";
                                    list = afterDisplay("ChickenSkip", orderList);
                                }
                                break;
                            case "13":
                                Chicken c13 = new Chicken("Grilled Chicken Wrap", "Chicken Wrap");
                                if (wantToComboChicken(c13)) {
                                    //Call the Burger Combo Function
                                    chickenCombo CC13 = currentChickenCombo(c13);
                                    if(CC13 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(CC13);
                                    displayOrder(orderList);
                                    anotherChicken(c13);
                                    lastChicken = "13";
                                    list = afterDisplay("ChickenSkip", orderList);

                                } else {
                                    //Call the non Burger Function
                                    dontWantToComboChicken(c13);
                                    orderList.add(c13);
                                    displayOrder(orderList);
                                    anotherChicken(c13);
                                    lastChicken = "13";
                                    list = afterDisplay("ChickenSkip", orderList);
                                }
                                break;
                            case "14":
                                list = "Choice";
                                if(orderContain(orderList)) {
                                    list = listChoiceOther();
                                }else{
                                    list = listChoice();
                                }
                                break;
                            default:
                                list = "Choice";
                                nl();
                                print("Error: That's not a accessible option");
                                if(orderContain(orderList)) {
                                    list = listChoiceOther();
                                }else{
                                    list = listChoice();
                                }
                                break;
                        }
                    }

                    //Burger Order List
                    if (list.contentEquals("Hamburger") || list.contentEquals("HamburgerSkip")) {
                        //When the user picks one of them
                        String burgerChoice;
                        if(!list.contentEquals("HamburgerSkip")){
                             burgerChoice = scrChoiceStr.nextLine();
                        }else{
                             burgerChoice = lastBurger;
                        }


                        switch (burgerChoice) {
                            case "1":
                                //Single Cheese
                                Burger b1 = new Burger("Single Cheese", "Burger");
                                if (wantToComboBurger(b1)) {
                                    //Call the Burger Combo Function
                                    burgerCombo BC1 = currentBurgerCombo(b1);
                                    if(BC1 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(BC1);
                                    displayOrder(orderList);
                                    anotherBurger(b1);
                                    lastBurger = "1";
                                    list = afterDisplay("Hamburger", orderList);

                                } else {
                                    //Call the non Burger Function
                                    b1 = dontWantToComboBurger(b1);
                                    orderList.add(b1);
                                    displayOrder(orderList);
                                    anotherBurger(b1);
                                    lastBurger = "1";
                                    list = afterDisplay("HamburgerSkip", orderList);
                                }
                                break;
                            case "2":
                                Burger b2 = new Burger("Double Cheese", "Burger");
                                if (wantToComboBurger(b2)) {
                                    //Call the Burger Combo Function
                                    burgerCombo BC2 = currentBurgerCombo(b2);
                                    if(BC2 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(BC2);
                                    displayOrder(orderList);
                                    anotherBurger(b2);
                                    lastBurger = "2";
                                    list = afterDisplay("Hamburger", orderList);

                                } else {
                                    //Call the non Burger Function
                                    b2 = dontWantToComboBurger(b2);
                                    orderList.add(b2);
                                    displayOrder(orderList);
                                    anotherBurger(b2);
                                    lastBurger = "2";
                                    list = afterDisplay("HamburgerSkip", orderList);
                                }
                                break;
                            case "3":
                                Burger b3 = new Burger("Triple Cheese", "Burger");
                                if (wantToComboBurger(b3)) {
                                    //Call the Burger Combo Function
                                    burgerCombo BC3 = currentBurgerCombo(b3);
                                    if(BC3 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(BC3);
                                    displayOrder(orderList);
                                    anotherBurger(b3);
                                    lastBurger = "3";
                                    list = afterDisplay("Hamburger", orderList);

                                } else {
                                    //Call the non Burger Function
                                    b3 = dontWantToComboBurger(b3);
                                    orderList.add(b3);
                                    displayOrder(orderList);
                                    anotherBurger(b3);
                                    lastBurger = "3";
                                    list = afterDisplay("HamburgerSkip", orderList);
                                }
                                break;
                            case "4":
                                Burger b4 = new Burger("Single Bacon Deluxe", "Burger");
                                if (wantToComboBurger(b4)) {
                                    //Call the Burger Combo Function
                                    burgerCombo BC4 = currentBurgerCombo(b4);
                                    if(BC4 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        };
                                        break;
                                    }
                                    orderList.add(BC4);
                                    displayOrder(orderList);
                                    anotherBurger(b4);
                                    lastBurger = "4";
                                    list = afterDisplay("Hamburger", orderList);

                                } else {
                                    //Call the non Burger Function
                                    b4 = dontWantToComboBurger(b4);
                                    orderList.add(b4);
                                    displayOrder(orderList);
                                    anotherBurger(b4);
                                    lastBurger = "4";
                                    list = afterDisplay("HamburgerSkip", orderList);
                                }
                                break;
                            case "5":
                                Burger b5 = new Burger("Double Bacon Deluxe", "Burger");
                                if (wantToComboBurger(b5)) {
                                    //Call the Burger Combo Function
                                    burgerCombo BC5 = currentBurgerCombo(b5);
                                    if(BC5 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(BC5);
                                    displayOrder(orderList);
                                    anotherBurger(b5);
                                    lastBurger = "5";
                                    list = afterDisplay("Hamburger", orderList);

                                } else {
                                    //Call the non Burger Function
                                    b5 = dontWantToComboBurger(b5);
                                    orderList.add(b5);
                                    displayOrder(orderList);
                                    anotherBurger(b5);
                                    lastBurger = "5";
                                    list = afterDisplay("HamburgerSkip", orderList);
                                }
                                break;
                            case "6":
                                Burger b6 = new Burger("Triple Bacon Deluxe", "Burger");
                                if (wantToComboBurger(b6)) {
                                    //Call the Burger Combo Function
                                    burgerCombo BC6 = currentBurgerCombo(b6);
                                    if(BC6 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(BC6);
                                    displayOrder(orderList);
                                    anotherBurger(b6);
                                    lastBurger = "6";
                                    list = afterDisplay("Hamburger", orderList);

                                } else {
                                    //Call the non Burger Function
                                    b6 = dontWantToComboBurger(b6);
                                    orderList.add(b6);
                                    displayOrder(orderList);
                                    anotherBurger(b6);
                                    lastBurger = "6";
                                    list = afterDisplay("HamburgerSkip", orderList);
                                }
                                break;
                            case "7":
                                Burger b7 = new Burger("Single Baconator", "Burger");
                                if (wantToComboBurger(b7)) {
                                    //Call the Burger Combo Function
                                    burgerCombo BC7 = currentBurgerCombo(b7);
                                    if(BC7 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(BC7);
                                    displayOrder(orderList);
                                    anotherBurger(b7);
                                    lastBurger = "7";
                                    list = afterDisplay("Hamburger", orderList);

                                } else {
                                    //Call the non Burger Function
                                    b7 = dontWantToComboBurger(b7);
                                    orderList.add(b7);
                                    displayOrder(orderList);
                                    anotherBurger(b7);
                                    lastBurger = "7";
                                    list = afterDisplay("HamburgerSkip", orderList);
                                }
                                break;
                            case "8":
                                Burger b8 = new Burger("Double Baconator", "Burger");
                                if (wantToComboBurger(b8)) {
                                    //Call the Burger Combo Function
                                    burgerCombo BC8 = currentBurgerCombo(b8);
                                    if(BC8 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(BC8);
                                    displayOrder(orderList);
                                    anotherBurger(b8);
                                    lastBurger = "8";
                                    list = afterDisplay("Hamburger", orderList);

                                } else {
                                    //Call the non Burger Function
                                    b8 = dontWantToComboBurger(b8);
                                    orderList.add(b8);
                                    displayOrder(orderList);
                                    anotherBurger(b8);
                                    lastBurger = "8";
                                    list = afterDisplay("HamburgerSkip", orderList);
                                }
                                break;
                            case "9":
                                Burger b9 = new Burger("Triple Baconator", "Burger");
                                if (wantToComboBurger(b9)) {
                                    //Call the Burger Combo Function
                                    burgerCombo BC9 = currentBurgerCombo(b9);
                                    if(BC9 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(BC9);
                                    displayOrder(orderList);
                                    anotherBurger(b9);
                                    lastBurger = "9";
                                    list = afterDisplay("Hamburger", orderList);

                                } else {
                                    //Call the non Burger Function
                                    b9 = dontWantToComboBurger(b9);
                                    orderList.add(b9);
                                    displayOrder(orderList);
                                    anotherBurger(b9);
                                    lastBurger = "9";
                                    list = afterDisplay("HamburgerSkip", orderList);
                                }
                                break;
                            case "10":
                                Burger b10 = new Burger("Son of the Baconator", "Burger");
                                if (wantToComboBurger(b10)) {
                                    //Call the Burger Combo Function
                                    burgerCombo BC10 = currentBurgerCombo(b10);
                                    if(BC10 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                           list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(BC10);
                                    displayOrder(orderList);
                                    anotherBurger(b10);
                                    lastBurger = "10";
                                    list = afterDisplay("Hamburger", orderList);

                                } else {
                                    //Call the non Burger Function
                                    b10 = dontWantToComboBurger(b10);
                                    orderList.add(b10);
                                    displayOrder(orderList);
                                    anotherBurger(b10);
                                    lastBurger = "10";
                                    list = afterDisplay("HamburgerSkip", orderList);
                                }
                                break;

                            case "11":
                                Burger b11 = new Burger("Jr. Hamburger Deluxe", "Burger");
                                if (wantToComboBurger(b11)) {
                                    //Call the Burger Combo Function
                                    burgerCombo BC11 = currentBurgerCombo(b11);
                                    if(BC11 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(BC11);
                                    displayOrder(orderList);
                                    anotherBurger(b11);
                                    lastBurger = "11";
                                    list = afterDisplay("Hamburger", orderList);

                                } else {
                                    //Call the non Burger Function
                                    b11 = dontWantToComboBurger(b11);
                                    orderList.add(b11);
                                    displayOrder(orderList);
                                    anotherBurger(b11);
                                    lastBurger = "11";
                                    list = afterDisplay("HamburgerSkip", orderList);
                                }
                                break;
                            case "12":
                                Burger b12 = new Burger("Jr. Cheeseburger Deluxe", "Burger");
                                if (wantToComboBurger(b12)) {
                                    //Call the Burger Combo Function
                                    burgerCombo BC12 = currentBurgerCombo(b12);
                                    if(BC12 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(BC12);
                                    displayOrder(orderList);
                                    anotherBurger(b12);
                                    lastBurger = "12";
                                    list = afterDisplay("Hamburger", orderList);

                                } else {
                                    //Call the non Burger Function
                                    b12 = dontWantToComboBurger(b12);
                                    orderList.add(b12);
                                    displayOrder(orderList);
                                    anotherBurger(b12);
                                    lastBurger = "12";
                                    list = afterDisplay("HamburgerSkip", orderList);
                                }
                                break;
                            case "13":
                                Burger b13 = new Burger("Jr. Bacon Cheeseburger", "Burger");
                                if (wantToComboBurger(b13)) {
                                    //Call the Burger Combo Function
                                    burgerCombo BC13 = currentBurgerCombo(b13);
                                    if(BC13 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                          list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(BC13);
                                    displayOrder(orderList);
                                    anotherBurger(b13);
                                    lastBurger = "13";
                                    list = afterDisplay("Hamburger", orderList);

                                } else {
                                    //Call the non Burger Function
                                    b13 = dontWantToComboBurger(b13);
                                    orderList.add(b13);
                                    displayOrder(orderList);
                                    anotherBurger(b13);
                                    lastBurger = "13";
                                    list = afterDisplay("HamburgerSkip", orderList);
                                }
                                break;
                            case "14":
                                Burger b14 = new Burger("Cheesey Cheeseburger", "Burger");
                                if (wantToComboBurger(b14)) {
                                    //Call the Burger Combo Function
                                    burgerCombo BC14 = currentBurgerCombo(b14);
                                    if(BC14 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderContain(orderList)) {
                                            list = listChoiceOther();
                                        }else{
                                            list = listChoice();
                                        }
                                        break;
                                    }
                                    orderList.add(BC14);
                                    displayOrder(orderList);
                                    anotherBurger(b14);
                                    lastBurger = "14";
                                    list = afterDisplay("Hamburger", orderList);

                                } else {
                                    //Call the non Burger Function
                                    b14 = dontWantToComboBurger(b14);
                                    orderList.add(b14);
                                    displayOrder(orderList);
                                    anotherBurger(b14);
                                    lastBurger = "14";
                                    list = afterDisplay("HamburgerSkip", orderList);
                                }
                                break;
                            case "15":

                                if(orderContain(orderList)) {
                                    list = listChoiceOther();
                                }else{
                                    list = listChoice();
                                }
                                break;
                            default:
                                nl();
                                print("Error: That's not a accessible option");
                                if(orderContain(orderList)) {
                                    list = listChoiceOther();
                                }else{
                                    list = listChoice();
                                }
                                break;
                        }
                    }

                    //Salad Order List
                    if (list.contentEquals("Salad") || list.contentEquals("SaladSkip")) {
                        //When the user picks one of them
                        String saladChoice;
                        if (!list.contentEquals("SaladSkip")) {
                            saladChoice = scrChoiceStr.nextLine();
                        } else {
                            saladChoice = lastSalad;
                        }

                        switch(saladChoice) {
                            case "1":
                                Salad s1 = new Salad("Taco Salad", "Full", "Salad");
                                //Call the non Burger Function
                                saladAdd(s1);
                                orderList.add(s1);
                                displayOrder(orderList);
                                anotherSalad(s1);
                                lastSalad = "1";
                                list = afterDisplay("SaladSkip", orderList);

                                break;
                            case "2":
                                Salad s2 = new Salad("Taco Salad", "Half", "Salad");
                                //Call the non Burger Function
                                saladAdd(s2);
                                orderList.add(s2);
                                displayOrder(orderList);
                                anotherSalad(s2);
                                lastSalad = "2";
                                list = afterDisplay("SaladSkip", orderList);

                                break;
                            case "3":
                                Salad s3 = new Salad("Apple Pecan Chicken Salad", "Full", "Salad");
                                //Call the non Burger Function
                                saladAdd(s3);
                                orderList.add(s3);
                                displayOrder(orderList);
                                anotherSalad(s3);
                                lastSalad = "3";
                                list = afterDisplay("SaladSkip", orderList);

                                break;
                            case "4":
                                Salad s4 = new Salad("Apple Pecan Chicken Salad", "Half", "Salad");
                                //Call the non Burger Function
                                saladAdd(s4);
                                orderList.add(s4);
                                displayOrder(orderList);
                                anotherSalad(s4);
                                lastSalad = "4";
                                list = afterDisplay("SaladSkip", orderList);

                                break;
                            case "5":
                                Salad s5 = new Salad("Southwest Avocado Chicken Salad", "Full", "Salad");
                                //Call the non Burger Function
                                saladAdd(s5);
                                orderList.add(s5);
                                displayOrder(orderList);
                                anotherSalad(s5);
                                lastSalad = "5";
                                list = afterDisplay("SaladSkip", orderList);

                                break;
                            case "6":
                                Salad s6 = new Salad("Southwest Avocado Chicken Salad", "Full", "Salad");
                                //Call the non Burger Function
                                saladAdd(s6);
                                orderList.add(s6);
                                displayOrder(orderList);
                                anotherSalad(s6);
                                lastSalad = "6";
                                list = afterDisplay("SaladSkip", orderList);

                                break;

                            case "7":
                                Salad s7 = new Salad("Grilled Chicken Casar Salad", "Full", "Salad");
                                //Call the non Burger Function
                                saladAdd(s7);
                                orderList.add(s7);
                                displayOrder(orderList);
                                anotherSalad(s7);
                                lastSalad = "7";
                                list = afterDisplay("SaladSkip", orderList);

                                break;
                            case "8":
                                Salad s8 = new Salad("Grilled Chicken Casar Salad", "Full", "Salad");
                                //Call the non Burger Function
                                saladAdd(s8);
                                orderList.add(s8);
                                displayOrder(orderList);
                                anotherSalad(s8);
                                lastSalad = "8";
                                list = afterDisplay("SaladSkip", orderList);

                                break;
                            case"9":
                                Salad s9 = new Salad("Caesar Side Salad", "Salad");
                                //Call the non Burger Function
                                saladAdd(s9);
                                orderList.add(s9);
                                displayOrder(orderList);
                                anotherSalad(s9);
                                lastSalad = "9";
                                list = afterDisplay("SaladSkip", orderList);

                                break;
                            case"10":
                                Salad s10 = new Salad("Garden Side Salad", "Salad");
                                //Call the non Burger Function
                                saladAdd(s10);
                                orderList.add(s10);
                                displayOrder(orderList);
                                anotherSalad(s10);
                                lastSalad = "10";
                                list = afterDisplay("SaladSkip", orderList);

                                break;
                            case"11":
                                list = "Choice";
                                if(orderContain(orderList)) {
                                    list = listChoiceOther();
                                }else{
                                    list = listChoice();
                                }
                                break;
                            default:
                                list = "Choice";
                                nl();
                                print("Error: That's not a accessible option");
                                if(orderContain(orderList)) {
                                    list = listChoiceOther();
                                }else{
                                    list = listChoice();
                                }
                                break;
                        }
                    }

                    //Side Order list
                    if (list.contentEquals("Side") || list.contentEquals("SideSkip")) {
                            //When the user picks one of them
                            String sideChoice;
                            if (!list.contentEquals("SideSkip")) {
                                sideChoice = scrChoiceStr.nextLine();
                            } else {
                                sideChoice = lastSide;
                            }

                            switch(sideChoice){
                                case "1":
                                    frySize();
                                    String fry = scrChoiceStr.nextLine();
                                    if(fry.contentEquals("1")){
                                        fry = "Small";
                                    }else if(fry.contentEquals("2")){
                                        fry = "Medium";
                                    }else{
                                        fry = "Large";
                                    }
                                    Side s1 = new Side("French Fries", fry, "Fries");
                                    orderList.add(s1);
                                    displayOrder(orderList);
                                    anotherSide(s1);
                                    lastSide = "1";
                                    list = afterDisplay("SideSkip", orderList);
                                    break;
                                case"2":
                                    Side s2 = new Side("Poutine", "Side");
                                    orderList.add(s2);
                                    displayOrder(orderList);
                                    anotherSide(s2);
                                    lastSide = "2";
                                    list = afterDisplay("SideSkip", orderList);
                                    break;
                                case"3":
                                    Side s3 = new Side("Bacon Poutine", "Side");
                                    orderList.add(s3);
                                    displayOrder(orderList);
                                    anotherSide(s3);
                                    lastSide = "3";
                                    list = afterDisplay("SideSkip", orderList);
                                    break;
                                case"4":
                                    Side s4 = new Side("Chilli Cheese Fries", "Side");
                                    orderList.add(s4);
                                    displayOrder(orderList);
                                    anotherSide(s4);
                                    lastSide = "4";
                                    list = afterDisplay("SideSkip", orderList);
                                    break;
                                case"5":
                                    Side s5 = new Side("Chilli Cheese Nachos", "Side");
                                    orderList.add(s5);
                                    displayOrder(orderList);
                                    anotherSide(s5);
                                    lastSide = "5";
                                    list = afterDisplay("SideSkip", orderList);
                                    break;
                                case"6":
                                    chilliSize();
                                    String chilli = scrChoiceStr.nextLine();
                                    if(chilli.contentEquals("1")){
                                        chilli = "Small";
                                    }else{
                                        chilli = "Large";
                                    }
                                    Side s6 = new Side("Chilli", chilli ,"Chilli");
                                    orderList.add(s6);
                                    displayOrder(orderList);
                                    anotherSide(s6);
                                    lastSide = "6";
                                    list = afterDisplay("SideSkip", orderList);
                                    break;
                                case"7":
                                    Side s7 = new Side("Garden Side Salad", "Side Salad");
                                    orderList.add(s7);
                                    displayOrder(orderList);
                                    anotherSide(s7);
                                    lastSide = "7";
                                    list = afterDisplay("SideSkip", orderList);
                                    break;
                                case"8":
                                    Side s8 = new Side("Caesar Side Salad", "Side Salad");
                                    orderList.add(s8);
                                    displayOrder(orderList);
                                    anotherSide(s8);
                                    lastSide = "8";
                                    list = afterDisplay("SideSkip", orderList);
                                    break;
                                case"9":
                                    Side s9 = new Side("Plain Potato", "Potato");
                                    orderList.add(s9);
                                    displayOrder(orderList);
                                    anotherSide(s9);
                                    lastSide = "9";
                                    list = afterDisplay("SideSkip", orderList);
                                    break;
                                case"10":
                                    Side s10 = new Side("Sour Cream & Chives Potato", "Potato");
                                    orderList.add(s10);
                                    displayOrder(orderList);
                                    anotherSide(s10);
                                    lastSide = "10";
                                    list = afterDisplay("SideSkip", orderList);
                                    break;
                                case"11":
                                    Side s11 = new Side("Bacon Cheese Potato", "Potato");
                                    orderList.add(s11);
                                    displayOrder(orderList);
                                    anotherSide(s11);
                                    lastSide = "11";
                                    list = afterDisplay("SideSkip", orderList);
                                    break;
                                case"12":
                                    Side s12 = new Side("Chilli Cheese Potato", "Potato");
                                    orderList.add(s12);
                                    displayOrder(orderList);
                                    anotherSide(s12);
                                    lastSide = "12";
                                    list = afterDisplay("SideSkip", orderList);
                                    break;
                                case"13":
                                    list = "Choice";
                                    if(orderContain(orderList)) {
                                        list = listChoiceOther();
                                    }else{
                                        list = listChoice();
                                    }
                                    break;
                                default:
                                    list = "Choice";
                                    nl();
                                    print("Error: That's not a accessible option");
                                    if(orderContain(orderList)) {
                                        list = listChoiceOther();
                                    }else{
                                        list = listChoice();
                                    }
                                    break;
                            }
                        }

                    //Drink Order list
                    if (list.contentEquals("Drink") || list.contentEquals("DrinkSkip")) {
                        //When the user picks one of them
                        String drinkChoice;
                        if (!list.contentEquals("DrinkSkip")) {
                            drinkChoice = scrChoiceStr.nextLine();
                        } else {
                            drinkChoice = lastDrink;
                        }
                        String drinkSize = "";
                        boolean ice;
                        switch(drinkChoice){
                            case"1":
                                drinkSize = drinkSizeChoice();
                                ice = wantIce();
                                Drink d1 = new Drink("Soda", drinkSize, ice, "Coca-cola", "Soda");
                                orderList.add(d1);
                                displayOrder(orderList);
                                anotherDrink(d1);
                                lastDrink = "1";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"2":
                                drinkSize = drinkSizeChoice();
                                ice = wantIce();
                                Drink d2 = new Drink("Soda", drinkSize, ice, "Coca-cola Zero", "Soda");
                                orderList.add(d2);
                                displayOrder(orderList);
                                anotherDrink(d2);
                                lastDrink = "2";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"3":
                                drinkSize = drinkSizeChoice();
                                ice = wantIce();
                                Drink d3 = new Drink("Soda", drinkSize, ice, "Diet Coke", "Soda");
                                orderList.add(d3);
                                displayOrder(orderList);
                                anotherDrink(d3);
                                lastDrink = "3";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"4":
                                drinkSize = drinkSizeChoice();
                                ice = wantIce();
                                Drink d4 = new Drink("Soda", drinkSize, ice, "Sprite", "Soda");
                                orderList.add(d4);
                                displayOrder(orderList);
                                anotherDrink(d4);
                                lastDrink = "4";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"5":
                                drinkSize = drinkSizeChoice();
                                ice = wantIce();
                                Drink d5 = new Drink("Soda", drinkSize, ice, "Barq's Root Beer", "Soda");
                                orderList.add(d5);
                                displayOrder(orderList);
                                anotherDrink(d5);
                                lastDrink = "5";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"6":
                                drinkSize = drinkSizeChoice();
                                ice = wantIce();
                                Drink d6 = new Drink("Soda", drinkSize, ice, "Fanata Orange", "Soda");
                                orderList.add(d6);
                                displayOrder(orderList);
                                anotherDrink(d6);
                                lastDrink = "6";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"7":
                                drinkSize = drinkSizeChoice();
                                ice = wantIce();
                                Drink d7 = new Drink("Soda", drinkSize, ice, "Nestea", "Soda");
                                orderList.add(d7);
                                displayOrder(orderList);
                                anotherDrink(d7);
                                lastDrink = "7";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"8":
                                drinkSize = drinkSizeChoice();
                                ice = wantIce();
                                Drink d8 = new Drink("Soda", drinkSize, ice, "Fruit Passion Fruitopia", "Soda");
                                orderList.add(d8);
                                displayOrder(orderList);
                                anotherDrink(d8);
                                lastDrink = "8";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"9":
                                Drink d9 = new Drink("Water", "Dasani", "Water");
                                orderList.add(d9);
                                displayOrder(orderList);
                                anotherDrink(d9);
                                lastDrink = "9";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"10":
                                Drink d10 = new Drink("Hot Drink", "Coffee", "Hot Drink");
                                orderList.add(d10);
                                displayOrder(orderList);
                                anotherDrink(d10);
                                lastDrink = "10";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"11":
                                Drink d11 = new Drink("Hot Drink", "Decaf Coffee", "Hot Drink");
                                orderList.add(d11);
                                displayOrder(orderList);
                                anotherDrink(d11);
                                lastDrink = "11";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"12":
                                Drink d12 = new Drink("Milk", "Milk", "Milk");
                                orderList.add(d12);
                                displayOrder(orderList);
                                anotherDrink(d12);
                                lastDrink = "12";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"13":
                                Drink d13 = new Drink("Milk", "Chocolate Milk", "Milk");
                                orderList.add(d13);
                                displayOrder(orderList);
                                anotherDrink(d13);
                                lastDrink = "13";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"14":
                                Drink d14 = new Drink("Packaged Drink", "Minute Maid Apple Juice", "Packaged Drink");
                                orderList.add(d14);
                                displayOrder(orderList);
                                anotherDrink(d14);
                                lastDrink = "14";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"15":
                                Drink d15 = new Drink("Packaged Drink", "Minute Maid Orange Juice", "Packaged Drink");
                                orderList.add(d15);
                                displayOrder(orderList);
                                anotherDrink(d15);
                                lastDrink = "15";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"16":
                                drinkSize = drinkSizeChoice();
                                ice = wantIce();
                                Drink d16 = new Drink("Lemonade", drinkSize, ice, "Lemonade", "Lemonade");
                                orderList.add(d16);
                                displayOrder(orderList);
                                anotherDrink(d16);
                                lastDrink = "16";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"17":
                                drinkSize = drinkSizeChoice();
                                ice = wantIce();
                                Drink d17 = new Drink("Lemonade", drinkSize, ice, "Strawberry Lemonade", "Lemonade");
                                orderList.add(d17);
                                displayOrder(orderList);
                                anotherDrink(d17);
                                lastDrink = "17";
                                list = afterDisplay("DrinkSkip", orderList);
                                break;
                            case"18":
                                list = "Choice";
                                if(orderContain(orderList)) {
                                    list = listChoiceOther();
                                }else{
                                    list = listChoice();
                                }
                                    break;
                            default:
                                list = "Choice";
                                nl();
                                print("Error: That's not a accessible option");
                                if(orderContain(orderList)) {
                                    list = listChoiceOther();
                                }else{
                                    list = listChoice();
                                }
                                break;

                        }
                    }
                }
            }
        }



    //Order Display Functions
    public static void displayOrder(ArrayList<Order> order){
        nl();
        printStar();
        nl();
        print("Order Currently: ");
        nl();
        for(int i=0; i < order.size(); i++){
            System.out.println(order.get(i));
            System.out.println("Price: " + "$" + order.get(i).getPrice());
            nl();
        }

    }
    public static String displayError(ArrayList orderList){
        nl();
        printStar();
        nl();
        print("Error: That's not a accessible option");
        if(orderContain(orderList)){
            return listChoiceOther();
        }else{
            return listChoice();
        }
    }

    public static double getOrderPrice(ArrayList<Order> order){
        double val = 0;
        for(int i=0; i < order.size(); i++){
            val += order.get(i).getPrice();

        }
        return val;
    }

    public static Burger addBurger(Burger b1){
        return b1;
    }


    public static boolean orderContain(ArrayList orderList){
        //Returns to the menu
        if(orderList.isEmpty()){
            displayList();
        }else{
            displayListOther();
            return true;
        }
        return false;
    }

    public static String listChoice(){
        Scanner scrChoice = new Scanner(System.in);
        int choice = scrChoice.nextInt();
        String list;
        //Choose from the current choices offered
        if (choice == 1) {
            //Display Hamburger list
            displayHamburger();
            list = "Hamburger";
        } else if (choice == 2) {
            //Display Chicken list
            displayChicken();
            list = "Chicken";
        } else if (choice == 3) {
            //Display Salad list
            displaySalad();
            list = "Salad";
        }else if (choice == 4) {
            //Display Side list
            displaySide();
            list = "Side";
        }else if (choice == 5) {
            //Display Drink list
            displaySideDrink();
            list = "Drink";
        } else {
            //Display an error if a nonvalid choice is picked
            print("Error: Invaild Choice");
            list = "Choice";
            nl();
            printStar();
            nl();
        }
        return list;
    }

    public static String listChoiceOther(){
        Scanner scrChoice = new Scanner(System.in);
        int choice = scrChoice.nextInt();
        String list;
        //Choose from the current choices offered
        if (choice == 1) {
            //Display Hamburger list
            displayHamburger();
            list = "Hamburger";
        } else if (choice == 2) {
            //Display Chicken list
            displayChicken();
            list = "Chicken";
        } else if (choice == 3) {
            //Display Salad list
            displaySalad();
            list = "Salad";
        }else if (choice == 4) {
            //Display Side list
            displaySide();
            list = "Side";
        }else if (choice == 5) {
            //Display Drinks list
            displaySideDrink();
            list = "Drink";
        }else if (choice == 6) {
            //Chaneg the order
            list = "Change Order";
        }else if (choice == 7) {
            list = "Remove from Order";
        }else if (choice == 8) {
            list = "Order End";
        }else {
            //Display an error if a nonvalid choice is picked
            print("Error: Invaild Choice");
            list = "Choice";
            nl();
            printStar();
            nl();
        }
        return list;
    }

    public static String afterDisplay(String currentList, ArrayList<Order> obj){
        Scanner scrChoiceStr = new Scanner(System.in);
        String nonComboChoice;
        nonComboChoice = scrChoiceStr.nextLine();
        /*if(currentList.contentEquals("ChickenSkip")){
            nonComboChoice = "1";
        }else if(currentList.contentEquals("HamburgerSkip")){
            nonComboChoice = "1";
        }else{
        */




        String list;
        if(nonComboChoice.contentEquals("1")){
            //Do something
            if(currentList.contentEquals("Chicken") || currentList.contentEquals("ChickenSkip")){
                list = "ChickenSkip";
            }else if(currentList.contentEquals("Hamburger") || currentList.contentEquals("HamburgerSkip")){
                list = "HamburgerSkip";
            }else if(currentList.contentEquals("Salad") || currentList.contentEquals("SaladSkip")){
                list = "SaladSkip";
                }else if(currentList.contentEquals("Side") || currentList.contentEquals("SideSkip")){
                list = "SideSkip";
            }else if(currentList.contentEquals("Drink") || currentList.contentEquals("DrinkSkip")){
                list = "DrinkSkip";
            }else{
                list = "";
            }

        }else if(nonComboChoice.contentEquals("2")){
            //Returns to the menu
            list = "Choice";
            if(orderContain(obj)) {
                list = listChoiceOther();
            }else{
                list = listChoice();
            }

        }else if(nonComboChoice.contentEquals("3")) {
            //Returns to the prev list
            if(currentList.contentEquals("Chicken")){
                list = "Chicken";
                displayChicken();
            }else if(currentList.contentEquals("Hamburger") || currentList.contentEquals("HamburgerSkip")){
                list = "Hamburger";
                displayHamburger();
            }else if(currentList.contentEquals("Salad") || currentList.contentEquals("SaladSkip")){
                list = "Salad";
                displaySalad();
            }else if(currentList.contentEquals("Side") || currentList.contentEquals("SideSkip")){
                list = "Side";
                displaySide();
            }else if(currentList.contentEquals("Drink") || currentList.contentEquals("DrinkSkip")){
                list = "Drink";
                displaySideDrink();
            }else{
                list = "";
            }

        }else if(nonComboChoice.contentEquals("4")){
            //End the Order
            list = "Order End";
        }else{
            //Throw an error and return to the main list
            print("Error: That's not a accessible option");
            if(orderContain(obj)) {
                list = listChoiceOther();
            }else{
                list = listChoice();
            }
        }
        return list;
    }



    public static boolean wantToComboBurger(Burger b1) {
        Scanner scrYN = new Scanner(System.in);
        boolean choice;
        if (b1.canCombo == true) {
            //See if the user wants to combo the burger
            wantToCombo();
            String userChoice = scrYN.nextLine();
            userChoice = userChoice.toLowerCase().trim();
            if(userChoice.contentEquals("y")){
                choice = true;
            }else{
                choice = false;
            }
        }else{
            choice = false;
        }
        //scrYN.close();
        return choice;
    }

    public static boolean wantToComboChicken(Chicken c1) {
        Scanner scrYN = new Scanner(System.in);
        boolean choice;
        if (c1.canCombo == true) {
            //See if the user wants to combo the burger
            wantToCombo();
            String userChoice = scrYN.nextLine();
            userChoice = userChoice.toLowerCase().trim();
            if(userChoice.contentEquals("y")){
                choice = true;
            }else{
                choice = false;
            }
        }else{
            choice = false;
        }
        //scrYN.close();
        return choice;
    }

    public static boolean wantIce(){
        //Ask the user if they want ice within a drink
        Scanner wantIce = new Scanner(System.in);
        nl();
        printStar();
        nl();
        print("Would you like ice in your drink? (y/n)");
        String ice = wantIce.nextLine();

        if(ice.contentEquals("y")){
            return true;
        }else{
            return false;
        }
    }

    public static Burger dontWantToComboBurger(Burger b1){
        print("You added a " + b1.nameType + " to your order");
        return b1;
    }

    public static Chicken dontWantToComboChicken(Chicken c1){
        print("You added a " + c1.nameType + " to your order");
        return c1;
    }

    public static Salad saladAdd(Salad s1){
        print("You added a " + s1.nameType + " " + s1.size + " to your order");
        return s1;
    }

    public static burgerCombo currentBurgerCombo(Burger b1) {
        //Ask what size the combo will be
        String currentComboSize = orderSize();
        Drink d1 = null;
        Side s1 = null;
        burgerCombo burgerCombo1;
        boolean bool = canSenior();

        if(bool){
            d1 = currentDrink("Value");
        }else{
            d1 = currentDrink(currentComboSize); //currentComboSize
        }

        s1 = currentSide(currentComboSize);

        if(s1 == null || d1 == null){
            burgerCombo1 = null;
        }else{
            burgerCombo1 = new burgerCombo(b1, s1, d1, currentComboSize, "Burger Combo");
            print("You added a " + b1.nameType +  " Combo to your order");
        }

        return burgerCombo1;
    }

    public static void whatSauce(Chicken c1){
        Scanner scrChoiceStr = new Scanner(System.in);
        nl();
        printStar();
        nl();
        print("What sauce would yoou like?");
        print("(1) None");
        print("(2) Sweet and Sour");
        print("(3) BBQ");
        print("(4) Buttermilk Ranch");
        print("(5) Honey Mustard");
        print("(6) Creamy sriracha");
        print("(7) Ketchup");
        String sauceChoice = scrChoiceStr.nextLine();
        sauceChoice = sauceChoice.trim();
        if(sauceChoice.contentEquals("1")){
            c1.item.add("None");
        }else if(sauceChoice.contentEquals("2")) {
            c1.item.add("Sweet and Sour");
        }else if(sauceChoice.contentEquals("3")) {
            c1.item.add("BBQ");
        }else if(sauceChoice.contentEquals("4")) {
            c1.item.add("Buttermilk Ranch");
        }else if(sauceChoice.contentEquals("5")) {
            c1.item.add("Honey Mustard");
        }else if(sauceChoice.contentEquals("6")) {
            c1.item.add("Creamy sriracha");
        }else if(sauceChoice.contentEquals("7")) {
            c1.item.add("Ketchup");
        }else if(sauceChoice.contentEquals("8")) {
            print("Error: That's not a accessible option");

        }
    }

    public static chickenCombo currentChickenCombo(Chicken c1) {
        //Ask what size the combo will be
        String currentComboSize = orderSize();
        Drink d1 = null;
        Side s1 = null;
        chickenCombo chickenCombo1 = null;
        boolean bool = canSenior();

        if(bool){
            d1 = currentDrink("Value");
        }else{
            d1 = currentDrink(currentComboSize);
        }

        s1 = currentSide(currentComboSize);

        if(s1 == null || d1 == null){
            chickenCombo1 = null;
        }else{
            chickenCombo1 = new chickenCombo(c1, s1, d1, currentComboSize, "Chicken Combo");
            print("You added a " + c1.nameType +  " Combo to your order");
        }
        return chickenCombo1;
    }

    public static boolean canSenior(){
        Scanner scrChoiceStr = new Scanner(System.in);
        nl();
        printStar();
        nl();
        print("If your 65 or older, you can get a free senior drink instead. Are you 65 or over? (y/n)");
        print("This will be confirmed when you get your order.");
        String boolSenior = scrChoiceStr.nextLine();
        boolSenior = boolSenior.toLowerCase().trim();
        if(boolSenior.contentEquals("y")){
            return true;
        }else if(boolSenior.contentEquals("n")){
            return false;
        }else{
            return false;
        }
        //scrChoiceStr.close();
    }

    //Drink Size
    public static String drinkSizeChoice() {
        Scanner scrComboChoice = new Scanner(System.in);
        drinkSize();
        String currentDrinkSize = scrComboChoice.nextLine();
        currentDrinkSize = currentDrinkSize.toLowerCase().trim();

        if (currentDrinkSize.equals("1")) {
            currentDrinkSize = "Small";
        } else if (currentDrinkSize.equals("2")) {
            currentDrinkSize = "Medium";
        } else if (currentDrinkSize.equals("3")) {
            currentDrinkSize = "Large";
        } else if (currentDrinkSize.equals("4")) {
            currentDrinkSize = "";
            //Return
        } else {
            currentDrinkSize = "";
            //Throw an error then return
        }
        //scrComboChoice.close();
        return currentDrinkSize;
    }

    public static String drinkSizeChoiceChange() {
        Scanner scrComboChoice = new Scanner(System.in);
        drinkSizeChange();
        String currentDrinkSize = scrComboChoice.nextLine();
        currentDrinkSize = currentDrinkSize.toLowerCase().trim();

        if (currentDrinkSize.equals("1")) {
            currentDrinkSize = "Small";
        } else if (currentDrinkSize.equals("2")) {
            currentDrinkSize = "Medium";
        } else if (currentDrinkSize.equals("3")) {
            currentDrinkSize = "Large";
        }else {
            currentDrinkSize = "";
            //Throw an error then return
        }
        //scrComboChoice.close();
        return currentDrinkSize;
    }

    public static String frySizeChoiceChange() {
        Scanner scrComboChoice = new Scanner(System.in);
        frySize();
        String currentDrinkSize = scrComboChoice.nextLine();
        currentDrinkSize = currentDrinkSize.toLowerCase().trim();

        if (currentDrinkSize.equals("1")) {
            currentDrinkSize = "Small";
        } else if (currentDrinkSize.equals("2")) {
            currentDrinkSize = "Medium";
        } else if (currentDrinkSize.equals("3")) {
            currentDrinkSize = "Large";
        }else {
            currentDrinkSize = "";
            //Throw an error then return
        }
        //scrComboChoice.close();
        return currentDrinkSize;
    }

    public static String orderSize(){
        Scanner scrComboChoice = new Scanner(System.in);
        comboSize();
        String comboSizeOrder;
        String sizeChoice = scrComboChoice.nextLine();
        //sizeChoice = sizeChoice.toLowerCase().trim();

        if(sizeChoice.equals("1")){
            comboSizeOrder = "Small";
        }else if(sizeChoice.equals("2")) {
            comboSizeOrder = "Medium";
        }else if(sizeChoice.equals("3")) {
            comboSizeOrder = "Large";
        }else if(sizeChoice.equals("4")) {
            comboSizeOrder = "";
            //Return
        }else{
            comboSizeOrder = "";
            //Throw an error then return
        }
        //scrComboChoice.close();
        return comboSizeOrder;
    }

    public static Side currentSide(String comboSize){
        //Side
        Scanner scrChoiceStr = new Scanner(System.in);
        nl();
        printStar();
        nl();
        print("What Side would you like?");
        displaySide();
        String sideChoice = scrChoiceStr.nextLine();
        sideChoice = sideChoice.trim();
        Side comboSide = null;
        if(sideChoice.contentEquals("1")){
            comboSide = new Side("French Fries", comboSize, "Fries");
        }else if(sideChoice.contentEquals("2")) {
            comboSide = new Side("Poutine", "Side");
        }else if(sideChoice.contentEquals("3")) {
            comboSide = new Side("Bacon Poutine", "Side");
        }else if(sideChoice.contentEquals("4")) {
            comboSide = new Side("Chili Cheese Fries", "Side");
        }else if(sideChoice.contentEquals("5")) {
            comboSide = new Side("Chili Cheese Nachos", "Side");
        }else if(sideChoice.contentEquals("6")) {
            //Check what size of chilli they want
            nl();
            printStar();
            nl();
            print("What size do you want your chilli?");
            print("(1) Small");
            print("(2) Large");
            String chilliChoice = scrChoiceStr.nextLine();
            chilliChoice = chilliChoice.trim();
            String chilliSize;
            if(chilliChoice.contentEquals("1")){
                chilliSize = "Small";
            }else if(chilliChoice.contentEquals("2")){
                chilliSize = "Large";
            }else{
                chilliSize = "";
                //Print out an error and reset to the menu
            }
            comboSide = new Side("Chilli", chilliSize,"Chilli");

        }else if(sideChoice.contentEquals("7")) {
            comboSide = new Side("Garden Side Salad", "Side Salad");
        }else if(sideChoice.contentEquals("8")) {
            comboSide = new Side("Caesar Side Salad", "Side Salad");
        }else if(sideChoice.contentEquals("9")) {
            comboSide = new Side("Plain Baked Potato", "Potato");
        }else if(sideChoice.contentEquals("10")) {
            comboSide = new Side("Sour Cream & Chive Potato", "Potato");
        }else if(sideChoice.contentEquals("11")) {
            comboSide = new Side("Bacon Cheese Potato", "Potato");
        }else if(sideChoice.contentEquals("12")) {
            comboSide = new Side("Chilli Cheese Potato", "Potato");
        }else{
            nl();
            print("Error: That's not a accessible option");
        }
        //scrChoiceStr.close();
        return comboSide;
    }

    public static Drink currentDrink(String comboSize){
        //Drink
        Scanner scrChoiceStr = new Scanner(System.in);
        nl();
        printStar();
        nl();
        print("What Drink would you like?");
        displayDrink();
        String drinkChoice = scrChoiceStr.nextLine();
        drinkChoice = drinkChoice.trim();
        Drink comboDrink = null;
        if(drinkChoice.contentEquals("1")){
            comboDrink = new Drink("Soda", comboSize, true, "Coca-Cola", "Soda");
        }else if(drinkChoice.contentEquals("2")) {
            comboDrink = new Drink("Soda", comboSize, true, "Coca-Cola Diet", "Soda");
        }else if(drinkChoice.contentEquals("3")) {
            comboDrink = new Drink("Soda", comboSize, true, "Diet Coke", "Soda");
        }else if(drinkChoice.contentEquals("4")) {
            comboDrink = new Drink("Soda", comboSize, true, "Sprite", "Soda");
        }else if(drinkChoice.contentEquals("5")) {
            comboDrink = new Drink("Soda", comboSize, true, "Barq's Root Beer", "Soda");
        }else if(drinkChoice.contentEquals("6")) {
            comboDrink = new Drink("Soda", comboSize, true, "Fanta Orange", "Soda");
        }else if(drinkChoice.contentEquals("7")) {
            comboDrink = new Drink("Soda", comboSize, true, "Nestea", "Soda");
        }else if(drinkChoice.contentEquals("8")) {
            comboDrink = new Drink("Soda", comboSize, true, "Fruit Passion Fruitopia", "Soda");
        }else if(drinkChoice.contentEquals("9")) {
            comboDrink = new Drink("Water", "Dasani", "Water");
        }else if(drinkChoice.contentEquals("10")) {
            comboDrink = new Drink("Hot Drink", "Coffee", "Hot Coffee");
        }else if(drinkChoice.contentEquals("11")) {
            comboDrink = new Drink("Hot Drink", "Decaf Coffee", "Hot Coffee");
        }else if(drinkChoice.contentEquals("12")) {
            comboDrink = new Drink("Milk", "Milk", "Milk");
        }else if(drinkChoice.contentEquals("13")) {
            comboDrink = new Drink("Milk", "Chocolate Milk", "Milk");
        }else if(drinkChoice.contentEquals("14")) {
            comboDrink = new Drink("Package Drink", "Minute Maid Apple Juice", "Package Drink");
        }else if(drinkChoice.contentEquals("15")) {
            comboDrink = new Drink("Package Drink", "Minute Maid Orange Juice", "Package Drink");
        }else if(drinkChoice.contentEquals("16")) {
            comboDrink = new Drink("Lemonade", comboSize, true, "Lemonade", "Lemonade");
        }else if(drinkChoice.contentEquals("17")) {
            comboDrink = new Drink("Lemonade", comboSize, true, "Strawberry Lemonade", "Lemonade");
        }else{
            nl();
            print("Error: That's not a accessible option");
        }
        //scrChoiceStr.close();
        return comboDrink;

    }

    public static void comboSize(){
        nl();
        printStar();
        nl();
        print("What size will be your Combo");
        print("(1) Small");
        print("(2) Medium");
        print("(3) Large");
        print("(4) **Return**");
    }

    public static void drinkSize(){
        nl();
        printStar();
        nl();
        print("What size will be your Drink be?");
        print("(1) Small");
        print("(2) Medium");
        print("(3) Large");
        print("(4) **Return**");
    }

    public static void drinkSizeChange(){
        nl();
        printStar();
        nl();
        print("What size will be your Drink be?");
        print("(1) Small");
        print("(2) Medium");
        print("(3) Large");
    }

    public static void frySize(){
        nl();
        printStar();
        nl();
        print("What size will be your Fries be?");
        print("(1) Small");
        print("(2) Medium");
        print("(3) Large");
    }

    public static void chilliSize(){
        print("What size do you want your chilli?");
        print("(1) Small");
        print("(2) Large");
    }

    public static void wantToCombo() {
        nl();
        printStar();
        nl();
        print("Would you like to combo that? (y/n)");
    }

    public static void anotherBurger(Burger b1){
        nl();
        printStar();
        nl();
        print("(1) Would you like to order another " + b1.nameType + " burger?");
        print("(2) Return to the Main List?");
        print("(3) Return back to Hamburger List?");
        print("(4) End your order?");
    }

    public static void anotherChicken(Chicken c1){
        nl();
        printStar();
        nl();
        print("(1) Would you like to order another " + c1.nameType + " burger?");
        print("(2) Return to the Main List?");
        print("(3) Return back to Chicken List?");
        print("(4) End your order?");
    }

    public static void anotherSalad(Salad s1){
        nl();
        printStar();
        nl();
        print("(1) Would you like to order another " + s1.nameType + " " + s1.size);
        print("(2) Return to the Main List?");
        print("(3) Return back to Salad List?");
        print("(4) End your order?");
    }

    public static void anotherSide(Side s1){
        nl();
        printStar();
        nl();
        print("(1) Would you like to order another " + s1.nameType);
        print("(2) Return to the Main List?");
        print("(3) Return back to Side List?");
        print("(4) End your order?");
    }

    public static void anotherDrink(Drink s1){
        nl();
        printStar();
        nl();
        print("(1) Would you like to order another " + s1.nameType);
        print("(2) Return to the Main List?");
        print("(3) Return back to Drink List?");
        print("(4) End your order?");
    }

    public static void printStar(){
        print("******************");
    }

    public static void nl(){
        System.out.println();
    }

    public static void displayList(){
        nl();
        printStar();
        nl();
        print("Choose from this list: ");
        nl();
        System.out.println("(1) Hamburgers");
        System.out.println("(2) Chicken");
        System.out.println("(3) Salads");
        System.out.println("(4) Side");
        System.out.println("(5) Drinks");
    }
    public static void displayListOther(){
        nl();
        printStar();
        nl();
        print("Choose from this list: ");
        nl();
        System.out.println("(1) Hamburgers");
        System.out.println("(2) Chicken");
        System.out.println("(3) Salads");
        System.out.println("(4) Side");
        System.out.println("(5) Drinks");
        System.out.println("(6) Change Order");
        System.out.println("(7) Remove from Order");
        System.out.println("(8) End Order");
    }

    public static void displayChoiceRemove(){
        nl();
        printStar();
        nl();
        System.out.println("(1) Would you like to Remove again?");
        System.out.println("(2) Would you like to Add?");
        System.out.println("(3) Would you like to return to the main menu?");
    }

    public static void displayRemoveOrder(){
        nl();
        printStar();
        nl();
        System.out.println("(1) Would you like to Remove again?");
        System.out.println("(2) Would you like to return to the main menu?");
    }

    public static void displayChoiceAdd(){
        nl();
        printStar();
        nl();
        System.out.println("(1) Would you like to Add again?");
        System.out.println("(2) Would you like to Remove?");
        System.out.println("(3) Would you like to return to the main menu?");
    }

    public static void displayDrinkAdd(){
        nl();
        printStar();
        nl();
        System.out.println("(1) Would you like change the size again?");
        System.out.println("(2) Would you like change the flavor again?");
        System.out.println("(3) Would you like to return to the main menu?");
    }

    public static void displayHotDrinkAdd(){
        nl();
        printStar();
        nl();
        System.out.println("(1) Would you like to add again?");
        System.out.println("(2) Would you like to return to the main menu?");
    }

    public static void displayHamburger(){
        nl();
        printStar();
        nl();
        print("Here are the list of Hamburgers to order from.");
        nl();
        print("(1) Dave's Single");
        print("(2) Dave's Double");
        print("(3) Dave's Triple");
        print("(4) Bacon Deluxe Single");
        print("(5) Bacon Deluxe Double");
        print("(6) Bacon Deluxe Triple");
        print("(7) Single Baconator");
        print("(8) Baconator");
        print("(9) Triple Baconator");
        print("(10) Son of Baconator");
        print("(11) Jr. Hamburger Deluxe");
        print("(12) Jr. Cheeseburger Deluxe");
        print("(13) Jr. Bacon Cheeseburger");
        print("(14) Cheesy CheddarBurger");
        print("(15) **Return**");
    }

    public static void displayDrink(){
        nl();
        printStar();
        nl();
        print("Here are the list of Drinks to order from.");
        nl();
        print("(1) Coca-Cola");
        print("(2) Coca-Cola Zero");
        print("(3) Diet Coke");
        print("(4) Sprite");
        print("(5) Barq's Root Beer");
        print("(6) Fanta Orange");
        print("(7) Nestea");
        print("(8) Fruit Passion Fruitopia");
        print("(9) Dasani");
        print("(10) Coffee");
        print("(11) Decaf Coffee");
        print("(12) Milk");
        print("(13) Chocolate Milk");
        print("(14) Minute Maid Apple Juice");
        print("(15) Minute Maid Orange Juice");
        print("(16) Lemonade");
        print("(17) Strawberry Lemonade");
    }

    public static void displaySideDrink(){
        nl();
        printStar();
        nl();
        print("Here are the list of Drinks to order from.");
        nl();
        print("(1) Coca-Cola");
        print("(2) Coca-Cola Zero");
        print("(3) Diet Coke");
        print("(4) Sprite");
        print("(5) Barq's Root Beer");
        print("(6) Fanta Orange");
        print("(7) Nestea");
        print("(8) Fruit Passion Fruitopia");
        print("(9) Dasani");
        print("(10) Coffee");
        print("(11) Decaf Coffee");
        print("(12) Milk");
        print("(13) Chocolate Milk");
        print("(14) Minute Maid Apple Juice");
        print("(15) Minute Maid Orange Juice");
        print("(16) Lemonade");
        print("(17) Strawberry Lemonade");
        print("(18) **Return**");
    }

    public static void displaySide(){
        nl();
        printStar();
        nl();
        print("Here are the list of Sides to order from.");
        nl();
        print("(1) French Fries");
        print("(2) Poutine");
        print("(3) Bacon Poutine");
        print("(4) Chilli Cheese Fries");
        print("(5) Chilli Cheese Nachos");
        print("(6) Chilli");
        print("(7) Garden Side Salad");
        print("(8) Caesar Side Salad");
        print("(9) Plain Baked Potato");
        print("(10) Sour Cream & Chive Potato");
        print("(11) Bacon Cheese Potato");
        print("(12) Chilli Cheese Potato");
        print("(13) **Return**");
    }

    public static void displayChicken(){
        nl();
        printStar();
        nl();
        print("Here are the list of Chicken to order from.");
        nl();
        print("(1) Spicy Chicken");
        print("(2) Homestyle Chicken");
        print("(3) Grilled Chicken");
        print("(4) Spicy Asiago Ranch Club");
        print("(5) Homestyle Asiago Ranch Club");
        print("(6) Grilled Asiago Ranch Club");
        print("(7) 5 Piece Chicken Nugget");
        print("(8) 10 Piece Chicken Nugget");
        print("(9) Homestyle Chicken Strips");
        print("(10) Spicy Chicken Strips");
        print("(11) Spicy Chicken Wrap");
        print("(12) Homestyle Chicken Wrap");
        print("(13) Grilled Chicken Wrap");
        print("(14) **Return**");
    }

    public static void displaySalad(){
        nl();
        printStar();
        nl();
        print("Here are the list of Salad to order from.");
        nl();
        print("(1) Taco Salad Full");
        print("(2) Taco Salad Half");
        print("(3) Apple Pecan Chicken Salad Full");
        print("(4) Apple Pecan Chicken Salad Half");
        print("(5) Southwest Avocado Chicken Salad Full");
        print("(6) Southwest Avocado Chicken Salad Half");
        print("(7) Grilled Chicken Casar Full");
        print("(8) Grilled Chicken Casar half");
        print("(9) Caesar Side Salad");
        print("(10) Garden Side Salad");
        print("(11) **Return**");
    }

    public static void displaySaladOptions(){
        nl();
        printStar();
        nl();
        print("(1) Shredded Cheese");
        print("(2) Dice tomatos");
        print("(3) Asiago Cheese");
        print("(4) 3 Pieces of Bacon");
        print("(5) Bacon bits");

    }

    public static void displaySideOptions(){
        nl();
        printStar();
        nl();
        print("(1) Chilli");
        print("(2) Shredded Cheese");
        print("(3) Cheese Sauce");
        print("(4) Bacon bits");
        print("(5) 3 Pieces of Bacon");

    }

    public static void displayCrownOptions(){
        nl();
        printStar();
        nl();
        print("(1) Ranch");
        print("(2) Honey Mustard");
        print("(3) Mayo");
        print("(4) Mustard");
        print("(5) Ketchup");

    }

    public static void displayBaseOptions(){
        nl();
        printStar();
        nl();
        print("(1) 2oz Patty");
        print("(2) 4oz Patty");
        print("(3) Grilled Chicken");
        print("(4) Homestyle Chicken");
        print("(5) Spicy Chicken");

    }

    public static void displaySauceOptions(){
        nl();
        printStar();
        nl();
        print("(1) Sweet and Sour");
        print("(2) BBQ");
        print("(3) Buttermilk Ranch");
        print("(4) Honey Mustard");
        print("(5) Creamy sriracha");
        print("(6) Ketchup");
    }

    public static void displayWrapOptions(){
        nl();
        printStar();
        nl();
        print("(1) Ranch");
        print("(2) Honey Mustard");
        print("(3) Shredded Cheese");
        print("(4) Half a Grilled Chicken");
        print("(5) Half a Spicy Chicken");
        print("(6) Half a Homestyle Chicken");
    }

    public static void displayDrinkOptions(){
        nl();
        printStar();
        nl();
        print("(1) Coca-Cola");
        print("(2) Coca-Cola Zero");
        print("(3) Diet Coke");
        print("(4) Sprite");
        print("(5) Barq's Root Beer");
        print("(6) Fanta Orange");
        print("(7) Nestea");
        print("(8) Fruit Passion Fruitopia");
    }

    public static void print(String a){
        System.out.println(a);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

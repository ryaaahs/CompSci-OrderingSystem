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
        int total = 0;
        ArrayList<Order> orderList = new ArrayList<>();
        String lastBurger = "0";
        String lastChicken = "0";
        String lastSalad = "0";


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
                    displaySalad();
                    list = "Side";
                    startingOrder = false;
                }else if(choice == 5){
                    //Display the Drink list
                    //displaySalad();
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
                //When we are on the Hamburger list
                if(list.contentEquals("Choice")) {

                    int choice = scrChoice.nextInt();
                    if (choice == 1) {
                        //Display Hamburger list
                        displayHamburger();
                        list = "Hamburger";
                    } else if (choice == 2){
                        //Display Chicken list
                        displayChicken();
                        list = "Chicken";
                    }else if(choice == 3){
                        //Display Salad list
                        //displaySalad();
                        list = "Salad";
                    }else if(choice == 4){
                        //Display Side list
                        displaySalad();
                        list = "Side";
                    }else if(choice == 5){
                        //Display the Drink list
                        //displaySalad();
                        list = "Drink";
                    }else if(choice == 6){
                        //Display ETC list
                        //displaySalad();
                        list = "ETC";
                    }else if(choice == 7){
                        //Display Salad list
                        //displaySalad();
                        if(!orderList.isEmpty()){
                            list = "Change Order";
                        }else{
                            print("Error: Order is Empty");
                            nl();
                            printStar();
                            nl();
                        }
                    }else{
                        //Display an error if a nonvalid choice is picked
                        print("Error: Invaild Choice");
                        nl();
                        printStar();
                        nl();
                    }
                }

                    if (list.contentEquals("Chicken") || list.contentEquals("ChickenSkip")) {
                        //When the user picks one of them
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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
                                        }
                                        break;
                                    }
                                    orderList.add(CC1);
                                    displayOrder(orderList);
                                    anotherChicken(c1);
                                    lastChicken = "1";
                                    list = afterDisplay("Chicken", orderList);


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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
                                        }
                                        break;
                                    }
                                    orderList.add(CC2);
                                    displayOrder(orderList);
                                    anotherChicken(c2);
                                    lastChicken = "2";
                                    list = afterDisplay("Chicken", orderList);

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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
                                        }
                                        break;
                                    }
                                    orderList.add(CC3);
                                    displayOrder(orderList);
                                    anotherChicken(c3);
                                    lastChicken = "3";
                                    list = afterDisplay("Chicken", orderList);

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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
                                        }
                                        break;
                                    }
                                    orderList.add(CC4);
                                    displayOrder(orderList);
                                    anotherChicken(c4);
                                    lastChicken = "4";
                                    list = afterDisplay("Chicken", orderList);

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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
                                        }
                                        break;
                                    }
                                    orderList.add(CC5);
                                    displayOrder(orderList);
                                    anotherChicken(c5);
                                    lastChicken = "5";
                                    list = afterDisplay("Chicken", orderList);

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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
                                        }
                                        break;
                                    }
                                    orderList.add(CC6);
                                    displayOrder(orderList);
                                    anotherChicken(c6);
                                    lastChicken = "6";
                                    list = afterDisplay("Chicken", orderList);

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
                                Chicken c7 = new Chicken("5 Piece Chicken Nugget", "Chicken");
                                if (wantToComboChicken(c7)) {
                                    //Call the Burger Combo Function
                                    whatSauce(c7);
                                    whatSauce(c7);
                                    chickenCombo CC7 = currentChickenCombo(c7);
                                    if(CC7 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
                                        }
                                        break;
                                    }
                                    orderList.add(CC7);
                                    displayOrder(orderList);
                                    anotherChicken(c7);
                                    lastChicken = "7";
                                    list = afterDisplay("Chicken", orderList);

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
                                Chicken c8 = new Chicken("10 Piece Chicken Nugget", "Chicken");
                                if (wantToComboChicken(c8)) {
                                    //Call the Burger Combo Function
                                    whatSauce(c8);
                                    whatSauce(c8);
                                    chickenCombo CC8 = currentChickenCombo(c8);
                                    if(CC8 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
                                        }
                                        break;
                                    }
                                    orderList.add(CC8);
                                    displayOrder(orderList);
                                    anotherChicken(c8);
                                    lastChicken = "8";
                                    list = afterDisplay("Chicken", orderList);

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
                                Chicken c9 = new Chicken("Homestyle Chicken Strips", "Chicken");
                                if (wantToComboChicken(c9)) {
                                    //Call the Burger Combo Function
                                    chickenCombo CC9 = currentChickenCombo(c9);
                                    if(CC9 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
                                        };
                                        break;
                                    }
                                    orderList.add(CC9);
                                    displayOrder(orderList);
                                    anotherChicken(c9);
                                    lastChicken = "9";
                                    list = afterDisplay("Chicken", orderList);

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
                                Chicken c10 = new Chicken("Spicy Chicken Strips", "Chicken");
                                if (wantToComboChicken(c10)) {
                                    //Call the Burger Combo Function
                                    whatSauce(c10);
                                    whatSauce(c10);
                                    chickenCombo CC10 = currentChickenCombo(c10);
                                    if(CC10 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
                                        }
                                        break;
                                    }
                                    orderList.add(CC10);
                                    displayOrder(orderList);
                                    anotherChicken(c10);
                                    lastChicken = "10";
                                    list = afterDisplay("Chicken", orderList);

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
                                Chicken c11 = new Chicken("Spicy Chicken Wrap", "Chicken");
                                if (wantToComboChicken(c11)){
                                    //Call the Burger Combo Function
                                    whatSauce(c11);
                                    whatSauce(c11);
                                    chickenCombo CC11 = currentChickenCombo(c11);
                                    if(CC11 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
                                        }
                                        break;
                                    }
                                    orderList.add(CC11);
                                    displayOrder(orderList);
                                    anotherChicken(c11);
                                    lastChicken = "11";
                                    list = afterDisplay("Chicken", orderList);

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
                                Chicken c12 = new Chicken("Homestyle Chicken Wrap", "Chicken");
                                if (wantToComboChicken(c12)) {
                                    //Call the Burger Combo Function
                                    chickenCombo CC12 = currentChickenCombo(c12);
                                    if(CC12 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
                                        }
                                        break;
                                    }
                                    orderList.add(CC12);
                                    displayOrder(orderList);
                                    anotherChicken(c12);
                                    lastChicken = "12";
                                    list = afterDisplay("Chicken", orderList);

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
                                Chicken c13 = new Chicken("Grilled Chicken Wrap", "Chicken");
                                if (wantToComboChicken(c13)) {
                                    //Call the Burger Combo Function
                                    chickenCombo CC13 = currentChickenCombo(c13);
                                    if(CC13 == null){
                                        list = "Choice";
                                        nl();
                                        print("Error: Combo contains null elements");
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
                                        }
                                        break;
                                    }
                                    orderList.add(CC13);
                                    displayOrder(orderList);
                                    anotherChicken(c13);
                                    lastChicken = "13";
                                    list = afterDisplay("Chicken", orderList);

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
                                displayList();
                                break;
                            default:
                                list = "Choice";
                                nl();
                                print("Error: That's not a accessible option");
                                displayList();
                                break;
                        }
                    }

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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
                                        }
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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
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
                                        if(orderList.isEmpty()){
                                            displayList();
                                        }else{
                                            displayListOther();
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
                                list = "Choice";
                                if(orderList.isEmpty()){
                                    displayList();
                                }else{
                                    displayListOther();
                                }
                                break;
                            default:
                                list = "Choice";
                                nl();
                                print("Error: That's not a accessible option");
                                if(orderList.isEmpty()){
                                    displayList();
                                }else{
                                    displayListOther();
                                }
                                break;
                        }
                    }

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
                                if(orderList.isEmpty()){
                                    displayList();
                                }else{
                                    displayListOther();
                                }
                                break;
                            default:
                                list = "Choice";
                                nl();
                                print("Error: That's not a accessible option");
                                if(orderList.isEmpty()){
                                    displayList();
                                }else{
                                    displayListOther();
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

    public static Burger addBurger(Burger b1){
        return b1;
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
            //Display Chicken list
            displaySalad();
            list = "Salad";
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
            list = "Salad";
        }else if (choice == 5) {
            //Display Drinks list
            displayDrink();
            list = "Salad";
        }else if (choice == 6) {
            //Display ETC list
            //displayETC();
            list = "Salad";
        }else if (choice == 7) {
            //Change Order
            //ChangeOrder();
            list = "Salad";
        }else if (choice == 8) {
            //End order
            list = "";
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

    public static String afterDisplay(String currentList, ArrayList<Order> obj){
        Scanner scrChoiceStr = new Scanner(System.in);
        String nonComboChoice;
        /*if(currentList.contentEquals("ChickenSkip")){
            nonComboChoice = "1";
        }else if(currentList.contentEquals("HamburgerSkip")){
            nonComboChoice = "1";
        }else{*/
            nonComboChoice = scrChoiceStr.nextLine();
        //}

        String list;
        if(nonComboChoice.contentEquals("1")){
            //Do something
            if(currentList.contentEquals("Chicken") || currentList.contentEquals("ChickenSkip")){
                list = currentList;
            }else if(currentList.contentEquals("Hamburger") || currentList.contentEquals("HamburgerSkip")){
                list = currentList;
            }else if(currentList.contentEquals("Salad") || currentList.contentEquals("SaladSkip")){
                list = currentList;
            }else{
                list = "";
            }
        }else if(nonComboChoice.contentEquals("2")){
            if(obj.isEmpty()){
                displayList();
                list = listChoice();
                print("ahahha");
            }else{
                displayListOther();
                list = listChoiceOther();
            }

        }else if(nonComboChoice.contentEquals("3")) {

            if(currentList.contentEquals("Chicken")){
                list = "Chicken";
                displayChicken();
            }else if(currentList.contentEquals("Hamburger")){
                list = "Hamburger";
                displayHamburger();
            }else if(currentList.contentEquals("Salad") || currentList.contentEquals("SaladSkip")){
                list = "Salad";
                displaySalad();
            }else{
                list = "";
            }
        }else if(nonComboChoice.contentEquals("4")){
            //End the Order
            list = "";
        }else{
            //Throw an error and return to the main list
            list = "Choice";
            displayList();
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
            burgerCombo1 = new burgerCombo(b1, s1, d1, currentComboSize);
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
            chickenCombo1 = new chickenCombo(c1, s1, d1, currentComboSize);
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
            comboSide = new Side("French Fries", comboSize, "Side");
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
            comboSide = new Side("Chilli", chilliSize,"Side");

        }else if(sideChoice.contentEquals("7")) {
            comboSide = new Side("Garden Side Salad", "Side");
        }else if(sideChoice.contentEquals("8")) {
            comboSide = new Side("Caesar Side Salad", "Side");
        }else if(sideChoice.contentEquals("9")) {
            comboSide = new Side("Plain Baked Potato", "Side");
        }else if(sideChoice.contentEquals("10")) {
            comboSide = new Side("Sour Cream & Chive Potato", "Side");
        }else if(sideChoice.contentEquals("11")) {
            comboSide = new Side("Bacon Cheese Potato", "Side");
        }else if(sideChoice.contentEquals("12")) {
            comboSide = new Side("Chilli Cheese Potato", "Side");
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
            comboDrink = new Drink("Soda", comboSize, true, "Coca-Cola", "Drink");
        }else if(drinkChoice.contentEquals("2")) {
            comboDrink = new Drink("Soda", comboSize, true, "Coca-Cola Diet", "Drink");
        }else if(drinkChoice.contentEquals("3")) {
            comboDrink = new Drink("Soda", comboSize, true, "Diet Coke", "Drink");
        }else if(drinkChoice.contentEquals("4")) {
            comboDrink = new Drink("Soda", comboSize, true, "Sprite", "Drink");
        }else if(drinkChoice.contentEquals("5")) {
            comboDrink = new Drink("Soda", comboSize, true, "Barq's Root Beer", "Drink");
        }else if(drinkChoice.contentEquals("6")) {
            comboDrink = new Drink("Soda", comboSize, true, "Fanta Orange", "Drink");
        }else if(drinkChoice.contentEquals("7")) {
            comboDrink = new Drink("Soda", comboSize, true, "Nestea", "Drink");
        }else if(drinkChoice.contentEquals("8")) {
            comboDrink = new Drink("Soda", comboSize, true, "Fruit Passion Fruitopia", "Drink");
        }else if(drinkChoice.contentEquals("9")) {
            comboDrink = new Drink("Water", "Dasani", "Drink");
        }else if(drinkChoice.contentEquals("10")) {
            comboDrink = new Drink("Hot Drink", "Coffee", "Drink");
        }else if(drinkChoice.contentEquals("11")) {
            comboDrink = new Drink("Hot Drink", "Decaf Coffee", "Drink");
        }else if(drinkChoice.contentEquals("12")) {
            comboDrink = new Drink("Milk", "Milk", "Drink");
        }else if(drinkChoice.contentEquals("13")) {
            comboDrink = new Drink("Milk", "Chocolate Milk", "Drink");
        }else if(drinkChoice.contentEquals("14")) {
            comboDrink = new Drink("Package Drink", "Minute Maid Apple Juice", "Drink");
        }else if(drinkChoice.contentEquals("15")) {
            comboDrink = new Drink("Package Drink", "Minute Maid Orange Juice", "Drink");
        }else if(drinkChoice.contentEquals("16")) {
            comboDrink = new Drink("Lemonade", comboSize, true, "Lemonade", "Drink");
        }else if(drinkChoice.contentEquals("17")) {
            comboDrink = new Drink("Lemonade", comboSize, true, "Strawberry Lemonade", "Drink");
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
        System.out.println("(6) Etc");
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
        System.out.println("(6) Etc");
        System.out.println("(7) Change Order");
        System.out.println("(8) End Order");
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


    public static void print(String a){
        System.out.println(a);
    }
}

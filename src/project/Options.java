package project;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import static project.FastFood.*;
import static project.CoffeeShop.*;
import static project.Registration.*;
import static project.Transaction.*;

public class Options {
    protected static FastFood fastFood;
    protected static CoffeeShop coffeeShop;
    protected static Customer myCustomer;
    protected static Admin myAdmin;
    protected static Scanner reader = new Scanner(System.in);
    protected static String mainCategory;
    protected static String subCategory;
    protected static String input;
    protected static boolean fastFoodFlag;
    protected static boolean coffeeShopFlag;

    static {
        fastFood = null;
        coffeeShop = null;
        myCustomer = null;
        myAdmin = null;
        mainCategory = null;
        subCategory = null;
        input = null;
        fastFoodFlag = false;
        coffeeShopFlag = false;
    }

    public static void optionsForAdmin() throws IOException {
        System.out.println("What do you want to do?1/2/...");
        System.out.println("1.add item\n2.remove item\n3.change state of an item\n4.change price of an item\n" +
                "5.check shopping balance");
        input = reader.next();

        if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")) {
            chooseMainCategory();
            chooseSubCategory();
        }

        switch (input) {
            case "1": {
                System.out.println("Enter name of your item:");
                String itemName = reader.next();
                System.out.println("Enter price for your item:");
                int price = reader.nextInt();
                if (mainCategory.equals("coffee_shop"))
                    coffeeShop.addItemByAdmin(itemName, price);
                else if (mainCategory.equals("fast_food"))
                    fastFood.addItemByAdmin(itemName, price);
                break;
            }
            case "2": {
                System.out.println("Enter name of your item:");
                String itemName = reader.next();
                if (mainCategory.equals("coffee_shop"))
                    coffeeShop.removeItemByAdmin(itemName);
                else if (mainCategory.equals("fast_food"))
                    fastFood.removeItemByAdmin(itemName);
                break;
            }
            case "3": {
                System.out.println("Enter name of your item:");
                String itemName = reader.next();
                System.out.println("Enter new state for your item:");
                String state = reader.next();
                ItemState newState;
                if (state.equals("available"))
                    newState = ItemState.AVAILABLE;
                else
                    newState = ItemState.UNAVAILABLE;
                if (mainCategory.equals("coffee_shop"))
                    coffeeShop.changeStateOfItemByAdmin(itemName, newState);
                else if (mainCategory.equals("fast_food"))
                    fastFood.changeStateOfItemByAdmin(itemName, newState);
                break;
            }
            case "4": {
                System.out.println("Enter name of your item:");
                String itemName = reader.next();
                System.out.println("Enter new price for your item:");
                int price = reader.nextInt();
                if (mainCategory.equals("coffee_shop"))
                    coffeeShop.changePriceOfItemByAdmin(itemName, price);
                else if (mainCategory.equals("fast_food"))
                    fastFood.changePriceOfItemByAdmin(itemName, price);
                break;
            }
            case "5": {
                System.out.println("Amount of shopping balance is: ");
                System.out.println(getShoppingBalance());
                break;
            }
        }
    }

    public static void optionsForCustomer() throws IOException {
        System.out.println("What do you want to do? 1/2/...\n1.order item\n2.check your balance\n3.change your password\n4.log out");
        input = reader.next();
        if (input.equals("1")) {
            chooseMainCategory();
            while (true) {
                if (mainCategory.equals("fast_food")) {
                    fastFoodFlag = true;
                    coffeeShopFlag = false;
                    chooseSubCategory();
                    if (subCategory.equals("pizza") || subCategory.equals("sandwich") || subCategory.equals("frise")) {
                        fastFood.viewItems();
                        chooseItemByCustomer();
                    } else if (!subCategory.equals("back"))
                        System.out.println("Not found this sub category");
                } else if (mainCategory.equals("coffee_shop")) {
                    fastFoodFlag = false;
                    coffeeShopFlag = true;
                    chooseSubCategory();
                    if (subCategory.equals("cold_drink") || subCategory.equals("warm_drink")
                            || subCategory.equals("cake")) {
                        coffeeShop.viewItems();
                        chooseItemByCustomer();
                    } else if (!subCategory.equals("back"))
                        System.out.println("Not found this sub category");
                }
            }
        } else if (input.equals("2")) {
            System.out.println("Your balance is: ");
            System.out.println(myCustomer.getBalance());
        } else if (input.equals("3")) {
            System.out.println("Enter your new password:");
            String newPassword = reader.next();
            myCustomer.changePassword(newPassword);
            System.out.println("Your password was changed successfully.");
        } else if (input.equals("4")) {
            logout();
        }
    }

    public static void chooseItemByCustomer() throws IOException {
        while (true) {
            System.out.println("What do you want to do?1/2/3...\n1.choose item\n2.go back\n3.finish");
            input = reader.next();
            if (input.equals("1")) {
                System.out.println("Enter the name of your item that you want order it: ");
                String itemName = reader.next();
                if (fastFoodFlag)
                    myCustomer.addItemToListOfOrders(itemName, fastFood, null);
                else if (coffeeShopFlag)
                    myCustomer.addItemToListOfOrders(itemName, null, coffeeShop);
            } else if (input.equals("2")) {
                subCategory = "0";
                break;
            } else if (input.equals("3")) {
                Options.customerOptionsAfterChoosingItem();
                break;
            }
        }
    }

    public static void cancelItemByCustomer() {
        if (myCustomer.isEmptyOrders())
            System.out.println("List of your orders is empty!");
        else {
            String itemName;
            while (true) {
                System.out.println("Enter name of item that you want remove it:" +
                        " (*if you don't want continue, enter stop*)");
                itemName = reader.next();
                if (itemName.equals("stop"))
                    break;
                else
                    myCustomer.removeItemFromListOfOrders(itemName);
            }
        }
    }

    public static void viewFactor() throws IOException {
        if (myCustomer.isEmptyOrders()) {
            System.out.println("List of your orders is empty!");
        } else {
            System.out.println("List of your orders is: ");
            myCustomer.order.viewListOfOrders();
            System.out.println("Sum of prices is: ");
            System.out.println(myCustomer.order.calculateSumOfPrices());

        }
    }

    public static void customerOptionsAfterChoosingItem() throws IOException {
        while (true) {
            System.out.println("What do you want to do?1/2/...");
            System.out.println("1.view factor\n2.pay money and order\n3.remove item\n4.check balance\n5.increase balance" +
                    "\n6.log out\n7.exit\n8.started_again");
            String answer = reader.next();
            switch (answer) {
                case "1" -> viewFactor();
                case "2" -> payProcess(myCustomer);
                case "3" -> cancelItemByCustomer();
                case "4" -> {
                    System.out.println("Your balance is: ");
                    System.out.println(myCustomer.getBalance());
                }
                case "5" -> {
                    System.out.println("How many do you want increase your balance: ");
                    int price = reader.nextInt();
                    myCustomer.increaseCustomerBalance(price);
                    System.out.println("Your request done successfully.");
                }

                case "6" -> logout();
                case "7" -> System.exit(0);
                case "8" -> {
                    myCustomer.order.clearOrders();
                    optionsForCustomer();
                }
            }
        }

    }

    public static void chooseMainCategory() {
        System.out.println("Choose a main category:");
        System.out.println(Arrays.toString(MainCategory.values()));
        mainCategory = reader.next();
        while (true) {
            if (mainCategory.equals("coffee_shop") || mainCategory.equals("fast_food"))
                break;
            else {
                System.out.println("Not found this main category");
                mainCategory = reader.next();
            }
        }
    }

    public static void chooseSubCategory() {
        boolean flag = false;
        if (mainCategory.equals("coffee_shop")) {
            System.out.println("Choose a sub category: (*if enter back, you can go back*)");
            viewCategoriesOfCoffeeShop();
            subCategory = reader.next();
            label:
            while (true) {
                switch (subCategory) {
                    case "cold_drink":
                        coffeeShop = new ColdDrink();
                        break label;
                    case "warm_drink":
                        coffeeShop = new WarmDrink();
                        break label;
                    case "cake":
                        coffeeShop = new Cake();
                        break label;
                    case "back":
                        flag = true;
                        chooseMainCategory();
                        break label;
                    default:
                        System.out.println("Not found this sub category");
                        subCategory = reader.next();
                        break;
                }
            }
        } else if (mainCategory.equals("fast_food")) {
            System.out.println("Choose a sub category: (*if enter back, you can go back*)");
            viewCategoriesOfFastFood();
            subCategory = reader.next();
            label1:
            while (true) {
                switch (subCategory) {
                    case "pizza":
                        fastFood = new Pizza();
                        break label1;
                    case "sandwich":
                        fastFood = new Sandwich();
                        break label1;
                    case "frise":
                        fastFood = new Frise();
                        break label1;
                    case "back":
                        chooseMainCategory();
                        break label1;
                    default:
                        System.out.println("Not found this sub category");
                        subCategory = reader.next();
                        break;
                }
            }
        }
    }
}



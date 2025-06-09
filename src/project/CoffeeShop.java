package project;

import java.util.ArrayList;
import java.util.List;

public abstract class CoffeeShop implements ManageItemInEveryCategory { ///MAIN CATEGORY
    protected MainCategory category = MainCategory.COFFEE_SHOP;
    protected static List<Item> listOfColdDrink = new ArrayList<Item>();
    protected static List<Item> listOfWarmDrink = new ArrayList<Item>();
    protected static List<Item> listOfCake = new ArrayList<Item>();

    public static void viewCategoriesOfCoffeeShop() {
        System.out.println("warm_drink\ncold_drink\ncake");
    }

}

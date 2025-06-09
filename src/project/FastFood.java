package project;

import java.util.ArrayList;
import java.util.List;

public abstract class FastFood implements ManageItemInEveryCategory { ///MAIN CATEGORY
    protected MainCategory category = MainCategory.FAST_FOOD;
    protected static List<Item> listOfPizza = new ArrayList<Item>();
    protected static List<Item> listOfSandwich = new ArrayList<Item>();
    protected static List<Item> listOfFrise = new ArrayList<Item>();

    public static void viewCategoriesOfFastFood() {
        System.out.println("pizza\nsandwich\nfrise");
    }

}

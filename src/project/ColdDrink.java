package project;

import java.io.*;

public class ColdDrink extends CoffeeShop implements ManageItemInEveryCategory {
    public static void loadItemAsColdDrink() throws IOException {
        FileInputStream inputStream = new FileInputStream("cold_drink.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = br.readLine()) != null) {
            Item item = new Item();
            String[] splitted = line.split("\\s+");
            item.setItemName(splitted[0]);
            item.setPrice(Integer.parseInt(splitted[1]));
            ItemState itemState;
            if (splitted[2].equals("AVAILABLE"))
                itemState = ItemState.AVAILABLE;
            else
                itemState = ItemState.UNAVAILABLE;
            item.changeState(itemState);
            listOfColdDrink.add(item);
        }
        inputStream.close();
    }

    public void viewItems() {
        System.out.println("List of cold_drink:");
        for (Item eachItem : listOfColdDrink) {
            System.out.println("Name of item: " + eachItem.getItemName() + "   Price of item: " + eachItem.getPrice());
        }
    }

    public void addItemByAdmin(String itemName, int price) {
        Item newItem = getItemByName(itemName);
        if (newItem != null)
            System.out.println("This item is already exist.");
        else {
            newItem = new Item(itemName, price);
            try (PrintWriter output = new PrintWriter(new FileWriter("cold_drink.txt", true))) {
                output.printf("%s\r\n", itemName + " " + price + " " + "AVAILABLE");
                listOfColdDrink.add(newItem);
                System.out.println("Item added successfully.");
            } catch (Exception e) {
                System.out.println("This file not found.");
            }
        }
    }

    public void removeItemByAdmin(String itemName) throws IOException {
        Item deletedItem = getItemByName(itemName);
        if (deletedItem == null)
            System.out.println("This item is not exist.");
        else {
            String deletedLine = deletedItem.getItemName() + " " + deletedItem.getPrice() + " " + deletedItem.getState();
            ManageItemInEveryCategory.deleteLine("cold_drink.txt", deletedLine);
            listOfColdDrink.remove(deletedItem);
            System.out.println("Item deleted successfully.");
        }
    }

    public void changeStateOfItemByAdmin(String itemName, ItemState state) throws IOException {
        Item item = getItemByName(itemName);
        if (item == null)
            System.out.println("This item not found.");
        else {
            String deletedLine = item.getItemName() + " " + item.getPrice() + " " + state.toString();
            ManageItemInEveryCategory.deleteLine("cold_drink.txt", deletedLine);
            item.changeState(state);
            try (PrintWriter output = new PrintWriter(new FileWriter("cold_drink.txt", true))) {
                output.printf("%s\r\n", item.getItemName() + " " + item.getPrice() + " " + state.toString());
            } catch (Exception e) {
                System.out.println("This file not found.");
            }
            System.out.println("This change done successfully.");
        }
    }

    public void changePriceOfItemByAdmin(String itemName, int newPrice) throws IOException {
        Item item = getItemByName(itemName);
        if (item == null)
            System.out.println("This item not found.");
        else {
            String deletedLine = item.getItemName() + " " + item.getPrice();
            ManageItemInEveryCategory.deleteLine("cold_drink.txt", deletedLine);
            item.changePrice(newPrice);
            try (PrintWriter output = new PrintWriter(new FileWriter("cold_drink.txt", true))) {
                output.printf("%s\r\n", item.getItemName() + " " + item.getPrice());
            } catch (Exception e) {
                System.out.println("This file not found.");
            }
            System.out.println("This change done successfully.");
        }
    }

    public Item getItemByName(String itemName) {
        for (Item eachItem : listOfColdDrink) {
            if (eachItem.getItemName().equals(itemName))
                return eachItem;
        }
        return null;
    }

}

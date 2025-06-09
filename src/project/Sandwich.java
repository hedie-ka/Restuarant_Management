package project;

import java.io.*;

public class Sandwich extends FastFood { ///SUB CATEGORY

    public static void loadItemAsSandwich() throws IOException {
        FileInputStream inputStream = new FileInputStream("sandwich.txt");
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
            listOfSandwich.add(item);
        }
        inputStream.close();
    }

    public void viewItems() {
        System.out.println("List of sandwich:");
        for (Item eachItem : listOfSandwich) {
            System.out.println("Name of item: " + eachItem.getItemName() + "   Price of item: " + eachItem.getPrice());
        }
    }

    public void addItemByAdmin(String itemName, int price) {
        Item newItem = getItemByName(itemName);
        if (newItem != null)
            System.out.println("This item is already exist.");
        else {
            newItem = new Item(itemName, price);
            try (PrintWriter output = new PrintWriter(new FileWriter("sandwich.txt", true))) {
                output.printf("%s\r\n", itemName + " " + price + " " + "AVAILABLE");
                listOfSandwich.add(newItem);
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
            ManageItemInEveryCategory.deleteLine("sandwich.txt", deletedLine);
            listOfSandwich.remove(deletedItem);
            System.out.println("Item deleted successfully.");
        }
    }

    public void changeStateOfItemByAdmin(String itemName, ItemState state) throws IOException {
        Item item = getItemByName(itemName);
        if (item == null)
            System.out.println("This item not found.");
        else {
            String deletedLine = item.getItemName() + " " + item.getPrice() + " " + state.toString();
            ManageItemInEveryCategory.deleteLine("sandwich.txt", deletedLine);
            item.changeState(state);
            try (PrintWriter output = new PrintWriter(new FileWriter("sandwich.txt", true))) {
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
            ManageItemInEveryCategory.deleteLine("sandwich.txt", deletedLine);
            item.changePrice(newPrice);
            try (PrintWriter output = new PrintWriter(new FileWriter("sandwich.txt", true))) {
                output.printf("%s\r\n", item.getItemName() + " " + item.getPrice());
            } catch (Exception e) {
                System.out.println("This file not found.");
            }
            System.out.println("This change done successfully.");
        }
    }

    public Item getItemByName(String itemName) {
        for (Item eachItem : listOfSandwich) {
            if (eachItem.getItemName().equals(itemName))
                return eachItem;
        }
        return null;
    }

}

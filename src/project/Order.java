package project;

import java.util.HashMap;

public class Order {
    private HashMap<Item, Integer> orders;
    private int sumOfPrices;

    public Order() {
        this.orders = new HashMap<>();
        this.sumOfPrices = 0;
    }

    public HashMap<Item, Integer> getOrders() {
        return this.orders;
    }

    public void increaseNumberOfItem(Item item) {
        this.orders.replace(item, this.orders.get(item) + 1);
    }

    public void decreaseNumberOfItem(Item item) {
        this.orders.replace(item, this.orders.get(item) - 1);
    }

    public void addItemInOrders(Item item) {
        this.orders.put(item, 1);
    }

    public void deleteItemOfOrders(Item item) {
        this.orders.remove(item);
    }

    public boolean isExistOrder(String itemName) {
        for (Item item : this.orders.keySet()) {
            if (item.getItemName().equals(itemName))
                return true;
        }
        return false;
    }

    public Item getOrderByName(String itemName) {
        for (Item item : this.orders.keySet()) {
            if (item.getItemName().equals(itemName))
                return item;
        }
        return null;
    }

    public Integer returnValueByKey(Item item) {
        return this.orders.get(item);
    }

    public void viewListOfOrders() {
        for (Item item : this.orders.keySet())
            System.out.println("name: " + item.getItemName() + " Price: " + item.getPrice()
                    + " Number: " + this.orders.get(item));
    }

    public int calculateSumOfPrices() {
        sumOfPrices = 0;
        for (Item item : this.orders.keySet())
            this.sumOfPrices += item.getPrice() * orders.get(item);
        return this.sumOfPrices;
    }

    public int getSumOfPrices() {
        return this.sumOfPrices;
    }

    public void clearOrders() {
        this.orders.clear();
    }

}

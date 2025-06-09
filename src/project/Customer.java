package project;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Customer extends User {
    private int balance;
    private static List<Customer> customers = new ArrayList<Customer>();
    private Address address;
    public Order order;


    public static void loadCustomer() throws IOException {
        FileInputStream inputStream = new FileInputStream("customer.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = br.readLine()) != null) {
            String[] splitted = line.split("\\s+");
            String newName = splitted[0];
            String newUsername = splitted[1];
            String newPassword = splitted[2];
            String newPhone = splitted[3];
            Customer customer = new Customer(newName, newUsername, newPassword, newPhone);
            customers.add(customer);
            int newBalance = Integer.parseInt(splitted[4]);
            customer.setBalance(newBalance);
        }
        inputStream.close();
    }

    public Customer(String name, String ID, String password, String phone) {
        super(name, ID, password, phone);
        this.balance = 0;
        customers.add(this);
        this.order = new Order();
    }

    public void increaseCustomerBalance(int balance) throws IOException {
        this.changeBalanceInFile(this.balance + balance);
        this.balance += balance;
    }

    public void decreaseCustomerBalance(int balance) throws IOException {
        this.changeBalanceInFile(this.balance - balance);
        this.balance -= balance;
    }

    public void changeBalanceInFile(int newBalance) throws IOException {
        String deletedLine = this.getName() + " " + this.getID() + " " + this.getPassword() + " " + this.getPhone()
                + " " + this.getBalance();
        ManageItemInEveryCategory.deleteLine("customer.txt", deletedLine);
        try (PrintWriter output = new PrintWriter(new FileWriter("customer.txt", true))) {
            output.printf("%s\r\n", this.getName() + " " + this.getID() + " " + this.getPassword() + " " + this.getPhone()
                    + " " + newBalance);
        } catch (Exception e) {
            System.out.println("This file not found.");
        }
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return this.balance;
    }

    public static Customer getCustomerByID(String ID) {
        for (Customer myCustomer : customers) {
            if (myCustomer.ID.equals(ID))
                return myCustomer;
        }
        return null;
    }

    public static boolean isExistCustomer(String ID) {
        for (Customer myCustomer : customers) {
            if (myCustomer.ID.equals(ID))
                return true;
        }
        return false;
    }

    public void addCustomerToFile() {
        try (PrintWriter output = new PrintWriter(new FileWriter("customer.txt", true))) {
            output.write("\r\n");
            output.printf("%s\r\n", this.getName() + " " + this.getID() + " " + this.getPassword() + " " + this.getPhone()
                    + " " + this.getBalance());
        } catch (Exception e) {
            System.out.println("This file not found.");
        }
    }

    public void changePassword(String password) throws IOException {
        String deletedLine = this.getName() + " " + this.getID() + " " + this.getPassword() + " " + this.getPhone()
                + " " + this.getBalance();
        ManageItemInEveryCategory.deleteLine("customer.txt", deletedLine);
        try (PrintWriter output = new PrintWriter(new FileWriter("customer.txt", true))) {
            output.printf("%s\r\n", this.getName() + " " + this.getID() + " " + password + " " + this.getPhone()
                    + " " + this.getBalance());
        } catch (Exception e) {
            System.out.println("This file not found.");
        }
        this.password = password;
    }

    public void createAddress(String area, String street, String alley, String postalCode){
        address = new Address(area, street, alley, postalCode);
    }

    public Address getAddress() {
        return address;
    }

    public void addItemToListOfOrders(String nameOfItem, FastFood fastFood, CoffeeShop coffeeShop) {
        Item newItem;
        if (coffeeShop != null)
            newItem = coffeeShop.getItemByName(nameOfItem);
        else
            newItem = fastFood.getItemByName(nameOfItem);
        if (newItem == null)
            System.out.println("This item not found.");
        else {
            if (newItem.getState() == ItemState.AVAILABLE) {
                if (this.order.isExistOrder(nameOfItem))
                    this.order.increaseNumberOfItem(newItem);
                else {
                    this.order.addItemInOrders(newItem);
                }
                System.out.println("This item added successfully.");
            } else
                System.out.println("This item is unavailable.");
        }
    }

    public void removeItemFromListOfOrders(String nameOfItem) {
        Item newItem = this.order.getOrderByName(nameOfItem);
        if (newItem == null)
            System.out.println("This item not found.");
        else {
            if (this.order.returnValueByKey(newItem) > 1)
                this.order.decreaseNumberOfItem(newItem);
            else
                this.order.deleteItemOfOrders(newItem);
            System.out.println("This item deleted successfully.");
        }
    }

    public boolean isEmptyOrders() {
        HashMap<Item, Integer> items = this.order.getOrders();
        if (items.isEmpty())
            return true;
        return false;
    }

}

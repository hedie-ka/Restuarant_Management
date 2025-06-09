package project;

import java.io.*;
import java.util.Scanner;

public class Transaction {
    private static int shoppingBalance;
    private static Scanner scanner = new Scanner(System.in);

    public static void loadShoppingBalance() throws IOException {
        FileInputStream inputStream = new FileInputStream("shopping_balance.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = br.readLine()) != null) {
            String[] splitted = line.split("\\s+");
            shoppingBalance = Integer.parseInt(splitted[0]);
        }
        inputStream.close();
    }

    public static int getShoppingBalance() {
        return shoppingBalance;
    }

    public static void increaseShoppingBalance(int amount) throws IOException {
        updateShoppingBalanceInFile(amount);
        shoppingBalance += amount;

    }

    public static void updateShoppingBalanceInFile(int amount) throws IOException {
        String deletedLine = String.valueOf(shoppingBalance);
        ManageItemInEveryCategory.deleteLine("shopping_balance.txt", deletedLine);
        try (PrintWriter output = new PrintWriter(new FileWriter("shopping_balance.txt", true))) {
            output.printf("%s\r\n", String.valueOf(shoppingBalance + amount));
        } catch (Exception e) {
            System.out.println("This file not found.");
        }
    }

    public static void payProcess(Customer customer) throws IOException {
        System.out.println("Are you sure pay the money and order? yes/no");
        String answer = scanner.next();
        if (answer.equals("yes")) {
            if (customer.isEmptyOrders())
                System.out.println("List of your orders is empty!");
            else if (customer.order.getSumOfPrices() > customer.getBalance())
                System.out.println("Unfortunately your balance is not enough!");
            else
                createTransaction(customer);
        }
    }

    public static void createTransaction(Customer customer) throws IOException {
        System.out.println("Pleas enter your address: ");
        System.out.println("Area: ");
        String area = scanner.next();
        System.out.println("Street: ");
        String street = scanner.next();
        System.out.println("Alley: ");
        String alley = scanner.next();
        System.out.println("Postal code: ");
        String postalCode = scanner.next();
        customer.createAddress(area, street, alley, postalCode);
        customer.decreaseCustomerBalance(customer.order.calculateSumOfPrices());
        increaseShoppingBalance(customer.order.calculateSumOfPrices());
        System.out.println("Your orders will send to this address:" + customer.getAddress() +
                "\nThey arrived less than 90 minutes.\n" +
                "If there is a problem with sending your orders, call this number: 34055521");
        System.out.println("Thank you for your choice\n" +
                "hope to see you again:)");
        customer.order.clearOrders();
    }
}

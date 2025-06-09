package project;

import java.io.*;

import static project.Customer.*;
import static project.Admin.*;
import static project.Transaction.*;
import static project.Frise.loadItemAsFrise;
import static project.Pizza.loadItemAsPizza;
import static project.Sandwich.loadItemAsSandwich;
import static project.WarmDrink.loadItemAsWarmDrink;
import static project.ColdDrink.loadItemAsColdDrink;
import static project.Cake.loadItemAsCake;

public class Registration extends Options {

    public static void signInCustomer() {
        System.out.println("Enter your ID:");
        String ID = reader.next();
        myCustomer = getCustomerByID(ID);
        if (myCustomer == null) {
            System.out.println("This ID not found! try again:");
            while (true) {
                ID = reader.next();
                myCustomer = getCustomerByID(ID);
                if (myCustomer != null)
                    break;
                System.out.println("This ID not found! try again:");
            }
        }
        System.out.println("Enter your password:");
        String password = reader.next();
        if (!password.equals(myCustomer.getPassword())) {
            System.out.println("Your password is not valid, try again:");
            while (true) {
                password = reader.next();
                if (password.equals(myCustomer.getPassword()))
                    break;
                System.out.println("Your password is not valid, try again:");
            }
        }
        System.out.println("Dear " + myCustomer.getName() + " your sign in was successfully.");
    }

    public static void signUpCustomer() {
        System.out.println("ّFill your information:");
        System.out.println("Name:");
        String name = reader.next();
        System.out.println("Username:");
        String username = reader.next();
        if (isExistCustomer(username)) {
            do {
                System.out.println("This username is exist, try again!");
                username = reader.next();
            } while (isExistCustomer(username));
        }
        System.out.println("Password:");
        String password = reader.next();
        System.out.println("Phone:");
        String phone = reader.next();
        myCustomer = new Customer(name, username, password, phone);
        myCustomer.addCustomerToFile();
        System.out.println("Do you want enter your email? yes / no.");
        String answer = reader.next();
        if (answer.equals("yes")) {
            System.out.println("Enter your email address:");
            String email = reader.next();
            myCustomer.setEmail(email);
        }
        System.out.println("Dear " + myCustomer.getName() + " your sign up was successfully.");
    }

    public static void loginAdmin() {
        System.out.println("Please enter your admin ID:");
        String adminID = reader.next();
        if (isExistAdmin(adminID)) {
            myAdmin = getAdminByID(adminID);
            System.out.println("Dear " + myAdmin.getName() + " your login was successfully.");
        } else {
            System.out.println("This admin ID is not exist.");
            while (true) {
                adminID = reader.next();
                if (isExistAdmin(adminID)) {
                    myAdmin = getAdminByID(adminID);
                    System.out.println("Dear " + myAdmin.getName() + " your login was successfully.");
                    break;
                }
                System.out.println("This admin ID is not exist.");
            }
        }

    }

    public static void logout() throws IOException {
        fastFood = null;
        coffeeShop = null;
        myCustomer = null;
        myAdmin = null;
        mainCategory = null;
        subCategory = null;
        input = null;
        fastFoodFlag = false;
        coffeeShopFlag = false;
        loadSite();
    }

    public static void loadSite() throws IOException {
        System.out.println("Welcome to our online order food dear:)\nDo you want to Login hear as admin or customer?");
        input = reader.next();
        while (true) {
            if (input.equals("customer")) {
                while (true) {
                    System.out.println("sign_up / sign_in?");
                    input = reader.next();
                    if (input.equals("sign_in")) {
                        signInCustomer();
                        break;
                    } else if (input.equals("sign_up")) {
                        signUpCustomer();
                        break;
                    } else {
                        System.out.println("This command is not valid, try again: ");
                        input = reader.next();
                    }
                }
                while (true)
                    optionsForCustomer();
            } else if (input.equals("admin")) {
                loginAdmin();
                optionsForAdmin();
                while (true) {
                    System.out.println("exit or back or logout?");
                    input = reader.next();
                    if (input.equals("exit"))
                        System.exit(0);
                    else if (input.equals("back")) {
                        optionsForAdmin();
                        continue;
                    } else if (input.equals("logout"))
                        logout();
                    else {
                        System.out.println("This command is not valid.");
                        continue;
                    }
                    optionsForAdmin();
                }
            } else {
                System.out.println("This command is not valid, try again: ");
                input = reader.next();
            }
        }
    }

    public static void loadFiles() throws IOException {
        loadItemAsPizza();
        loadItemAsSandwich();
        loadItemAsFrise();
        loadItemAsColdDrink();
        loadItemAsWarmDrink();
        loadItemAsCake();
        loadCustomer();
        loadShoppingBalance();
    }

    public static void main(String[] args) throws IOException {
        loadFiles();
        loadSite();
    }
}


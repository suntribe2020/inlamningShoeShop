package shoeShop;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Katri Vidén
 * Date: 2021-02-18
 * Time: 17:52
 * Project: inlamningShoeShop
 * Copyright: MIT
 */
public class Shop {
    private static Customer currentCustomer;
    private int currentOrderId = -1;
    private static Scanner sc = new Scanner(System.in);
    private static Repository repository = new Repository();
    private String input;
    private boolean loggedIn = false;

    ZoneId zonedId = ZoneId.of("Europe/Stockholm");
    LocalDate today = LocalDate.now(zonedId);

    public Shop() {
        customerLogin();
        mainMenuSelect();
    }

    private void customerLogin() {
        System.out.println("Welcome to Shop'em'Shoes! To shop please log in");
        List<Customer> customers = repository.getCustomers();
        String username;
        String password;
        boolean loginSuccesfull = false;
        while (!loginSuccesfull) {
            System.out.println("Enter your username:");
            username = sc.nextLine().trim();
            for (Customer customer : customers) {
                if (username.equals(customer.getUserName())) {
                    System.out.println("Enter your password:");
                    password = sc.nextLine().trim();
                    if (password.equals(customer.getPassWord())) {
                        System.out.println("Login successful!");
                        currentCustomer = customer;
                        loginSuccesfull = true;
                        break;
                    }
                    System.out.println("Invalid password, please try again");
                    break;
                }
            }
        }
    }

    public void drawMainMenu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-----MAIN MENU-----" + "\n")
                .append("1. See shoes available" + "\n")
                .append("2. Show items in cart" + "\n")
                .append("3. Quit" + "\n")
                .append("--------------------");
        System.out.println(stringBuilder.toString());
    }

    private void mainMenuSelect() {
        while (!loggedIn) {
            drawMainMenu();
            System.out.println("Choose one of the alternatives above:");
            input = sc.nextLine();
            if (input.equals("1")) {
                placeNewOrderMenu();
            } else if (input.equals("2")) {
                listOrdersMenu();
            } else if (input.equals("3")) {
                System.out.println("Leaving already? Hope to see you soon again!");
                System.exit(0);
            }
        }
    }

    private void listAvailableShoes() {
        List<Shoe> allShoes = repository.getAllShoes();
        for (Shoe shoe : allShoes) {
            if (shoe.getInStock() <= 0) {
                continue;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("------------------------------------------------------------------" + "\n")
                    .append("Name: " + shoe.getName() + " | ")
                    .append("Color: " + shoe.getColor() + " | ")
                    .append("Size: " + shoe.getSize() + " | ")
                    .append("Price: " + shoe.getPrice() + " | ")
                    .append("In Stock: " + shoe.getInStock());
            System.out.println(stringBuilder.toString());
        }
    }

    private int getShoeId(String shoeName) {
        List<Shoe> shoes = repository.getAllShoes();
        for (Shoe shoe : shoes) {
            if (shoeName.equalsIgnoreCase(shoe.getName())) {
                return shoe.getId();
            }
        }
        return -1;
    }

    private void placeNewOrderMenu() {
        int quantity;
        int shoeId;

        while (true) {
            listAvailableShoes();
            System.out.println("------------------------------------------------------------------");
            System.out.println("Write the name of the shoe you would like to purchase (Write x to go back to the main menu):");
            input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("x")) {
                break;
            }
            shoeId = getShoeId(input);
            if (shoeId == -1) {
                System.out.println("Couldn't find shoe in database");
                continue;
            }

            System.out.println("Write the quantity you would like to purchase:");
            quantity = Integer.parseInt(sc.nextLine());
            currentOrderId = (repository.addToCart(currentCustomer.getId(), currentOrderId, shoeId, quantity,
                    today.toString()));
        }
    }

    private void listOrdersMenu() {
        List<CartItem> itemsInCart = repository.getListOfCustomersOrderedItems(currentCustomer.getId(), currentOrderId);
        int orderTotal = 0;
        if (itemsInCart.isEmpty()) {
            System.out.println("Your cart is empty!");
        } else {
            for (CartItem item : itemsInCart) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("---------------------------------------------------------------" + "\n")
                        .append("Date: " + item.getDate() + " | ")
                        .append("Brand: " + item.getBrand() + " | ")
                        .append("Name: " + item.getName() + " | ")
                        .append("Color: " + item.getColor() + " | ")
                        .append("Size: " + item.getSize() + " | ")
                        .append("Price: " + item.getPriceEach() + " | ")
                        .append("Quantity: " + item.getQuantity() + " | ")
                        .append("Total: " + item.getTotal() + " | ");
                System.out.println(stringBuilder.toString());
                orderTotal += item.getTotal();
            }
            System.out.println("-----------------------------------------[ order total: " + orderTotal + "]");
        }
    }

    public static void main(String[] args) {
        Shop shop = new Shop();

    }
}

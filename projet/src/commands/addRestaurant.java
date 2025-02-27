package commands;

import java.time.LocalDate;
import java.util.Scanner;

import models.Menus;
import models.Restaurant;

public class addRestaurant {
    private void addRestaurant(Scanner scanner) {
        System.out.println("Add a new restaurant:");
        System.out.print("Enter restaurant id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter restaurant name: ");
        String name = scanner.nextLine();
        System.out.print("Enter restaurant address: ");
        String address = scanner.nextLine();
        // Create an empty menu for the new restaurant
        Menus newMenu = new Menus(id, name + " Menu", LocalDate.now(), "General");
        restaurant = new Restaurant(id, name, address, newMenu);
        System.out.println("Restaurant created: " + restaurant);
    }
}
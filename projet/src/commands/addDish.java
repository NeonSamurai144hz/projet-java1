package commands;

import java.util.Scanner;

import models.Dishes;

public class addDish {
    private void addDish(Scanner scanner) {
        System.out.println("Add a dish:");
        System.out.print("Enter dish name: ");
        String dishName = scanner.nextLine();
        System.out.print("Enter dish price: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        Dishes dish = new Dishes(dishName, price);
        restaurant.getMenu().addDish(dish);
        System.out.println("Dish added: " + dish);
    }
}
package commands;

import java.util.Scanner;

import models.Dishes;
import models.Orders;

public class takeOrder {
    private void takeOrder(Scanner scanner) {
        System.out.println("Take an order:");
        System.out.print("Enter order number: ");
        int orderNumber = scanner.nextInt();
        scanner.nextLine();
        Orders order = new Orders(orderNumber);
        boolean ordering = true;
        while (ordering) {
            System.out.println("Select a dish from the menu:");
            restaurant.getMenu().showMenu();
            System.out.print("Enter dish name (or type 'done' to finish): ");
            String dishName = scanner.nextLine();
            if (dishName.equalsIgnoreCase("done")) {
                ordering = false;
            } else {
                Dishes dish = restaurant.getMenu().searchDishByName(dishName);
                if (dish != null) {
                    order.addDish(dish);
                    System.out.println("Added: " + dish);
                } else {
                    System.out.println("Dish not found.");
                }
            }
        }
        System.out.println("Order completed. Total: " + order.calculateTotal() + " euros");
        restaurant.addOrder(order);
    }
}
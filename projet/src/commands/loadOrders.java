package commands;

import java.util.Scanner;

public class loadOrders {
        private void loadOrders(Scanner scanner) {
        System.out.print("Enter file path to load orders: ");
        String path = scanner.nextLine();
        restaurant.loadOrders(path);
    }
}
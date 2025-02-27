package commands;

import java.util.Scanner;

public class saveOrders {
    private void saveOrders(Scanner scanner) {
        System.out.print("Enter file path to save orders: ");
        String path = scanner.nextLine();
        restaurant.saveOrders(path);
    }
}

package commands;

import java.util.Scanner;

public class removeEmployee {
        private void removeEmployee(Scanner scanner) {
        System.out.println("Remove an employee:");
        System.out.print("Enter employee id to remove: ");
        int empId = scanner.nextInt();
        scanner.nextLine();
        boolean removed = restaurant.removeEmployee(empId);
        if (removed) {
            System.out.println("Employee removed.");
        } else {
            System.out.println("Employee not found.");
        }
    }
}
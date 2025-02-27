package commands;

import java.time.LocalDate;
import java.util.Scanner;

import models.Employees;

public class addEmployee {
    private void addEmployee(Scanner scanner) {
        System.out.println("Add an employee:");
        System.out.print("Enter employee id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter role: ");
        String role = scanner.nextLine();
        System.out.print("Enter salary: ");
        int salary = scanner.nextInt();
        scanner.nextLine();
        Employees emp = new Employees(id, firstName, lastName, role, LocalDate.now(), salary);
        restaurant.addEmployee(emp);
        System.out.println("Employee added: " + emp);
    }
}
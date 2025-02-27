package models;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Interface {
    private Restaurant restaurant;

    public Interface() {
        Menus menu = new Menus(1, "Main Menu", LocalDate.now(), "Lunch");
        menu.addDish(new Dishes("Pizza", 12));
        menu.addDish(new Dishes("Pasta", 10));
        menu.addDish(new Dishes("Salad", 8));
        restaurant = new Restaurant(1, "Deli-o", "123 Main Street", menu);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        Map<Integer, String> menuOptions = new HashMap<>();
        menuOptions.put(1, "Add a restaurant");
        menuOptions.put(2, "Add an employee to a restaurant");
        menuOptions.put(3, "Add a dish to a restaurant's menu");
        menuOptions.put(4, "Remove an employee from a restaurant");
        menuOptions.put(5, "Display employees of a restaurant");
        menuOptions.put(6, "Take an order for a restaurant");
        menuOptions.put(7, "Display all orders of a restaurant");
        menuOptions.put(8, "Save restaurant data");
        menuOptions.put(9, "Load restaurant data");
        menuOptions.put(10, "Quit");

        Map<Integer, Runnable> actions = new HashMap<>();
        actions.put(1, () -> addRestaurant(scanner));
        actions.put(2, () -> addEmployee(scanner));
        actions.put(3, () -> addDish(scanner));
        actions.put(4, () -> removeEmployee(scanner));
        actions.put(5, this::displayEmployees);
        actions.put(6, () -> takeOrder(scanner));
        actions.put(7, this::displayOrders);
        actions.put(8, () -> saveRestaurant(scanner));
        actions.put(9, () -> loadRestaurant(scanner));
        actions.put(10, () -> System.out.println("Exiting..."));

        int choice = 0;
        do {
            System.out.println("\n=== Deli-o Restaurant Manager ===");
            for (int i = 1; i <= 10; i++) {
                System.out.println(i + ". " + menuOptions.get(i));
            }
            System.out.print("Please choose an option: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                Runnable action = actions.get(choice);
                if (action != null) {
                    action.run();
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer choice.");
                scanner.next();
            }
        } while (choice != 10);
        scanner.close();
        System.out.println("Goodbye!");
    }

    private void addRestaurant(Scanner scanner) {
        System.out.println("Add a new restaurant:");
        System.out.print("Enter restaurant id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter restaurant name: ");
        String name = scanner.nextLine();
        System.out.print("Enter restaurant address: ");
        String address = scanner.nextLine();
        
        Menus newMenu = new Menus(id, name + " Menu", LocalDate.now(), "General");
        
        restaurant = new Restaurant(id, name, address, newMenu);
        System.out.println("Restaurant created: " + restaurant);
        
        databases.dbRestaurant db = new databases.dbRestaurant();
        db.create(restaurant);
        
        String filename = "/Restaurants/restaurant_" + restaurant.getId() + ".txt";
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("File created successfully: " + file.getAbsolutePath());
        } else {
            System.out.println("File not created!");
        }
    }

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

    private void displayEmployees() {
        System.out.println("Employees:");
        restaurant.displayEmployees();
    }

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

    private void displayOrders() {
        System.out.println("Orders:");
        restaurant.displayOrders();
    }

    private void saveRestaurant(Scanner scanner) {
        System.out.print("Enter file path to save restaurant data: ");
        String path = scanner.nextLine();
        restaurant.saveToFile(path);
    }

    private void loadRestaurant(Scanner scanner) {
        System.out.print("Enter file path to load restaurant data: ");
        String path = scanner.nextLine();
        Restaurant loaded = Restaurant.loadFromFile(path);
        if (loaded != null) {
            restaurant = loaded;
            System.out.println("Restaurant loaded: " + restaurant);
        }
    }
}



// import java.time.LocalDate;
// import java.util.HashMap;
// import java.util.InputMismatchException;
// import java.util.Map;
// import java.util.Scanner;

// public class Interface {
//     private Restaurant restaurant;

//     // Initialize with a default restaurant and pre-populated menu
//     public Interface() {
//         Menu menu = new Menu(1, "Main Menu", LocalDate.now(), "Lunch");
//         // Pre-populate menu with some dishes
//         menu.addDish(new Dish("Pizza", 12));
//         menu.addDish(new Dish("Pasta", 10));
//         menu.addDish(new Dish("Salad", 8));
//         restaurant = new Restaurant(1, "Deli-o", "123 Main Street", menu);
//     }

//     public void start() {
//         Scanner scanner = new Scanner(System.in);

//         Map<Integer, String> menuOptions = new HashMap<>();
//         menuOptions.put(1, "Add a restaurant");
//         menuOptions.put(2, "Add an employee to a restaurant");
//         menuOptions.put(3, "Add a dish to a restaurant's menu");
//         menuOptions.put(4, "Remove an employee from a restaurant");
//         menuOptions.put(5, "Display employees of a restaurant");
//         menuOptions.put(6, "Take an order for a restaurant");
//         menuOptions.put(7, "Display all orders of a restaurant");
//         menuOptions.put(8, "Save orders of a restaurant");
//         menuOptions.put(9, "Load orders of a restaurant");
//         menuOptions.put(10, "Quit");

//         Map<Integer, Runnable> actions = new HashMap<>();
//         actions.put(1, () -> addRestaurant(scanner));
//         actions.put(2, () -> addEmployee(scanner));
//         actions.put(3, () -> addDish(scanner));
//         actions.put(4, () -> removeEmployee(scanner));
//         actions.put(5, this::displayEmployees);
//         actions.put(6, () -> takeOrder(scanner));
//         actions.put(7, this::displayOrders);
//         actions.put(8, () -> saveOrders(scanner));
//         actions.put(9, () -> loadOrders(scanner));
//         actions.put(10, () -> System.out.println("Exiting..."));

//         int choice = 0;
//         do {
//             System.out.println("\n=== Deli-o Restaurant Manager ===");
//             for (int i = 1; i <= 10; i++) {
//                 System.out.println(i + ". " + menuOptions.get(i));
//             }
//             System.out.print("Please choose an option: ");
//             try {
//                 choice = scanner.nextInt();
//                 scanner.nextLine(); // consume newline
//                 Runnable action = actions.get(choice);
//                 if (action != null) {
//                     action.run();
//                 } else {
//                     System.out.println("Invalid option. Please try again.");
//                 }
//             } catch (InputMismatchException e) {
//                 System.out.println("Please enter a valid integer choice.");
//                 scanner.next(); // clear invalid input
//             }
//         } while (choice != 10);
//         scanner.close();
//         System.out.println("Goodbye!");
//     }

//     // Option 1: Add a new restaurant (creates a new Restaurant object)
//     private void addRestaurant(Scanner scanner) {
//         System.out.println("Add a new restaurant:");
//         System.out.print("Enter restaurant id: ");
//         int id = scanner.nextInt();
//         scanner.nextLine();
//         System.out.print("Enter restaurant name: ");
//         String name = scanner.nextLine();
//         System.out.print("Enter restaurant address: ");
//         String address = scanner.nextLine();
//         // Create an empty menu for the new restaurant
//         Menu newMenu = new Menu(id, name + " Menu", LocalDate.now(), "General");
//         restaurant = new Restaurant(id, name, address, newMenu);
//         System.out.println("Restaurant created: " + restaurant);
//     }

//     // Option 2: Add an employee
//     private void addEmployee(Scanner scanner) {
//         System.out.println("Add an employee:");
//         System.out.print("Enter employee id: ");
//         int id = scanner.nextInt();
//         scanner.nextLine();
//         System.out.print("Enter first name: ");
//         String firstName = scanner.nextLine();
//         System.out.print("Enter last name: ");
//         String lastName = scanner.nextLine();
//         System.out.print("Enter role: ");
//         String role = scanner.nextLine();
//         System.out.print("Enter salary: ");
//         int salary = scanner.nextInt();
//         scanner.nextLine();
//         Employee emp = new Employee(id, firstName, lastName, role, LocalDate.now(), salary);
//         restaurant.addEmployee(emp);
//         System.out.println("Employee added: " + emp);
//     }

//     // Option 3: Add a dish to the restaurant's menu
//     private void addDish(Scanner scanner) {
//         System.out.println("Add a dish:");
//         System.out.print("Enter dish name: ");
//         String dishName = scanner.nextLine();
//         System.out.print("Enter dish price: ");
//         int price = scanner.nextInt();
//         scanner.nextLine();
//         Dish dish = new Dish(dishName, price);
//         restaurant.getMenu().addDish(dish);
//         System.out.println("Dish added: " + dish);
//     }

//     // Option 4: Remove an employee by id
//     private void removeEmployee(Scanner scanner) {
//         System.out.println("Remove an employee:");
//         System.out.print("Enter employee id to remove: ");
//         int empId = scanner.nextInt();
//         scanner.nextLine();
//         boolean removed = restaurant.removeEmployee(empId);
//         if (removed) {
//             System.out.println("Employee removed.");
//         } else {
//             System.out.println("Employee not found.");
//         }
//     }

//     // Option 5: Display all employees
//     private void displayEmployees() {
//         System.out.println("Employees:");
//         restaurant.displayEmployees();
//     }

//     // Option 6: Take an order (choose dishes from the menu)
//     private void takeOrder(Scanner scanner) {
//         System.out.println("Take an order:");
//         System.out.print("Enter order number: ");
//         int orderNumber = scanner.nextInt();
//         scanner.nextLine();
//         Order order = new Order(orderNumber);
//         boolean ordering = true;
//         while (ordering) {
//             System.out.println("Select a dish from the menu:");
//             restaurant.getMenu().showMenu();
//             System.out.print("Enter dish name (or type 'done' to finish): ");
//             String dishName = scanner.nextLine();
//             if (dishName.equalsIgnoreCase("done")) {
//                 ordering = false;
//             } else {
//                 Dish dish = restaurant.getMenu().searchDishByName(dishName);
//                 if (dish != null) {
//                     order.addDish(dish);
//                     System.out.println("Added: " + dish);
//                 } else {
//                     System.out.println("Dish not found.");
//                 }
//             }
//         }
//         System.out.println("Order completed. Total: " + order.calculateTotal() + " euros");
//         restaurant.addOrder(order);
//     }

//     // Option 7: Display all orders
//     private void displayOrders() {
//         System.out.println("Orders:");
//         restaurant.displayOrders();
//     }

//     // Option 8: Save orders to a file
//     private void saveOrders(Scanner scanner) {
//         System.out.print("Enter file path to save orders: ");
//         String path = scanner.nextLine();
//         restaurant.saveOrders(path);
//     }

//     // Option 9: Load orders from a file
//     private void loadOrders(Scanner scanner) {
//         System.out.print("Enter file path to load orders: ");
//         String path = scanner.nextLine();
//         restaurant.loadOrders(path);
//     }
// }
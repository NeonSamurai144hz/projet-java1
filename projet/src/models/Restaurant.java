package models;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private String address;
    private Menus menu;
    private ArrayList<Employees> employees;
    private ArrayList<Orders> orders;
    
    public Restaurant(int id, String name, String address, Menus menu) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.menu = menu;
        this.employees = new ArrayList<>();
        this.orders = new ArrayList<>();
    }
    
    // Employees methods
    public void addEmployee(Employees e) {
        employees.add(e);
    }
    
    public boolean removeEmployee(int employeeId) {
        // Here, we assume employee IDs are unique.
        return employees.removeIf(e -> e.toFileString().contains("Employee:" + employeeId + "|"));
    }
    
    public void displayEmployees() {
        for (Employees e : employees) {
            System.out.println(e);
        }
    }
    
    // Orders methods
    public void addOrder(Orders o) {
        orders.add(o);
    }
    
    public void displayOrders() {
        for (Orders o : orders) {
            System.out.println(o);
        }
    }
    
    public Menus getMenu() {
        return menu;
    }
    
    // Save all restaurant data into one file.
    public void saveToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Header
            writer.write("RestaurantID:" + id);
            writer.newLine();
            writer.write("Name:" + name);
            writer.newLine();
            writer.write("Address:" + address);
            writer.newLine();
            // Menu section
            writer.write(menu.toFileString());
            // Employees section
            writer.write("---Employees---");
            writer.newLine();
            writer.write("EmployeeCount:" + employees.size());
            writer.newLine();
            for (Employees e : employees) {
                writer.write(e.toFileString());
                writer.newLine();
            }
            // Orders section
            writer.write("---Orders---");
            writer.newLine();
            writer.write("OrderCount:" + orders.size());
            writer.newLine();
            for (Orders o : orders) {
                writer.write(o.toFileString());
                writer.newLine();
            }
            System.out.println("Restaurant saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving restaurant: " + e.getMessage());
        }
    }
    
    // Load restaurant data from file.
    public static Restaurant loadFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + filePath);
            return null;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int id = 0;
            String name = "";
            String address = "";
            Menus menu = null;
            ArrayList<Employees> employees = new ArrayList<>();
            ArrayList<Orders> orders = new ArrayList<>();
            
            String currentSection = "";
            List<String> sectionLines = new ArrayList<>();
            
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("RestaurantID:")) {
                    id = Integer.parseInt(line.substring("RestaurantID:".length()).trim());
                } else if (line.startsWith("Name:")) {
                    name = line.substring("Name:".length()).trim();
                } else if (line.startsWith("Address:")) {
                    address = line.substring("Address:".length()).trim();
                } else if (line.equals("---Menu---")) {
                    currentSection = "MENU";
                    sectionLines.clear();
                } else if (line.equals("---Employees---")) {
                    if (currentSection.equals("MENU")) {
                        menu = Menu.fromFileString(sectionLines);
                    }
                    currentSection = "EMPLOYEES";
                    sectionLines.clear();
                } else if (line.equals("---Orders---")) {
                    if (currentSection.equals("EMPLOYEES")) {
                        if (!sectionLines.isEmpty()) {
                            // First line contains count; then employee lines
                            for (int i = 1; i < sectionLines.size(); i++) {
                                employees.add(Employees.fromFileString(sectionLines.get(i)));
                            }
                        }
                    }
                    currentSection = "ORDERS";
                    sectionLines.clear();
                } else {
                    if (!currentSection.isEmpty()) {
                        sectionLines.add(line);
                    }
                }
            }
            if (currentSection.equals("ORDERS")) {
                if (!sectionLines.isEmpty()) {
                    for (int i = 1; i < sectionLines.size(); i++) {
                        orders.add(Orders.fromFileString(sectionLines.get(i)));
                    }
                }
            }
            Restaurant restaurant = new Restaurant(id, name, address, menu);
            for (Employees e : employees) {
                restaurant.addEmployee(e);
            }
            for (Orders o : orders) {
                restaurant.addOrder(o);
            }
            return restaurant;
        } catch (IOException e) {
            System.err.println("Error loading restaurant: " + e.getMessage());
            return null;
        }
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "Restaurant #" + id + " Name: " + name + ", Address: " + address;
    }
}


















// package models;
// import java.io.*;
// import java.util.ArrayList;
// import java.util.List;

// public class Restaurant {
//     private int id;
//     private String name;
//     private String address;
//     private Menus menu;
//     private ArrayList<Orders> orders;
//     private ArrayList<Employees> employees;

//     public Restaurant(int id, String name, String address, Menus menu) {
//         this.id = id;
//         this.name = name;
//         this.address = address;
//         this.menu = menu;
//         this.orders = new ArrayList<>();
//         this.employees = new ArrayList<>();
//     }

//         // Orders
//     public void addOrder(Orders order) {
//         orders.add(order);
//     }

//     public void displayOrders() {
//         for (Orders order : orders) {
//             System.out.println(order);
//         }
//     }

//     public void saveOrders(String filePath) {
//         try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//             for (Orders order : orders) {
//                 writer.write(order.toString());
//                 writer.newLine();
//             }
//             System.out.println("Orders saved to " + filePath);
//         } catch (IOException e) {
//             System.err.println("Error saving orders: " + e.getMessage());
//         }
//     }

//     public void loadOrders(String filePath) {
//         File file = new File(filePath);
//         if (!file.exists()) {
//             System.out.println("No previous orders found.");
//             return;
//         }
//         try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//             String line;
//             orders.clear();
//             while ((line = reader.readLine()) != null) {
//                 orders.add(Orders.parseOrder(line));
//                 System.out.println("Order loaded: " + line);
//             }
//             System.out.println("Orders loaded from " + filePath);
//         } catch (IOException e) {
//             System.err.println("Error loading orders: " + e.getMessage());
//         }
//     }

//         // Employees
//     public void addEmployee(Employees employee) {
//         employees.add(employee);
//     }

//     public boolean removeEmployee(int employeeId) {
//         for (int i = 0; i < employees.size(); i++) {
//             if (employees.get(i).getIdNumber() == employeeId) {
//                 employees.remove(i);
//                 return true;
//             }
//         }
//         return false;
//     }

//     public List<Employees> searchEmployeeByRole(String position) {
//         List<Employees> employeesByPosition = new ArrayList<>();
//         for (Employees employee : employees) {
//             if (employee.getPosition().equalsIgnoreCase(position)) {
//                 employeesByPosition.add(employee);
//             }
//         }
//         return employeesByPosition;
//     }

//     public void displayEmployees() {
//         for (Employees employee : employees) {
//             System.out.println(employee);
//         }
//     }

//     public int totalEmployeeSalaries() {
//         int total = 0;
//         for (Employees employee : employees) {
//             total += employee.getSalary();
//         }

//         return total;
//     }

//         // Menus
//     public Menus getMenu() {
//         return this.menu;
//     }

//     public void showRestaurant() {
//         System.out.println("Restaurant: " + name);
//         System.out.println("Address: " + address);
//         System.out.println("Menus: " + menu);
//         System.out.println("Employees: ");
//         displayEmployees();
//         System.out.println("Orders:");
//         displayOrders();
//         System.out.println("Total Revenue:");
//         totalRevenue();
//     }

//     public  int totalRevenue() {
//         int total = 0;
//         for (Orders order : orders) {
//             total += order.calculateTotal();
//         }
//         return total;
//     }

//     @Override
//     public String toString() {
//         return "Restaurant #" + id + " name: " + name + ", address: " + address;
//     }

// }

// Attributs :
// id (identifiant unique du restaurant)
// nomRestaurant (nom du restaurant)
// adresse (adresse du restaurant)
// menu (un objet de type Menu)
// commandes (une liste de commandes)
// employes (une liste d'employés, ArrayList<Employe>)
// Méthodes :
// Constructeur pour initialiser tous les attributs.
// ajouterCommande(Commande commande) : pour ajouter une commande au restaurant.
// afficherCommandes() : pour afficher toutes les commandes passées dans ce restaurant.
// ajouterEmploye(Employe employe) : pour ajouter un employé au restaurant.
// supprimerEmploye(Employe employe) : pour supprimer un employé du restaurant.
// chercherEmployeParRole(String role) : pour rechercher des employés par rôle dans le restaurant.
// afficherEmployes() : pour afficher tous les employés d'un restaurant.
// sauvegarderCommandes(String fichier) : pour sauvegarder les informations des commandes dans un fichier texte.
// totalSalaireEmployes(): Pour avoir la totalité des dépenses lié aux salaires des employés du restaurant.
// chargerCommandes(String fichier) : pour charger les informations des commandes depuis un fichier texte.
// afficherRestaurant() : pour afficher les détails du restaurant et de ses employés.
// totalChiffreAffaire(): Pour avoir la totalité du chiffres d’affaire du restaurant sur une journée ou depuis toujours sans argument passé à la méthode
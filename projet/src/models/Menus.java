package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Menus {
    private int id;
    private String menuName;
    private LocalDate creationDate;
    private String menuType;
    private List<Dishes> availableDishes;

    public Menus(int menuId, String menuName, LocalDate creationDate, String menuType) {
        this.id = menuId;
        this.menuName = menuName;
        this.creationDate = creationDate;
        this.menuType = menuType;
        this.availableDishes = new ArrayList<>();
    }

    public void addDish(Dishes dish) {
        availableDishes.add(dish);
    }

    public void removeDish(Dishes dish) {
        availableDishes.remove(dish);
    }

    public void showMenu() {
        for (Dishes dish : availableDishes) {
            System.out.println(dish);
        }
    }

    public Dishes searchDishByName(String name) {
        for (Dishes dish : availableDishes) {
            if (dish.getName().equalsIgnoreCase(name)) {
                return dish;
            }
        }
        System.out.println("Dish not found.");
        return null;
    }

    // Format: ---Menu--- then DishCount:<n> then each dish on a line.
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---Menu---\n");
        sb.append("DishCount:").append(availableDishes.size()).append("\n");
        for (Dishes dish : availableDishes) {
            sb.append(dish.toFileString()).append("\n");
        }
        return sb.toString();
    }

    // Load from a list of lines (assumes lines for the menu section only)
    public static Menus fromFileString(List<String> lines) {
        if (lines.size() < 2) return new Menus(0, "", LocalDate.now(), "");
        int count = Integer.parseInt(lines.get(1).split(":")[1].trim());
        Menus menu = new Menus(0, "", LocalDate.now(), "");
        for (int i = 2; i < 2 + count && i < lines.size(); i++) {
            menu.addDish(Dishes.fromFileString(lines.get(i)));
        }
        return menu;
    }

    @Override
    public String toString() {
        return toFileString();
    }
}





















// package models;
// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;

// public class Menus {
//     private int id;
//     private String menuName;
//     private LocalDate creationDate;
//     private String menuType;
//     private List<Dishes> availableDishes;

//         //constructor for menus
//     public Menus(int menuId, String menuName, LocalDate creationDate, String menuType) {
//         this.id = menuId;
//         this.menuName = menuName;
//         this.creationDate = creationDate;
//         this.menuType = menuType;
//         this.availableDishes = new ArrayList<>();
//     }

//     public void addDish(Dishes dish) {
//         this.availableDishes.add(dish);
//     }

//     public void removeDish(Dishes dish) {
//         this.availableDishes.remove(dish);
//     }

//     public void showMenu() {
//         for (Dishes dish : availableDishes) {
//             System.out.println(dish);
//         }
//     }

//     public Dishes searchDishByName(String name) {
//         for (Dishes dish : availableDishes) {
//             if (dish.getName().equalsIgnoreCase(name)) {
//                 return dish;
//             }
//         }
//         System.out.println("Dish not found.");
//         return null;
//     }

//     public String toString() {
//         return "Menu ID: " + id + "\nMenu Name: " + menuName 
//         + "\nCreation Date: " + creationDate + "\nMenu Type: " + menuType 
//         + "\nAvailable Dishes: " + availableDishes;
//     }
// }


// Classe Menu :
// Attributs : id, nomMenu, dateCreation, typeMenu, platsDisponibles.
// Méthodes :
// ajouterPlat(Plat plat) : pour ajouter un plat au menu.
// supprimerPlat(Plat plat) : pour supprimer un plat du menu.
// afficherMenu() : pour afficher tous les plats du menu.
// chercherPlatParNom(String nom) : pour rechercher un plat par son nom.
// toString() pour afficher les détails du menu.
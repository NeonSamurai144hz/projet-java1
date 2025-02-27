package models;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private int orderNumber;
    private List<Dishes> dishes;
    private int total;
    
    public Orders(int orderNumber) {
        this.orderNumber = orderNumber;
        this.dishes = new ArrayList<>();
        this.total = 0;
    }
    
    public void addDish(Dishes dish) {
        dishes.add(dish);
        calculateTotal();
    }
    
    public int calculateTotal() {
        total = 0;
        for (Dishes dish : dishes) {
            total += dish.getPrice();
        }
        return total;
    }

    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order:").append(orderNumber).append("|");
        for (Dishes dish : dishes) {
            sb.append(dish.getName()).append(",").append(dish.getPrice()).append(";");
        }
        if (!dishes.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("|").append(total);
        return sb.toString();
    }
    
    public static Orders fromFileString(String line) {
        String data = line.substring("Order:".length());
        String[] parts = data.split("\\|");
        int orderNumber = Integer.parseInt(parts[0].trim());
        Orders order = new Orders(orderNumber);
        String dishData = parts[1].trim();
        if (!dishData.isEmpty()) {
            String[] dishEntries = dishData.split(";");
            for (String entry : dishEntries) {
                String[] dishParts = entry.split(",");
                String dishName = dishParts[0].trim();
                int dishPrice = Integer.parseInt(dishParts[1].trim());
                Dishes dish = new Dishes(dishName, dishPrice);
                order.addDish(dish);
            }
        }
        order.calculateTotal();
        return order;
    }
    
    @Override
    public String toString() {
        return toFileString();
    }
}












// package models;
// import java.util.ArrayList;
// import java.util.List;

// public class Orders {
//     private int orderNumber;
//     private List<Dishes> dishes;
//     private int total;

//     public Orders(int orderNumber) {
//         this.orderNumber = orderNumber;
//         this.dishes = new ArrayList<>();
//         this.total = 0;
//     }

//     public void addDish(Dishes dish) {
//         dishes.add(dish);
//         calculateTotal();
//     }

//     public int calculateTotal() {
//         this.total = 0;
//         for (Dishes dish : dishes) {
//             this.total += dish.getPrice();
//         }
//         return this.total;
//     }

//     public void showOrder(){
//         System.out.println("Order #" + orderNumber + ":");
//         for (Dishes dish : dishes) {
//             System.out.println(dish.getName() + " - " + dish.getPrice());
//         }
//         System.out.println("Total : " + total + "€");
//     }

//     public int getOrderNumber(){
//         return orderNumber;
//     }

//     public List<Dishes> getDishes(){
//         return dishes;
//     }

//     public String dishesToString() {
//         StringBuilder sb = new StringBuilder();
//         for (Dishes dish : dishes) {
//             sb.append(dish.getName() + ", " + dish.getPrice() + "€ |\n");
//         }

//         return sb.toString();
//     }

//     public static Orders parseOrder(String line) {
//         String parts[] = line.split("\\|");
//         int orderNumber = Integer.parseInt(parts[0].trim());
//         Orders order = new Orders(orderNumber);
//         if(parts.length >= 2 && !parts[1].trim().isEmpty()) {
//             String dishesPart = parts[1].trim();
//             String[] dishEntries = dishesPart.split(";");
//             for (String entry : dishEntries) {
//                 if(entry.trim().isEmpty()) continue;
//                 String[] dishParts = entry.split(",");
//                 String dishName = dishParts[0].trim();
//                 int dishPrice = Integer.parseInt(dishParts[1].trim());
//                 Dishes dish = new Dishes(dishName, dishPrice);
//                 order.addDish(dish);
//             }
//         }

//         return order;
//     }
    
//     @Override
//     public String toString() {
//         return "Order #" + orderNumber + " | " + dishesToString() + " | Total : " + total + "€";
//     }
// }

// Attributs : numeroCommande, plats, total.
// Méthodes :
// ajouterPlat(Plat plat) : pour ajouter un plat à la commande.
// calculerTotal() : pour calculer et retourner le total de la commande.
// afficherCommande() : pour afficher la liste des plats et le total de la commande.
// toString() pour retourner une chaîne représentant la commande.
package databases;

import models.Restaurant;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import models.Menus;

public class dbRestaurant {
    private List<Restaurant> restaurants;

    public dbRestaurant() {
        this.restaurants = loadAll();
    }

    public void create(Restaurant restaurant) {
        String filename = "./Restaurants/restaurant_" + restaurant.getId() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(restaurant.getId() + "\n");
            writer.write(restaurant.getName() + "\n");
            writer.write(restaurant.getAddress() + "\n");
            System.out.println("Restaurant saved!");
        } catch (IOException e) {
            System.out.println("Error saving restaurant: " + e.getMessage());
        }
    }

    public List<Restaurant> loadAll() {
        File folder = new File("./Restaurants/");
        File[] files = folder.listFiles();
        List<Restaurant> list = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                if (file.getName().startsWith("restaurant_")) {
                    list.add(load(file));
                }
            }
        }
        return list;
    }

    private Restaurant load(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int id = Integer.parseInt(reader.readLine());
            String name = reader.readLine();
            String address = reader.readLine();
            return new Restaurant(id, name, address);
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getName());
            return null;
        }
    }
}
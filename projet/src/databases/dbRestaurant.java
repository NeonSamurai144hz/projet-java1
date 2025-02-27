package databases;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Menus;
import models.Restaurant;

public class dbRestaurant {
    private List<Restaurant> restaurants;

    public dbRestaurant() {
        this.restaurants = loadAll();
    }

    public void create(Restaurant restaurant) {
        String filename = "/Restaurants/restaurant_" + restaurant.getId() + ".txt";
        File file = new File(filename);
        file.getParentFile().mkdirs();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(restaurant.getId() + "\n");
            writer.write(restaurant.getName() + "\n");
            writer.write(restaurant.getAddress() + "\n");
            System.out.println("Restaurant saved!");
        } catch (IOException e) {
            System.out.println("Error saving restaurant: " + e.getMessage());
        }
    }

    public List<Restaurant> loadAll() {
        File folder = new File("./Restaurants");
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
            Menus defaultMenu = new Menus(id, name + " Menu", LocalDate.now(), "Default");
            return new Restaurant(id, name, address, defaultMenu);
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getName());
            return null;
        }
    }
}
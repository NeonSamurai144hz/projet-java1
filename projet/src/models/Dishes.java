package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dishes {
    private static int count = 0;
    private int id;
    private String name;
    private String description;
    private int price;
    private int calories;
    private String category;
    private String portionSize;
    private LocalDate addDate;
    private boolean availability;
    private List<String> ingredients;
    private String typeDish;
    private int preparationTime;
    private int specialPrice;
    private String imageURL;

    // Constructors
    public Dishes(String name, String description, int price, int calories, String category, String portionSize, 
                  LocalDate addDate, Boolean availability, List<String> ingredients, String typeDish, int preparationTime, int specialPrice, String imageURL) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.calories = calories;
        this.category = category;
        this.portionSize = portionSize;
        this.addDate = addDate;
        this.availability = availability;
        this.ingredients = ingredients;
        this.typeDish = typeDish;
        this.preparationTime = preparationTime;
        this.specialPrice = specialPrice;
        this.imageURL = imageURL;
        count++;
        this.id = count;
    }

    // Minimal constructor (for quick creation & when parsing orders)
    public Dishes(String name, int price) {
        this.name = name;
        this.description = "";
        this.price = price;
        this.calories = 0;
        this.category = "";
        this.portionSize = "";
        this.addDate = LocalDate.now();
        this.availability = true;
        this.ingredients = new ArrayList<>();
        this.typeDish = "";
        this.preparationTime = 0;
        this.specialPrice = 0;
        this.imageURL = "";
        count++;
        this.id = count;
    }

    // Getters (only a few shown for brevity)
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    // (Other getters are similar.)

    @Override
    public String toString() {
        return "Dish: " + name + "\nDescription: " + description + "\nPrice: " + price + 
               "\nCalories: " + calories + "\nCategory: " + category + "\nPortion Size: " + portionSize + 
               "\nAdd Date: " + addDate + "\nAvailability: " + availability + "\nIngredients: " + ingredients + 
               "\nCuisine Type: " + typeDish + "\nPreparation Time: " + preparationTime + 
               "\nSpecial Price: " + specialPrice + "\nImage URL: " + imageURL;
    }
    
    // Converts the dish to a single-line string for file saving.
    // Format: Dish:<id>|<name>|<description>|<price>|<calories>|<category>|<portionSize>|<addDate>|<availability>|<ingredients>|<typeDish>|<preparationTime>|<specialPrice>|<imageURL>
    public String toFileString() {
        String ingStr = String.join(",", ingredients);
        return String.format("Dish:%d|%s|%s|%d|%d|%s|%s|%s|%b|%s|%s|%d|%d|%s",
                id, name, description, price, calories, category, portionSize,
                addDate.toString(), availability, ingStr, typeDish, preparationTime, specialPrice, imageURL);
    }
    
    // Rebuilds a dish from its file string.
    public static Dishes fromFileString(String line) {
        // Remove "Dish:" prefix.
        String data = line.substring("Dish:".length());
        String[] parts = data.split("\\|");
        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        String description = parts[2].trim();
        int price = Integer.parseInt(parts[3].trim());
        int calories = Integer.parseInt(parts[4].trim());
        String category = parts[5].trim();
        String portionSize = parts[6].trim();
        LocalDate addDate = LocalDate.parse(parts[7].trim());
        boolean availability = Boolean.parseBoolean(parts[8].trim());
        List<String> ingredients = List.of(parts[9].trim().split(","));
        String typeDish = parts[10].trim();
        int preparationTime = Integer.parseInt(parts[11].trim());
        int specialPrice = Integer.parseInt(parts[12].trim());
        String imageURL = parts[13].trim();
        // Create dish; note: we set the id manually (you might adjust count accordingly if needed)
        Dishes dish = new Dishes(name, description, price, calories, category, portionSize, addDate, availability, ingredients, typeDish, preparationTime, specialPrice, imageURL);
        dish.id = id; // override auto-generated id
        return dish;
    }
}















// package models;
// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;

// public class Dishes {
//         //attributes
//     private static int count = 0;
//     private int id;
//     private String name;
//     private String description;
//     private int price;
//     private int calories;
//     private String category;
//     private String portionSize;
//     private LocalDate addDate;
//     private boolean availability;
//     private List<String> ingredients;
//     private String typeDish;
//     private int preparationTime;
//     private int specialPrice;
//     private String imageURL;

//             //setters
//     public void setName ( String name){
//         this.name = name;
//     }
//     public void setID ( int id){
//         this.id = id;
//     }
//     public void setID ( String id){
//         this.id = Integer.parseInt(id);
//     }
//     public void setDescription ( String description){
//         this.description = description;
//     }
//     public void setPrice ( int price){
//         this.price = price;
//     }
//     public void setCalories ( int calories){
//         this.calories = calories;
//     }
//     public void setCategory ( String category){
//         this.category = category;
//     }
//     public void setPortionSize ( String portionSize){
//         this.portionSize = portionSize;
//     }
//     public void setDate ( LocalDate addDate){
//         this.addDate = addDate;
//     }
//     public void setAvailability ( Boolean availability){
//         this.availability = availability;
//     }
//     public void setIngredients ( String ingredients){
//         this.ingredients = List.of(ingredients.split(","));
//     }
//     public void setTypeDish ( String typeDish){
//         this.typeDish = typeDish;
//     }
//     public void setPreparationTime ( int preparationTime){
//         this.preparationTime = preparationTime;
//     }
//     public void setSpecialPrice ( int specialPrice){
//         this.specialPrice = specialPrice;
//     }
//     public void setImageURL ( String imageURL){
//         this.imageURL = imageURL;
//     }


//             //getters
//     public String getName (){
//         return name;
//     }
//     public int getIdNumber (){
//         return id;
//     }
//     public String getDescription (){
//         return description;
//     }
//     public int getPrice (){
//         return price;
//     }
//     public int getCalories (){
//         return calories;
//     }
//     public String getCategory (){
//         return category;
//     }
//     public String getPortionSize (){
//         return portionSize;
//     }
//     public LocalDate getDate (){
//         return addDate;
//     }
//     public Boolean getAvailability (){
//         return availability;
//     }
//     public List<String> getIngredients (){
//         return ingredients;
//     }
//     public String getTypeDish (){
//         return typeDish;
//     }
//     public int getPreparationTime (){
//         return preparationTime;
//     }
//     public int getSpecialPrice (){
//         return specialPrice;
//     }
//     public String getImageURL (){
//         return imageURL;
//     }



//         //constructors
//     public Dishes(String name, String description, int price, int calories, String category, String portionSize, 
//     LocalDate addDate, Boolean availability, List<String> ingredients, String typeDish, int preparationTime, int specialPrice, String imageURL){
//         this.name = name;
//         this.description = description;
//         this.price = price;
//         this.calories = calories;
//         this.category = category;
//         this.portionSize = portionSize;
//         this.addDate = addDate;
//         this.availability = availability;
//         this.ingredients = ingredients;
//         this.typeDish = typeDish;
//         this.preparationTime = preparationTime;
//         this.specialPrice = specialPrice;
//         this.imageURL = imageURL;
//         count++;
//         this.id = count;
//     }

//     public Dishes(String name, int price) {
//         this.name = name;
//         this.description = "";
//         this.price = price;
//         this.calories = 0;
//         this.category = "";
//         this.portionSize = "";
//         this.addDate = LocalDate.now();
//         this.availability = true;
//         this.ingredients = new ArrayList<>();
//         this.typeDish = "";
//         this.preparationTime = 0;
//         this.specialPrice = 0;
//         this.imageURL = "";
//         count++;
//         this.id = count;
//     }

//     @ Override
//     public String toString() {
//         return "Dish: " + name + "\nDescription: " + description + "\nPrice: " + price + 
//                "\nCalories: " + calories + "\nCategory: " + category + "\nPortion Size: " + portionSize + 
//                "\nAdd Date: " + addDate + "\nAvailability: " + availability + "\nIngredients: " + ingredients + 
//                "\nCuisine Type: " + typeDish + "\nPreparation Time: " + preparationTime + 
//                "\nSpecial Price: " + specialPrice + "\nImage URL: " + imageURL;
//     }

// }


// Attributs : nom, description, prix, calories, categorie, taillePortion, dateAjout, disponibilite, ingredients,
//  typeCuisine, tempsPreparation, prixSpecial, imageURL.
// Méthodes :
// Constructeur pour initialiser tous les attributs.
// Getters et setters pour chaque attribut.
// toString() pour afficher les informations du plat.
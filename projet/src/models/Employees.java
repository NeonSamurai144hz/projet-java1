package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employees {
    private int id;
    private String firstName;
    private String lastName;
    private String role;
    private LocalDate hireDate;
    private int salary;
    
    private static final DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
    
    public Employees(int id, String firstName, String lastName, String role, LocalDate hireDate, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.hireDate = hireDate;
        this.salary = salary;
    }
    
    // Save as a single line string.
    // Format: Employee:<id>|<firstName>|<lastName>|<role>|<hireDate>|<salary>
    public String toFileString() {
        return String.format("Employee:%d|%s|%s|%s|%s|%d",
                id, firstName, lastName, role, hireDate.format(dtf), salary);
    }
    
    // Parse an employee from a file line.
    public static Employees fromFileString(String line) {
        String data = line.substring("Employee:".length());
        String[] parts = data.split("\\|");
        int id = Integer.parseInt(parts[0].trim());
        String firstName = parts[1].trim();
        String lastName = parts[2].trim();
        String role = parts[3].trim();
        LocalDate hireDate = LocalDate.parse(parts[4].trim(), dtf);
        int salary = Integer.parseInt(parts[5].trim());
        return new Employees(id, firstName, lastName, role, hireDate, salary);
    }
    
    public int getSalary() {
        return salary;
    }
    
    @Override
    public String toString() {
        return String.format("Employee #%d: %s %s, Role: %s, Hire Date: %s, Salary: %d",
                id, firstName, lastName, role, hireDate.format(dtf), salary);
    }
}
















// package models;
// import java.time.LocalDate;

// public class Employees {
//         //attributes
//     private static int count = 0; 
//     private int id;
//     private int salary;
//     private String lastName;
//     private String firstName;
//     private String position;
//     private LocalDate startDate;

//         //constructors to initialize employee attributes
//     public Employees(int id, String firstName, String lastName, String position, LocalDate startDate, int salary) {
//         this.id = id;
//         this.firstName = firstName;
//         this.lastName = lastName;
//         this.position = position;
//         this.startDate = startDate;
//         this.salary = salary;
//     }

//     public Employees(String firstName, String lastName, int id, int salary) {
//         this(id, firstName, lastName, "", LocalDate.now(), salary);
//     }

//     public Employees() {
//         this(0, "", "", "", LocalDate.now(), 0);
//     }

//         //methods
//             //setters  (i private int startDate;nstance methdos)
//     public void setName ( String firstName, String lastName){
//         this.firstName = firstName;
//         this.lastName = lastName;
//     }
//                 //flexible set id method
//     public void setID ( int id){
//         this.id = id;
//     }
//     public void setID ( String id){
//         this.id = Integer.parseInt(id);
//     }
//     public void setPosition ( String position){
//         this.position = position;
//     }
//     public void setSalary ( int salary){
//         this.salary = salary;
//     }
//     public void setStartDate ( LocalDate startDate){
//         this.startDate = startDate;
//     }


//             //getters
//     public String getName (){
//         return firstName + " " + lastName;
//     }
//     public int getIdNumber (){
//         return id;
//     }
//     public String getPosition (){
//         return position;
//     }
//     public int getSalary (){
//         return salary;
//     }
//     public LocalDate getStartDate (){
//         return startDate;
//     }

//     @Override
//     public String toString() {
//         return "Employee #" + id + " : " +
//                "Name: " + getName() + ", " +
//                "Position: " + position + ", " +
//                "Salary: " + salary + ", " +
//                "Start Date: " + startDate;
//     }
// }

// Attributs : id, nom, prenom, role, dateEmbauche, salaire.
// Méthodes :
// Constructeur pour initialiser tous les attributs.
// Getters et setters pour chaque attribut.
// toString() pour afficher les informations de l'employé sous forme de chaîne.
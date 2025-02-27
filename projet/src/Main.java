import models.Interface;

public class Main {
    public static void main(String[] args) throws Exception {
        Interface ui = new Interface();
        ui.start();
    }
}











// Créer un système de gestion de plusieurs restaurants avec des menus, des commandes et des employés. Chaque restaurant 
// pourra gérer des employés (serveurs, managers de la salle, cuisiniers) et chaque employé aura un rôle spécifique.
// Le but est de pouvoir gérer les informations des employés pour chaque restaurant, avec un identifiant unique pour 
// chaque employé et restaurant.
// Vous pouvez utiliser toutes les briques algorithmiques et les possiblités de Java vous n’avez pas le droit d’utiliser les 
// librairies extérieur (.jar) en java.

// Classe Employe :
// Attributs : idEmploye, nom, prenom, role, dateEmbauche, salaire.
// Méthodes :
// Constructeur pour initialiser tous les attributs.
// Getters et setters pour chaque attribut.
// toString() pour afficher les informations de l'employé sous forme de chaîne.
// Classe Plat :
// Attributs : nom, description, prix, calories, categorie, taillePortion, dateAjout, disponibilite, ingredients, typeCuisine, tempsPreparation, prixSpecial, imageURL.
// Méthodes :
// Constructeur pour initialiser tous les attributs.
// Getters et setters pour chaque attribut.
// toString() pour afficher les informations du plat.
// Classe Menu :
// Attributs : idMenu, nomMenu, dateCreation, typeMenu, platsDisponibles.
// Méthodes :
// ajouterPlat(Plat plat) : pour ajouter un plat au menu.
// supprimerPlat(Plat plat) : pour supprimer un plat du menu.
// afficherMenu() : pour afficher tous les plats du menu.
// chercherPlatParNom(String nom) : pour rechercher un plat par son nom.
// toString() pour afficher les détails du menu.
// Classe Commande :
// Attributs : numeroCommande, plats, total.
// Méthodes :
// ajouterPlat(Plat plat) : pour ajouter un plat à la commande.
// calculerTotal() : pour calculer et retourner le total de la commande.
// afficherCommande() : pour afficher la liste des plats et le total de la commande.
// toString() pour retourner une chaîne représentant la commande.
// Classe Restaurant :
// Attributs :
// idRestaurant (identifiant unique du restaurant)
// nomRestaurant (nom du restaurant)
// adresse (adresse du restaurant)
// menu (un objet de type Menu)
// commandes (une liste de commandes)
// employes (une liste d'employés, ArrayList<Employe>)
// Méthodes :
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
// totalChiffreAffaire(): Pour avoir la totalité du chiffres d’affaire du restaurant sur une journée ou depuis toujours 
// sans argument passé à la méthode.
// Gestion des fichiers : Utilise les classes File et BufferedReader ou BufferedWriter pour sauvegarder et charger les 
// informations des restaurants, des commandes, et des employés dans un fichier texte.
// Créer une classe Main  : Comme point d’entréer avec la méthode scanner de java.
// Proposer un menu interactif avec des choix pour l'utilisateur afin de :
// Ajouter un restaurant.
// Ajouter un employé à un restaurant.
// Ajouter un plat au menu d'un restaurant.
// Prendre une commande dans un restaurant.
// Afficher les employés et les commandes d'un restaurant.
// Sauvegarder et charger les informations.
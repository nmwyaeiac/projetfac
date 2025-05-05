/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 import java.util.List;
 import java.util.Scanner;
 
 /**
  * Classe principale qui gère le déroulement du jeu
  * @author jo
  */
 public class Jeu {
     private Plateau plateau;
     private Joueur joueur;
     private List<Adversaire> adversaires;
     private Collecteur collecteur;
 
     public Jeu() {
         this.plateau = new Plateau(
             ParametresJeu.NB_LIGNES,
             ParametresJeu.NB_COLONNES,
             ParametresJeu.NB_ADVERSAIRES,
             ParametresJeu.NB_BIDONS
         );
         this.joueur = plateau.getJoueur();
         this.adversaires = plateau.getAdversaires();
         this.collecteur = plateau.getCollecteur();
     }
 
     /**
      * Démarre et gère une partie de jeu
      */
    /**
 * Démarre et gère une partie de jeu
 */
public void joue() {
    int tours = 0;
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== DÉBUT DU JEU ===");
    plateau.afficherPlateau(); // Afficher le plateau au début

    // Modification de la condition pour continuer même sans adversaires
    boolean continuer = true;
    
    while (continuer && !joueur.estNeutralise()) {
        System.out.println("\n--- Tour " + (++tours) + " ---");
        
        // 1. Déplacement du joueur (deux déplacements)
        System.out.println("C'est votre tour de jouer!");
        joueur.deplacer(); // Assurez-vous que cette méthode est appelée
        
        if (joueur.estNeutralise()) {
            break; // Le joueur a été neutralisé pendant son déplacement
        }

        // 2. Déplacement des adversaires (un déplacement chacun)
        if (!adversaires.isEmpty()) {
            System.out.println("\nTour des adversaires...");
            for (Adversaire a : adversaires) {
                if (!a.estNeutralise()) {
                    a.deplacer();
                    
                    // Si le joueur est neutralisé après ce déplacement, on arrête immédiatement
                    if (joueur.estNeutralise()) {
                        System.out.println("Le joueur a été neutralisé!");
                        break;
                    }
                }
            }
            
            // Vérifier si tous les adversaires sont neutralisés
            if (adversaires.stream().noneMatch(a -> !a.estNeutralise())) {
                System.out.println("Tous les adversaires ont été neutralisés!");
                // Plutôt que d'arrêter, on continue à jouer
            }
        }
        
        // Afficher le plateau après tous les déplacements
        plateau.afficherPlateau();
        
        // 3. Tous les 5 tours, on propose au joueur d'arrêter
        if (tours % 5 == 0) {
            System.out.println("\nSouhaitez-vous arrêter la partie ? (o/n)");
            String rep = scanner.nextLine().trim().toLowerCase();
            if (rep.equals("o")) {
                continuer = false;
            }
        }
    }

    // Affichage des résultats de fin de partie
    System.out.println("\n=== FIN DE LA PARTIE ===");
    
    if (joueur.estNeutralise()) {
        afficherResultatsFinaux("Vous avez été neutralisé ! Défaite...");
    } else if (!adversaires.isEmpty() && adversaires.stream().noneMatch(a -> !a.estNeutralise())) {
        afficherResultatsFinaux("Tous les adversaires ont été neutralisés ! Victoire !");
    } else {
        afficherResultatsFinaux("Partie arrêtée manuellement.");
    }
}
     /**
      * Affiche les résultats finaux de la partie
      */
     private void afficherResultatsFinaux(String message) {
         System.out.println("\n" + message);
         System.out.println("Score (énergie dans le collecteur) : " + collecteur.getEnergie());
         long nbAdversairesNeutralises = adversaires.stream().filter(Adversaire::estNeutralise).count();
         System.out.println("Adversaires neutralisés : " + nbAdversairesNeutralises + " / " + ParametresJeu.NB_ADVERSAIRES);
         System.out.println("Énergie restante du joueur : " + joueur.getEnergie());
     }
 }
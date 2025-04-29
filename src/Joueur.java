/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 import java.util.Scanner;

 /**
  * Représente le joueur de l'énoncé.
  * Dans cette version du jeu, cette classe a une seule instance, dont la position doit être connue de ses adversaires.
  * Il doit pouvoir accéder au collecteur
  * @author jo
  */
 public class Joueur extends Personnage {
     private Collecteur collecteur;
 
     public Joueur(int ligne, int colonne, Plateau plateau, Collecteur collecteur) {
         super(ParametresJeu.INERTIE_JOUEUR); // Inertie fixée selon paramètres
         this.collecteur = collecteur;
         SalleDedans salleCentrale = (SalleDedans) plateau.getSalle(ligne, colonne);
         this.salle = salleCentrale;
         salleCentrale.setOccupant(this);
     }
     
     public Collecteur getCollecteur() {
         return collecteur;
     }
 
     @Override
     public void deplacer() {
         for (int i = 0; i < 2; i++) {
             if (estNeutralise()) return;
 
             System.out.println("Déplacement " + (i+1) + "/2 du joueur");
             Direction d = Direction.getDirectionQuelconque();
             Salle nouvelleSalle = salle.getVoisine(d);
 
             if (nouvelleSalle != null && nouvelleSalle instanceof SalleDedans) {
                 System.out.println("Déplacement vers " + d);
                 nouvelleSalle.entre(this);
             } else {
                 System.out.println("Déplacement impossible dans cette direction.");
                 i--; // On redonne une chance au joueur
             }
         }
     }
 
     @Override
     public void interagit(Reservoir r) {
         if (r instanceof Bidon b) {
             System.out.println("Vous avez trouvé un bidon contenant " + b.getEnergie() + " unités d'énergie!");
             
             System.out.println("1. Prendre de l'énergie pour vous");
             System.out.println("2. Stocker l'énergie dans le collecteur");
             System.out.println("3. Ne rien faire");
             
             try (Scanner scanner = new Scanner(System.in)) {
                int choix = -1;
                 while (choix < 1 || choix > 3) {
                     System.out.print("Votre choix (1-3): ");
                     try {
                         choix = Integer.parseInt(scanner.nextLine());
                     } catch (NumberFormatException e) {
                         System.out.println("Entrée invalide. Veuillez entrer un nombre entre 1 et 3.");
                     }
                 }
                 
                 if (choix == 1) {
                     System.out.print("Quantité à prendre (max " + Math.min(b.getEnergie(), ParametresJeu.MAX_ENERGIE - getEnergie()) + "): ");
                     int quantite = -1;
                     while (quantite < 0 || quantite > Math.min(b.getEnergie(), ParametresJeu.MAX_ENERGIE - getEnergie())) {
                         try {
                             quantite = Integer.parseInt(scanner.nextLine());
                             if (quantite < 0 || quantite > Math.min(b.getEnergie(), ParametresJeu.MAX_ENERGIE - getEnergie())) {
                                 System.out.println("Quantité invalide.");
                             }
                         } catch (NumberFormatException e) {
                             System.out.println("Entrée invalide. Veuillez entrer un nombre valide.");
                         }
                     }
                     if (quantite > 0) {
                         int energiePrise = b.puiser(quantite);
                         recevoirEnergie(energiePrise);
                         System.out.println("Vous avez pris " + energiePrise + " unités d'énergie.");
                     }
                 } else if (choix == 2) {
                     System.out.print("Quantité à stocker dans le collecteur (max " + b.getEnergie() + "): ");
                     int quantite = -1;
                     while (quantite < 0 || quantite > b.getEnergie()) {
                         try {
                             quantite = Integer.parseInt(scanner.nextLine());
                             if (quantite < 0 || quantite > b.getEnergie()) {
                                 System.out.println("Quantité invalide.");
                             }
                         } catch (NumberFormatException e) {
                             System.out.println("Entrée invalide. Veuillez entrer un nombre valide.");
                         }
                     }
                     if (quantite > 0) {
                         int energiePrise = b.puiser(quantite);
                         collecteur.stocker(energiePrise);
                         System.out.println("Vous avez stocké " + energiePrise + " unités d'énergie dans le collecteur.");
                     }
                 }
            }
         }
     }
     
     @Override
     public String toString() {
         return "♜";
     }
 }
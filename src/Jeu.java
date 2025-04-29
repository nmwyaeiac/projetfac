/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jo
 */
public class Jeu
{
     private plateau plateau;
    private joueur joueur;
    private list<adversaire> adversaires;

    public jeu() {
        this.plateau = new Plateau(
            parametresjeu.nb_lignes,
            parametresjeu.nb_colonnes,
            parametresjeu.nb_adversaires,
            parametresjeu.nb_bidons
        );
        this.joueur = plateau.getJoueur();
        this.adversaires = plateau.getaAversaires();
    }

   public void joue() {
    int tours = 0;

    while (!joueur.estneutralise() && adversaires.stream().anymatch(a -> !a.estneutralise())) {
        system.out.println("\n=== tour " + (++tours) + " ===");

        // 1. déplacement du joueur
        joueur.deplacer();
        system.out.println("état du plateau après déplacement du joueur :");
        plateau.afficherplateau();

        // 2. déplacement des adversaires
        for (Adversaire a : adversaires) {
            if (!a.estNeutralise()) {
                System.out.println("\nDéplacement d'un adversaire...");
                a.deplacer();
                plateau.afficherPlateau();
            }
        }

        // 3. Tous les 5 tours, on propose au joueur d’arrêter
        if (tours % 5 == 0) {
            System.out.println("\nSouhaitez-vous arrêter la partie ? (o/n)");
            Scanner sc = new Scanner(System.in);
            String rep = sc.nextLine().trim().toLowerCase();
            if (rep.equals("o")) {
                break;
            }
        }
    }

    // Fin du jeu
    System.out.println("\n=== FIN DE LA PARTIE ===");
    System.out.println(joueur.estNeutralise() ? "Défaite !" : "Victoire !");
    System.out.println("Score (énergie dans le collecteur) : " + plateau.getCollecteur().getEnergie());
    long nbNeutralises = adversaires.stream().filter(Adversaire::estNeutralise).count();
    System.out.println("Nombre d'adversaires neutralisés : " + nbNeutralises);
    System.out.println("Énergie restante du joueur : " + joueur.getEnergie());
}


    private void afficherResultatsFinaux(String message) {
        System.out.println("\n" + message);
        System.out.println("Score (énergie dans le collecteur) : " + plateau.getCollecteur().getEnergie());
        long nbAdversairesNeutralises = adversaires.stream().filter(Adversaire::estNeutralise).count();
        System.out.println("Adversaires neutralisés : " + nbAdversairesNeutralises + " / " + adversaires.size());
        System.out.println("Énergie restante du joueur : " + joueur.getEnergie());
    }
}

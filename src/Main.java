/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Scanner;

/**
 *
 * @author jo
 */
public class Main
{
    public static void main(String[] args)
    {
Plateau plateau = new Plateau(
            ParametresJeu.NB_LIGNES,
            ParametresJeu.NB_COLONNES,
            ParametresJeu.NB_ADVERSAIRES,
            ParametresJeu.NB_BIDONS
        );
        Joueur joueur = plateau.getJoueur();
        Scanner scanner = new Scanner(System.in);
        int tour = 0;

        System.out.println("=== DÉBUT DU JEU ===");
        plateau.afficherPlateau();

        while (!joueur.estNeutralise()
                && plateau.getAdversaires().stream().anyMatch(a -> !a.estNeutralise())) {

            System.out.println("\n--- Tour " + (++tour) + " ---");
            System.out.println("👤 Joueur : Énergie = " + joueur.getEnergie() + " | Inertie = " + joueur.getInertie());

            joueur.deplacer(); // Affiche déjà le plateau dans deplacer()
            plateau.afficherPlateau();

            // Déplacement des adversaires
            for (Adversaire a : plateau.getAdversaires()) {
                if (!a.estNeutralise()) {
                    a.deplacer();
                }
            }

            if (tour % 5 == 0) {
                System.out.println("\nSouhaitez-vous arrêter la partie ? (o/n)");
                String choix = scanner.nextLine();
                if (choix.equalsIgnoreCase("o")) {
                    break;
                }
            }
        }

        System.out.println("\n=== FIN DU JEU ===");

        if (joueur.estNeutralise()) {
            System.out.println("💀 Le joueur a été neutralisé. Défaite.");
        } else if (plateau.getAdversaires().stream().allMatch(Adversaire::estNeutralise)) {
            System.out.println("🏆 Tous les adversaires ont été neutralisés !");
        } else {
            System.out.println("🛑 Partie arrêtée manuellement par le joueur.");
        }

        long adversairesNeutralises = plateau.getAdversaires().stream().filter(Adversaire::estNeutralise).count();
        System.out.println("✔️ Énergie restante du joueur : " + joueur.getEnergie());
        System.out.println("✔️ Adversaires neutralisés : " + adversairesNeutralises + " / " + ParametresJeu.NB_ADVERSAIRES);
        System.out.println("✔️ Score (énergie dans le collecteur) : " + plateau.getCollecteur().getEnergie());
    }
    }
}

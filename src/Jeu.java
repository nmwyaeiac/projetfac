import java.util.List;
import java.util.Scanner;

public class Jeu {
    private Plateau plateau;
    private Joueur joueur;
    private List<Adversaire> adversaires;
    private Collecteur collecteur;

    public Jeu() {
        this.plateau = new Plateau(
            ParametresJeu.getNbLignes(),
            ParametresJeu.getNbColonnes(),
            ParametresJeu.getNbAdversaires(),
            ParametresJeu.getNbBidons()
        );
        this.joueur = plateau.getJoueur();
        this.adversaires = plateau.getAdversaires();
        this.collecteur = plateau.getCollecteur();
    }

    public void joue() {
        int tours = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== DÉBUT DU JEU ===");
        plateau.afficherPlateau(); // Afficher le plateau au début

        // Modification de la condition pour continuer même sans adversaires
        boolean continuer = true;
        
        while (continuer && !joueur.estNeutralise()) {
            System.out.println("\n--- Tour " + (++tours) + " ---");
            
            // Afficher les informations de jeu à chaque tour
            afficherInformationsTour();
            
            // 1. Déplacement du joueur (deux déplacements)
            System.out.println("C'est votre tour de jouer!");
            joueur.deplacer();
            
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
                boolean tousNeutralises = adversaires.stream().allMatch(Adversaire::estNeutralise);
                if (tousNeutralises) {
                    System.out.println("Tous les adversaires ont été neutralisés!");
                    System.out.println("Souhaitez-vous terminer la partie? (o/n)");
                    String rep = scanner.nextLine().trim().toLowerCase();
                    if (rep.equals("o")) {
                        continuer = false;
                    }
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
    
    private void afficherInformationsTour() {
        System.out.println("Énergie du joueur: " + joueur.getEnergie() + "/" + ParametresJeu.getMaxEnergie());
        System.out.println("Énergie dans le collecteur: " + collecteur.getEnergie());
        System.out.println("Adversaires neutralisés: " + 
                          adversaires.stream().filter(Adversaire::estNeutralise).count() + 
                          "/" + ParametresJeu.getNbAdversaires());
    }
    
    private void afficherResultatsFinaux(String message) {
        System.out.println("\n" + message);
        System.out.println("Score (énergie dans le collecteur) : " + collecteur.getEnergie());
        long nbAdversairesNeutralises = adversaires.stream().filter(Adversaire::estNeutralise).count();
        System.out.println("Adversaires neutralisés : " + nbAdversairesNeutralises + " / " + ParametresJeu.getNbAdversaires());
        System.out.println("Énergie restante du joueur : " + joueur.getEnergie());
    }
}
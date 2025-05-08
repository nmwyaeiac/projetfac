import java.util.List;
import java.util.Scanner;

/**
 * Classe principale gérant le déroulement d'une partie de jeu.
 * Coordonne les tours de jeu, les déplacements des personnages et
 * détermine les conditions de fin de partie.
 */
public class Jeu {
    /** Plateau de jeu */
    private final Plateau plateau;
    /** Référence au joueur */
    private final Joueur joueur;
    /** Liste des adversaires */
    private final List<Adversaire> adversaires;
    /** Collecteur d'énergie du joueur */
    private final Collecteur collecteur;

    /**
     * Constructeur d'une partie de jeu.
     * Initialise le plateau et récupère les références aux éléments de jeu.
     */
    public Jeu() {
        // Crée le plateau avec les dimensions et éléments spécifiés dans les paramètres
        this.plateau = new Plateau(
            ParametresJeu.getNbLignes(),
            ParametresJeu.getNbColonnes(),
            ParametresJeu.getNbAdversaires(),
            ParametresJeu.getNbBidons()
        );
        // Récupère les références aux éléments du jeu
        this.joueur = plateau.getJoueur();
        this.adversaires = plateau.getAdversaires();
        this.collecteur = plateau.getCollecteur();
    }

    /**
     * Démarre et gère le déroulement de la partie.
     * Alterne entre les tours du joueur et des adversaires jusqu'à la fin du jeu.
     */
    public void joue() {
        int tours = 0; // Compteur de tours
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== DÉBUT DU JEU ===");
        plateau.afficherPlateau(); // Afficher le plateau au début

        // Modification de la condition pour continuer même sans adversaires
        boolean continuer = true;
        
        // Boucle principale du jeu
        while (continuer && !joueur.estNeutralise()) {
            System.out.println("\n--- Tour " + (++tours) + " ---");
            
            // Afficher les informations de jeu à chaque tour
            afficherInformationsTour();
            
            // 1. Déplacement du joueur (deux déplacements)
            System.out.println("C'est votre tour de jouer!");
            joueur.deplacer();
            
            // Vérifie si le joueur a été neutralisé pendant son déplacement
            if (joueur.estNeutralise()) {
                break; // Termine la partie si le joueur est neutralisé
            }

            // 2. Déplacement des adversaires (un déplacement chacun)
            if (!adversaires.isEmpty()) {
                System.out.println("\nTour des adversaires...");
                for (Adversaire a : adversaires) {
                    // Seuls les adversaires non neutralisés se déplacent
                    if (!a.estNeutralise()) {
                        a.deplacer();
                        
                        // Si le joueur est neutralisé après ce déplacement, arrête immédiatement
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
                        continuer = false; // Fin de partie si choix de l'utilisateur
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
                    continuer = false; // Fin de partie si choix de l'utilisateur
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
     * Affiche les informations du tour en cours : énergie du joueur,
     * énergie dans le collecteur et nombre d'adversaires neutralisés.
     */
    private void afficherInformationsTour() {
        // Affiche l'énergie actuelle et maximale du joueur
        System.out.println("Énergie du joueur: " + joueur.getEnergie() + "/" + ParametresJeu.getMaxEnergie());
        // Affiche l'énergie stockée dans le collecteur (score)
        System.out.println("Énergie dans le collecteur: " + collecteur.getEnergie());
        // Affiche le nombre d'adversaires neutralisés
        System.out.println("Adversaires neutralisés: " + 
                          adversaires.stream().filter(Adversaire::estNeutralise).count() + 
                          "/" + ParametresJeu.getNbAdversaires());
    }
    
    /**
     * Affiche les résultats de fin de partie avec un message personnalisé.
     * 
     * @param message Message indiquant la raison de fin de partie
     */
    private void afficherResultatsFinaux(String message) {
        // Affiche le message de fin
        System.out.println("\n" + message);
        // Affiche le score final (énergie dans le collecteur)
        System.out.println("Score (énergie dans le collecteur) : " + collecteur.getEnergie());
        // Affiche le nombre d'adversaires neutralisés
        long nbAdversairesNeutralises = adversaires.stream().filter(Adversaire::estNeutralise).count();
        System.out.println("Adversaires neutralisés : " + nbAdversairesNeutralises + " / " + ParametresJeu.getNbAdversaires());
        // Affiche l'énergie restante du joueur
        System.out.println("Énergie restante du joueur : " + joueur.getEnergie());
    }
}
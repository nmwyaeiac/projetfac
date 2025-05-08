import java.util.Scanner;

/**
 * Représente le joueur contrôlé par l'utilisateur.
 * Le joueur peut se déplacer sur le plateau, interagir avec les bidons
 * et combattre les adversaires.
 */
public class Joueur extends Personnage {
    /** Référence au collecteur d'énergie du joueur */
    private final Collecteur collecteur;
    /** Scanner partagé pour lire les entrées de l'utilisateur */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Constructeur du joueur.
     * 
     * @param ligne Position initiale (ligne) du joueur
     * @param colonne Position initiale (colonne) du joueur
     * @param plateau Référence au plateau de jeu
     * @param collecteur Référence au collecteur d'énergie
     */
    public Joueur(int ligne, int colonne, Plateau plateau, Collecteur collecteur) {
        super(ParametresJeu.getInertieJoueur()); // Inertie fixée selon paramètres
        this.collecteur = collecteur; // Stocke la référence au collecteur
        // Récupère la salle centrale
        SalleDedans salleCentrale = (SalleDedans) plateau.getSalle(ligne, colonne);
        // Positionne le joueur dans la salle
        this.salle = salleCentrale;
        salleCentrale.setOccupant(this);
    }
    
    /**
     * Récupère le collecteur d'énergie du joueur.
     * 
     * @return Le collecteur d'énergie
     */
    public Collecteur getCollecteur() {
        return collecteur;
    }

    /**
     * Gère le déplacement du joueur sur le plateau.
     * Le joueur a droit à deux déplacements par tour, sauf s'il est neutralisé.
     */
    @Override
    public void deplacer() {
        // Deux déplacements par tour
        for (int i = 0; i < 2; i++) {
            // Ne pas continuer si joueur neutralisé
            if (estNeutralise()) return;

            System.out.println("Déplacement " + (i+1) + "/2 du joueur");
            // Demande la direction à l'utilisateur
            Direction d = Direction.getDirectionQuelconque();
            // Récupère la salle voisine dans cette direction
            Salle nouvelleSalle = salle.getVoisine(d);

            // Vérifie si le déplacement est possible
            if (nouvelleSalle != null && nouvelleSalle instanceof SalleDedans) {
                System.out.println("Déplacement vers " + d);
                // Entre dans la nouvelle salle
                nouvelleSalle.entre(this);
                // Affiche le plateau après le déplacement
                salle.getPlateau().afficherPlateau();
            } else {
                System.out.println("Déplacement impossible dans cette direction.");
                i--; // Redonne une chance au joueur (ne compte pas ce déplacement)
            }
        }
    }

    /**
     * Gère l'interaction du joueur avec un réservoir d'énergie.
     * Si c'est un bidon, propose au joueur de prendre l'énergie pour lui
     * ou de la stocker dans son collecteur.
     * 
     * @param r Le réservoir avec lequel interagir
     */
    @Override
    public void interagit(Reservoir r) {
        // Vérifier si le réservoir est un bidon
        if (r instanceof Bidon b) {
            System.out.println("Vous avez trouvé un bidon contenant " + b.getEnergie() + " unités d'énergie!");
            
            // Affiche les options au joueur
            System.out.println("1. Prendre de l'énergie pour vous");
            System.out.println("2. Stocker l'énergie dans le collecteur");
            System.out.println("3. Ne rien faire");
            
            // Lecture du choix utilisateur
            int choix = -1;
            while (choix < 1 || choix > 3) {
                System.out.print("Votre choix (1-3): ");
                try {
                    choix = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre entre 1 et 3.");
                }
            }
            
            // Traitement selon le choix
            if (choix == 1) {
                // Calculer le maximum qu'on peut prendre
                int maxPossible = Math.min(b.getEnergie(), ParametresJeu.getMaxEnergie() - getEnergie());
                System.out.print("Quantité à prendre (max " + maxPossible + "): ");
                
                // Lecture de la quantité
                int quantite = -1;
                while (quantite < 0 || quantite > maxPossible) {
                    try {
                        quantite = Integer.parseInt(scanner.nextLine());
                        if (quantite < 0 || quantite > maxPossible) {
                            System.out.println("Quantité invalide.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide. Veuillez entrer un nombre valide.");
                    }
                }
                
                // Prendre l'énergie si quantité positive
                if (quantite > 0) {
                    int energiePrise = b.puiser(quantite);
                    recevoirEnergie(energiePrise);
                    System.out.println("Vous avez pris " + energiePrise + " unités d'énergie.");
                }
            } else if (choix == 2) {
                // Stocker dans le collecteur
                System.out.print("Quantité à stocker dans le collecteur (max " + b.getEnergie() + "): ");
                
                // Lecture de la quantité
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
                
                // Stocker l'énergie si quantité positive
                if (quantite > 0) {
                    int energiePrise = b.puiser(quantite);
                    collecteur.stocker(energiePrise);
                    System.out.println("Vous avez stocké " + energiePrise + " unités d'énergie dans le collecteur.");
                }
            }
        }
    }
    
    /**
     * Représentation textuelle du joueur.
     * 
     * @return Symbole représentant le joueur (♜)
     */
    @Override
    public String toString() {
        return "♜"; // Symbole de la tour des échecs
    }
}
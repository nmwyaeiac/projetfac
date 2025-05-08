import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Représente le plateau de jeu composé d'une grille de salles.
 * Gère la création du plateau, le placement des personnages et des bidons,
 * ainsi que l'affichage de l'état du jeu.
 */
public class Plateau {
    /** Symbole pour représenter le bord du plateau */
    public static final String BORD = "█";
    /** Symbole pour représenter le fond du plateau */
    public static final String FOND = "░";
    
    /** Grille des salles composant le plateau */
    private final Salle[][] grille;
    /** Référence au joueur */
    private Joueur joueur;
    /** Liste des adversaires présents sur le plateau */
    private List<Adversaire> adversaires;
    /** Référence au collecteur d'énergie du joueur */
    private Collecteur collecteur;
    /** Générateur de nombres aléatoires pour le placement des éléments */
    private final Random random = new Random();

    /**
     * Récupère le nombre de lignes du plateau.
     * 
     * @return Nombre de lignes
     */
    public int getNbLig() {
        return grille.length;
    }

    /**
     * Récupère le nombre de colonnes du plateau.
     * 
     * @return Nombre de colonnes
     */
    public int getNbCol() {
        return grille[0].length;
    }
    
    /**
     * Récupère le joueur.
     * 
     * @return Référence au joueur
     */
    public Joueur getJoueur() {
        return joueur;
    }
    
    /**
     * Récupère la liste des adversaires.
     * 
     * @return Liste des adversaires
     */
    public List<Adversaire> getAdversaires() {
        return adversaires;
    }
    
    /**
     * Récupère le collecteur d'énergie.
     * 
     * @return Référence au collecteur
     */
    public Collecteur getCollecteur() {
        return collecteur;
    }

    /**
     * Récupère une salle à des coordonnées données.
     * 
     * @param lig Ligne de la salle
     * @param col Colonne de la salle
     * @return La salle aux coordonnées spécifiées ou null si hors limites
     */
    public Salle getSalle(int lig, int col) {
        if (isValide(lig, col)) {
            return grille[lig][col];
        } else {
            return null;
        }
    }

    /**
     * Définit une salle à des coordonnées données.
     * 
     * @param s Nouvelle salle
     * @param lig Ligne où placer la salle
     * @param col Colonne où placer la salle
     */
    public void setSalle(Salle s, int lig, int col) {
        if (isValide(lig, col)) {
            grille[lig][col] = s;
        }
    }

    /**
     * Vérifie si des coordonnées sont valides sur le plateau.
     * 
     * @param lig Ligne à vérifier
     * @param col Colonne à vérifier
     * @return true si les coordonnées sont valides, false sinon
     */
    private boolean isValide(int lig, int col) {
        return lig >= 0 && lig < getNbLig() && col >= 0 && col < getNbCol();
    }

    /**
     * Récupère la salle voisine d'une salle donnée dans une direction.
     * 
     * @param s Salle de départ
     * @param d Direction vers la salle voisine
     * @return Salle voisine ou null si hors limites
     */
    public Salle getVoisine(SalleDedans s, Direction d) {
        // Calcule les coordonnées de la salle voisine
        int newLig = s.getLigne() + d.getdLig();
        int newCol = s.getColonne() + d.getdCol();
        // Récupère la salle à ces coordonnées
        return getSalle(newLig, newCol);
    }

    /**
     * Initialise le contenu du plateau avec le joueur, les adversaires et les bidons.
     * 
     * @param nbAdversaires Nombre d'adversaires à placer
     * @param nbBidons Nombre de bidons à placer
     */
    private void initContenu(int nbAdversaires, int nbBidons) {
        // Création du collecteur
        this.collecteur = new Collecteur();
        
        // Initialisation de toutes les salles
        for (int i = 0; i < getNbLig(); i++) {
            for (int j = 0; j < getNbCol(); j++) {
                if (i == 0 || i == getNbLig() - 1 || j == 0 || j == getNbCol() - 1) {
                    // Bordures autour du plateau
                    grille[i][j] = new SalleBordure(i, j, this);
                } else {
                    // Salles vides à l'intérieur
                    grille[i][j] = new SalleVide(i, j, this);
                }
            }
        }
        
        // Position du centre du plateau
        int centreI = getNbLig() / 2;
        int centreJ = getNbCol() / 2;
        
        // Création du joueur au centre
        SalleDedans salleCentrale = (SalleDedans) grille[centreI][centreJ];
        this.joueur = new Joueur(centreI, centreJ, this, collecteur);
        
        // Liste pour garder trace des positions utilisées
        List<int[]> positionsUtilisees = new ArrayList<>();
        positionsUtilisees.add(new int[]{centreI, centreJ});
        
        // Placement des bidons d'énergie
        this.adversaires = new ArrayList<>();
        int bidonsPlaces = 0;
        while (bidonsPlaces < nbBidons) {
            // Choisit une position aléatoire à l'intérieur du plateau
            int i = 1 + random.nextInt(getNbLig() - 2);
            int j = 1 + random.nextInt(getNbCol() - 2);
            
            // Vérifie si la position est déjà utilisée
            boolean positionLibre = true;
            for (int[] pos : positionsUtilisees) {
                if (pos[0] == i && pos[1] == j) {
                    positionLibre = false;
                    break;
                }
            }
            
            if (positionLibre) {
                // Place un bidon à cette position
                grille[i][j] = new SalleBidon(i, j, this);
                bidonsPlaces++;
                positionsUtilisees.add(new int[]{i, j});
            }
        }
        
        // Placement des adversaires
        int adversairesPlaces = 0;
        while (adversairesPlaces < nbAdversaires) {
            // Choisit une position aléatoire à l'intérieur du plateau
            int i = 1 + random.nextInt(getNbLig() - 2);
            int j = 1 + random.nextInt(getNbCol() - 2);
            
            // Vérifie si la position est déjà utilisée
            boolean positionLibre = true;
            for (int[] pos : positionsUtilisees) {
                if (pos[0] == i && pos[1] == j) {
                    positionLibre = false;
                    break;
                }
            }
            
            if (positionLibre) {
                // Choix du type d'adversaire
                int typeAdversaire;
                
                // Vérifier s'il y a un type spécifique à tester
                if (TestManager.getTypeAdversaireTest() > 0) {
                    // Ajuster l'indice (1->0, 2->1, 3->2)
                    typeAdversaire = TestManager.getTypeAdversaireTest() - 1;
                } else {
                    // Choix aléatoire entre les trois types
                    typeAdversaire = random.nextInt(3); // 0, 1 ou 2
                }
                
                // Génère une inertie aléatoire entre MIN et MAX
                int inertie = ParametresJeu.getMinInertieAdversaire() + 
                    random.nextInt(ParametresJeu.getMaxInertieAdversaire() - ParametresJeu.getMinInertieAdversaire() + 1);
                
                // Crée l'adversaire selon le type choisi
                Adversaire nouvelAdversaire;
                nouvelAdversaire = switch (typeAdversaire) {
                    case 0 -> new AdversaireVelleitaire(inertie, joueur);
                    case 1 -> new AdversaireDetermine(inertie, joueur);
                    default -> new AdversaireIntelligent(inertie, joueur);
                };
                
                // Place l'adversaire sur le plateau
                nouvelAdversaire.setSalle((SalleDedans)grille[i][j]);
                ((SalleDedans)grille[i][j]).setOccupant(nouvelAdversaire);
                adversaires.add(nouvelAdversaire);
                
                adversairesPlaces++;
                positionsUtilisees.add(new int[]{i, j});
            }
        }
    }

    /**
     * Affiche le plateau dans la console.
     * Montre chaque salle avec son contenu actuel.
     */
    public void afficherPlateau() {
        System.out.println();
        for (int i = 0; i < getNbLig(); i++) {
            for (int j = 0; j < getNbCol(); j++) {
                // Affiche chaque salle suivie d'un espace
                System.out.print(grille[i][j].toString() + " ");
            }
            System.out.println(); // Nouvelle ligne à la fin de chaque ligne du plateau
        }
    }
    
    /**
     * Représentation textuelle du plateau.
     * Inclut les bordures et le contenu formaté.
     * 
     * @return Chaîne représentant tout le plateau
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        // Bordure supérieure
        for (int j = 0; j < getNbCol(); j++) {
            sb.append(BORD).append(BORD);
        }
        sb.append("\n");
        
        // Contenu du plateau
        for (int i = 0; i < getNbLig(); i++) {
            // Bordure gauche
            sb.append(BORD);
            
            // Contenu de la ligne
            for (int j = 0; j < getNbCol(); j++) {
                sb.append(grille[i][j].toString());
            }
            
            // Bordure droite
            sb.append(BORD);
            sb.append("\n");
        }
        
        // Bordure inférieure
        for (int j = 0; j < getNbCol(); j++) {
            sb.append(BORD).append(BORD);
        }
        
        return sb.toString();
    }

    /**
     * Constructeur du plateau avec dimensions et contenu spécifiés.
     * 
     * @param nbLig Nombre de lignes du plateau
     * @param nbCol Nombre de colonnes du plateau
     * @param nbAdversaires Nombre d'adversaires à placer
     * @param nbBidons Nombre de bidons à placer
     */
    public Plateau(int nbLig, int nbCol, int nbAdversaires, int nbBidons) {
        this.grille = new Salle[nbLig][nbCol]; // Crée la grille de salles
        initContenu(nbAdversaires, nbBidons); // Initialise le contenu
    }
}
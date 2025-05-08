import java.util.Scanner;

/**
 * Classe utilitaire contenant les paramètres configurables du jeu.
 * Permet de définir et récupérer tous les paramètres (dimensions du plateau,
 * nombre d'adversaires, nombre de bidons, limites d'énergie, etc.).
 */
public class ParametresJeu {
    /** Nombre de lignes du plateau (toujours impair pour avoir un centre) */
    private static int nbLignes = 11;
    /** Nombre de colonnes du plateau (toujours impair pour avoir un centre) */
    private static int nbColonnes = 11;
    /** Nombre d'adversaires sur le plateau */
    private static int nbAdversaires = 10;
    /** Nombre de bidons d'énergie sur le plateau */
    private static int nbBidons = 15;
    /** Énergie maximale que peut avoir un personnage */
    private static int maxEnergie = 10;
    /** Inertie du joueur (coefficient de force) */
    private static int inertieJoueur = 5;
    /** Inertie minimale des adversaires */
    private static int minInertieAdversaire = 2;
    /** Inertie maximale des adversaires */
    private static int maxInertieAdversaire = 9;
    
    /**
     * Récupère le nombre de lignes du plateau.
     * 
     * @return Nombre de lignes
     */
    public static int getNbLignes() { return nbLignes; }
    
    /**
     * Définit le nombre de lignes du plateau.
     * La valeur est ajustée pour être au moins 3 et toujours impaire.
     * 
     * @param val Nouveau nombre de lignes
     */
    public static void setNbLignes(int val) { 
        if (val < 3) val = 3; // Minimum 3 lignes
        if (val % 2 == 0) val++; // Assure que c'est impair
        nbLignes = val; 
    }
    
    /**
     * Récupère le nombre de colonnes du plateau.
     * 
     * @return Nombre de colonnes
     */
    public static int getNbColonnes() { return nbColonnes; }
    
    /**
     * Définit le nombre de colonnes du plateau.
     * La valeur est ajustée pour être au moins 3 et toujours impaire.
     * 
     * @param val Nouveau nombre de colonnes
     */
    public static void setNbColonnes(int val) { 
        if (val < 3) val = 3; // Minimum 3 colonnes
        if (val % 2 == 0) val++; // Assure que c'est impair
        nbColonnes = val; 
    }
    
    /**
     * Récupère le nombre d'adversaires.
     * 
     * @return Nombre d'adversaires
     */
    public static int getNbAdversaires() { return nbAdversaires; }
    
    /**
     * Définit le nombre d'adversaires.
     * La valeur est ajustée pour être au moins 0.
     * 
     * @param val Nouveau nombre d'adversaires
     */
    public static void setNbAdversaires(int val) { 
        if (val < 0) val = 0; // Pas de nombre négatif
        nbAdversaires = val; 
    }
    
    /**
     * Récupère le nombre de bidons d'énergie.
     * 
     * @return Nombre de bidons
     */
    public static int getNbBidons() { return nbBidons; }
    
    /**
     * Définit le nombre de bidons d'énergie.
     * La valeur est ajustée pour être au moins 0.
     * 
     * @param val Nouveau nombre de bidons
     */
    public static void setNbBidons(int val) { 
        if (val < 0) val = 0; // Pas de nombre négatif
        nbBidons = val; 
    }
    
    /**
     * Récupère l'énergie maximale d'un personnage.
     * 
     * @return Énergie maximale
     */
    public static int getMaxEnergie() { return maxEnergie; }
    
    /**
     * Définit l'énergie maximale d'un personnage.
     * La valeur est ajustée pour être au moins 1.
     * 
     * @param val Nouvelle énergie maximale
     */
    public static void setMaxEnergie(int val) { 
        if (val < 1) val = 1; // Minimum 1 unité d'énergie
        maxEnergie = val; 
    }
    
    /**
     * Récupère l'inertie du joueur.
     * 
     * @return Inertie du joueur
     */
    public static int getInertieJoueur() { return inertieJoueur; }
    
    /**
     * Définit l'inertie du joueur.
     * La valeur est ajustée pour être entre 1 et 9.
     * 
     * @param val Nouvelle inertie du joueur
     */
    public static void setInertieJoueur(int val) { 
        if (val < 1) val = 1; // Minimum 1
        if (val > 9) val = 9; // Maximum 9
        inertieJoueur = val; 
    }
    
    /**
     * Récupère l'inertie minimale des adversaires.
     * 
     * @return Inertie minimale des adversaires
     */
    public static int getMinInertieAdversaire() { return minInertieAdversaire; }
    
    /**
     * Définit l'inertie minimale des adversaires.
     * La valeur est ajustée pour être au moins 1 et ne pas dépasser l'inertie maximale.
     * 
     * @param val Nouvelle inertie minimale
     */
    public static void setMinInertieAdversaire(int val) { 
        if (val < 1) val = 1; // Minimum 1
        if (val > maxInertieAdversaire) val = maxInertieAdversaire; // Ne dépasse pas le max
        minInertieAdversaire = val; 
    }
    
    /**
     * Récupère l'inertie maximale des adversaires.
     * 
     * @return Inertie maximale des adversaires
     */
    public static int getMaxInertieAdversaire() { return maxInertieAdversaire; }
    
    /**
     * Définit l'inertie maximale des adversaires.
     * La valeur est ajustée pour être au moins égale à l'inertie minimale et au plus 9.
     * 
     * @param val Nouvelle inertie maximale
     */
    public static void setMaxInertieAdversaire(int val) { 
        if (val < minInertieAdversaire) val = minInertieAdversaire; // Au moins égal au min
        if (val > 9) val = 9; // Maximum 9
        maxInertieAdversaire = val; 
    }
    
    /**
     * Permet à l'utilisateur de configurer tous les paramètres du jeu.
     * Demande interactivement les nouvelles valeurs et les valide.
     */
    public static void configurerJeu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Configuration du jeu ===");
        
        System.out.println("Les dimensions du plateau doivent être impaires (pour avoir un centre)");
        System.out.print("Nombre de lignes (valeur impaire, minimum 3) : ");
        try {
            // Lecture et validation du nombre de lignes
            int lignes = Integer.parseInt(scanner.nextLine());
            setNbLignes(lignes);
            if (lignes != getNbLignes()) {
                System.out.println("Valeur ajustée à " + getNbLignes() + " pour garantir un nombre impair.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Valeur invalide, utilisation de la valeur par défaut: " + getNbLignes());
        }
        
        System.out.print("Nombre de colonnes (valeur impaire, minimum 3) : ");
        try {
            // Lecture et validation du nombre de colonnes
            int colonnes = Integer.parseInt(scanner.nextLine());
            setNbColonnes(colonnes);
            if (colonnes != getNbColonnes()) {
                System.out.println("Valeur ajustée à " + getNbColonnes() + " pour garantir un nombre impair.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Valeur invalide, utilisation de la valeur par défaut: " + getNbColonnes());
        }
        
        System.out.print("Nombre d'adversaires : ");
        try {
            // Lecture et validation du nombre d'adversaires
            setNbAdversaires(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Valeur invalide, utilisation de la valeur par défaut: " + getNbAdversaires());
        }
        
        System.out.print("Nombre de bidons d'énergie : ");
        try {
            // Lecture et validation du nombre de bidons
            setNbBidons(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Valeur invalide, utilisation de la valeur par défaut: " + getNbBidons());
        }
        
        System.out.print("Énergie maximale : ");
        try {
            // Lecture et validation de l'énergie maximale
            setMaxEnergie(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Valeur invalide, utilisation de la valeur par défaut: " + getMaxEnergie());
        }
        
        System.out.print("Inertie du joueur (1-9) : ");
        try {
            // Lecture et validation de l'inertie du joueur
            setInertieJoueur(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Valeur invalide, utilisation de la valeur par défaut: " + getInertieJoueur());
        }
        
        // Affichage des paramètres configurés
        System.out.println("\nConfiguration terminée !");
        System.out.println("Plateau: " + getNbLignes() + "×" + getNbColonnes());
        System.out.println("Adversaires: " + getNbAdversaires());
        System.out.println("Bidons: " + getNbBidons());
    }
}
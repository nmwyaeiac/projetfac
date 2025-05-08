import java.util.Scanner;

public class ParametresJeu {
    // Valeurs par défaut (toutes impaires pour nbLignes et nbColonnes)
    private static int nbLignes = 11;
    private static int nbColonnes = 11;
    private static int nbAdversaires = 10;
    private static int nbBidons = 15;
    private static int maxEnergie = 10;
    private static int inertieJoueur = 5;
    private static int minInertieAdversaire = 2;
    private static int maxInertieAdversaire = 9;
    
    // Getters et setters
    public static int getNbLignes() { return nbLignes; }
    public static void setNbLignes(int val) { 
        if (val < 3) val = 3;
        if (val % 2 == 0) val++; 
        nbLignes = val; 
    }
    
    public static int getNbColonnes() { return nbColonnes; }
    public static void setNbColonnes(int val) { 
        if (val < 3) val = 3;
        if (val % 2 == 0) val++; 
        nbColonnes = val; 
    }
    
    public static int getNbAdversaires() { return nbAdversaires; }
    public static void setNbAdversaires(int val) { 
        if (val < 0) val = 0;
        nbAdversaires = val; 
    }
    
    public static int getNbBidons() { return nbBidons; }
    public static void setNbBidons(int val) { 
        if (val < 0) val = 0;
        nbBidons = val; 
    }
    
    public static int getMaxEnergie() { return maxEnergie; }
    public static void setMaxEnergie(int val) { 
        if (val < 1) val = 1;
        maxEnergie = val; 
    }
    
    public static int getInertieJoueur() { return inertieJoueur; }
    public static void setInertieJoueur(int val) { 
        if (val < 1) val = 1;
        if (val > 9) val = 9;
        inertieJoueur = val; 
    }
    
    public static int getMinInertieAdversaire() { return minInertieAdversaire; }
    public static void setMinInertieAdversaire(int val) { 
        if (val < 1) val = 1;
        if (val > maxInertieAdversaire) val = maxInertieAdversaire;
        minInertieAdversaire = val; 
    }
    
    public static int getMaxInertieAdversaire() { return maxInertieAdversaire; }
    public static void setMaxInertieAdversaire(int val) { 
        if (val < minInertieAdversaire) val = minInertieAdversaire;
        if (val > 9) val = 9;
        maxInertieAdversaire = val; 
    }
    
    // Méthode pour configurer le jeu avant de commencer
    public static void configurerJeu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Configuration du jeu ===");
        
        System.out.println("Les dimensions du plateau doivent être impaires (pour avoir un centre)");
        System.out.print("Nombre de lignes (valeur impaire, minimum 3) : ");
        try {
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
            setNbAdversaires(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Valeur invalide, utilisation de la valeur par défaut: " + getNbAdversaires());
        }
        
        System.out.print("Nombre de bidons d'énergie : ");
        try {
            setNbBidons(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Valeur invalide, utilisation de la valeur par défaut: " + getNbBidons());
        }
        
        System.out.print("Énergie maximale : ");
        try {
            setMaxEnergie(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Valeur invalide, utilisation de la valeur par défaut: " + getMaxEnergie());
        }
        
        System.out.print("Inertie du joueur (1-9) : ");
        try {
            setInertieJoueur(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Valeur invalide, utilisation de la valeur par défaut: " + getInertieJoueur());
        }
        
        System.out.println("\nConfiguration terminée !");
        System.out.println("Plateau: " + getNbLignes() + "×" + getNbColonnes());
        System.out.println("Adversaires: " + getNbAdversaires());
        System.out.println("Bidons: " + getNbBidons());
    }
}
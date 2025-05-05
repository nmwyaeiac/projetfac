import java.util.Scanner;

public class TestManager {
    // Variable statique pour indiquer le type d'adversaire à créer
    private static int typeAdversaireTest = 0; // 0 = tous types, 1 = velléitaire, 2 = déterminé, 3 = intelligent
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean quitter = false;
        
        while (!quitter) {
            System.out.println("\n======= DÉMONSTRATEUR DU JEU DE PLATEAU =======");
            System.out.println("1. Jeu standard");
            System.out.println("2. Test: Joueur seul (déplacement)");
            System.out.println("3. Test: Joueur et bidons (collecte d'énergie)");
            System.out.println("4. Test: Combat avec adversaires");
            System.out.println("5. Test: Types d'adversaires");
            System.out.println("6. Test: Petit plateau");
            System.out.println("0. Quitter");
            
            System.out.print("Votre choix: ");
            int choix = lireEntier(scanner);
            
            switch (choix) {
                case 0 -> quitter = true;
                case 1 -> lancerJeuStandard();
                case 2 -> lancerTestJoueurSeul();
                case 3 -> lancerTestBidons();
                case 4 -> lancerTestCombat();
                case 5 -> lancerTestTypesAdversaires();
                case 6 -> lancerTestPetitPlateau();
                default -> System.out.println("Choix invalide");
            }
        }
    }
    
    private static int lireEntier(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrée invalide, entrez un nombre: ");
            }
        }
    }
    
    // Méthode pour définir le type d'adversaire à tester
    public static void setTypeAdversaireTest(int type) {
        typeAdversaireTest = type;
    }
    
    // Méthode pour récupérer le type d'adversaire à tester
    public static int getTypeAdversaireTest() {
        return typeAdversaireTest;
    }
    
    // Méthodes pour lancer les différents tests
    private static void lancerJeuStandard() {
        resetParametres();
        setTypeAdversaireTest(0); // Réinitialise le type d'adversaire
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    private static void lancerTestJoueurSeul() {
        resetParametres();
        // Modifier les paramètres pour ce test
        ParametresJeu.NB_ADVERSAIRES = 0;
        ParametresJeu.NB_BIDONS = 0;
        
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    private static void lancerTestBidons() {
        resetParametres();
        ParametresJeu.NB_ADVERSAIRES = 0;
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    private static void lancerTestCombat() {
        resetParametres();
        // Modifier les paramètres pour ce test
        ParametresJeu.NB_LIGNES = 7;     // Plateau plus petit pour faciliter les rencontres
        ParametresJeu.NB_COLONNES = 7;
        ParametresJeu.NB_ADVERSAIRES = 3; // Moins d'adversaires pour un test ciblé
        ParametresJeu.NB_BIDONS = 5;     // Quelques bidons pour que le joueur puisse gagner en énergie
        ParametresJeu.INERTIE_JOUEUR = 7; // Joueur plus fort pour tester la victoire en combat
        
        System.out.println("=== TEST DE COMBAT ===");
        System.out.println("Ce test utilise un plateau réduit avec 3 adversaires.");
        System.out.println("Le joueur a une inertie augmentée (7) pour tester les victoires en combat.");
        System.out.println("Objectif : Trouvez et combattez les adversaires !");
        
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    private static void lancerTestTypesAdversaires() {
        resetParametres();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n=== TEST DES TYPES D'ADVERSAIRES ===");
        System.out.println("Choisissez le type d'adversaire à tester :");
        System.out.println("1. Adversaire Velléitaire (♟) - se déplace avec une part d'aléatoire");
        System.out.println("2. Adversaire Déterminé (♞) - va directement vers ou s'éloigne du joueur");
        System.out.println("3. Adversaire Intelligent (♝) - cherche le chemin optimal");
        System.out.println("4. Tous les types d'adversaires ensemble");
        
        System.out.print("Votre choix (1-4) : ");
        int choixType = lireEntier(scanner);
        
        // Configuration du jeu selon le choix
        ParametresJeu.NB_LIGNES = 9;
        ParametresJeu.NB_COLONNES = 9;
        ParametresJeu.NB_BIDONS = 10;
        
        switch (choixType) {
            case 1 -> {
                System.out.println("\n=== TEST ADVERSAIRE VELLÉITAIRE ===");
                System.out.println("Ce test présente uniquement des adversaires velléitaires (♟)");
                System.out.println("Ces adversaires se déplacent avec une part d'aléatoire.");
                ParametresJeu.NB_ADVERSAIRES = 3;
                setTypeAdversaireTest(1); // 1 pour velléitaire
            }
            case 2 -> {
                System.out.println("\n=== TEST ADVERSAIRE DÉTERMINÉ ===");
                System.out.println("Ce test présente uniquement des adversaires déterminés (♞)");
                System.out.println("Ces adversaires vont directement vers ou s'éloignent du joueur.");
                ParametresJeu.NB_ADVERSAIRES = 3;
                setTypeAdversaireTest(2); // 2 pour déterminé
            }
            case 3 -> {
                System.out.println("\n=== TEST ADVERSAIRE INTELLIGENT ===");
                System.out.println("Ce test présente uniquement des adversaires intelligents (♝)");
                System.out.println("Ces adversaires cherchent le chemin optimal pour atteindre le joueur.");
                ParametresJeu.NB_ADVERSAIRES = 3;
                setTypeAdversaireTest(3); // 3 pour intelligent
            }
            case 4 -> {
                System.out.println("\n=== TEST TOUS TYPES D'ADVERSAIRES ===");
                System.out.println("Ce test présente les trois types d'adversaires :");
                System.out.println("- ♟ : Adversaire Velléitaire (se déplace avec une part d'aléatoire)");
                System.out.println("- ♞ : Adversaire Déterminé (va directement vers ou s'éloigne du joueur)");
                System.out.println("- ♝ : Adversaire Intelligent (cherche le chemin optimal)");
                System.out.println("Observez leurs différents comportements de déplacement !");
                ParametresJeu.NB_ADVERSAIRES = 6;  // 2 de chaque type
                setTypeAdversaireTest(0); // 0 pour tous types
            }
            default -> {
                System.out.println("Choix invalide. Test avec tous les types d'adversaires par défaut.");
                ParametresJeu.NB_ADVERSAIRES = 6;
                setTypeAdversaireTest(0);
            }
        }
        
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    private static void lancerTestPetitPlateau() {
        resetParametres();
        ParametresJeu.NB_LIGNES = 5;
        ParametresJeu.NB_COLONNES = 5;
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    private static void resetParametres() {
        // Réinitialiser tous les paramètres à leurs valeurs par défaut
        ParametresJeu.NB_LIGNES = 11;
        ParametresJeu.NB_COLONNES = 11;
        ParametresJeu.NB_ADVERSAIRES = 10;
        ParametresJeu.NB_BIDONS = 15;
        ParametresJeu.MAX_ENERGIE = 10;
        ParametresJeu.INERTIE_JOUEUR = 5;
        // Réinitialiser le type d'adversaire à tester
        setTypeAdversaireTest(0);
    }
}
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
            System.out.println("7. Configurer les paramètres du jeu");
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
                case 7 -> ParametresJeu.configurerJeu();
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
    
    private static void resetParametres() {
        // Réinitialiser tous les paramètres à leurs valeurs par défaut
        ParametresJeu.setNbLignes(11);
        ParametresJeu.setNbColonnes(11);
        ParametresJeu.setNbAdversaires(10);
        ParametresJeu.setNbBidons(15);
        ParametresJeu.setMaxEnergie(10);
        ParametresJeu.setInertieJoueur(5);
        ParametresJeu.setMinInertieAdversaire(2);
        ParametresJeu.setMaxInertieAdversaire(9);
        setTypeAdversaireTest(0);
    }
    
    private static void lancerJeuStandard() {
        resetParametres();
        setTypeAdversaireTest(0); // Réinitialise le type d'adversaire
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    private static void lancerTestJoueurSeul() {
        resetParametres();
        // Modifier les paramètres pour ce test
        ParametresJeu.setNbAdversaires(0);
        ParametresJeu.setNbBidons(0);
        
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    private static void lancerTestBidons() {
        resetParametres();
        ParametresJeu.setNbAdversaires(0);
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    private static void lancerTestCombat() {
        resetParametres();
        // Modifier les paramètres pour ce test
        ParametresJeu.setNbLignes(7);
        ParametresJeu.setNbColonnes(7);
        ParametresJeu.setNbAdversaires(3);
        ParametresJeu.setNbBidons(5);
        ParametresJeu.setInertieJoueur(7);
        
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
        ParametresJeu.setNbLignes(9);
        ParametresJeu.setNbColonnes(9);
        ParametresJeu.setNbBidons(10);
        
        switch (choixType) {
            case 1 -> {
                System.out.println("\n=== TEST ADVERSAIRE VELLÉITAIRE ===");
                System.out.println("Ce test présente uniquement des adversaires velléitaires (♟)");
                System.out.println("Ces adversaires se déplacent avec une part d'aléatoire.");
                ParametresJeu.setNbAdversaires(3);
                setTypeAdversaireTest(1); // 1 pour velléitaire
            }
            case 2 -> {
                System.out.println("\n=== TEST ADVERSAIRE DÉTERMINÉ ===");
                System.out.println("Ce test présente uniquement des adversaires déterminés (♞)");
                System.out.println("Ces adversaires vont directement vers ou s'éloignent du joueur.");
                ParametresJeu.setNbAdversaires(3);
                setTypeAdversaireTest(2); // 2 pour déterminé
            }
            case 3 -> {
                System.out.println("\n=== TEST ADVERSAIRE INTELLIGENT ===");
                System.out.println("Ce test présente uniquement des adversaires intelligents (♝)");
                System.out.println("Ces adversaires cherchent le chemin optimal pour atteindre le joueur.");
                ParametresJeu.setNbAdversaires(3);
                setTypeAdversaireTest(3); // 3 pour intelligent
            }
            case 4 -> {
                System.out.println("\n=== TEST TOUS TYPES D'ADVERSAIRES ===");
                System.out.println("Ce test présente les trois types d'adversaires :");
                System.out.println("- ♟ : Adversaire Velléitaire (se déplace avec une part d'aléatoire)");
                System.out.println("- ♞ : Adversaire Déterminé (va directement vers ou s'éloigne du joueur)");
                System.out.println("- ♝ : Adversaire Intelligent (cherche le chemin optimal)");
                System.out.println("Observez leurs différents comportements de déplacement !");
                ParametresJeu.setNbAdversaires(6);  // 2 de chaque type
                setTypeAdversaireTest(0); // 0 pour tous types
            }
            default -> {
                System.out.println("Choix invalide. Test avec tous les types d'adversaires par défaut.");
                ParametresJeu.setNbAdversaires(6);
                setTypeAdversaireTest(0);
            }
        }
        
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    private static void lancerTestPetitPlateau() {
        resetParametres();
        ParametresJeu.setNbLignes(5);
        ParametresJeu.setNbColonnes(5);
        ParametresJeu.setNbAdversaires(2);
        ParametresJeu.setNbBidons(3);
        Jeu jeu = new Jeu();
        jeu.joue();
    }
}
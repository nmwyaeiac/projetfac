import java.util.Scanner;

/**
 * Gestionnaire de tests pour le jeu.
 * Permet de lancer différents scénarios de test pour évaluer des aspects spécifiques du jeu.
 */
public class TestManager {
    /** Variable indiquant le type d'adversaire à créer (0 = tous types, 1-3 = type spécifique) */
    private static int typeAdversaireTest = 0;
    
    /**
     * Point d'entrée du gestionnaire de tests.
     * Affiche un menu de tests et exécute le test choisi par l'utilisateur.
     * 
     * @param args Arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean quitter = false;
        
        // Boucle principale du menu de tests
        while (!quitter) {
            // Affiche le menu de tests
            System.out.println("\n======= DÉMONSTRATEUR DU JEU DE PLATEAU =======");
            System.out.println("1. Jeu standard");
            System.out.println("2. Test: Joueur seul (déplacement)");
            System.out.println("3. Test: Joueur et bidons (collecte d'énergie)");
            System.out.println("4. Test: Combat avec adversaires");
            System.out.println("5. Test: Types d'adversaires");
            System.out.println("6. Test: Petit plateau");
            System.out.println("7. Configurer les paramètres du jeu");
            System.out.println("0. Quitter");
            
            // Demande le choix de l'utilisateur
            System.out.print("Votre choix: ");
            int choix = lireEntier(scanner);
            
            // Exécute le test choisi
            switch (choix) {
                case 0 -> quitter = true; // Quitter
                case 1 -> lancerJeuStandard(); // Jeu normal
                case 2 -> lancerTestJoueurSeul(); // Test du joueur seul
                case 3 -> lancerTestBidons(); // Test des bidons
                case 4 -> lancerTestCombat(); // Test des combats
                case 5 -> lancerTestTypesAdversaires(); // Test des types d'adversaires
                case 6 -> lancerTestPetitPlateau(); // Test avec un petit plateau
                case 7 -> {
                    // Configuration des paramètres puis proposition de lancer le jeu
                    ParametresJeu.configurerJeu();
                    
                    // Demande à l'utilisateur s'il souhaite lancer le jeu avec ces paramètres
                    System.out.println("\nVoulez-vous lancer une partie avec ces paramètres ? (o/n) : ");
                    String reponse = scanner.nextLine().trim().toLowerCase();
                    
                    if (reponse.equals("o")) {
                        Jeu jeu = new Jeu();
                        jeu.joue();
                    }
                }
                default -> System.out.println("Choix invalide");
            }
        }
    }
    
    /**
     * Lit un entier depuis la console, avec gestion des erreurs.
     * Redemande en cas d'entrée non numérique.
     * 
     * @param scanner Scanner pour lire l'entrée utilisateur
     * @return Entier saisi par l'utilisateur
     */
    private static int lireEntier(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // En cas d'erreur, demande une nouvelle saisie
                System.out.print("Entrée invalide, entrez un nombre: ");
            }
        }
    }
    
    /**
     * Définit le type d'adversaire à utiliser pour les tests.
     * 
     * @param type Type d'adversaire (0 = tous types, 1 = velléitaire, 2 = déterminé, 3 = intelligent)
     */
    public static void setTypeAdversaireTest(int type) {
        typeAdversaireTest = type;
    }
    
    /**
     * Récupère le type d'adversaire configuré pour les tests.
     * 
     * @return Type d'adversaire actuel
     */
    public static int getTypeAdversaireTest() {
        return typeAdversaireTest;
    }
    
    /**
     * Réinitialise tous les paramètres à leurs valeurs par défaut.
     */
    private static void resetParametres() {
        // Réinitialise tous les paramètres à leurs valeurs par défaut
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
    
    /**
     * Lance une partie de jeu standard avec les paramètres par défaut.
     */
    private static void lancerJeuStandard() {
        resetParametres(); // Réinitialise les paramètres
        setTypeAdversaireTest(0); // Utilise tous les types d'adversaires
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    /**
     * Lance un test avec uniquement le joueur, sans adversaires ni bidons.
     * Permet de tester les déplacements de base.
     */
    private static void lancerTestJoueurSeul() {
        resetParametres();
        // Modifier les paramètres pour ce test
        ParametresJeu.setNbAdversaires(0); // Aucun adversaire
        ParametresJeu.setNbBidons(0); // Aucun bidon
        
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    /**
     * Lance un test avec le joueur et des bidons, sans adversaires.
     * Permet de tester la collecte d'énergie.
     */
    private static void lancerTestBidons() {
        resetParametres();
        ParametresJeu.setNbAdversaires(0); // Aucun adversaire
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    /**
     * Lance un test focalisé sur les combats.
     * Utilise un plateau réduit avec peu d'adversaires et une inertie augmentée pour le joueur.
     */
    private static void lancerTestCombat() {
        resetParametres();
        // Modifier les paramètres pour ce test
        ParametresJeu.setNbLignes(7); // Petit plateau
        ParametresJeu.setNbColonnes(7);
        ParametresJeu.setNbAdversaires(3); // Peu d'adversaires
        ParametresJeu.setNbBidons(5);
        ParametresJeu.setInertieJoueur(7); // Joueur plus fort
        
        // Affiche les instructions du test
        System.out.println("=== TEST DE COMBAT ===");
        System.out.println("Ce test utilise un plateau réduit avec 3 adversaires.");
        System.out.println("Le joueur a une inertie augmentée (7) pour tester les victoires en combat.");
        System.out.println("Objectif : Trouvez et combattez les adversaires !");
        
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    /**
     * Lance un test ciblant les différents types d'adversaires.
     * Permet de tester et comparer les comportements des trois types d'adversaires.
     */
    private static void lancerTestTypesAdversaires() {
        resetParametres();
        Scanner scanner = new Scanner(System.in);
        
        // Affiche le menu de choix du type d'adversaire
        System.out.println("\n=== TEST DES TYPES D'ADVERSAIRES ===");
        System.out.println("Choisissez le type d'adversaire à tester :");
        System.out.println("1. Adversaire Velléitaire (♟) - se déplace avec une part d'aléatoire");
        System.out.println("2. Adversaire Déterminé (♞) - va directement vers ou s'éloigne du joueur");
        System.out.println("3. Adversaire Intelligent (♝) - cherche le chemin optimal");
        System.out.println("4. Tous les types d'adversaires ensemble");
        
        // Demande le choix de l'utilisateur
        System.out.print("Votre choix (1-4) : ");
        int choixType = lireEntier(scanner);
        
        // Configuration du jeu selon le choix
        ParametresJeu.setNbLignes(9);
        ParametresJeu.setNbColonnes(9);
        ParametresJeu.setNbBidons(10);
        
        switch (choixType) {
            case 1 -> {
                // Test des adversaires velléitaires
                System.out.println("\n=== TEST ADVERSAIRE VELLÉITAIRE ===");
                System.out.println("Ce test présente uniquement des adversaires velléitaires (♟)");
                System.out.println("Ces adversaires se déplacent avec une part d'aléatoire.");
                ParametresJeu.setNbAdversaires(3);
                setTypeAdversaireTest(1); // 1 pour velléitaire
            }
            case 2 -> {
                // Test des adversaires déterminés
                System.out.println("\n=== TEST ADVERSAIRE DÉTERMINÉ ===");
                System.out.println("Ce test présente uniquement des adversaires déterminés (♞)");
                System.out.println("Ces adversaires vont directement vers ou s'éloignent du joueur.");
                ParametresJeu.setNbAdversaires(3);
                setTypeAdversaireTest(2); // 2 pour déterminé
            }
            case 3 -> {
                // Test des adversaires intelligents
                System.out.println("\n=== TEST ADVERSAIRE INTELLIGENT ===");
                System.out.println("Ce test présente uniquement des adversaires intelligents (♝)");
                System.out.println("Ces adversaires cherchent le chemin optimal pour atteindre le joueur.");
                ParametresJeu.setNbAdversaires(3);
                setTypeAdversaireTest(3); // 3 pour intelligent
            }
            case 4 -> {
                // Test de tous les types d'adversaires ensemble
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
                // Choix invalide, utilise tous les types par défaut
                System.out.println("Choix invalide. Test avec tous les types d'adversaires par défaut.");
                ParametresJeu.setNbAdversaires(6);
                setTypeAdversaireTest(0);
            }
        }
        
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    /**
     * Lance un test avec un plateau de taille réduite.
     * Permet de tester le jeu dans un espace plus confiné.
     */
    private static void lancerTestPetitPlateau() {
        resetParametres();
        // Configure un petit plateau avec peu d'éléments
        ParametresJeu.setNbLignes(5);
        ParametresJeu.setNbColonnes(5);
        ParametresJeu.setNbAdversaires(2);
        ParametresJeu.setNbBidons(3);
        Jeu jeu = new Jeu();
        jeu.joue();
    }
}
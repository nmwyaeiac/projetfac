import java.util.Scanner;

public class TestManager {
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
    
    // Méthodes pour lancer les différents tests
    private static void lancerJeuStandard() {
        resetParametres();
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
        
    }   
    private static void lancerTestTypesAdversaires() {
        resetParametres();
        // Modifier les paramètres pour ce test
        ParametresJeu.NB_ADVERSAIRES = 5;
        ParametresJeu.NB_BIDONS = 0;
        
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
    }
}

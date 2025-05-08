import java.util.Scanner;

/**
 * Classe principale d'entrée du programme.
 * Propose un menu pour lancer le jeu, accéder aux tests ou configurer les paramètres.
 */
public class Main {
    /**
     * Point d'entrée principal du programme.
     * Affiche un menu et dirige vers les différentes fonctionnalités selon le choix de l'utilisateur.
     * 
     * @param args Arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Affiche le menu principal
        System.out.println("Bienvenue dans le jeu de plateau!");
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("1. Lancer une partie de jeu");
        System.out.println("2. Accéder aux tests");
        System.out.println("3. Configurer les paramètres du jeu");
        
        // Demande le choix de l'utilisateur
        System.out.print("Votre choix (1-3) : ");
        int choix = lireEntier(scanner);
        
        // Traite le choix de l'utilisateur
        switch (choix) {
            case 1 -> lancerJeu(); // Lance une partie normale
            case 2 -> lancerTests(); // Accède aux tests
            case 3 -> {
                // Configure les paramètres puis propose de lancer le jeu
                ParametresJeu.configurerJeu();
                
                // Demande à l'utilisateur s'il souhaite lancer le jeu maintenant
                System.out.println("\nVoulez-vous lancer le jeu avec ces paramètres ? (o/n) : ");
                String reponse = scanner.nextLine().trim().toLowerCase();
                
                if (reponse.equals("o")) {
                    lancerJeu();
                }
            }
            default -> {
                // En cas de choix invalide, lance le jeu par défaut
                System.out.println("Choix invalide. Lancement du jeu par défaut.");
                lancerJeu();
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
                System.out.print("Entrée invalide, entrez un nombre : ");
            }
        }
    }
    
    /**
     * Lance une partie de jeu normale.
     * Affiche les instructions de base avant de commencer.
     */
    private static void lancerJeu() {
        // Affiche les instructions et légende des symboles
        System.out.println("\nVous êtes représenté par le symbole ♜.");
        System.out.println("Les adversaires sont représentés par les symboles ♟, ♞, ou ♝.");
        System.out.println("Les bidons d'énergie sont représentés par le symbole ☼.");
        System.out.println("Les bordures du plateau sont représentées par le symbole ■.");
        System.out.println("Bonne chance!\n");
        
        // Création et démarrage d'une partie
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    /**
     * Lance le gestionnaire de tests.
     * Permet d'accéder aux différents modes de test du jeu.
     */
    private static void lancerTests() {
        // Lancement du gestionnaire de tests
        TestManager.main(new String[0]);
    }
}
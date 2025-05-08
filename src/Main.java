import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bienvenue dans le jeu de plateau!");
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("1. Lancer une partie de jeu");
        System.out.println("2. Accéder aux tests");
        System.out.println("3. Configurer les paramètres du jeu");
        
        System.out.print("Votre choix (1-3) : ");
        int choix = lireEntier(scanner);
        
        switch (choix) {
            case 1 -> lancerJeu();
            case 2 -> lancerTests();
            case 3 -> {
                // Appeler la configuration des paramètres
                ParametresJeu.configurerJeu();
                
                // Demander à l'utilisateur s'il souhaite lancer le jeu maintenant
                System.out.println("\nVoulez-vous lancer le jeu avec ces paramètres ? (o/n) : ");
                String reponse = scanner.nextLine().trim().toLowerCase();
                
                if (reponse.equals("o")) {
                    lancerJeu();
                }
            }
            default -> {
                System.out.println("Choix invalide. Lancement du jeu par défaut.");
                lancerJeu();
            }
        }
    }
    
    private static int lireEntier(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrée invalide, entrez un nombre : ");
            }
        }
    }
    
    private static void lancerJeu() {
        System.out.println("\nVous êtes représenté par le symbole ♜.");
        System.out.println("Les adversaires sont représentés par les symboles ♟, ♞, ou ♝.");
        System.out.println("Les bidons d'énergie sont représentés par le symbole ☼.");
        System.out.println("Les bordures du plateau sont représentées par le symbole ■.");
        System.out.println("Bonne chance!\n");
        
        // Création et démarrage d'une partie
        Jeu jeu = new Jeu();
        jeu.joue();
    }
    
    private static void lancerTests() {
        // Lancement du gestionnaire de tests
        TestManager.main(new String[0]);
    }
}
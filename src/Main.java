public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenue dans le jeu de plateau!");
        System.out.println("Vous êtes représenté par le symbole ♜.");
        System.out.println("Les adversaires sont représentés par les symboles ♟, ♞, ou ♝.");
        System.out.println("Lejs bidons d'énergie sont représentés par le symbole ☼.");
        System.out.println("Les bordures du plateau sont représentées par le symbole █.");
        System.out.println("Bonne chance!\n");
        
        // Création et démarrage d'une partie
        Jeu jeu = new Jeu();
        jeu.joue();
    }
}
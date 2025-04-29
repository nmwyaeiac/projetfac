/**
 * Classe contenant les paramètres globaux du jeu
 */
public class ParametresJeu {
    // Taille du plateau (nombre impair pour avoir un centre)
    public static final int NB_LIGNES = 11;
    public static final int NB_COLONNES = 11;
    
    // Nombre d'adversaires et de bidons
    public static final int NB_ADVERSAIRES = 10;
    public static final int NB_BIDONS = 15;
    
    // Énergie maximale pour les personnages et les bidons
    public static final int MAX_ENERGIE = 10;
    
    // Inertie du joueur
    public static final int INERTIE_JOUEUR = 5;
    
    // Valeurs minimale et maximale pour l'inertie des adversaires
    public static final int MIN_INERTIE_ADVERSAIRE = 2;
    public static final int MAX_INERTIE_ADVERSAIRE = 9;
}

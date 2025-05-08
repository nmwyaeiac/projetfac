import java.util.Random;

/**
 * Représente un adversaire velléitaire qui se déplace avec une part d'aléatoire.
 * Il suit généralement la direction vers ou loin du joueur, mais peut parfois
 * choisir une direction complètement aléatoire.
 */
public class AdversaireVelleitaire extends Adversaire {
    /** Générateur de nombres aléatoires partagé par tous les adversaires velléitaires */
    private static final Random random = new Random();
    
    /**
     * Constructeur de l'adversaire velléitaire
     * 
     * @param inertie Valeur d'inertie de l'adversaire
     * @param joueur Référence au joueur
     */
    public AdversaireVelleitaire(int inertie, Joueur joueur) {
        super(inertie, joueur); // Appel au constructeur de la classe parente
    }
    
    /**
     * Choisit la direction de déplacement avec une part d'aléatoire
     * 
     * @return Direction vers le joueur, loin du joueur, ou aléatoire selon le cas
     */
    @Override
    protected Direction choisirDirection() {
        // Direction normale en fonction de la force relative (vers ou loin du joueur)
        Direction directionBase = plusFortQueJoueur() ? directionVersJoueur() : directionFuiteJoueur();
        
        // 30% de chance de choisir une direction aléatoire
        if (random.nextDouble() < 0.3) {
            // Tableau des 8 directions possibles
            Direction[] directions = {
                Direction.HAUT, Direction.BAS, Direction.GAUCHE, Direction.DROITE,
                Direction.HAUT_GAUCHE, Direction.HAUT_DROITE, Direction.BAS_GAUCHE, Direction.BAS_DROITE
            };
            // Sélection aléatoire d'une direction
            return directions[random.nextInt(directions.length)];
        }
        
        // 70% du temps, utilise la direction normale
        return directionBase;
    }
    
    /**
     * Représentation textuelle de l'adversaire velléitaire
     * 
     * @return Symbole représentant l'adversaire velléitaire
     */
    @Override
    public String toString() {
        return "♟"; // Symbole du pion des échecs
    }
}
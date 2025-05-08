/**
 * Représente un adversaire déterminé qui va directement vers le joueur
 * s'il est plus fort que lui, ou le fuit directement s'il est plus faible.
 * Le comportement est prévisible et sans adaptation au contexte.
 */
public class AdversaireDetermine extends Adversaire {
    
    /**
     * Constructeur de l'adversaire déterminé
     * 
     * @param inertie Valeur d'inertie de l'adversaire
     * @param joueur Référence au joueur à poursuivre ou fuir
     */
    public AdversaireDetermine(int inertie, Joueur joueur) {
        super(inertie, joueur); // Appel au constructeur de la classe parente
    }
    
    /**
     * Choisit la direction de déplacement en fonction de la force relative par rapport au joueur
     * 
     * @return Direction vers le joueur si l'adversaire est plus fort, direction opposée sinon
     */
    @Override
    protected Direction choisirDirection() {
        // Si plus fort que le joueur, va vers lui, sinon le fuit
        return plusFortQueJoueur() ? directionVersJoueur() : directionFuiteJoueur();
    }
    
    /**
     * Représentation textuelle de l'adversaire déterminé
     * 
     * @return Symbole représentant l'adversaire déterminé
     */
    @Override
    public String toString() {
        return "♞"; // Symbole du cavalier des échecs
    }
}
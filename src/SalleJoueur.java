/**
 * Représente la salle où le joueur commence la partie.
 * Cette salle spéciale est placée au centre du plateau au début du jeu.
 */
public class SalleJoueur extends SalleDedans {
  
  /**
   * Constructeur d'une salle de joueur.
   * 
   * @param lig Ligne de la salle sur le plateau (généralement au centre)
   * @param col Colonne de la salle sur le plateau (généralement au centre)
   * @param p Plateau contenant la salle
   */
  public SalleJoueur(int lig, int col, Plateau p) {
    super(lig, col, p); // Appel au constructeur de la classe parente
  }

  /**
   * Représentation textuelle de la salle du joueur.
   * 
   * @return Symbole représentant le joueur (♜)
   */
  @Override
  public String toString() {
      return "♜"; // Symbole du joueur (tour des échecs)
  }
  
  /**
   * Gère l'entrée d'un personnage dans la salle.
   * Utilise le comportement standard d'une SalleDedans.
   * 
   * @param p Personnage qui tente d'entrer
   */
  @Override
  public void entre(Personnage p) {
    super.entre(p); // Comportement standard
  }
}